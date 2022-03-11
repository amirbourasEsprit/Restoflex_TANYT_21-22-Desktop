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
public class fournisseur_restaurant {
    private long id_fournisseur_restaurant;
    private long id_rest;
    private long id_fournisseur;

    public fournisseur_restaurant() {
    }

    public fournisseur_restaurant(long id_fournisseur_restaurant, long id_rest, long id_fournisseur) {
        this.id_fournisseur_restaurant = id_fournisseur_restaurant;
        this.id_rest = id_rest;
        this.id_fournisseur = id_fournisseur;
    }

    public fournisseur_restaurant(long id_rest, long id_fournisseur) {
        this.id_rest = id_rest;
        this.id_fournisseur = id_fournisseur;
    }

    public long getId_fournisseur_restaurant() {
        return id_fournisseur_restaurant;
    }

    public long getId_rest() {
        return id_rest;
    }

    public long getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_fournisseur_restaurant(long id_fournisseur_restaurant) {
        this.id_fournisseur_restaurant = id_fournisseur_restaurant;
    }

    public void setId_rest(long id_rest) {
        this.id_rest = id_rest;
    }

    public void setId_fournisseur(long id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    @Override
    public String toString() {
        return "fournisseur_restaurant{" + "id_fournisseur_restaurant=" + id_fournisseur_restaurant + ", id_rest=" + id_rest + ", id_fournisseur=" + id_fournisseur + '}';
    }
    

}
