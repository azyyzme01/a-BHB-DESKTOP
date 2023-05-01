/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package crudpi.GUI;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author ANAS
 */
public class Liaison_front_backController implements Initializable {

    @FXML
    private Button btnfront;
    @FXML
    private Button btnback;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void front(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("comptefront.fxml"));
       try{
       Parent root = loader.load(); 

        btnback.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
        
    }

    @FXML
    private void back(ActionEvent event) {
         FXMLLoader loader = new FXMLLoader(getClass().getResource("viewComptes.fxml"));
       try{
       Parent root = loader.load(); 

        btnback.getScene().setRoot(root);
       
       }catch(IOException ex){
       
        System.out.println(ex.getMessage());
    }
    }
    
}
