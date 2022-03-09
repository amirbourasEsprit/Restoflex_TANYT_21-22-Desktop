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
public class role {
    private long id_role;
    private String nom_role;

    public role() {
    }

    public role(String nom_role) {
        this.nom_role = nom_role;
    }

    public role(long id_role, String nom_role) {
        this.id_role = id_role;
        this.nom_role = nom_role;
    }

    public long getId_role() {
        return id_role;
    }

    public String getNom_role() {
        return nom_role;
    }

    public void setId_role(long id_role) {
        this.id_role = id_role;
    }

    public void setNom_role(String nom_role) {
        this.nom_role = nom_role;
    }

    @Override
    public String toString() {
        return nom_role ;
    }
    
}
