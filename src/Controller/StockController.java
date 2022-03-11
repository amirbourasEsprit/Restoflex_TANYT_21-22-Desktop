  /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.cell.PropertyValueFactory;
import service.StockService;
import interfaces.L_StockService;
import entities.stock;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.Observable;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.print.PageLayout;
import javafx.print.PageOrientation;
import javafx.print.Paper;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import javax.swing.JOptionPane;


/**
 * FXML Controller class
 *
 * @author abyrm
 */
public class StockController implements Initializable {

    @FXML
    private TableView<stock> table;
    @FXML
    private TableColumn<stock, String> nom_stock;
    @FXML
    private TableColumn<stock, Float> prix_unitaire;
    @FXML
    private TableColumn<stock, Float> quantite;
   
  
    @FXML
    private AnchorPane pane;
 
    private TableColumn<stock, Integer> id_fournisseur;
    
    
    static stock s;
    @FXML
    private TableColumn<stock, String> editCol;
    @FXML
    private TextField search;
   
    @FXML
    private Button stat;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
     StockService st = new StockService();
        List stock = st.afficherStock();
        ObservableList list = FXCollections.observableArrayList(stock);
        nom_stock.setCellValueFactory(new PropertyValueFactory<>("nom_stock"));
        prix_unitaire.setCellValueFactory(new PropertyValueFactory<>("prix_unitaire"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));

        
      //  id_fournisseur.setCellValueFactory(new PropertyValueFactory<>("id_fournisseur"));    
        ////HI 
                //add cell of button edit 
         Callback<TableColumn<stock, String>, TableCell<stock, String>> cellFoctory = (TableColumn<stock, String> param) -> {
            // make cell containing buttons
            final TableCell<stock, String> cell = new TableCell<stock, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.PENCIL_SQUARE);

                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#ff1744;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00E676;"
                        );
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                          try{
                            stock s = new stock();
                            s=table.getSelectionModel().getSelectedItem();
                           st.supprimerStock((int) s.getId_stock());
                            JOptionPane.showMessageDialog(null,"le produit  a été supprimer avec succes");
                       //     loadData();
                         Actualiser();
                                }catch(Exception e){
                                    JOptionPane.showMessageDialog(null, e);}

                        });
                        
                        
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            stock s = new stock();
                            s=table.getSelectionModel().getSelectedItem();
                          
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ModifierStock.fxml"));
                            try {
                                 loader.load();
                                
                            } catch (IOException ex) {
                                Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            ModifierStockController ModifierStockController = loader.getController();
                        ModifierStockController.setUpdate(true);
                            
                       ModifierStockController.setTextField(s.getId_stock(),s.getNom_stock(),s.getPrix_unitaire(),s.getQuantite());


                       Parent root = loader.getRoot();
                            editIcon.getScene().setRoot(root);

                        });


                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };

            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         /////
             table.setItems(list);
             
    ;

    }    

 /*   private void Ajouter(ActionEvent event) {
     try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterStock.fxml"));
            Parent root = loader.load();
                        pane.getChildren().add(root);
          // TODO
       } catch (IOException ex) {
           Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }*/

    private void Update(ActionEvent event) throws SQLException {
        
      try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ModifierStock.fxml"));
            Parent root = loader.load();
                        pane.getChildren().add(root);
          // TODO
       } catch (IOException ex) {
           Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void delete(ActionEvent event) throws SQLException {
          StockService rs = new StockService();
      stock s = table.getSelectionModel().getSelectedItem();
      stock st = rs.rechercheStock((int) s.getId_stock());
     rs.supprimerStock((int) st.getId_stock());
        Actualiser();
    }
    
     private void Actualiser() {
            StockService st = new StockService();
        List stock = st.afficherStock();
        ObservableList list = FXCollections.observableArrayList(stock);
        table.setItems(list);
        nom_stock.setCellValueFactory(new PropertyValueFactory<>("nom_stock"));
        prix_unitaire.setCellValueFactory(new PropertyValueFactory<>("prix_unitaire"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
  
        
        //id_fournisseur.setCellValueFactory(new PropertyValueFactory<>("id_fournisseur"));    
    }

    @FXML
    private void GETADD(MouseEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterStock.fxml"));
            Parent root = loader.load();
            pane.getChildren().add(root);
        } catch (IOException ex) {
            Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    
   
    

    @FXML
    private void search(KeyEvent event) {
       int user_session_id_fournisseur=1;
        StockService us = new StockService();
        String recherche=this.search.getText();
        ObservableList<stock> data = FXCollections.observableArrayList(us.rechercheStock2(user_session_id_fournisseur,recherche));
        // TODO
        nom_stock.setCellValueFactory(new PropertyValueFactory<>("nom_stock"));
        prix_unitaire.setCellValueFactory(new PropertyValueFactory<>("prix_unitaire"));
        quantite.setCellValueFactory(new PropertyValueFactory<>("quantite"));
           

        table.setItems(data);  
}

    @FXML
    private void OnClickedPrint(ActionEvent event) {
            PrinterJob job = PrinterJob.createPrinterJob();
       
        Node root= this.table;
        
     if(job != null){
     job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
     Printer printer = job.getPrinter();
     PageLayout pageLayout = printer.createPageLayout(Paper.A3, PageOrientation.LANDSCAPE, Printer.MarginType.HARDWARE_MINIMUM);
     boolean success = job.printPage(pageLayout, root);
     if(success){
        job.endJob();
     }
   }
    }

    @FXML
    private void stat(ActionEvent event) {
              try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/stockChart.fxml"));
            Parent root = loader.load();
                        pane.getChildren().add(root);
          // TODO
       } catch (IOException ex) {
           Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}