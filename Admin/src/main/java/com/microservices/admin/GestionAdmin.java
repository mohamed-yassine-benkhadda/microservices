package com.microservices.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/admin")
public class GestionAdmin {

    @Autowired
    AdminRespository adminRespository;
    @GetMapping("ajouter")
    public Admin addAdmin(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("cin") String cin, @RequestParam("dateNaissance") String dateNaissance, @RequestParam("adresse") String adresse){
        Admin a = new Admin(cin,nom,prenom,dateNaissance,adresse);
        adminRespository.save(a);
        return a;
    }
}
