package com.microservices.mission;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/mission")
public class GestionMission {

    @Autowired
    MissionRespository missionRespository;
    @Autowired
    private EurekaClient discoveryClient;
    @GetMapping("ajouter")
    public Mission addMission(@RequestParam("idChauffeur") int idChauffeur, @RequestParam("idVehicule") int idVehicule, @RequestParam("adresse") String adresse, @RequestParam("dateDebut") String dateDebut, @RequestParam("dateFin") String dateFin, @RequestParam("titre") String titre, @RequestParam("description") String description){
        Mission m = new Mission(idChauffeur,idVehicule, adresse, dateDebut, dateFin, titre, description);
        missionRespository.save(m);
        return m;
    }

    @GetMapping("supprimer")
    public void deleteMission(@RequestParam("id") int id){
        missionRespository.deleteById(id);
    }

    @GetMapping("")
    public List<ListeMission> listMission(){
        List<Mission> listeMission;
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        listeMission = missionRespository.findAll();
        List<ListeMission> liste= new ArrayList<ListeMission>();
        ListeMission mission = new ListeMission();
        for (Mission m:listeMission) {
            List<InstanceInfo> apps = discoveryClient.getApplication("VEHICULE").getInstances();
            String url_vehicule = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/"+apps.get(0).getAppName().toLowerCase()+"/page?id="+m.getId_vehicule();
            apps = discoveryClient.getApplication("CHAUFFEUR").getInstances();
            String url_chauffeur = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/"+apps.get(0).getAppName().toLowerCase()+"/page?id="+m.getId_chauffeur();
            System.out.println(url_chauffeur + "\n" + url_vehicule);
            ResponseEntity<Chauffeur> result_chauffeur = restTemplate.exchange(url_chauffeur, HttpMethod.GET, entity, Chauffeur.class);
            ResponseEntity<Vehicule> result_vehicule = restTemplate.exchange(url_vehicule, HttpMethod.GET, entity, Vehicule.class);
            mission.setId_mission(m.getId_mission());
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
            liste.add(mission);
        }
        System.out.println(listeMission);
        return liste;
//        List<InstanceInfo> apps = discoveryClient.getApplication("VEHICULE").getInstances();
//        String url = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/"+apps.get(0).getAppName().toLowerCase()+"/page?id="+id;
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        return listeMission;
    }

    @GetMapping("page")
    public Optional<Mission> getAdmin(@RequestParam("id") int id){
        Optional<Mission> m = missionRespository.findById(id);
        return m;
    }

//    @GetMapping("In")
//    public ResponseEntity<String> testVehicule(){
//        RestTemplate restTemplate = new RestTemplate();
//        List<InstanceInfo> apps = discoveryClient.getApplication("VEHICULE").getInstances();
//        String url = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/"+apps.get(0).getAppName().toLowerCase();
//        HttpHeaders headers = new HttpHeaders();
//        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
//        HttpEntity<String> entity = new HttpEntity<String>(headers);
//        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
//        return result;
//    }
}
