/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaces;
import entities.stock;
import java.sql.SQLException;
import java.util.List;
/**
 *
 * @author abyrm
 */
public interface L_StockService  <T>{
     void ajouterStock (T entity) throws SQLException ; 
    void modifierStock(int id_fournisseur , T t) throws SQLException ; 
     void supprimerStock (int id_stock) throws SQLException;
        List<T> afficherStock();
         public stock rechercheStock (int id_stock);
}
