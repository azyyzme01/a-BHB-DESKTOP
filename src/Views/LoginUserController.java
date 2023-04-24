package Views;

import entities.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import services.LoginSession;
import services.UserServices;
import utils.ConnectionBD;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;

public class LoginUserController {

    @FXML
    private TextField email;

    @FXML
    private Button login;

    @FXML
    private PasswordField password;

    private Stage stage;
    private Scene scene;
    private Parent root;

    @FXML
    public void login(ActionEvent event) throws IOException {
        UserServices userServices = new UserServices();

        if (password.getText().trim().isEmpty()) {
            System.out.println("Password is required.");
        } else {
            String passwordHash = userServices.crypter_password(password.getText());
            String emailStr = email.getText();

            if (userServices.login(emailStr, passwordHash)) {
                if (Arrays.equals(LoginSession.roles, new String[]{"ROLE_ADMIN"})) {
                    root = FXMLLoader.load(getClass().getResource("GestionUserBack.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Dashboard");
                    stage.setScene(scene);
                    stage.show();
                } else {
                    root = FXMLLoader.load(getClass().getResource("FXMLFrontProfil.fxml"));
                    stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
                    scene = new Scene(root);
                    stage.setTitle("Profile");
                    stage.setScene(scene);
                    stage.show();
                }
            } else {
                System.out.println("Invalid email or password.");
            }
        }
    }
}
