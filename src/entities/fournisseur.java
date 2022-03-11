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
public class fournisseur {
    private int id_fournisseur;
    private String nom_fournisseur;
    private String matricule_fiscale;
    private String domaine_fournisseur;
    private String num_tel_fournisseur;
    private String email_fournisseur;
    private String adresse_fournisseur;
   private String logo;

    public fournisseur() {
    }

    public fournisseur(int id_fournisseur, String nom_fournisseur, String matricule_fiscale, String domaine_fournisseur, String num_tel_fournisseur, String email_fournisseur, String adresse_fournisseur, String logo) {
        this.id_fournisseur = id_fournisseur;
        this.nom_fournisseur = nom_fournisseur;
        this.matricule_fiscale = matricule_fiscale;
        this.domaine_fournisseur = domaine_fournisseur;
        this.num_tel_fournisseur = num_tel_fournisseur;
        this.email_fournisseur = email_fournisseur;
        this.adresse_fournisseur = adresse_fournisseur;
        this.logo = logo;
    }

    public fournisseur(String nom_fournisseur, String matricule_fiscale, String domaine_fournisseur, String num_tel_fournisseur, String email_fournisseur, String adresse_fournisseur,String logo) {
        this.nom_fournisseur = nom_fournisseur;
        this.matricule_fiscale = matricule_fiscale;
        this.domaine_fournisseur = domaine_fournisseur;
        this.num_tel_fournisseur = num_tel_fournisseur;
        this.email_fournisseur = email_fournisseur;
        this.adresse_fournisseur = adresse_fournisseur;
         this.logo = logo;
    }

    public int getId_fournisseur() {
        return id_fournisseur;
    }

    public String getNom_fournisseur() {
        return nom_fournisseur;
    }

    public String getMatricule_fiscale() {
        return matricule_fiscale;
    }

    public String getDomaine_fournisseur() {
        return domaine_fournisseur;
    }

    public String getNum_tel_fournisseur() {
        return num_tel_fournisseur;
    }

    public String getEmail_fournisseur() {
        return email_fournisseur;
    }

    public String getAdresse_fournisseur() {
        return adresse_fournisseur;
    }

    public String getLogo() {
        return logo;
    }

    public void setId_fournisseur(int id_fournisseur) {
        this.id_fournisseur = id_fournisseur;
    }

    public void setNom_fournisseur(String nom_fournisseur) {
        this.nom_fournisseur = nom_fournisseur;
    }

    public void setMatricule_fiscale(String matricule_fiscale) {
        this.matricule_fiscale = matricule_fiscale;
    }

    public void setDomaine_fournisseur(String domaine_fournisseur) {
        this.domaine_fournisseur = domaine_fournisseur;
    }

    public void setNum_tel_fournisseur(String num_tel_fournisseur) {
        this.num_tel_fournisseur = num_tel_fournisseur;
    }

    public void setEmail_fournisseur(String email_fournisseur) {
        this.email_fournisseur = email_fournisseur;
    }

    public void setAdresse_fournisseur(String adresse_fournisseur) {
        this.adresse_fournisseur = adresse_fournisseur;
    }

  public void setLogo(String logo) {
        this.logo = logo;
    }

    @Override
    public String toString() {
        return "fournisseur{" + "id_fournisseur=" + id_fournisseur + ", nom_fournisseur=" + nom_fournisseur + ", matricule_fiscale=" + matricule_fiscale + ", domaine_fournisseur=" + domaine_fournisseur + ", num_tel_fournisseur=" + num_tel_fournisseur + ", email_fournisseur=" + email_fournisseur + ", adresse_fournisseur=" + adresse_fournisseur + ", logo=" + logo + '}';
    }


    
    
    
}
