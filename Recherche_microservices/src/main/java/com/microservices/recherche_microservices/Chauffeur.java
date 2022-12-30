package com.microservices.recherche_microservices;

public class Chauffeur {

    private int id_chauffeur;
    private String CIN;
    private String nom;
    private String prenom;
    private String date_de_naissance;
    private String adresse;
    private String permis;

    public String getPermis() {
        return permis;
    }

    public void setPermis(String permis) {
        this.permis = permis;
    }

    public String getCIN() {
        return CIN;
    }

    public void setCIN(String CIN) {
        this.CIN = CIN;
    }

    public int getIdChauffeur() {
        return id_chauffeur;
    }

    public void setIdChauffeur(int id_chauffeur) {
        this.id_chauffeur = id_chauffeur;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getDateNaissance() {
        return date_de_naissance;
    }

    public void setDateNaissance(String date_de_naissance) {
        this.date_de_naissance = date_de_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Chauffeur(String CIN, String nom, String prenom, String date_de_naissance, String adresse, String permis) {
        this.CIN = CIN;
        this.nom = nom;
        this.prenom = prenom;
        this.date_de_naissance = date_de_naissance;
        this.adresse = adresse;
        this.permis = permis;
    }

    public Chauffeur() {
    }

    @Override
    public String toString() {
        return "Chauffeur{" +
                "id_chauffeur=" + id_chauffeur +
                ", CIN='" + CIN + '\'' +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", date_de_naissance='" + date_de_naissance + '\'' +
                ", adresse='" + adresse + '\'' +
                ", permis='" + permis + '\'' +
                '}';
    }
}
