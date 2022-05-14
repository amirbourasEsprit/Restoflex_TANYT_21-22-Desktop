/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.sql.*;

public class Conge {
    private int id_conge;
    private Date date_deb;
    private Date date_fin;
    private int solde_restant;
    private String etat;
    private int id_type_conge;
    private int id_utilisateur;
    public utilisateur user;
    public Conge() {
    }

    public Conge(Date date_deb, Date date_fin, int solde_restant, String etat, int id_type_conge, int id_utilisateur) {
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.solde_restant = solde_restant;
        this.etat = etat;
        this.id_type_conge = id_type_conge;
        this.id_utilisateur = id_utilisateur;
    }

    
    public Conge(int id_conge, Date date_deb, Date date_fin, int solde_restant, String etat, int id_type_conge, int id_utilisateur) {
        this.id_conge = id_conge;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.solde_restant = solde_restant;
        this.etat = etat;
        this.id_type_conge = id_type_conge;
        this.id_utilisateur = id_utilisateur;
    }

    public int getId_conge() {
        return id_conge;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public int getSolde_restant() {
        return solde_restant;
    }

    public String getEtat() {
        return etat;
    }

    public int getId_type_conge() {
        return id_type_conge;
    }

    public int getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_conge(int id_conge) {
        this.id_conge = id_conge;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setSolde_restant(int solde_restant) {
        this.solde_restant = solde_restant;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    public void setId_type_conge(int id_type_conge) {
        this.id_type_conge = id_type_conge;
    }

    public void setId_utilisateur(int id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "Conge{" + "id_conge=" + id_conge + ", date_deb=" + date_deb + ", date_fin=" + date_fin + ", solde_restant=" + solde_restant + ", etat=" + etat + ", id_type_conge=" + id_type_conge + ", id_utilisateur=" + id_utilisateur + '}';
    }
    
}
