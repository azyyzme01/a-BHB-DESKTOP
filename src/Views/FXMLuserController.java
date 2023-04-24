package Views;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import entities.User;
import services.UserServices;
import org.mindrot.jbcrypt.BCrypt;

public class FXMLuserController implements Initializable {

    @FXML
    private Button signin;
    @FXML
    private TextField prenomf;
    @FXML
    private TextField telf;
    @FXML
    private TextField mdpf;
    @FXML
    private TextField emailf;
    @FXML
    private TextField adressef;
    @FXML
    private Label nomctrl;
    @FXML
    private Label prenomctrl;
    @FXML
    private Label adressectrl;
    @FXML
    private Label passwordctrl;
    @FXML
    private Label telctrl;
    @FXML
    private Label emailctrl;
    @FXML
    private TextField Nomf;

    private UserServices userService = new UserServices();

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void signup(ActionEvent event) {
        // contrôler les champs obligatoires
        boolean isValid = true;
        if (Nomf.getText().isEmpty()) {
            nomctrl.setText("Le nom est obligatoire");
            isValid = false;
        } else {
            nomctrl.setText("");
        }
        if (prenomf.getText().isEmpty()) {
            prenomctrl.setText("Le prénom est obligatoire");
            isValid = false;
        } else {
            prenomctrl.setText("");
        }
        if (emailf.getText().isEmpty() || !emailf.getText().contains("@")) {
            emailctrl.setText("L'email est obligatoire et doit contenir @");
            isValid = false;
        } else {
            emailctrl.setText("");
        }
        if (mdpf.getText().isEmpty() || mdpf.getText().length() < 6) {
            passwordctrl.setText("Le mot de passe est obligatoire et doit contenir au moins 6 caractères");
            isValid = false;
        }
        else {
            passwordctrl.setText("");
        }
        if (telf.getText().isEmpty()) {
            telctrl.setText("Le téléphone est obligatoire");
            isValid = false;
        } else {
            telctrl.setText("");
        }
        if (adressef.getText().isEmpty()) {
            adressectrl.setText("L'adresse est obligatoire");
            isValid = false;
        } else {
            adressectrl.setText("");
        }
        
        if (!isValid) {
            return;
        }
        
        // Créer un objet User avec les valeurs saisies
        User user = new User();
         

        user.setName(Nomf.getText());
        user.setPrenomc(prenomf.getText());
        user.setNumTel((telf.getText()));
        user.setEmail(emailf.getText());
        user.setCity(adressef.getText());
        
        // Encrypt password
        String hashedPassword = BCrypt.hashpw(mdpf.getText(), BCrypt.gensalt());
        user.setPassword(hashedPassword);

        // Ajouter l'utilisateur
        userService.ajouteruser(user);
        
        // Afficher un message de confirmation
        System.out.println("Utilisateur ajouté avec succès");
    }
}
