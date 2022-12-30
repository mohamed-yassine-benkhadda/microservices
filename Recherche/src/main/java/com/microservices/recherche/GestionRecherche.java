package com.microservices.recherche;

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
    public void getRecherche(@RequestParam("text") String text){
        Recherche r = new Recherche();
        r.setChauffeur(rechercheChauffeur(text));
    }

    public List<Chauffeur> rechercheChauffeur(String text){
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        Chauffeur mission = new Chauffeur();
        List<InstanceInfo> apps = discoveryClient.getApplication("CHAUFFEUR").getInstances();
        String url_chauffeur = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/"+apps.get(0).getAppName().toLowerCase();
        System.out.println(url_chauffeur);
//        ResponseEntity<Chauffeur> result_chauffeur = restTemplate.exchange(url_chauffeur, HttpMethod.GET, entity, Chauffeur.class);
//        System.out.println(result_chauffeur);
//        List<Chauffeur> liste_chauffeur = new ArrayList<Chauffeur>();
//        for(Chauffeur c:result_chauffeur){
//            if (c.getAdresse() == text){
//                liste_chauffeur.add(c);
//            }
//            if (c.getDateNaissance() == text){
//                liste_chauffeur.add(c);
//            }
//            if (c.getCIN() == text){
//                liste_chauffeur.add(c);
//            }
//            if (c.getNom() == text){
//                liste_chauffeur.add(c);
//            }
//            if (c.getPrenom() == text){
//                liste_chauffeur.add(c);
//            }
//        }
//        System.out.println(liste_chauffeur);
        return null;
    }
}
