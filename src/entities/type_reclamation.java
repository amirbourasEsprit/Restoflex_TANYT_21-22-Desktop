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
public class type_reclamation {
    private long id_type_reclamation;
    private String nom_type_reclamation;

    public type_reclamation() {
    }

    public type_reclamation(String nom_type_reclamation) {
        this.nom_type_reclamation = nom_type_reclamation;
    }

    public type_reclamation(long id_type_reclamation, String nom_type_reclamation) {
        this.id_type_reclamation = id_type_reclamation;
        this.nom_type_reclamation = nom_type_reclamation;
    }

    public long getId_type_reclamation() {
        return id_type_reclamation;
    }

    public String getNom_type_reclamation() {
        return nom_type_reclamation;
    }

    public void setId_type_reclamation(long id_type_reclamation) {
        this.id_type_reclamation = id_type_reclamation;
    }

    public void setNom_type_reclamation(String nom_type_reclamation) {
        this.nom_type_reclamation = nom_type_reclamation;
    }

    @Override
    public String toString() {
        return  nom_type_reclamation;
    }
    
}