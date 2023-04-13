package Views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import entities.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import services.UserServices;

public class GestionUserBackController implements Initializable {

    @FXML
    private TableView<User> tableuserback;
    @FXML
    private TableColumn<User, Integer> iduser;
    @FXML
    private TableColumn<User, String> nom;
    @FXML
    private TableColumn<User, String> prenom;
    @FXML
    private TableColumn<User, String> email;
    @FXML
    private TableColumn<User, String> adresse;
    @FXML
    private TableColumn<User, String> telephone;
    @FXML
    private TableColumn<User, String> motdepass;
    @FXML
    private TextField recherche;
    @FXML
    private Button pdf;

    private UserServices userServices;
    private ObservableList<User> users;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
    userServices = new UserServices();
    users = FXCollections.observableArrayList();
    tableuserback.setEditable(true);
    
    // Set up the table columns and their cell factories
    iduser.setCellValueFactory(new PropertyValueFactory<>("id"));
    nom.setCellValueFactory(new PropertyValueFactory<>("name"));
    prenom.setCellValueFactory(new PropertyValueFactory<>("prenomc"));
    email.setCellValueFactory(new PropertyValueFactory<>("email"));
    adresse.setCellValueFactory(new PropertyValueFactory<>("city"));
    telephone.setCellValueFactory(new PropertyValueFactory<>("num_tel"));

    motdepass.setCellValueFactory(new PropertyValueFactory<>("password"));

    nom.setCellFactory(TextFieldTableCell.forTableColumn());
    prenom.setCellFactory(TextFieldTableCell.forTableColumn());
    email.setCellFactory(TextFieldTableCell.forTableColumn());
    adresse.setCellFactory(TextFieldTableCell.forTableColumn());
    telephone.setCellValueFactory(new PropertyValueFactory<>("num_tel"));

    motdepass.setCellFactory(TextFieldTableCell.forTableColumn());

    // Populate the table with the user information
    users.addAll(userServices.listerUsers());
    tableuserback.setItems(users);
}

    

    

   @FXML
    private void OnEditNom(TableColumn.CellEditEvent<User, String> event) {
        User re=tableuserback.getSelectionModel().getSelectedItem();
        re.setName(event.getNewValue());
        UserServices reService = new UserServices();
        reService.modifierUser(re);
    }

    @FXML
    private void OnEditPrenom(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setPrenomc(event.getNewValue());
        userServices.modifierUser(user);
    }

   @FXML
    private void OnEditEmail(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setEmail(event.getNewValue());
        userServices.modifierUser(user);
    }

   @FXML
    private void OnEditAdresse(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setCity(event.getNewValue());
        userServices.modifierUser(user);
    }

   @FXML
    private void OnEdittel(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setNumTel(event.getNewValue());
        userServices.modifierUser(user);
    }

   @FXML
private void OnEditMdp(TableColumn.CellEditEvent<User, String> event) {
    User user = event.getRowValue();
    user.setPassword(event.getNewValue());
    userServices.modifierUser(user);
}

    @FXML
    private void recherche(ActionEvent event) {
    }

   @FXML

private void SupprimerResBack(ActionEvent event) {
    User user = tableuserback.getSelectionModel().getSelectedItem();
    UserServices userService = new UserServices();
    userService.supprimerUser(user);
    tableuserback.getItems().remove(user);
}



    @FXML
    private void excel(ActionEvent event) {
    }

    @FXML
    private void pdf(ActionEvent event) {
    }

    @FXML
    private void AfficheResBack(ActionEvent event) {
    }
    
}
