package com.projet.microservices.chauffeur;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/chauffeur")
public class GestionChauffeur {

    @Autowired
    ChauffeurRespository chauffeurRespository;

    //Create Chauffeur
    @GetMapping("/ajouter")
    public Chauffeur addChaufeur(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("cin") String cin, @RequestParam("dateNaissance") String dateNaissance, @RequestParam("permis") String permis, @RequestParam("adresse") String adresse){
        Chauffeur c = new Chauffeur(cin, nom, prenom, dateNaissance, adresse, permis);
        System.out.println(c.getNom()+" "+c.getPrenom());
        chauffeurRespository.save(c);
        return c;
    }

    @GetMapping("/supprimer")
    public void deleteChaufeur(@RequestParam("id") String id){
        int idChauffeur = Integer.parseInt(id);
        try{
            chauffeurRespository.deleteById(idChauffeur);
        }
        catch(Exception e){
            System.out.println("cannot delete");
        }
    }
    @GetMapping("")
    public List<Chauffeur> allChaufeur(){
        List<Chauffeur> listeChauffeur;
        listeChauffeur = chauffeurRespository.findAll();
        for (Chauffeur c:listeChauffeur) {
            System.out.println(c);
        }
        return listeChauffeur;
    }

    @GetMapping("page")
    public Optional<Chauffeur> getAdmin(@RequestParam("id") int id){
        Optional<Chauffeur> c = chauffeurRespository.findById(id);
        return c;
    }
}
