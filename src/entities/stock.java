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
public class stock {
    private long id_stock;
    private String nom_stock;
    private float prix_unitaire;
    private float quantite;
    private long id_fournisseur;

    public stock() {
    }

    public stock(long id_stock, String nom_stock, float prix_unitaire, float quantite, long id_fournisseur) {
        this.id_stock = id_stock;
        this.nom_stock = nom_stock;
        this.prix_unitaire = prix_unitaire;
        this.quantite = quantite;
        this.id_fournisseur = id_fournisseur;
    }

    public long getId_stock() {
        return id_stock;
    }

    public String getNom_stock() {
        return nom_stock;
    }

    public float getPrix_unitaire() {
        return prix_unitaire;
    }

    public float getQuantite() {
        return quantite;
    }

    public long getId_fournisseur() {
        return id_fournisseur;
    }

    public void setId_stock(long id_stock) {
        this.id_stock = id_stock;
    }

    public void setNom_stock(String nom_stock) {
        this.nom_stock = nom_stock;
    }

    public void setPrix_unitaire(float prix_unitaire) {
        this.prix_unitaire = prix_unitaire;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public void setId_fournisseur(long id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    @Override
    public String toString() {
        return "stock{" + "id_stock=" + id_stock + ", nom_stock=" + nom_stock + ", prix_unitaire=" + prix_unitaire + ", quantite=" + quantite + ", id_fournisseur=" + id_fournisseur + '}';
    }
    
}
