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
    private String quantit_pdt;
    private long id_rest;
    private long id_sctg;

    public produit_restaurant() {
    }

    public produit_restaurant(long id_pdrest, String nom_pdt, String quantit_pdt, long id_rest, long id_sctg) {
        this.id_pdrest = id_pdrest;
        this.nom_pdt = nom_pdt;
        this.quantit_pdt = quantit_pdt;
        this.id_rest = id_rest;
        this.id_sctg = id_sctg;
    }

    public long getId_pdrest() {
        return id_pdrest;
    }

    public String getNom_pdt() {
        return nom_pdt;
    }

    public String getQuantit_pdt() {
        return quantit_pdt;
    }

    public long getId_rest() {
        return id_rest;
    }

    public long getId_sctg() {
        return id_sctg;
    }

    public void setId_pdrest(long id_pdrest) {
        this.id_pdrest = id_pdrest;
    }

    public void setNom_pdt(String nom_pdt) {
        this.nom_pdt = nom_pdt;
    }

    public void setQuantit_pdt(String quantit_pdt) {
        this.quantit_pdt = quantit_pdt;
    }

    public void setId_rest(long id_rest) {
        this.id_rest = id_rest;
    }

    public void setId_sctg(long id_sctg) {
        this.id_sctg = id_sctg;
    }

    @Override
    public String toString() {
        return "produit_restaurant{" + "id_pdrest=" + id_pdrest + ", nom_pdt=" + nom_pdt + ", quantit_pdt=" + quantit_pdt + ", id_rest=" + id_rest + ", id_sctg=" + id_sctg + '}';
    }
    
}
