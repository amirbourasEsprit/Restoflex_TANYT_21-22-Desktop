/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.sql.Date;


/**
 *
 * @author boura
 */
public class reclamation {
    private long num_reclamation;
    private String destinataire;
    private String description;
    private String statut_reclamation;
    private Date date_reclamation;
    private long id_type_reclamation;
    private long id_utilisateur;
    public utilisateur utilisateur;
    public reclamation() {
    }

    public reclamation(long num_reclamation, String destinataire, String description, String statut_reclamation, Date date_reclamation, long id_type_reclamation, long id_utilisateur) {
        this.num_reclamation = num_reclamation;
        this.destinataire = destinataire;
        this.description = description;
        this.statut_reclamation = statut_reclamation;
        this.date_reclamation = date_reclamation;
        this.id_type_reclamation = id_type_reclamation;
        this.id_utilisateur = id_utilisateur;
    }

    public reclamation(String destinataire, String description, String statut_reclamation, Date date_reclamation, long id_type_reclamation, long id_utilisateur) {
        this.destinataire = destinataire;
        this.description = description;
        this.statut_reclamation = statut_reclamation;
        this.date_reclamation = date_reclamation;
        this.id_type_reclamation = id_type_reclamation;
        this.id_utilisateur = id_utilisateur;
    }
    
    

    public long getNum_reclamation() {
        return num_reclamation;
    }

    public String getDestinataire() {
        return destinataire;
    }

    public String getDescription() {
        return description;
    }

    public String getStatut_reclamation() {
        return statut_reclamation;
    }

    public Date getDate_reclamation() {
        return date_reclamation;
    }

    public long getId_type_reclamation() {
        return id_type_reclamation;
    }

    public long getId_utilisateur() {
        return id_utilisateur;
    }

    public void setNum_reclamation(long num_reclamation) {
        this.num_reclamation = num_reclamation;
    }

    public void setDestinataire(String destinataire) {
        this.destinataire = destinataire;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatut_reclamation(String statut_reclamation) {
        this.statut_reclamation = statut_reclamation;
    }

    public void setDate_reclamation(Date date_reclamation) {
        this.date_reclamation = date_reclamation;
    }

    public void setId_type_reclamation(long id_type_reclamation) {
        this.id_type_reclamation = id_type_reclamation;
    }

    public void setId_utilisateur(long id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    @Override
    public String toString() {
        return "reclamation{" + "num_reclamation=" + num_reclamation + ", destinataire=" + destinataire + ", description=" + description + ", statut_reclamation=" + statut_reclamation + ", date_reclamation=" + date_reclamation + ", id_type_reclamation=" + id_type_reclamation + ", id_utilisateur=" +utilisateur+ '}';
    }
    

}
