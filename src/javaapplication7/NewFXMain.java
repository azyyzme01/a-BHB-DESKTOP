/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javaapplication7;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.application.Application;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author USER
 */
public class NewFXMain extends Application {
    
    @Override
    public void start(Stage primaryStage) {
        try {
             String musicFile = "C:\\Users\\USER\\Downloads\\m.mp3";
               Media sound = new Media(new File(musicFile).toURI().toString());
        
        // création d'un objet MediaPlayer à partir du Media
        MediaPlayer mediaPlayer = new MediaPlayer(sound);
        
        // démarrage de la lecture
        mediaPlayer.play();
        
        // arrêt de la lecture après 10 secondes
        mediaPlayer.setOnEndOfMedia(() -> {
            mediaPlayer.stop();
        });
            Parent root =FXMLLoader.load(getClass().getResource("../GUI/FXML.fxml"));
            Scene scence= new Scene(root);
           
            primaryStage.setScene(scence);
            primaryStage.setTitle("Ajouter Service");
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(NewFXMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }   
}