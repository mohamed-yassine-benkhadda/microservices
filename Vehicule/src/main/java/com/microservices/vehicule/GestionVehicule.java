package com.microservices.vehicule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/vehicule")
public class GestionVehicule {
    @Autowired
    VehiculeRespository vehiculeRespository;
    @GetMapping("ajouter")
    public Vehicule addVehicule(@RequestParam("immatricule") String immatricule, @RequestParam("marque") String marque, @RequestParam("model") String model, @RequestParam("categorie") String categorie, @RequestParam("permis") String permis){
        Vehicule v= new Vehicule(immatricule,marque,model,categorie,permis);
        vehiculeRespository.save(v);
        return v;
    }

    @GetMapping("supprimer")
    public void deleteVehicule(@RequestParam("id") int id){
        vehiculeRespository.deleteById(id);
    }

    @GetMapping("")
    public List<Vehicule> allVehicule(){
        List<Vehicule> listeVehicule;
        listeVehicule = vehiculeRespository.findAll();
        for (Vehicule v:listeVehicule) {
            System.out.println(v);
        }
        return listeVehicule;
    }
}