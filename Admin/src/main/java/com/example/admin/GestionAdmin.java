package com.example.admin;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@RestController
@CircuitBreaker(name = "admin", fallbackMethod = "adminMethod")
public class GestionAdmin {

    @Autowired
    AdminRespository adminRespository;
    @Autowired
    private EurekaClient discoveryClient;
    @GetMapping("/ajouter")
    public Admin addAdmin(@RequestParam("nom") String nom, @RequestParam("prenom") String prenom, @RequestParam("cin") String cin, @RequestParam("dateNaissance") String dateNaissance, @RequestParam("adresse") String adresse){
        Admin a = new Admin(cin,nom,prenom,dateNaissance,adresse);
        adminRespository.save(a);
        return a;
    }

    public ResponseEntity<String> adminMethod(Exception e){
		return new ResponseEntity<String>("Admin service is down", HttpStatus.OK);
    }

    @GetMapping("/supprimer")
    public void deleteAdmin(@RequestParam("id") int id){
        adminRespository.deleteById(id);
    }

    @GetMapping("/")
    public List<Admin> listAdmin(){
        List<Admin> listeAdmin;
        listeAdmin = adminRespository.findAll();
        for (Admin c:listeAdmin) {
            System.out.println(c);
        }
        return listeAdmin;
    }

    @GetMapping("/page")
    public Optional<Admin> getAdmin(@RequestParam("id") int id){
        Optional<Admin> a = adminRespository.findById(id);
        return a;
    }

    @GetMapping("/test")
    public ResponseEntity<String> testVehicule(){
        RestTemplate restTemplate = new RestTemplate();
        List<InstanceInfo> apps = discoveryClient.getApplication("VEHICULE").getInstances();
        String url = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/"+apps.get(0).getAppName().toLowerCase();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity <String> entity = new HttpEntity<String>(headers);
        ResponseEntity<String> result = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);
        return result;
    }
}
