package com.projet.microservices.chauffeur;

public class Permis {
    private int idPermis;
    private int idChauffeur;
    private String typePermis;
    private String dateExpiration;

    public int getIdPermis() {
        return idPermis;
    }

    public void setIdPermis(int idPermis) {
        this.idPermis = idPermis;
    }

    public int getIdChauffeur() {
        return idChauffeur;
    }

    public void setIdChauffeur(int idChauffeur) {
        this.idChauffeur = idChauffeur;
    }

    public String getTypePermis() {
        return typePermis;
    }

    public void setTypePermis(String typePermis) {
        this.typePermis = typePermis;
    }

    public String getDateExpiration() {
        return dateExpiration;
    }

    public void setDateExpiration(String dateExpiration) {
        this.dateExpiration = dateExpiration;
    }

    public Permis(int idPermis, int idChauffeur, String typePermis, String dateExpiration) {
        this.idPermis = idPermis;
        this.idChauffeur = idChauffeur;
        this.typePermis = typePermis;
        this.dateExpiration = dateExpiration;
    }

    public Permis() {
    }
}
