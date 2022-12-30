package com.microservices.recherche;

import java.util.ArrayList;
import java.util.List;

public class Recherche {
    private List<Locataire> loc;
    private List<Vehicule> vehicule;
    private List<Mission> mission;
    private List<Chauffeur> chauffeur;
    private List<Admin> admin;

    public List<Locataire> getLoc() {
        return loc;
    }

    public void setLoc(List<Locataire> loc) {
        this.loc = loc;
    }

    public List<Vehicule> getVehicule() {
        return vehicule;
    }

    public void setVehicule(List<Vehicule> vehicule) {
        this.vehicule = vehicule;
    }

    public List<Mission> getMission() {
        return mission;
    }

    public void setMission(List<Mission> mission) {
        this.mission = mission;
    }

    public List<Chauffeur> getChauffeur() {
        return chauffeur;
    }

    public void setChauffeur(List<Chauffeur> chauffeur) {
        this.chauffeur = chauffeur;
    }

    public List<Admin> getAdmin() {
        return admin;
    }

    public void setAdmin(List<Admin> admin) {
        this.admin = admin;
    }

    public Recherche() {
    }

    public Recherche(List<Locataire> loc, List<Vehicule> vehicule, List<Mission> mission, List<Chauffeur> chauffeur, List<Admin> admin) {
        this.loc = new ArrayList<Locataire>();
        this.loc = loc;
        this.vehicule = new ArrayList<Vehicule>();
        this.vehicule = vehicule;
        this.mission = new ArrayList<Mission>();
        this.mission = mission;
        this.chauffeur = new ArrayList<Chauffeur>();
        this.chauffeur = chauffeur;
        this.admin = new ArrayList<Admin>();
        this.admin = admin;
    }

    @Override
    public String toString() {
        return "Recherche{" +
                "loc=" + loc +
                ", vehicule=" + vehicule +
                ", mission=" + mission +
                ", chauffeur=" + chauffeur +
                ", admin=" + admin +
                '}';
    }
}
