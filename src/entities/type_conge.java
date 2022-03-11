/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;


public class type_conge {
    private int id_type_conge;
    private String nom_type_conge;

    public type_conge() {
    }

    public type_conge(String nom_type_conge) {
        this.nom_type_conge = nom_type_conge;
    }
    

    public type_conge(int id_type_conge, String nom_type_conge) {
        this.id_type_conge = id_type_conge;
        this.nom_type_conge = nom_type_conge;
    }

    public int getId_type_conge() {
        return id_type_conge;
    }

    public void setId_type_conge(int id_type_conge) {
        this.id_type_conge = id_type_conge;
    }

    public String getNom_type_conge() {
        return nom_type_conge;
    }

    public void setNom_type_conge(String nom_type_conge) {
        this.nom_type_conge = nom_type_conge;
    }

    @Override
    public String toString() {
        return "type_conge{" + "id_type_conge=" + id_type_conge + ", nom_type_conge=" + nom_type_conge + '}';
    }
    
}
