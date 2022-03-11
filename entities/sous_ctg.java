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
public class sous_ctg {
    private long id_sctg;
    private String nom_sctg;
    private long id_categorie;

    public sous_ctg() {
    }

    public sous_ctg(long id_sctg, String nom_sctg, long id_categorie) {
        this.id_sctg = id_sctg;
        this.nom_sctg = nom_sctg;
        this.id_categorie = id_categorie;
    }

    public long getId_sctg() {
        return id_sctg;
    }

    public String getNom_sctg() {
        return nom_sctg;
    }

    public long getId_categorie() {
        return id_categorie;
    }

    public void setId_sctg(long id_sctg) {
        this.id_sctg = id_sctg;
    }

    public void setNom_sctg(String nom_sctg) {
        this.nom_sctg = nom_sctg;
    }

    public void setId_categorie(long id_categorie) {
        this.id_categorie = id_categorie;
    }

    @Override
    public String toString() {
        return "sous_ctg{" + "id_sctg=" + id_sctg + ", nom_sctg=" + nom_sctg + ", id_categorie=" + id_categorie + '}';
    }
    
}
