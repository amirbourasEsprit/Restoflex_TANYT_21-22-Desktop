 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import entities.facture;
import entities.utilisateur;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Callback;
import service.FactureService;

/**
 * FXML Controller class
 *
 * @author Nesrine
 */
public class ListeFactureController implements Initializable {

    @FXML
    private TableView<facture> tableFactures;
    @FXML
    private TableColumn<facture, Long> colNumFac;
    @FXML
    private TableColumn<facture, Date> colDate;
    @FXML
    private TableColumn<facture, String> colEtat;
    @FXML
    private TableColumn<facture, String> colAction;
    @FXML
    private Label lblEtatFact;
    @FXML
    private Button btnModifier;
    @FXML
    private Button btnStatFact;
    @FXML
    private TextField txtSearch;
    @FXML
    private CheckBox checkNP;
    @FXML
    private CheckBox checkP;
    
     private ObservableList<facture> mesFactures ;
    facture f = null;
    protected static  facture p;
    
 utilisateur u= utilisateur.current_user;
    @FXML
    private AnchorPane pane;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        lblEtatFact.setVisible(false);
        checkNP.setVisible(false);
        checkP.setVisible(false);
        btnModifier.setVisible(false);
        
        FactureService fs = new FactureService();
        
        //pour la liste des facture
       
        mesFactures = FXCollections.observableArrayList();
        List<facture> Al = fs.afficherFactureRest(u.getId_rest());
        
       Al.stream().forEach((j)->{
            mesFactures.add(j);
        });
        
         colNumFac.setCellValueFactory(new PropertyValueFactory<>("num_facture"));
         colDate.setCellValueFactory(new PropertyValueFactory<>("date_facture"));
         colEtat.setCellValueFactory(new PropertyValueFactory<>("statut"));
     
        //add cell Action
         Callback<TableColumn<facture, String>, TableCell<facture, String>> cellFoctory = (TableColumn<facture, String> param) -> {
            // make cell containing buttons
            final TableCell<facture, String> cell = new TableCell<facture, String>() {
                @Override
                public void updateItem(String item, boolean empty) {
                    super.updateItem(item, empty);
                    //that cell created only on non-empty rows
                    if (empty) {
                        setGraphic(null);
                        setText(null);

                    } else {

                        FontAwesomeIconView downloadIcon = new FontAwesomeIconView(FontAwesomeIcon.DOWNLOAD);
                        FontAwesomeIconView editIcon = new FontAwesomeIconView(FontAwesomeIcon.EDIT); 
                        FontAwesomeIconView deleteIcon = new FontAwesomeIconView(FontAwesomeIcon.TRASH);
                        FontAwesomeIconView detailIcon = new FontAwesomeIconView(FontAwesomeIcon.ARROW_CIRCLE_RIGHT); 
                         
                        deleteIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:red;"
                        );
                        downloadIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:gray;"
                        );
                        detailIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:#00A693;"
                        );
                        editIcon.setStyle(
                                " -fx-cursor: hand ;"
                                + "-glyph-size:28px;"
                                + "-fx-fill:green;"
                        );
                        
                        downloadIcon.setOnMouseClicked((MouseEvent) -> {
                            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                            alert.setTitle("Confirmer téléchargement");
                            alert.setHeaderText("");
                            alert.setContentText("Veuillez vérifier votre facture avant téléchargement!");

                            Optional<ButtonType> result = alert.showAndWait();
                             if (result.get() == ButtonType.OK){
                                 p = (facture)tableFactures.getSelectionModel().getSelectedItem();
                          try {
                             FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjoutFacture.fxml"));
                            Parent root = loader.load();
                             pane.getChildren().add(root);
                              } catch (IOException ex) {
                                  System.out.println(ex);
                            }
                             } else {
                                  alert.hide();
                            } 
                        });
                        
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {
                          p = tableFactures.getSelectionModel().getSelectedItem();
                          facture fac=fs.rechercherFacture(p.getNum_facture());
                          
                        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                        alert.setTitle("Confirmer la suppression");
                        alert.setHeaderText("Vous allez Supprimer une facture!");
                        alert.setContentText("Vous voulez vraiment la supprimer ?");

                            Optional<ButtonType> result = alert.showAndWait();
                             if (result.get() == ButtonType.OK){
                                 fs.supprimerFacture(fac.getNum_facture()); 
                                 refreshTable();
                             } else {
                                  alert.hide();
                            } 
                           });
                    
