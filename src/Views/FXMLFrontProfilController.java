package Views;

import entities.User;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import services.UserServices;

/**
 * FXML Controller class
 *
 */
public class FXMLFrontProfilController implements Initializable {

    @FXML
    private TableView<User> tableuserfront;
    @FXML
    private TableColumn<User, Integer> idp;
    @FXML
    private TableColumn<User, String> nomp;
    @FXML
    private TableColumn<User, String> prenomp;
    @FXML
    private TableColumn<User, String> emailp;
    @FXML
    private TableColumn<User, String> adressep;
    @FXML
    private TableColumn<User, String> nump;

    private UserServices userServices;
    private ObservableList<User> users;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userServices = new UserServices();
        users = FXCollections.observableArrayList();
        tableuserfront.setEditable(true);

        // Set up the table columns and their cell factories
        idp.setCellValueFactory(new PropertyValueFactory<>("id"));
        nomp.setCellValueFactory(new PropertyValueFactory<>("name"));
        prenomp.setCellValueFactory(new PropertyValueFactory<>("prenomc"));
        emailp.setCellValueFactory(new PropertyValueFactory<>("email"));
        adressep.setCellValueFactory(new PropertyValueFactory<>("city"));
        nump.setCellValueFactory(new PropertyValueFactory<>("numTel"));

        nomp.setCellFactory(TextFieldTableCell.forTableColumn());
        prenomp.setCellFactory(TextFieldTableCell.forTableColumn());
        emailp.setCellFactory(TextFieldTableCell.forTableColumn());
        adressep.setCellFactory(TextFieldTableCell.forTableColumn());
        nump.setCellFactory(TextFieldTableCell.forTableColumn());

        // Populate the table with the user information
        users.addAll(userServices.listerUsers());
        tableuserfront.setItems(users);
    }

    @FXML
    private void OnEditNomp(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setName(event.getNewValue());
        userServices.modifierUser(user);
    }

    @FXML
    private void OnEditPrenomp(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setPrenomc(event.getNewValue());
        userServices.modifierUser(user);
    }

    @FXML
    private void OnEditEmailp(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setEmail(event.getNewValue());
        userServices.modifierUser(user);
    }

    @FXML
    private void OnEditAdressep(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setCity(event.getNewValue());
        userServices.modifierUser(user);
    }

    @FXML
    private void OnEdittelp(TableColumn.CellEditEvent<User, String> event) {
        User user = event.getRowValue();
        user.setNumTel(event.getNewValue());
        userServices.modifierUser(user);
    }
}
