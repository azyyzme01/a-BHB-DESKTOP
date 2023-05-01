/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudpi.GUI;

import java.net.URL;
import java.util.List;
import crudpi.services.qrcode;
import com.google.zxing.WriterException;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import crudpi.entities.comptesBancaire;
import crudpi.services.compteBancaireCRUD;
import crudpi.services.qrcode;
import java.io.IOException;
import static java.nio.file.Files.delete;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
/**
 * FXML Controller class
 *
 * @author ANAS
 */
public class ViewComptesController implements Initializable {

    @FXML
     TableView<comptesBancaire> tableview;
    @FXML
     TableColumn<comptesBancaire, Integer> col1;
    @FXML
     TableColumn<comptesBancaire, String> col2;
    @FXML
     TableColumn<comptesBancaire, String> col3;
    @FXML
     TableColumn<comptesBancaire, Integer> col5;
    @FXML
     TableColumn<comptesBancaire, Float> col6;
    @FXML
     TableColumn<comptesBancaire, String> col4;
    @FXML
    private Button addBtn;
    @FXML
    private TableColumn<comptesBancaire, Void> delete;
    @FXML
    private Button pdf;
    @FXML
    private TextField recherche;
    @FXML
    private Button btnvoirtransaction;
    @FXML
    private Button btnmenu;
   

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
   
       
 List<comptesBancaire> comptesBancaireList = compteBancaireCRUD.getAll();
    

    // Add data to the table
    ObservableList<comptesBancaire> data = FXCollections.observableArrayList(comptesBancaireList);
 col1.setCellValueFactory(new PropertyValueFactory<comptesBancaire, Integer>("id")); 
    col2.setCellValueFactory(new PropertyValueFactory<comptesBancaire, String>("nom"));
    col3.setCellValueFactory(new PropertyValueFactory<comptesBancaire, String>("prenom"));
    col4.setCellValueFactory(new PropertyValueFactory<comptesBancaire, String>("email"));
    col5.setCellValueFactory(new PropertyValueFactory<comptesBancaire, Integer>("num_tlfn"));
    col6.setCellValueFactory(new PropertyValueFactory<comptesBancaire, Float>("solde_initial"));
// blocage.setCellValueFactory(new PropertyValueFactory<comptesBancaire, Boolean>("blolc"));


        col6.setCellFactory(column -> new TableCell<comptesBancaire, Float>() {
            @Override
            protected void updateItem(Float balance, boolean empty) {
                super.updateItem(balance, empty);
                if (empty) {
                    setText("");
                    setStyle("");
                } else {
                    setText(String.valueOf(balance));
                    if (balance < 10) {
                        setStyle("-fx-text-fill: red;");
                    } else {
                        setStyle("");
                    }
                }
            }
        });
    
 col1.setCellFactory(column -> new TableCell<comptesBancaire, Integer>() {
        @Override
        protected void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText("");
                setStyle("");
            } else {
                setText(String.valueOf(item));
                if (getTableView().getItems().get(getIndex()).getSolde_initial() < 10) {
                    setStyle("-fx-text-fill: red;");
                } else {
                    setStyle("");
                }
            }
        }
    });
  col2.setCellFactory(column -> new TableCell<comptesBancaire, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText("");
                setStyle("");
            } else {
                setText(item);
                if (getTableView().getItems().get(getIndex()).getSolde_initial() < 10) {
                    setStyle("-fx-text-fill: red;");
                } else {
                    setStyle("");
                }
            }
        }
    });
    col3.setCellFactory(column -> new TableCell<comptesBancaire, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText("");
                setStyle("");
            } else {
                setText(item);
                if (getTableView().getItems().get(getIndex()).getSolde_initial() < 10) {
                    setStyle("-fx-text-fill: red;");
                } else {
                    setStyle("");
                }
            }
        }
    });
     col4.setCellFactory(column -> new TableCell<comptesBancaire, String>() {
        @Override
        protected void updateItem(String item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText("");
                setStyle("");
            } else {
                setText(item);
                if (getTableView().getItems().get(getIndex()).getSolde_initial() < 10) {
                    setStyle("-fx-text-fill: red;");
                } else {
                    setStyle("");
                }
            }
        }
    });
 col5.setCellFactory(column -> new TableCell<comptesBancaire, Integer>() {
        @Override
        protected void updateItem(Integer item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setText("");
                setStyle("");
            } else {
                setText(String.valueOf(item));
                if (getTableView().getItems().get(getIndex()).getSolde_initial() < 10) {
                    setStyle("-fx-text-fill: red;");
                } else {
                    setStyle("");
                }
            }
        }
    });
  tableview.setItems(data);
  
    tableview.setItems(data);

    // Create new TableColumn for QR code
    TableColumn<comptesBancaire, Void> qrCodeCol = new TableColumn<>("QR Code");
    qrCodeCol.setPrefWidth(100);
    qrCodeCol.setSortable(false);

    // Set cellFactory for QR code column to display QR code for each reservation
    qrCodeCol.setCellFactory(new Callback<TableColumn<comptesBancaire, Void>, TableCell<comptesBancaire, Void>>() {
        @Override
        public TableCell<comptesBancaire, Void> call(TableColumn<comptesBancaire, Void> param) {
            final TableCell<comptesBancaire, Void> cell = new TableCell<comptesBancaire, Void>() {
                private final Button btnqr = new Button("QR Code");
                {
                    btnqr.setOnAction((ActionEvent event) -> {
                        // Retrieve reservation information from the database
                        comptesBancaire res = getTableView().getItems().get(getIndex());

                        String compteinfo = " identifiant: " + res.getId() + "\n nom: " + res.getNom() + "\n prenom: " + res.getPrenom()+
                                 "\n solde_initial: " + res.getSolde_initial();

                        // Generate QR code
                        qrcode qrCodeGenerator = new qrcode();
                        ImageView qrCodeImageView = null;
                        qrCodeImageView = qrCodeGenerator.generateQRCode(compteinfo);

                        // Create new stage
                        Stage qrCodeStage = new Stage();
                        qrCodeStage.setTitle("Reservation QR Code");

                        // Check if qrCodeImageView is null
                        if (qrCodeImageView != null) {
                            // Add ImageView object to the scene
                            Scene scene = new Scene(new Group(qrCodeImageView));
                            qrCodeStage.setScene(scene);

                            // Show the new stage
                            qrCodeStage.show();
                        }
                    });
                }
            @Override
            public void updateItem(Void item, boolean empty) {
                super.updateItem(item, empty);
                if (empty) {
                    setGraphic(null);
                } else {
                    setGraphic(btnqr);
                }
            }
        };
        return cell;
    }
});

