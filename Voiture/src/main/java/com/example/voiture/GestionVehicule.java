package com.microservices.vehicule;

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

@ 
public class GestionVehicule {
    @Autowired
    VehiculeRespository vehiculeRespository;
    @Autowired
    private EurekaClient discoveryClient;

    @Autowired
    private RestTemplate restTemplate;

    @GetMapping("/ajouter")
    public Vehicule addVehicule(@RequestParam("id_locataire") int id_locataire, @RequestParam("immatricule") String immatricule, @RequestParam("marque") String marque, @RequestParam("model") String model, @RequestParam("categorie") String categorie, @RequestParam("permis") String permis){
        Vehicule v= new Vehicule(id_locataire,immatricule,marque,model,categorie,permis);
        vehiculeRespository.save(v);
        return v;
    }

    @GetMapping("/supprimer")
    public void deleteVehicule(@RequestParam("id") int id){
        vehiculeRespository.deleteById(id);
    }

    @GetMapping("/")
    public List<ListeVehicule> allVehicule(){
        List<Vehicule> listeVehicule;
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        listeVehicule = vehiculeRespository.findAll();
        List<ListeVehicule> liste= new ArrayList<ListeVehicule>();
        ListeVehicule vehicule = new ListeVehicule();
        for (Vehicule v:listeVehicule) {
            List<InstanceInfo> apps = discoveryClient.getApplication("LOCATAIRE").getInstances();
            String url_locataire = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/page?id="+v.getId_locataire();
            ResponseEntity<Locataire> result_locataire = restTemplate.exchange(url_locataire, HttpMethod.GET, entity, Locataire.class);
            System.out.println(result_locataire.getStatusCode());
            vehicule.setId_vehicule(v.getId_vehicule());
            vehicule.setLocataire(result_locataire.getBody().getNom());
            vehicule.setImmatricule(v.getImmatricule());
            vehicule.setMarque(v.getMarque());
            vehicule.setModel(v.getModel());
            vehicule.setPermis(v.getPermis());
            vehicule.setCategorie(v.getCategorie());
            liste.add(vehicule);
        }
        return liste;
    }

    @GetMapping("/page")
    public ListeVehicule getVehicule(@RequestParam("id") int id){
        System.out.println(id);
        restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        ListeVehicule vehicule = new ListeVehicule();
        vehiculeRespository.findById(id).ifPresent(v -> {
            List<InstanceInfo> apps = discoveryClient.getApplication("LOCATAIRE").getInstances();
            String url_locataire = "http://"+apps.get(0).getHostName()+":"+apps.get(0).getPort()+"/page?id="+v.getId_locataire();
            ResponseEntity<Locataire> result_locataire = restTemplate.exchange(url_locataire, HttpMethod.GET, entity, Locataire.class);
            vehicule.setId_vehicule(v.getId_vehicule());
            vehicule.setLocataire(result_locataire.getBody().getNom());
            vehicule.setImmatricule(v.getImmatricule());
            vehicule.setMarque(v.getMarque());
            vehicule.setModel(v.getModel());
            vehicule.setPermis(v.getPermis());
            vehicule.setCategorie(v.getCategorie());
        });
        return vehicule;
    }
}
