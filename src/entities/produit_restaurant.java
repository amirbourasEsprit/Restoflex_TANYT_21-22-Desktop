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
public class produit_restaurant {
    private long id_pdrest;
    private String nom_pdt;
    private float quantit_pdt;
    private long id_rest;

    public String getCateg() {
        return categ;
    }

    public void setCateg(String categ) {
        this.categ = categ;
    }
    private String categ;

    public produit_restaurant() {
    }

    public produit_restaurant(long id_pdrest, String nom_pdt, float quantit_pdt, long id_rest, String categ) {
        this.id_pdrest = id_pdrest;
        this.nom_pdt = nom_pdt;
        this.quantit_pdt = quantit_pdt;
        this.id_rest = id_rest;
        this.categ = categ;
    }

    

    public long getId_pdrest() {
        return id_pdrest;
    }

    public String getNom_pdt() {
        return nom_pdt;
    }

    public float getQuantit_pdt() {
        return quantit_pdt;
    }

    public long getId_rest() {
        return id_rest;
    }

    

    public void setId_pdrest(long id_pdrest) {
        this.id_pdrest = id_pdrest;
    }

    public void setNom_pdt(String nom_pdt) {
        this.nom_pdt = nom_pdt;
    }

    public void setQuantit_pdt(float quantit_pdt) {
        this.quantit_pdt = quantit_pdt;
    }

    public void setId_rest(long id_rest) {
        this.id_rest = id_rest;
    }

   

    @Override
    public String toString() {
        return nom_pdt;
    }
    
}
