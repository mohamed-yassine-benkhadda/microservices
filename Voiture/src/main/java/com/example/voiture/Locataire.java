package com.microservices.vehicule;

import jakarta.persistence.*;

@Entity
@Table(name = "locataire")
public class Locataire {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_locataire;
    private String Nom;
    private String adresse;

    public int getId_locataire() {
        return id_locataire;
    }

    public void setId_locataire(int id_locataire) {
        this.id_locataire = id_locataire;
    }

    public String getNom() {
        return Nom;
    }

    public void setNom(String nom) {
        Nom = nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Locataire(String nom, String adresse) {
        Nom = nom;
        this.adresse = adresse;
    }

    public Locataire() {
    }

    @Override
    public String toString() {
        return "Locataire{" +
                "id_locataire=" + id_locataire +
                ", Nom='" + Nom + '\'' +
                ", adresse='" + adresse + '\'' +
                '}';
    }
}
