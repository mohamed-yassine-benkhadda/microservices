package com.microservices.mission;

public class ListeMission {
    private int id_mission;
    private String titre;
    private String adresse;
    private String nomChauffeur;
    private String cin;
    private String dateNaissance;
    private String permis;
    private String marque;
    private String model;
    private String categorie;
    private String immatricule;
    private String description;

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

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getNomChauffeur() {
        return nomChauffeur;
    }

    public void setNomChauffeur(String nomChauffeur) {
        this.nomChauffeur = nomChauffeur;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(String dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
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

    public String getImmatricule() {
        return immatricule;
    }

    public void setImmatricule(String immatricule) {
        this.immatricule = immatricule;
    }

    public ListeMission(int id_mission, String titre, String adresse, String nomChauffeur, String cin, String dateNaissance, String permis, String marque, String model, String categorie, String immatricule, String description) {
        this.id_mission = id_mission;
        this.titre = titre;
        this.adresse = adresse;
        this.nomChauffeur = nomChauffeur;
        this.cin = cin;
        this.dateNaissance = dateNaissance;
        this.permis = permis;
        this.marque = marque;
        this.model = model;
        this.categorie = categorie;
        this.immatricule = immatricule;
        this.description = description;
    }

    public ListeMission() {
    }

    @Override
    public String toString() {
        return "Mission{" +
                "id_mission=" + id_mission +
                ", titre='" + titre + '\'' +
                ", adresse='" + adresse + '\'' +
                ", nomChauffeur='" + nomChauffeur + '\'' +
                ", cin='" + cin + '\'' +
                ", dateNaissance='" + dateNaissance + '\'' +
                ", permis='" + permis + '\'' +
                ", marque='" + marque + '\'' +
                ", model='" + model + '\'' +
                ", categorie='" + categorie + '\'' +
                ", immatricule='" + immatricule + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
