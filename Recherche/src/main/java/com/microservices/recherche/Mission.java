package com.microservices.recherche;

import jakarta.persistence.*;

@Entity
@Table(name = "mission")
public class Mission {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_mission;
    private int id_chauffeur;
    private int id_vehicule;
    private String adresse;
    private String date_de_debut;
    private String date_de_fin;
    private String titre;
    private String description;

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId_mission() {
        return id_mission;
    }

    public void setId_mission(int id_mission) {
        this.id_mission = id_mission;
    }

    public int getId_chauffeur() {
        return id_chauffeur;
    }

    public void setId_chauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }

    public int getId_vehicule() {
        return id_vehicule;
    }

    public void setId_vehicule(int id_vehicule) {
        this.id_vehicule = id_vehicule;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getDate_de_debut() {
        return date_de_debut;
    }

    public void setDate_de_debut(String date_de_debut) {
        this.date_de_debut = date_de_debut;
    }

    public String getDate_de_fin() {
        return date_de_fin;
    }

    public void setDate_de_fin(String date_de_fin) {
        this.date_de_fin = date_de_fin;
    }

    public Mission(int id_chauffeur, int id_vehicule, String adresse, String date_de_debut, String date_de_fin, String titre, String description) {
        this.id_chauffeur = id_chauffeur;
        this.id_vehicule = id_vehicule;
        this.adresse = adresse;
        this.date_de_debut = date_de_debut;
        this.date_de_fin = date_de_fin;
        this.titre = titre;
        this.description = description;
    }

    public Mission() {
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id_mission=" + id_mission +
                ", id_chauffeur=" + id_chauffeur +
                ", id_vehicule=" + id_vehicule +
                ", adresse='" + adresse + '\'' +
                ", date_de_debut='" + date_de_debut + '\'' +
                ", date_de_fin='" + date_de_fin + '\'' +
                '}';
    }
}
