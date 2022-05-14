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
public class Commande {
    private int id_cmd;
    private String statut;
    private Date date_cmd;
    private Date date_livraison;

   
    private float quantite;
    private long id_utilisateur;
    private long id_produit;
    private long id_four;
    private String nomUtil;
    private String nomProd;
    private String nomFour;
    public produit_restaurant produit;
  
    

    public Commande() {
    }
 public Commande(String statut, Date date_cmd, Date date_livraison, float quantite, long id_utilisateur) {
        this.statut = statut;
        this.date_cmd = date_cmd;
        this.date_livraison = date_livraison;
        this.quantite = quantite;
        this.id_utilisateur = id_utilisateur;
        
    }
    public Commande(int id_cmd,String statut, Date date_cmd, Date date_livraison, float quantite, long id_utilisateur) {
        this.id_cmd=id_cmd;
        this.statut = statut;
        this.date_cmd = date_cmd;
        this.date_livraison = date_livraison;
        this.quantite = quantite;
        this.id_utilisateur = id_utilisateur;
        
    }
      public Commande(String statut, Date date_cmd, Date date_livraison, float quantite) {
        this.statut = statut;
        this.date_cmd = date_cmd;
        this.date_livraison = date_livraison;
        this.quantite = quantite;
        
        
    }

    public Commande( String statut, Date date_cmd, Date date_livraison, float quantite, long id_utilisateur, long id_produit, long id_four) {
        this.statut = statut;
        this.date_cmd = date_cmd;
        this.date_livraison = date_livraison;
        this.quantite = quantite;
        this.id_utilisateur = id_utilisateur;
        this.id_produit = id_produit;
        this.id_four = id_four;
    }
        public Commande( String statut, Date date_cmd, Date date_livraison, float quantite, long id_utilisateur, long id_produit) {
        this.statut = statut;
        this.date_cmd = date_cmd;
        this.date_livraison = date_livraison;
        this.quantite = quantite;
        this.id_utilisateur = id_utilisateur;
        this.id_produit = id_produit;
        
    }

    public Commande(int id_cmd, String statut, Date date_cmd, Date date_livraison, float quantite, String nomUtil, String nomProd, String nomFour) {
        this.id_cmd = id_cmd;
        this.statut = statut;
        this.date_cmd = date_cmd;
        this.date_livraison = date_livraison;
        this.quantite = quantite;
        this.nomUtil = nomUtil;
        this.nomProd = nomProd;
        this.nomFour = nomFour;
    }

    public long getId_four() {
        return id_four;
    }

    public void setId_four(long id_four) {
        this.id_four = id_four;
    }

    public String getNomUtil() {
        return nomUtil;
    }

    public void setNomUtil(String nomUtil) {
        this.nomUtil = nomUtil;
    }

    public String getNomProd() {
        return nomProd;
    }

    public void setNomProd(String nomProd) {
        this.nomProd = nomProd;
    }

    public String getNomFour() {
        return nomFour;
    }

    public void setNomFour(String nomFour) {
        this.nomFour = nomFour;
    }

    

   
    


    public int getId_cmd() {
        return id_cmd;
    }

    public String getStatut() {
        return statut;
    }


    public float getQuantite() {
        return quantite;
    }

    public long getId_utilisateur() {
        return id_utilisateur;
    }

    public long getId_produit() {
        return id_produit;
    }

    public void setId_cmd(int id_cmd) {
        this.id_cmd = id_cmd;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public void setId_utilisateur(long id_utilisateur) {
        this.id_utilisateur = id_utilisateur;
    }

    public void setId_produit(long id_produit) {
        this.id_produit = id_produit;
    }

    public Date getDate_cmd() {
        return date_cmd;
    }

    public void setDate_cmd(Date date_cmd) {
        this.date_cmd = date_cmd;
    }

    public Date getDate_livraison() {
        return date_livraison;
    }

    public void setDate_livraison(Date date_livraison) {
        this.date_livraison = date_livraison;
    }
   

    @Override
    public String toString() {
        return "Commande{" + "id_cmd=" + id_cmd + ", statut=" + statut + ", date_cmd=" + date_cmd + ", date_livraison=" + date_livraison + ", quantite=" + quantite + ", id_utilisateur=" + id_utilisateur + ", id_produit=" + id_produit + '}';
    }

    
  
}
