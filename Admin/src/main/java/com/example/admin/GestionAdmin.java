package com.example.admin;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CircuitBreaker(name = "admin", fallbackMethod = "adminMethod")
public class GestionAdmin {
    @Autowired
    AdminRespository adminRespository;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private EurekaClient discoveryClient;

    @CircuitBreaker(name = "admin")
    @GetMapping("/ajouter")
    public Admin addAdmin(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("cin") String cin, @RequestParam("dateNaissance") String dateNaissance, @RequestParam("adresse") String adresse){
        Admin a = new Admin(cin,nom,prenom,dateNaissance,adresse);
        adminRespository.save(a);
        return a;
    }

    @CircuitBreaker(name = "admin", fallbackMethod = "adminDeleteFallBack")
    @GetMapping("/supprimer")
    public void deleteAdmin(@RequestParam("id") int id){
        adminRespository.deleteById(id);
    }

    @CircuitBreaker(name = "admin", fallbackMethod = "adminFallBack")
    @GetMapping("/")
    public List<Admin> listAdmin(){
        List<Admin> listeAdmin;
        listeAdmin = adminRespository.findAll();
        for (Admin c:listeAdmin) {
            System.out.println(c);
        }
        return listeAdmin;
    }

    public ResponseEntity<String> adminTestFallBack(Exception e){
        System.out.println("redirected due to an issue");
        return null;
    }

    public Optional<Admin> adminPageFallBack(Exception e){
        System.out.println("redirected due to an issue");
        return null;
    }

    public List<Admin> adminFallBack(Exception e){
        System.out.println("redirected due to an issue");
        return null;
    }

    public void adminDeleteFallBack(Exception e){
        System.out.println(e);
        System.out.println("redirected due to an issue");
    }

    public Admin adminAddFallBack(Exception e){
        System.out.println("redirected due to an issue");
        return null;
    }
}
