/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.*;

/**
 *
 * @author boura
 */
public class utilisateur {
    private int id_utilisateur;
    private String nom;
    private String prenom;
    private String cin;
    private String mdp;
    private String email;
    private String num_tel;
    private Date date_naissance;
    private String adresse;
    private int Salaire;
    private String Status_compte;

    public String getStatus_compte() {
        return Status_compte;
    }

    public void setStatus_compte(String Status_compte) {
        this.Status_compte = Status_compte;
    }
    //employee
    private int solde_conge;
    private String poste_employe;
   // cle etrangé     
    private int id_role;
    private int id_rest;
    private int id_fournisseur;
    public static utilisateur current_user; 
     public fournisseur fournisseur;
     public restaurant restaurant;
    //constructeur non parametrer
    public utilisateur() {
    }

    public utilisateur(int id_utilisateur, String nom, String prenom, String cin, String mdp, String email, String num_tel, Date date_naissance, String adresse, int id_role, int id_rest, int id_fournisseur,String Status_compte) {
        this.id_utilisateur = id_utilisateur;
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
        this.id_role = id_role;
        this.id_rest = id_rest;
        this.id_fournisseur = id_fournisseur;
        this.Status_compte=Status_compte;
    }
    
    //constructure pour ajouter un employee

    public utilisateur(String nom, String prenom, String cin, String mdp, String email, String numt_tel, Date date_naissance, String adresse, int Salaire, int solde_conge, String poste_employe, int id_role, int id_rest) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = numt_tel;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
        this.Salaire = Salaire;
        this.solde_conge = solde_conge;
        this.poste_employe = poste_employe;
        this.id_role = id_role;
        this.id_rest = id_rest;
    }
    //constucteur fournisseur

    public utilisateur(String nom, String prenom, String cin, String mdp, String email, String numt_tel, Date date_naissance, String adresse, int id_role, int id_rest, int id_fournisseur ) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = numt_tel;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
        this.id_role = id_role;
        this.id_rest = id_rest;
        this.id_fournisseur = id_fournisseur;
        
    }
    
//constructure modification

    public utilisateur(String nom, String prenom, String cin, String mdp, String email, String num_tel, Date date_naissance, String adresse, int Salaire, int solde_conge, String poste_employe) {
        this.nom = nom;
        this.prenom = prenom;
        this.cin = cin;
        this.mdp = mdp;
        this.email = email;
        this.num_tel = num_tel;
        this.date_naissance = date_naissance;
        this.adresse = adresse;
        this.Salaire = Salaire;
        this.solde_conge = solde_conge;
        this.poste_employe = poste_employe;
    }
    
    
    
    ///////////////GETTER/////////////
    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getCin() {
        return cin;
    }

    public String getMdp() {
        return mdp;
    }

    public String getEmail() {
        return email;
    }

    public String getNum_tel() {
        return num_tel;
    }

    public Date getDate_naissance() {
        return date_naissance;
    }

    public String getAdresse() {
        return adresse;
    }

    public int getSalaire() {
        return Salaire;
    }

    public int getSolde_conge() {
        return solde_conge;
    }

    public String getPoste_employe() {
        return poste_employe;
    }

    public int getId_role() {
        return id_role;
    }

    public int getId_rest() {
        return id_rest;
    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    
    /////////////SETTER//////////
    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setNum_tel(String num_tel) {
        this.num_tel = num_tel;
    }

    public void setDate_naissance(Date date_naissance) {
        this.date_naissance = date_naissance;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setSalaire(int Salaire) {
        this.Salaire = Salaire;
    }

    public void setSolde_conge(int solde_conge) {
        this.solde_conge = solde_conge;
    }

    public void setPoste_employe(String poste_employe) {
        this.poste_employe = poste_employe;
    }

    public void setId_role(int id_role) {
        this.id_role = id_role;
    }

    public void setId_rest(int id_rest) {
        this.id_rest = id_rest;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    @Override
    public String toString() {
        return nom+" "+prenom; 
                }
    //    return "utilisateur{" + "id_utilisateur=" + id_utilisateur + ", nom=" + nom + ", prenom=" + prenom + ", cin=" + cin + ", mdp=" + mdp + ", email=" + email + ", numt_tel=" + num_tel + ", date_naissance=" + date_naissance + ", adresse=" + adresse + ", Salaire=" + Salaire + ", solde_conge=" + solde_conge + ", poste_employe=" + poste_employe + ", id_role=" + id_role + ", id_rest=" + id_rest + ", fournisseur=" + restaurant.getNom() + '}';
    


    
}
