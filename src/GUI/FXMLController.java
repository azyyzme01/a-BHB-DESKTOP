/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;


import com.itextpdf.text.DocumentException;
import com.vonage.client.VonageClient;
import com.vonage.client.sms.MessageStatus;
import com.vonage.client.sms.SmsSubmissionResponse;
import com.vonage.client.sms.messages.TextMessage;
import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javaapplication7.entites.rdv;
import javaapplication7.entites.service;
import javaapplication7.services.CRUD;
import javaapplication7.services.CRUDRDV;
import javafx.animation.TranslateTransition;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Pagination;
import javafx.scene.control.ProgressBar;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.util.Duration;
/**
 * FXML Controller class
 *
 * @author USER
 */
public class FXMLController implements Initializable {

   private service selectedService;
    @FXML
    private TextField tftype;
    @FXML
    private CheckBox tffff;
    @FXML
    private Label mbshow;
    private TextField idsup;
    @FXML
    private AnchorPane tfdispo;
    @FXML
    private AnchorPane p2;
     @FXML
    private AnchorPane pr;
    @FXML
    private TextField dispo;
    @FXML
    private TextField type;
  
    @FXML
    private DatePicker date;
    @FXML
    private TextArea rason;
    @FXML
    private ChoiceBox<String> heure;
    @FXML
    private ChoiceBox<String> servi;
    @FXML
    private TableView<rdv> affrd;
  @FXML
    private ScrollBar scroule;
 @FXML
    private ChoiceBox<String> hc;
@FXML
    private Label st;
 @FXML
    private ImageView img;
@FXML
private TableColumn<rdv, Integer> id;

@FXML
private TableColumn<rdv, Date> d;

@FXML
private TableColumn<rdv, String> r;

@FXML
private TableColumn<rdv, String> h;

@FXML
private TableColumn<rdv, String> sr;
    @FXML
    private TableColumn<rdv, Void> ac;
      @FXML
    private VBox VBox;
   @FXML
    private TextField searchtype;  
      
   
    /**
     * Initializes the controller class.
     */
  

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        p2.setVisible(false);
pr.setVisible(true);
      
          TranslateTransition t= new TranslateTransition(Duration.seconds(1),VBox);
        t.setToX(VBox.getLayoutX()* (0));
        t.play();
          heure.getItems().addAll("12h30", "13h30", "14h");
            hc.getItems().addAll("12h30", "13h30", "14h");
       List<String> typesDeServices = CRUDRDV.getTypesDeServices();

        
// Ajoutez les types de services récupérés à la ChoiceBox "servi"
 servi.getItems().clear();      
servi.getItems().addAll(typesDeServices);
         
    }    

    @FXML
   private void ajouter(ActionEvent event) {
    service s= new service();
    if (tftype.getText() != null && tftype.getText().length() > 2) {
        s.setDispo(tffff.isSelected());
        s.setType(tftype.getText());
        CRUD cs= new CRUD();
        cs.ajouterservice2(s);
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Le service a été ajouté avec succès.");
        alert.showAndWait();
    } else {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Le champ 'type' doit contenir au moins 3 caractères.");
        alert.showAndWait();
    }
}


 @FXML
