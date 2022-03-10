/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author boura
 */
public class restaurant {
    private long id_rest;
    private String nom;
    private String specialite;
    private String adresse;
    private String email ;
    private String num_tel;

    
    ///////////CONSTRUCTEUR////////////
    public restaurant() {    
    }

    public restaurant(long id_rest, String nom, String specialite, String adresse, String email, String num_tel) {
        this.id_rest = id_rest;
        this.nom = nom;
        this.specialite = specialite;
        this.adresse = adresse;
        this.email = email;
        this.num_tel = num_tel;
    }

    public restaurant(String nom, String specialite, String adresse, String email, String num_tel) {
        this.nom = nom;
        this.specialite = specialite;
        this.adresse = adresse;
        this.email = email;
        this.num_tel = num_tel;
    }

    ////////GETTER/////////
    public long getId_rest() {
        return id_rest;
    }

    public String getNom() {
        return nom;
    }

    public String getSpecialite() {
        return specialite;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getEmail() {
        return email;
    }

    public String getNum_tel() {
        return num_tel;
    }
    //////  setter   /////////

    public void setId_rest(long id_rest) {
        this.id_rest = id_rest;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setSpecialite(String specialite) {
        this.specialite = specialite;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }
    /////////TO STRING ////////

    @Override
    public String toString() {
        return  nom  ;
    }
    
}

