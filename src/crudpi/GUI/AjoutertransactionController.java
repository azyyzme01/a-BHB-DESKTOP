/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudpi.GUI;

import crudpi.entities.Transaction;
import crudpi.entities.comptesBancaire;
import crudpi.services.compteBancaireCRUD;
import crudpi.services.transactionCRUD;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author ANAS
 */
public class AjoutertransactionController implements Initializable {

    @FXML
    private Label tfcompte_source_id;
    @FXML
    private Label tfnom;
    @FXML
    private Label tfprenom;
    @FXML
    private Label tfemail;
    @FXML
    private Label tfnum_tlfn;
    @FXML
    private Label tf_compte_destination;
    @FXML
    private Label tfmontant;
    @FXML
    private Button btnajoutertransaction;
    @FXML
    private Button btnmodifier_transaction;
    @FXML
    private Label idtf;
    @FXML
    private TextField idf;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    private boolean isFieldNotEmpty(String field) {
    return field != null && !field.isEmpty();
    }
     private boolean validateForm() {
         int compte_source_id = Integer.parseInt(tfcompte_source_id.getText());
         String nom = tfnom.getText();
        String prenom= tfprenom.getText();
        String email = tfemail.getText();
        int num_tlfn = Integer.parseInt(tfnum_tlfn.getText());
        int compte_destination = Integer.parseInt(tf_compte_destination.getText());
        float montant = Float.valueOf(tfmontant.getText());
        
        
        if  (!isFieldNotEmpty(nom) || !isFieldNotEmpty(prenom) || !isFieldNotEmpty(email))
                 {
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Form");
            alert.setHeaderText(null);
            alert.setContentText("All fields must be filled!");
            alert.showAndWait();
            return false;
        }
        return true;
        
        
    }
     
         public void showtransaction(){
         FXMLLoader loader = new FXMLLoader(getClass().getResource("viewTransactions.fxml"));
       try{
       Parent root = loader.load(); 

        btnajoutertransaction.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
   }

    @FXML
    private void ajoutertransaction(ActionEvent event) {
          if(validateForm()){
        int compte_source_id = Integer.parseInt(tfcompte_source_id.getText());
        String nom = tfnom.getText();
        String prenom= tfprenom.getText();
        String email = tfemail.getText();
        int num_tlfn = Integer.parseInt(tfnum_tlfn.getText());
         int compte_destination = Integer.parseInt(tf_compte_destination.getText());
         float montant = Float.valueOf(tfmontant.getText());
         if(montant<0f || num_tlfn<0){
           Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Form");
            alert.setHeaderText(null);
            alert.setContentText("solde_initial or num_tlfn invalid");
            alert.showAndWait(); 
        }
        else{
        Transaction t = new Transaction(compte_source_id,nom,prenom,email,num_tlfn,compte_destination,montant);
        System.out.println(t);
        transactionCRUD tran = new transactionCRUD();
        
        tran.addEntity(t);
        showtransaction();
        }
        }
        else{
            System.out.println("Invalid champ");
        }
         
    }

    @FXML
   
     
    public void modifier_transaction(ActionEvent event){
        
        int id = Integer.parseInt(idf.getText());
       int compte_source_id = Integer.parseInt(tfcompte_source_id.getText());
       String nom = tfnom.getText();
        String prenom= tfprenom.getText();
        String email = tfemail.getText();
        int num_tlfn = Integer.parseInt(tfnum_tlfn.getText());
        int compte_destination = Integer.parseInt(tf_compte_destination.getText());
        float montant = Float.valueOf(tfmontant.getText());
       
         if(montant<0f || num_tlfn<0){
           Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Validate Form");
            alert.setHeaderText(null);
            alert.setContentText("solde_initial or num_tlfn invalid");
            alert.showAndWait(); 
        }
        else{
        Transaction t = new Transaction(compte_source_id,nom,prenom,email,num_tlfn,compte_destination,montant);
        transactionCRUD tran = new transactionCRUD();
        tran.updateEntity(id,compte_source_id,nom,prenom,email,num_tlfn,compte_destination,montant);
        }
        
    }
}