public void showsimple(ActionEvent event) {
    CRUD cs = new CRUD();
    VBox cardContainer = new VBox(); // conteneur pour les cartes des services
    cardContainer.setSpacing(20); // espacement entre les cartes
    cardContainer.setPadding(new Insets(20)); // marge autour du conteneur

    // Tableau de couleurs
    Color[] colors = {Color.RED, Color.ORANGE, Color.YELLOW, Color.GREEN, Color.BLUE, Color.INDIGO, Color.VIOLET};
    int colorIndex = 0;

    for (service s : cs.afficherservice()) {
        // Création de la carte pour chaque service
        Label typeLabel = new Label(s.getType());
        typeLabel.setStyle("-fx-font-weight: bold;"); // style pour le texte en gras
        Label dispoLabel = new Label(s.isDispo() ? "Disponible" : "Indisponible");
        dispoLabel.setStyle("-fx-font-style: italic;"); // style pour le texte en italique
        BorderPane card = new BorderPane();
        card.getStyleClass().add("backgrd");
        card.setTop(typeLabel);
        card.setCenter(dispoLabel);
        card.setAlignment(typeLabel, Pos.CENTER);
        card.setAlignment(dispoLabel, Pos.CENTER);

        // Attribution de la couleur à la carte
        card.setBackground(new Background(new BackgroundFill(colors[colorIndex], new CornerRadii(20), Insets.EMPTY)));
        card.setBorder(new Border(new BorderStroke(colors[colorIndex], BorderStrokeStyle.SOLID, new CornerRadii(20), BorderWidths.DEFAULT, Insets.EMPTY)));
        card.setPadding(new Insets(20));
card.setStyle( ";-fx-border-radius: 40px; -fx-padding: 40px;");
        // Incrémenter l'index de couleur
        colorIndex = (colorIndex + 1) % colors.length;

        // Création du bouton "Supprimer" et ajout de l'événement de clic correspondant
        Button deleteButton = new Button("Supprimer");
        deleteButton.setOnAction(e -> {
            cs.supprimerService(s.getId()); // supprimer le service de la base de données
            cardContainer.getChildren().remove(card); // supprimer la carte du conteneur
          
        });

        // Ajout du bouton "Supprimer" à la carte
        HBox hbox = new HBox(card, deleteButton);
        hbox.setAlignment(Pos.CENTER_LEFT);
        hbox.setSpacing(20);

        // Ajout de l'événement de clic sur chaque carte
        hbox.setOnMouseClicked(e -> {
            // Mettre à jour la variable selectedService avec les informations du service sélectionné
            selectedService = s;

            // Affecter les valeurs sélectionnées aux champs de texte correspondants
            type.setText(selectedService.getType());
            dispo.setText(selectedService.isDispo() ? "Disponible" : "Indisponible");
        });

        cardContainer.getChildren().add(hbox); // ajout de la carte et du bouton au conteneur
    }

    // Ajout du VBox contenant les cartes au ScrollPane
    ScrollPane scrollPane = new ScrollPane(cardContainer);
    scrollPane.setFitToWidth(true);

    // Configuration de la barre de défilement
    scroule.setOrientation(Orientation.VERTICAL);
    scroule.setUnitIncrement(10);
    scroule.setBlockIncrement(100);
    scroule.valueProperty().addListener((observable, oldValue, newValue) -> {
        scrollPane.setVvalue(newValue.doubleValue());
    });
    mbshow.setGraphic(scrollPane);
}

    private void supprimer(ActionEvent event) {
         int id = Integer.parseInt(idsup.getText()); // récupérer l'ID du service à supprimer
    CRUD cs = new CRUD();
    cs.supprimerService(id);
    }

    @FXML
    private void modifier(ActionEvent event) {
   
        CRUD cs = new CRUD();
    if (selectedService != null) {
        // Mettre à jour le service sélectionné avec les valeurs des champs de texte
        selectedService.setType(type.getText());
        selectedService.setDispo(dispo.getText().equalsIgnoreCase("Disponible"));

        // Appeler la méthode de mise à jour du service dans la base de données
        cs.modifierService(selectedService.getId(), selectedService);

        // Afficher un message de succès
        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Le service a été modifié avec succès.");
        alert.showAndWait();
    } else {
        // Afficher un message d'erreur si aucun service n'est sélectionné
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner un service à modifier.");
        alert.showAndWait();
    }

    }

  

 

    @FXML
    private void RdvAjout(ActionEvent event) {
          LocalDate localDate = date.getValue();
VonageClient client = VonageClient.builder().apiKey("8d2d6af6").apiSecret("X0b5NV4CMdugk9wE").build();
TextMessage message = new TextMessage("BFB",
        "21626411812",
        "votre rensezvous est pris avec succes"
);

SmsSubmissionResponse response = client.getSmsClient().submitMessage(message);

if (response.getMessages().get(0).getStatus() == MessageStatus.OK) {
    System.out.println("Message sent successfully.");
} else {
    System.out.println("Message failed with error: " + response.getMessages().get(0).getErrorText());
}
    // Vérifier que la date est non null et supérieure à la date système
    if (localDate == null || localDate.isBefore(LocalDate.now())) {
         Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Veuillez sélectionner date.");
        alert.showAndWait();
        return;
    }

    Date sqlDate = Date.valueOf(localDate);
    String selectedHeure = heure.getSelectionModel().getSelectedItem();
    String selectedService = servi.getSelectionModel().getSelectedItem();
    String selectedRaison = rason.getText();

    // Vérifier que tous les champs sont remplis
    if (selectedHeure == null || selectedService == null || selectedRaison.isEmpty()) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setContentText("Veuillez remplir tous les champs.");
        alert.showAndWait();
        return;
    }

    // Récupérer le service correspondant à la valeur sélectionnée dans la ChoiceBox "servi"
    service s = CRUD.getServiceByType(selectedService);

    // Créer un objet rdv avec les valeurs récupérées
    rdv r = new rdv();
    r.setDate(sqlDate);
    r.setHeure(selectedHeure);
    r.setS(s);
    r.setRaison(selectedRaison);

    // Ajouter le rdv à la base de données
    CRUDRDV crudRdv = new CRUDRDV();
    List<rdv> rdvs = crudRdv.afficherRDV();
    crudRdv.ajouterRDV(r, rdvs, sqlDate);

   Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setContentText("Rdv a été ajouter avec succès.");
         alert.showAndWait();
          int count = 0;
    for (rdv rd : rdvs) {
        if (rd.getDate().equals(sqlDate)) {
            count++;
        }
    }

   alert = new Alert(AlertType.INFORMATION);
    alert.setHeaderText(null);
    alert.setContentText("Rdv a été ajouté avec succès. Il y a " + count + " rendez-vous qui vous précèdent pour la date " + sqlDate.toString() + ".");
    alert.showAndWait();
    }

    @FXML
    private void affrdv(ActionEvent event) {
    //id.setCellValueFactory(new PropertyValueFactory<>("id"));
    d.setCellValueFactory(new PropertyValueFactory<>("date"));
    r.setCellValueFactory(new PropertyValueFactory<>("raison"));
    h.setCellValueFactory(new PropertyValueFactory<>("heure"));
    sr.setCellValueFactory(cellData -> new SimpleStringProperty(cellData.getValue().getS().getType()));
    
   // Initialise la colonne "Action" avec un bouton "Supprimer" pour chaque ligne
ac.setCellFactory(column -> {
    return new TableCell<rdv, Void>() {
        private final Button deleteButton = new Button("Supprimer");

        {
            // Action du bouton "Supprimer"
            deleteButton.setOnAction((ActionEvent event) -> {
                rdv r = getTableView().getItems().get(getIndex());
                CRUDRDV crudRdv = new CRUDRDV();
                crudRdv.supprimerRDV(r.getId());
                affrd.getItems().remove(r);
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(deleteButton);
                rdv r = getTableView().getItems().get(getIndex());
                // Si la date est égale à la date système, on colore la ligne en rouge
                if (r.getDate().toLocalDate().isBefore(LocalDate.now())) {
                    setStyle("-fx-background-color: red;");
                }
            }
        }
    };
});

// Charge les données de la table depuis la base de données
CRUDRDV crudRdv = new CRUDRDV();
List<rdv> rdvs = crudRdv.afficherRDV();
ObservableList<rdv> observableList = FXCollections.observableArrayList(rdvs);
affrd.setItems(observableList);

// Ajouter un listener pour sélectionner les champs et les afficher dans l'interface graphique
affrd.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
    if (newSelection != null) {
        date.setValue(newSelection.getDate().toLocalDate());
        rason.setText(newSelection.getRaison());
        heure.setValue(newSelection.getHeure());
        servi.setValue(newSelection.getS().getType());
    }
});

    }

  @FXML
