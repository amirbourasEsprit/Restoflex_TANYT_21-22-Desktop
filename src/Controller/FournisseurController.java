 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import service.FournisseurService;
import entities.fournisseur;
import entities.stock;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;
import javax.swing.JOptionPane;
import service.StockService;

/**
 * FXML Controller class
 *
 * @author abyrm
 */
public class FournisseurController implements Initializable {

    @FXML
    private TableColumn<fournisseur, String> nom_fournisseur;
    @FXML
    private TableColumn<fournisseur, String> matricule_fiscale;
    @FXML
    private TableColumn<fournisseur, String> domaine_fournisseur;
    @FXML
    private TableColumn<fournisseur, String> num_tel_fournisseur;
    @FXML
    private TableColumn<fournisseur, String> editCol;
    @FXML
    private Button add;
    @FXML
    private TableColumn<fournisseur, String> adresse_fournisseur;
    @FXML
    private TableColumn<fournisseur, String> email_fournisseur;
    @FXML
    private AnchorPane pane;
    @FXML
    private TableView<fournisseur> table;
static fournisseur f ;
    @FXML
    private TableColumn<fournisseur, String> logo;
    @FXML
    private TextField search;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        FournisseurService fr = new FournisseurService();
        List fournisseur = fr.afficherFournisseur();
        ObservableList list = FXCollections.observableArrayList(fournisseur);
        table.setItems(list);
        nom_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nom_fournisseur"));
        matricule_fiscale.setCellValueFactory(new PropertyValueFactory<>("matricule_fiscale"));
        domaine_fournisseur.setCellValueFactory(new PropertyValueFactory<>("domaine_fournisseur"));
        num_tel_fournisseur.setCellValueFactory(new PropertyValueFactory<>("num_tel_fournisseur"));
        email_fournisseur.setCellValueFactory(new PropertyValueFactory<>("email_fournisseur"));
         adresse_fournisseur.setCellValueFactory(new PropertyValueFactory<>("adresse_fournisseur"));
         logo.setCellValueFactory(new PropertyValueFactory<>("logo"));
         
         
           ////HI 
                //add cell of button edit 
         Callback<TableColumn<fournisseur, String>, TableCell<fournisseur, String>> cellFoctory = (TableColumn<fournisseur, String> param) -> {
            // make cell containing buttons
            final TableCell<fournisseur, String> cell = new TableCell<fournisseur, String>() {
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
                         
                        //supprimer fournisseur et appel methode supprimer 
                        deleteIcon.setOnMouseClicked((MouseEvent event) -> {

                          try{
                        
                            fournisseur f = new fournisseur();
                            f=table.getSelectionModel().getSelectedItem();
                            fr.supprimerFournisseur(f.getId_fournisseur());
                           
                            JOptionPane.showMessageDialog(null,"le fournisseur  a été supprimer avec succes");
                       //     loadData();
                         Actualiser();
                                }catch(Exception e){
                                    JOptionPane.showMessageDialog(null, e);}

                        });
                        
                        // Modifier fournisseur et appel methide midifierFournisseur
                        editIcon.setOnMouseClicked((MouseEvent event) -> {
                            fournisseur f = new fournisseur();
                            f=table.getSelectionModel().getSelectedItem();
                          
                            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/ModifierFournisseur.fxml"));
                            try {
                                 loader.load();
                                
                            } catch (IOException ex) {
                                Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
                            }
                            
                            ModifierFournisseurController ModifierFournisseurController = loader.getController();
                        ModifierFournisseurController.setUpdate(true);
                            
                       ModifierFournisseurController.setTextField(f.getId_fournisseur(),f.getNom_fournisseur(),f.getMatricule_fiscale(),f.getDomaine_fournisseur(),f.getNum_tel_fournisseur(),f.getEmail_fournisseur(),f.getAdresse_fournisseur(), f.getLogo());


                       Parent root = loader.getRoot();
                            editIcon.getScene().setRoot(root);

                        });
                          /// finishes here Update fournisseur

                        HBox managebtn = new HBox(editIcon, deleteIcon);
                        managebtn.setStyle("-fx-alignment:center");
                        HBox.setMargin(deleteIcon, new Insets(2, 2, 0, 3));
                        HBox.setMargin(editIcon, new Insets(2, 3, 0, 2));

                        setGraphic(managebtn);

                        setText(null);

                    }
                }

            };// end Table Cell

            return cell;
        };
         editCol.setCellFactory(cellFoctory);
         /////
             table.setItems(list);
             
             /*
             ///Image
    
        Callback<TableColumn<fournisseur, String>, TableCell<fournisseur, String>> cellFactoryImage
                = //
                new Callback<TableColumn<fournisseur, String>, TableCell<fournisseur, String>>() {
            String path;

            @Override
            public TableCell call(final TableColumn<fournisseur, String> param) {
                final TableCell<fournisseur, String> cell = new TableCell<fournisseur, String>() {

                    @Override
                    public void updateItem(String item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                            setText(null);
                        } else {
                            path = item;

                            ImageView imagev = new ImageView(new Image(item));
                            imagev.setFitHeight(100);
                            imagev.setFitWidth(100);
                            setGraphic(imagev);
                            setText(null);
//                            System.out.println(item);
                        }
                    }
                };

                cell.setOnMouseClicked((MouseEvent event2)
                        -> {
                    if (event2.getClickCount() == 1) {
                        if (table.getSelectionModel().getSelectedItem() != null && !table.getSelectionModel().getSelectedItem().getLogo().contains("null")) {
                            Stage window = new Stage();
//
                            window.setMinWidth(250);
                            ImageView imagevPOPUP = new ImageView(new Image(table.getSelectionModel().getSelectedItem().getLogo()));
                            imagevPOPUP.setFitHeight(576);
                            imagevPOPUP.setFitWidth(1024);

                            VBox layout = new VBox(10);
                            layout.getChildren().addAll(imagevPOPUP);
                            layout.setAlignment(Pos.CENTER);

                            //Display window and wait for it to be closed before returning
                            Scene scene = new Scene(layout);
                            window.setScene(scene);
                            window.show();

                        }
                    }


                });

                return cell;
            }
        };

        logo.setCellFactory(cellFactoryImage);       
             ///// finish 
             */
             // search function 
              FilteredList<fournisseur> filteredData =new FilteredList<> (list , e->true);
              search.setOnKeyReleased((e) -> {
                  search.textProperty().addListener((observable, oldValue, newValue) -> {
                      filteredData.setPredicate((Predicate<fournisseur>) fs -> {
                          if(newValue == null || newValue.isEmpty())
                      { return true ;}
                          String LowerCaseFilter = newValue.toLowerCase();
                          if (fs.getNom_fournisseur().toLowerCase().contains(newValue)){
                          return true ; }
                          else if (fs.getDomaine_fournisseur().toLowerCase().contains(newValue))
                      {return true ;}
                          else if (fs.getMatricule_fiscale().toLowerCase().contains(newValue))
                      { return true ;}
                          return false ;
                      });
                  });
                  SortedList <fournisseur> sortedData = new SortedList<>(filteredData);
                  sortedData.comparatorProperty().bind(table.comparatorProperty());
                  table.setItems(sortedData);
              });
            
    }    

    
    
    
    
    @FXML
    private void Ajouter(ActionEvent event) {
         try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../gui/AjouterFournisseur.fxml"));
            Parent root = loader.load();
                        pane.getChildren().add(root);
          // TODO
       } catch (IOException ex) {
           Logger.getLogger(StockController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
        private void delete(ActionEvent event) throws SQLException {
          FournisseurService fr = new FournisseurService();
      fournisseur f = table.getSelectionModel().getSelectedItem();
      fournisseur frs = fr.rechercheFournisseur(f.getId_fournisseur());
     fr.supprimerFournisseur(frs.getId_fournisseur());
        Actualiser();
    }
     private void Actualiser() {
          FournisseurService fr = new FournisseurService();
        List fournisseur = fr.afficherFournisseur();
        ObservableList list = FXCollections.observableArrayList(fournisseur);
        table.setItems(list);
        nom_fournisseur.setCellValueFactory(new PropertyValueFactory<>("nom_fournisseur"));
        matricule_fiscale.setCellValueFactory(new PropertyValueFactory<>("matricule_fiscale"));
        domaine_fournisseur.setCellValueFactory(new PropertyValueFactory<>("domaine_fournisseur"));
        num_tel_fournisseur.setCellValueFactory(new PropertyValueFactory<>("num_tel_fournisseur"));
        email_fournisseur.setCellValueFactory(new PropertyValueFactory<>("email_fournisseur"));
         adresse_fournisseur.setCellValueFactory(new PropertyValueFactory<>("adresse_fournisseur"));
         logo.setCellValueFactory(new PropertyValueFactory<>("logo"));      
    }

    @FXML
    private void searching(KeyEvent event) {
    }
    
}
