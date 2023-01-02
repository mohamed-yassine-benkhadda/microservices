package com.microservices.locataire;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
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
public class GestionLocataire {
    @Autowired
    LocataireRespository locataireRespository;
    @Autowired
    private EurekaClient discoveryClient;
    @GetMapping("/ajouter")
    @CircuitBreaker(name = "locataire", fallbackMethod = "ajouterFallBack")
    public Locataire addLocataire(@RequestParam("nom") String nom, @RequestParam("adresse") String adresse){
        Locataire l = new Locataire(nom,adresse);
        locataireRespository.save(l);
        return l;
    }

    @GetMapping("/supprimer")
    @CircuitBreaker(name = "locataire", fallbackMethod = "supprimerFallBack")
    public void deleteLocataire(@RequestParam("id") int id){
        locataireRespository.deleteById(id);
    }

    @GetMapping("/")
    @CircuitBreaker(name = "locataire", fallbackMethod = "allFallBack")
    public List<Locataire> listLocataire(){
        List<Locataire> listeMission = locataireRespository.findAll();
        return listeMission;
    }

    @GetMapping("/page")
    public Optional<Locataire> getLocataire(@RequestParam("id") int id){
        Optional<Locataire> l = locataireRespository.findById(id);
        return l;
    }

    public Locataire ajouterFallBack(Exception e){
        System.out.println("redirected due to an issue");
        return null;
    }

    public void supprimerFallBack(Exception e){
        System.out.println("redirected due to an issue");
    }

    public List<Locataire> AllFallBack(Exception e){
        System.out.println("redirected due to an issue");
        return null;
    }
}