// Add QR code column to the table
tableview.getColumns().add(qrCodeCol);
    
Callback<TableColumn<comptesBancaire, Void>, TableCell<comptesBancaire, Void>> cellFactory = new Callback<TableColumn<comptesBancaire, Void>, TableCell<comptesBancaire, Void>>() {
    @Override
    public TableCell<comptesBancaire, Void> call(final TableColumn<comptesBancaire, Void> param) {
        final TableCell<comptesBancaire, Void> cell = new TableCell<comptesBancaire, Void>() {
            private final Button btn = new Button("Supprimer");
            {
                btn.setOnAction((ActionEvent event) -> {
                    int rowIndex = getTableRow().getIndex();
                    compteBancaireCRUD pcd = new compteBancaireCRUD();
                    if (tableview.getItems().get(rowIndex).isBl()) {
                          btn.setStyle("-fx-background-color: blue; -fx-text-fill: white;");
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setTitle("Blocage");
                        alert.setHeaderText(null);
                        alert.setContentText("Le compte est bloqué");
                        alert.showAndWait();
                    } else {
                       pcd.deleteEntity(col1.getCellObservableValue(rowIndex).getValue());
                    }
                });
              
            }                   
                  @Override
        public void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(btn);
                comptesBancaire cmpb= getTableView().getItems().get(getIndex());
                if (cmpb.isBl()) {
                   btn.setStyle("-fx-background-color: blue; -fx-text-fill: white;");

                }
            }
        }
                };
               return cell;
            }
        };
       delete.setCellFactory(cellFactory);
        
        
}

    @FXML
         public void showAdd(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("ajouter_comptebancaire.fxml"));
       try{
       Parent root = loader.load(); 

        addBtn.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }

    @FXML
    private void pdf(ActionEvent event) {
         PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.tableview;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            Node node = new Circle(400, 800, 800);

            boolean success = job.printPage(tableview);
            if (success) {
                job.endJob();
            }
        }
    }

    @FXML
    private void recherche(ActionEvent event) {
        
        compteBancaireCRUD evc = new compteBancaireCRUD();

    List<comptesBancaire> liste = evc.entitiesList();
    ObservableList<comptesBancaire> olist = FXCollections.observableArrayList(liste);
    try {

        tableview.setItems(olist);
        FilteredList<comptesBancaire> filtredData = new FilteredList<>(olist, b -> true);
        recherche.textProperty().addListener((observable, olValue, newValue) -> {
            filtredData.setPredicate(person -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();

                if (person.getNom().toLowerCase().indexOf(lowerCaseFilter) != -1) {
                    return true;
                } else if (String.valueOf(person.getPrenom()).indexOf(lowerCaseFilter) != -1)
                    return true;
                else
                    return false;
            });
        });
        SortedList<comptesBancaire> sortedData = new SortedList<comptesBancaire>(filtredData);
        sortedData.comparatorProperty().bind(tableview.comparatorProperty());
        tableview.setItems(sortedData);

    } catch (Exception e) {
        System.out.println(e.getMessage());

    }
    }

    @FXML
    private void voirtransaction(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("viewTransactions.fxml"));
       try{
       Parent root = loader.load(); 

        btnvoirtransaction.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
    }

    @FXML
    private void menu(ActionEvent event) {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("liaison_front_back.fxml"));
       try{
       Parent root = loader.load(); 

        btnmenu.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
    }
}


   

    



   

 
    

