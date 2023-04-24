package Views;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import java.io.FileOutputStream;
import java.util.ArrayList;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;


import entities.User;
import java.io.FileOutputStream;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.print.Printer;
import javafx.print.PrinterJob;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.shape.Circle;
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
    UserServices reService = new UserServices();

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
        
        
        UserServices evc = new UserServices();



        List<User> liste = evc.listerUsers();
        ObservableList<User> olist = FXCollections.observableArrayList(liste);
        try{

            tableuserback.setItems(olist);
            FilteredList<User> filtredData = new FilteredList<>(olist, b -> true);
            recherche.textProperty().addListener((observable, olValue, newValue)->{
                filtredData.setPredicate(person-> {
                    if(newValue == null|| newValue.isEmpty()){
                        return true;
                    }
                    String lowerCaseFilter= newValue.toLowerCase();

                    if(person.getName().toLowerCase().indexOf(lowerCaseFilter)!=-1){
                        return true;
                    }
                    else if(String.valueOf(person.getPrenomc()).indexOf(lowerCaseFilter)!=-1)
                        return true;
                    else
                        return false;
                });
            });
            SortedList<User> sortedData = new SortedList<User>(filtredData);
            sortedData.comparatorProperty().bind(tableuserback.comparatorProperty());
            tableuserback.setItems(sortedData);

        }catch(Exception e){
            System.out.println(e.getMessage());

        }
        
        
        
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
        
        String filename = "C:\\Users\\Azyyzme01\\Desktop\\PI\\DESKTOP\\TryCrud\\Reclamation.xlsx";
        try
        {
            //creating an instance of HSSFWorkbook class
            HSSFWorkbook workbook = new HSSFWorkbook();
            //invoking creatSheet() method and passing the name of the sheet to be created
            HSSFSheet sheet = workbook.createSheet("List Users");
            //creating the 0th row using the createRow() method
            HSSFRow rowhead = sheet.createRow((short)0);
            //creating cell by using the createCell() method and setting the values to the cell by using the setCellValue() method
            rowhead.createCell((short)0).setCellValue("ID");
            rowhead.createCell(Short.parseShort("1")).setCellValue("First Name");
            rowhead.createCell(Short.parseShort("2")).setCellValue("Last Name");
            rowhead.createCell(Short.parseShort("3")).setCellValue("Email");
            rowhead.createCell(Short.parseShort("4")).setCellValue("Adresse");
            for(int i=0;i<reService.listerUsers().size();i++){
                HSSFRow row = sheet.createRow((short)i+1);
                //inserting data in the first row
                row.createCell(Short.parseShort("0")).setCellValue(reService.listerUsers().get(i).getId());
                row.createCell(Short.parseShort("1")).setCellValue(reService.listerUsers().get(i).getName());
                row.createCell(Short.parseShort("2")).setCellValue(reService.listerUsers().get(i).getPrenomc());
                row.createCell(Short.parseShort("3")).setCellValue(reService.listerUsers().get(i).getEmail());
                row.createCell(Short.parseShort("4")).setCellValue(reService.listerUsers().get(i).getCity());
            }
            //creating the 1st row


            FileOutputStream fileOut = new FileOutputStream(filename);
            workbook.write(fileOut);
            //closing the Stream
            fileOut.close();
            //closing the workbook

            //prints the message on the console
            System.out.println("Excel file has been generated successfully.");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    
        
        
        
    }

    @FXML
    private void pdf(ActionEvent event) {
        
        PrinterJob job = PrinterJob.createPrinterJob();

        Node root = this.tableuserback;

        if (job != null) {
            job.showPrintDialog(root.getScene().getWindow()); // Window must be your main Stage
            Printer printer = job.getPrinter();
            Node node = new Circle(400, 800, 800);

            boolean success = job.printPage(tableuserback);
            if (success) {
                job.endJob();
            }
        }
    }

    @FXML
    private void AfficheResBack(ActionEvent event) {
    }
    
}
