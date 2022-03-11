/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;
import java.util.*;
/**
 *
 * @author boura
 */
public class Conge {
    private long id_conge;
    private Date date_deb;
    private Date date_fin;
    private long sold_restant;
    private String validation;
    private long id_type_conge;
    private long id_utilisateur;

    public Conge() {
    }

    public Conge(long id_conge, Date date_deb, Date date_fin, long sold_restant, String validation, long id_type_conge, long id_utilisateur) {
        this.id_conge = id_conge;
        this.date_deb = date_deb;
        this.date_fin = date_fin;
        this.sold_restant = sold_restant;
        this.validation = validation;
        this.id_type_conge = id_type_conge;
        this.id_utilisateur = id_utilisateur;
    }

    public long getId_conge() {
        return id_conge;
    }

    public Date getDate_deb() {
        return date_deb;
    }

    public Date getDate_fin() {
        return date_fin;
    }

    public long getSold_restant() {
        return sold_restant;
    }

    public String getValidation() {
        return validation;
    }

    public long getId_type_conge() {
        return id_type_conge;
    }

    public long getId_utilisateur() {
        return id_utilisateur;
    }

    public void setId_conge(long id_conge) {
        this.id_conge = id_conge;
    }

    public void setDate_deb(Date date_deb) {
        this.date_deb = date_deb;
    }

    public void setDate_fin(Date date_fin) {
        this.date_fin = date_fin;
    }

    public void setSold_restant(long sold_restant) {
        this.sold_restant = sold_restant;
    }

    public void setValidation(String validation) {
        this.validation = validation;
    }

    public void setId_type_conge(long id_type_conge) {
        this.id_type_conge = id_type_conge;
    }

    public void setId_utilisateur(long id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "Conge{" + "id_conge=" + id_conge + ", date_deb=" + date_deb + ", date_fin=" + date_fin + ", sold_restant=" + sold_restant + ", validation=" + validation + ", id_type_conge=" + id_type_conge + ", id_utilisateur=" + id_utilisateur + '}';
    }
    
}
