package com.microservices.mission;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicule")
public class Vehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_vehicule;
    private String immatricule;
    private String marque;
    private String model;
    private String categorie;
    private String permis;

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public String getImmatricule() {
        return immatricule;
    }

    public void setImmatricule(String immatricule) {
        this.immatricule = immatricule;
    }

    public String getMarque() {
        return marque;
    }

    public void setMarque(String marque) {
        this.marque = marque;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public Vehicule(String immatricule, String marque, String model, String categorie, String permis) {
        this.immatricule = immatricule;
        this.marque = marque;
        this.model = model;
        this.categorie = categorie;
        this.permis = permis;
    }

    public Vehicule() {
    }

    @Override
    public String toString() {
        return "Vehicule{" +
                "id_vehicule=" + id_vehicule +
                ", immatricule='" + immatricule + '\'' +
                ", marque='" + marque + '\'' +
                ", model='" + model + '\'' +
                ", categorie='" + categorie + '\'' +
                ", permis='" + permis + '\'' +
                '}';
    }
}
