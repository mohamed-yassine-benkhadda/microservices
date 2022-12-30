package com.microservices.recherche_microservices;

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

@RestController
@RequestMapping("/recherche")
public class GestionRecherche {
//    @Autowired
//    RechercheRespository rechercheRespository;
    @Autowired
    private EurekaClient discoveryClient;
    @GetMapping("")
    public Recherche getRecherche(@RequestParam("text") String text){
        Recherche r = new Recherche();
        r.setChauffeur(rechercheChauffeur(text));
        r.setAdmin(rechercheAdmin(text));
        r.setMission(rechercheMission(text));
        r.setLoc(rechercheLocataire(text));
        return r;
    }

    public List<Chauffeur> rechercheChauffeur(String text){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        List<InstanceInfo> apps = discoveryClient.getApplication("CHAUFFEUR").getInstances();
        String url_chauffeur = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/"+apps.get(0).getAppName().toLowerCase();
        Chauffeur[] result_chauffeur = restTemplate.getForObject(url_chauffeur, Chauffeur[].class);
        List<Chauffeur> liste_chauffeur = new ArrayList<Chauffeur>();
        for(Chauffeur c:Arrays.stream(result_chauffeur).toList()){
            if (c.getAdresse().equals(text)){
                liste_chauffeur.add(c);
            }
            if (c.getDateNaissance().equals(text)){
                liste_chauffeur.add(c);
            }
            if (c.getCIN().equals(text)){
                liste_chauffeur.add(c);
            }
            if (c.getNom().equals(text)){
                liste_chauffeur.add(c);
            }
            if (c.getPrenom().equals(text)){
                liste_chauffeur.add(c);
            }
        }
        return liste_chauffeur;
    }

    public List<Admin> rechercheAdmin(String text){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        List<InstanceInfo> apps = discoveryClient.getApplication("ADMIN").getInstances();
        String url_admin = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/"+apps.get(0).getAppName().toLowerCase();
        Admin[] result_admin = restTemplate.getForObject(url_admin, Admin[].class);
        List<Admin> liste_admin = new ArrayList<Admin>();
        for(Admin a:Arrays.stream(result_admin).toList()){
            if (a.getAdresse().equals(text)){
                liste_admin.add(a);
            }
            if (a.getDate_de_naissance().equals(text)){
                liste_admin.add(a);
            }
            if (a.getCIN().equals(text)){
                liste_admin.add(a);
            }
            if (a.getNom().equals(text)){
                liste_admin.add(a);
            }
            if (a.getPrenom().equals(text)){
                liste_admin.add(a);
            }
        }
        return liste_admin;
    }

    public List<Mission> rechercheMission(String text){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        List<InstanceInfo> apps = discoveryClient.getApplication("MISSION").getInstances();
        String url_mission = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/"+apps.get(0).getAppName().toLowerCase();
        Mission[] result_mission = restTemplate.getForObject(url_mission, Mission[].class);
        List<Mission> liste_mission = new ArrayList<Mission>();
        for(Mission m:Arrays.stream(result_mission).toList()){
            if (m.getAdresse().equals(text)){
                liste_mission.add(m);
            }
            if (m.getDescription().equals(text)){
                liste_mission.add(m);
            }
            if (m.getTitre().equals(text)){
                liste_mission.add(m);
            }
        }
        return liste_mission;
    }

    public List<Locataire> rechercheLocataire(String text){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        List<InstanceInfo> apps = discoveryClient.getApplication("LOCATAIRE").getInstances();
        String url_locataire = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/"+apps.get(0).getAppName().toLowerCase();
        Locataire[] result_locataire = restTemplate.getForObject(url_locataire, Locataire[].class);
        List<Locataire> liste_locataire = new ArrayList<Locataire>();
        for(Locataire l:Arrays.stream(result_locataire).toList()){
            if (l.getAdresse().equals(text)){
                liste_locataire.add(l);
            }
            if (l.getNom().equals(text)){
                liste_locataire.add(l);
            }
        }
        return liste_locataire;
    }

    public List<Vehicule> rechercheVehicule(String text){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        List<InstanceInfo> apps = discoveryClient.getApplication("VEHICULE").getInstances();
        String url_vehicule = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/"+apps.get(0).getAppName().toLowerCase();
        Vehicule[] result_vehicule = restTemplate.getForObject(url_vehicule, Vehicule[].class);
        List<Vehicule> liste_vehicule = new ArrayList<Vehicule>();
        for(Vehicule v:Arrays.stream(result_vehicule).toList()){
            if (v.getCategorie().equals(text)){
                liste_vehicule.add(v);
            }
            if (v.getImmatricule().equals(text)){
                liste_vehicule.add(v);
            }
            if (v.getMarque().equals(text.toLowerCase())){
                liste_vehicule.add(v);
            }
            if (v.getModel().equals(text)){
                liste_vehicule.add(v);
            }
        }
        return liste_vehicule;
    }
}
