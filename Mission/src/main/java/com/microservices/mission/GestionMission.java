package com.microservices.mission;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@RestController
public class GestionMission {

    @Autowired
    MissionRespository missionRespository;
    @Autowired
    private EurekaClient discoveryClient;
    @GetMapping("/ajouter")
    @CircuitBreaker(name = "mission", fallbackMethod = "ajouterFallBack")
    public Mission addMission(@RequestParam("idChauffeur") int idChauffeur, @RequestParam("idVehicule") int idVehicule, @RequestParam("adresse") String adresse, @RequestParam("dateDebut") String dateDebut, @RequestParam("dateFin") String dateFin, @RequestParam("titre") String titre, @RequestParam("description") String description){
        Mission m = new Mission(idChauffeur,idVehicule, adresse, dateDebut, dateFin, titre, description);
        missionRespository.save(m);
        return m;
    }

    @GetMapping("/supprimer")
    @CircuitBreaker(name = "mission", fallbackMethod = "supprimerFallBack")
    public void deleteMission(@RequestParam("id") int id){
        missionRespository.deleteById(id);
    }

    @GetMapping("/")
    @CircuitBreaker(name = "mission", fallbackMethod = "allFallBack")
    public List<ListeMission> listMission(){
        System.out.println("===================================================");
        System.out.println("===================================================");
        System.out.println("===================================================");
        System.out.println("===================================================");
        System.out.println("===================================================");
        System.out.println("===================================================");
        System.out.println("===================================================");
        System.out.println("===================================================");
        System.out.println("===================================================");
        List<Mission> listeMission;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        listeMission = missionRespository.findAll();
        List<ListeMission> liste= new ArrayList<ListeMission>();
        ListeMission mission = new ListeMission();
        for (Mission m:listeMission) {
            System.out.println(m);
            List<InstanceInfo> apps = discoveryClient.getApplication("VEHICULE").getInstances();
            String url_vehicule = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/page?id="+m.getId_vehicule();
            System.out.println(url_vehicule);
            apps = discoveryClient.getApplication("CHAUFFEUR").getInstances();
            String url_chauffeur = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/page?id="+m.getId_chauffeur();
            ResponseEntity<Chauffeur> result_chauffeur = restTemplate.exchange(url_chauffeur, HttpMethod.GET, entity, Chauffeur.class);
            ResponseEntity<ListeVehicule> result_vehicule = restTemplate.exchange(url_vehicule, HttpMethod.GET, entity, ListeVehicule.class);

            mission.setId_mission(m.getId_mission());
            mission.setAdresse(m.getAdresse());
            mission.setLocataire(result_vehicule.getBody().getLocataire());
            mission.setCategorie(result_vehicule.getBody().getCategorie());
            mission.setCin(result_chauffeur.getBody().getCIN());
            mission.setImmatricule(result_vehicule.getBody().getImmatricule());
            mission.setMarque(result_vehicule.getBody().getMarque());
            mission.setModel(result_vehicule.getBody().getModel());
            mission.setPermis(result_vehicule.getBody().getPermis());
            mission.setTitre(m.getTitre());
            mission.setDateNaissance(result_chauffeur.getBody().getDateNaissance());
            mission.setNomChauffeur(result_chauffeur.getBody().getNom() + " "+ result_chauffeur.getBody().getPrenom());
            mission.setDescription(m.getDescription());
            liste.add(mission);
        }
        return liste;
    }

    @GetMapping("/page")
    @CircuitBreaker(name = "mission", fallbackMethod = "pageFallBack")
    public ListeMission getMission(@RequestParam("id") int id){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ListeMission mission = new ListeMission();
        missionRespository.findById(id).ifPresent(m -> {
            List<InstanceInfo> apps = discoveryClient.getApplication("VEHICULE").getInstances();
            System.out.println(m.getClass());
            String url_vehicule = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/page?id="+m.getId_vehicule();
            apps = discoveryClient.getApplication("CHAUFFEUR").getInstances();
            String url_chauffeur = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/page?id="+m.getId_chauffeur();
            ResponseEntity<Chauffeur> result_chauffeur = restTemplate.exchange(url_chauffeur, HttpMethod.GET, entity, Chauffeur.class);
            ResponseEntity<ListeVehicule> result_vehicule = restTemplate.exchange(url_vehicule, HttpMethod.GET, entity, ListeVehicule.class);
            mission.setId_mission(m.getId_mission());
            mission.setLocataire(result_vehicule.getBody().getLocataire());
            mission.setAdresse(m.getAdresse());
            mission.setCategorie(result_vehicule.getBody().getCategorie());
            mission.setCin(result_chauffeur.getBody().getCIN());
            mission.setImmatricule(result_vehicule.getBody().getImmatricule());
            mission.setMarque(result_vehicule.getBody().getMarque());
            mission.setModel(result_vehicule.getBody().getModel());
            mission.setPermis(result_vehicule.getBody().getPermis());
            mission.setTitre(m.getTitre());
            mission.setDateNaissance(result_chauffeur.getBody().getDateNaissance());
            mission.setNomChauffeur(result_chauffeur.getBody().getNom() + " "+ result_chauffeur.getBody().getPrenom());
            mission.setDescription(m.getDescription());
        });
        return mission;
    }

    public Mission ajouterFallBack(Exception e){
        System.out.println("redirected due to an issue");
        return null;
    }

    public void supprimerFallBack(Exception e){
        System.out.println("redirected due to an issue");
    }

    public List<ListeMission> AllFallBack(Exception e){
        System.out.println("redirected due to an issue");
        return null;
    }

    public ListeMission pageFallBack(Exception e){
        System.out.println("redirected due to an issue");
        return null;
    }
}
