package com.microservices.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

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

    @GetMapping("supprimer")
    public void deleteAdmin(@RequestParam("id") int id){
        adminRespository.deleteById(id);
    }

    @GetMapping("")
    public List<Admin> listAdmin(){
        List<Admin> listeAdmin;
        listeAdmin = adminRespository.findAll();
        for (Admin c:listeAdmin) {
            System.out.println(c);
        }
        return listeAdmin;
    }

    @GetMapping("test")
    public void testVehicule(){
        RestTemplate restTemplate = new RestTemplate();
        int id = 3;
//        List<Vehicule> listeVehicule = restTemplate.getForObject("http://localhost:8086/vehicule", Vehicule.class);
//        for(Vehicule v:listeVehicule){
//            System.out.println(v);
//        }
        System.out.println( restTemplate.getForObject("http://vehicule/vehicule/ajouter?marque=renault&model=symbol&categorie=sedan&permis=B&immatricule=26B96546", Vehicule.class));
    }
}