                        detailIcon.setOnMouseClicked((MouseEvent event) -> {
                           p = (facture)tableFactures.getSelectionModel().getSelectedItem();
                          try {
                               FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjoutFacture.fxml"));
                            Parent root = loader.load();
                             pane.getChildren().add(root);
                              } catch (IOException ex) {
                                  System.out.println(ex);
                            }
                           });
                        
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            
                            lblEtatFact.setVisible(true);
                            checkNP.setVisible(true);
                            checkP.setVisible(true);
                            btnModifier.setVisible(true);
                            
                            p = tableFactures.getSelectionModel().getSelectedItem();
                            facture fac=fs.rechercherFacture(p.getNum_facture());
                            if(p.getStatut()=="payée"){
                               checkP.setSelected(true);
                            }else{
                               checkNP.setSelected(true);
                            }
                         
                               });
                        HBox managebtn = new HBox(downloadIcon, editIcon, deleteIcon, detailIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(downloadIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(detailIcon, new Insets(2, 3, 0, 2));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));
                       
                        setGraphic(managebtn);
                        setText(null);
                    }
                }
            };
         return cell;
        };
      
        colAction.setCellFactory(cellFoctory);
        
        tableFactures.setItems(null);
        tableFactures.setItems(mesFactures);  
        
        //recherche 
        FilteredList<facture> filtredData = new FilteredList<>(mesFactures, b -> true);
        txtSearch.textProperty().addListener((observable, oldValue, newValue) -> {
            
            filtredData.setPredicate(facture -> {
                if (newValue.isEmpty() || newValue == null){
                    return true;
                }
                
                String keyword = newValue.toLowerCase();
                if (facture.getStatut().toLowerCase().indexOf(keyword) > -1){
                    return true;
                }else if (facture.getDate_facture().toString().indexOf(keyword) > -1){
                    return true;                                              
                }else
                return false;
           });
        });
        
        SortedList<facture> sortedFact = new SortedList<>(filtredData);
        sortedFact.comparatorProperty().bind(tableFactures.comparatorProperty());
        tableFactures.setItems(sortedFact);
         
        
    }
   
 private void refreshTable() {
      
       mesFactures.clear();
       FactureService fs = new FactureService();
     
       mesFactures = FXCollections.observableArrayList();
       List<facture> Al = fs.afficherFactureRest(u.getId_rest());
        
       Al.stream().forEach((j)->{
            mesFactures.add(j);
        });
         colNumFac.setCellValueFactory(new PropertyValueFactory<>("num_facture"));
         colDate.setCellValueFactory(new PropertyValueFactory<>("date_facture"));
         colEtat.setCellValueFactory(new PropertyValueFactory<>("statut"));
        
        tableFactures.setItems(null);
        tableFactures.setItems(mesFactures);   
    }    

    @FXML
    private void modifierEtatFact(ActionEvent event) {
        FactureService fs = new FactureService();
        p = tableFactures.getSelectionModel().getSelectedItem();
        
        if(checkNP.isSelected()){
                      p.setStatut("non payée");
                  }
                  else{
                      p.setStatut("payée");
                  }
        
        fs.modifierFacture(p.getNum_facture(),p);
                
        Alert a1 = new Alert(Alert.AlertType.CONFIRMATION);
        a1.setTitle("Confirmation ");
        a1.setContentText("Facture modifié avec succès !");
        a1.show();
                    
        refreshTable();
        lblEtatFact.setVisible(false);
        checkNP.setVisible(false);
        checkP.setVisible(false);
        btnModifier.setVisible(false);
    }

    @FXML
    private void getStatFact(ActionEvent event) {
        try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/StatFacture.fxml"));
                Parent root = loader.load();
               pane.getChildren().add(root);
            } catch (IOException ex) {
                System.out.println(ex);
            }
    }

    @FXML
    private void handleCBNP(ActionEvent event) {
         if(checkNP.isSelected()){
            checkP.setSelected(false);
        }
    }

    @FXML
    private void handleCBP(ActionEvent event) {
         if(checkP.isSelected()){
            checkNP.setSelected(false);
        }
    }

   

    
}
