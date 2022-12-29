package com.microservices.locataire;

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
@RequestMapping("/locataire")
public class GestionLocataire {
    @Autowired
    LocataireRespository locataireRespository;
    @Autowired
    private EurekaClient discoveryClient;
    @GetMapping("ajouter")
    public Locataire addLocataire(@RequestParam("nom") String nom, @RequestParam("adresse") String adresse){
        Locataire l = new Locataire(nom,adresse);
        locataireRespository.save(l);
        return l;
    }

    @GetMapping("supprimer")
    public void deleteLocataire(@RequestParam("id") int id){
        locataireRespository.deleteById(id);
    }

    @GetMapping("")
    public List<Locataire> listLocataire(){
        List<Locataire> listeMission = locataireRespository.findAll();
        return listeMission;
    }

    @GetMapping("page")
    public Optional<Locataire> getLocataire(@RequestParam("id") int id){
        Optional<Locataire> l = locataireRespository.findById(id);
        return l;
    }
}