private void modifierrrdv(ActionEvent event) {
    rdv r = affrd.getSelectionModel().getSelectedItem();
    // Mettre à jour les champs de l'objet avec les valeurs du formulaire
    r.setDate(Date.valueOf(date.getValue()));
    r.setRaison(rason.getText());
    r.setHeure(heure.getValue());
    // Récupérer l'objet Service correspondant au type sélectionné dans la ChoiceBox
    CRUD cs = new CRUD();
    service s = cs.getServiceByType(servi.getValue());
    // Mettre à jour l'objet RDV avec le Service correspondant
    r.setS(s);
    // Appeler la méthode de mise à jour du RDV dans la base de données
    CRUDRDV c = new CRUDRDV();
    c.modifierRDV(r.getId(), r);

    // Rafraîchir la table
    affrdv(event);
}
 
     @FXML
    private void trierrdv(ActionEvent event) {
       boolean croissant = true;
    CRUDRDV rd = new CRUDRDV();
    List<rdv> rdvsTries;

    rdvsTries = rd.trierRdvParDate(rd.afficherRDV(), croissant);
    ObservableList<rdv> observableRdvsTries = FXCollections.observableArrayList(rdvsTries);

    affrd.setItems(observableRdvsTries);

   // id.setCellValueFactory(new PropertyValueFactory<>("id"));
    d.setCellValueFactory(new PropertyValueFactory<>("date"));
    r.setCellValueFactory(new PropertyValueFactory<>("raison"));
    h.setCellValueFactory(new PropertyValueFactory<>("heure"));
   sr.setCellValueFactory(new PropertyValueFactory<>("s"));
  
    ac.setCellFactory(param -> new TableCell<rdv, Void>() {
        private final Button annulerButton = new Button("Supprimer");

        {
            annulerButton.setOnAction(event -> {
                rdv rdv = getTableView().getItems().get(getIndex());
                // implémentez la logique pour annuler le rdv
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(annulerButton);
            }
        }
    });
    

    }
    @FXML
    void cherhcherd(ActionEvent event) {
 CRUDRDV rd = new CRUDRDV();
    List<rdv> rdvsCherches;
    String heure = hc.getValue();
    rdvsCherches = rd.chercherRdvParDate(rd.afficherRDV(), heure);

    ObservableList<rdv> observableRdvsCherches = FXCollections.observableArrayList(rdvsCherches);
    affrd.setItems(observableRdvsCherches);

   // id.setCellValueFactory(new PropertyValueFactory<>("id"));
    d.setCellValueFactory(new PropertyValueFactory<>("date"));
    r.setCellValueFactory(new PropertyValueFactory<>("raison"));
    h.setCellValueFactory(new PropertyValueFactory<>("heure"));
    sr.setCellValueFactory(new PropertyValueFactory<>("s"));

    ac.setCellFactory(param -> new TableCell<rdv, Void>() {
        private final Button annulerButton = new Button("Supprimer");

        {
            annulerButton.setOnAction(event -> {
                rdv rdv = getTableView().getItems().get(getIndex());
                // implémentez la logique pour annuler le rdv
            });
        }

        @Override
        protected void updateItem(Void item, boolean empty) {
            super.updateItem(item, empty);
            if (empty) {
                setGraphic(null);
            } else {
                setGraphic(annulerButton);
            }
        }
    });
   
    }
   @FXML
void stat(ActionEvent event) {
    CRUDRDV rds=new CRUDRDV();
    Map<String, Integer> stats = rds.getStatistiquesRdvParTypeService();

    // Créer un PieChart avec les données de statistiques
    PieChart pieChart = new PieChart();
    ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();
    for (Map.Entry<String, Integer> entry : stats.entrySet()) {
        pieChartData.add(new PieChart.Data(entry.getKey() + " (" + entry.getValue() + ")", entry.getValue()));
    }
    pieChart.setData(pieChartData);

    // Afficher le PieChart et le nombre total de rendez-vous dans un Label
    int totalRdv = stats.values().stream().mapToInt(Integer::intValue).sum();
    String statsText = "Nombre total de rendez-vous : " + totalRdv + "\n\nStatistiques par type de service :\n";
    for (Map.Entry<String, Integer> entry : stats.entrySet()) {
        statsText += entry.getKey() + " : " + entry.getValue() + "\n";
    }
    st.setText(statsText);
    st.setGraphic(pieChart);
}

  @FXML
    void gr(ActionEvent event) {
       TranslateTransition t = new TranslateTransition(Duration.seconds(1), VBox);
    t.setToX(800); // déplace le VBox vers la droite de 200 pixels
    t.play();

    }
      @FXML
 void gs(ActionEvent event) {
    TranslateTransition t= new TranslateTransition(Duration.seconds(1),VBox);
        t.setToX(VBox.getLayoutX()* (0));
        t.play();

    }

  @FXML
    void  gotos(ActionEvent event) {

//pr.setVisible(false);
p2.setVisible(true);
    }
   
 @FXML
  void   gotoautre(ActionEvent event) {
p2.setVisible(false);
    }
  
   @FXML
    void imprimer(ActionEvent event) {
CRUDRDV r=new CRUDRDV();
        List<rdv> rdvs =r.afficherRDV();
        r.genererPDF(rdvs);
    }
   
@FXML
    void impstat(ActionEvent event) throws DocumentException {
CRUDRDV c=new CRUDRDV();

    try {
        c.generateStatistiquesRdvParTypeServicePDF();
        System.out.println("Le fichier  a été généré avec succès !");
    } catch (IOException e) {
        System.err.println("Erreur lors de la génération du fichier : " + e.getMessage());
    }
        
        
    }
    
    
     @FXML
    void rechtype(ActionEvent event) {
        
        

    }
    
    
    
    
    
 
}
