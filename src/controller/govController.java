package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Database;
import model.Government;
import javafx.fxml.Initializable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class govController implements Initializable{


    private Database db = new Database();
    private Government dgov = new Government();


    @FXML
    private TextField txtfieldUsername;
    @FXML
    private Button btnCheckUser;
    @FXML
    private Button btnCreate;
    @FXML
    private Label labelPass;
    @FXML
    private Label labelcheckUser;

    @FXML
    private TextField first;
    @FXML
    private TextField middle;
    @FXML
    private TextField last;
    @FXML
    private TextField home;
    @FXML
    private TextField work;
    @FXML
    private TextField phone;
    @FXML
    private TextField email;
    @FXML
    private TextField fieldCity;
    @FXML
    private DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;



    private String username = txtfieldUsername.getText();
    private int check;


    public void checkUser() {

        if (db.regusername(txtfieldUsername.getText())) {
            labelcheckUser.setText("Username unique!");
            check = 1;
        } else {
            labelcheckUser.setText("Username not unique!");

        }

    }

    public String randompass() {
        return "passwordtest";

    }

    public void creategovAcc() {
        String newpass = "empty";
        if (check == 1) {

            dgov.setUsername(username);
            dgov.setPassword(randompass());
            //dcase.setDateReported(temp);
            db.newgov(dgov);


        }




    }



    public boolean checkuserinfo() {
        if (first.getText().contains(":") || first.getText().contains(",") ||
                middle.getText().contains(":") || middle.getText().contains(",") ||
                last.getText().contains(":") || last.getText().contains(",") ||
                home.getText().contains(":") ||
                work.getText().contains(":") ||
                findspace(phone.getText()) || phone.getText().contains(":") || phone.getText().contains(",") ||
                findspace(email.getText()) || email.getText().contains(":") || email.getText().contains(",")) {
            stringerror();
            return false;
        }
        return true;
    }

    public void stringerror() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Register Error");
        alert.setContentText("Input cannot contain a space, colon, or comma!");
        alert.showAndWait();
    }

    public boolean findspace(String s) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(s);
        boolean found = matcher.find();
        return found;
    }

    public void saveaction(ActionEvent event) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText(null);
        alert1.setTitle("Confirmation Dialog");
        alert1.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK && checkuserinfo()) {
            // ... user chose OK
            dgov.setFirstname(first.getText());
            dgov.setMiddlename(middle.getText());
            dgov.setLastname(last.getText());
            dgov.setHomeadress(home.getText());
            dgov.setWorkadress(work.getText());
            dgov.setPhonenum(phone.getText());
            dgov.setEmail(email.getText());

            db.newgov(dgov);

            goback(event);
        } else {
            // ... user chose CANCEL or closed the dialog

        }
    }

    public void goback(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/sample.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("COVID Tracker");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();

            //closewindow(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void closeaction(ActionEvent event) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText(null);
        alert1.setTitle("Confirmation Dialog");
        alert1.setContentText("Cancel registration?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            goback(event);
        } else {
            // ... user chose CANCEL or closed the dialog

        }
    }


    public void opencreateGov() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/createGov.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("User Menu");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opencreateTracer() {

    }

    public void openTerminate() {

    }

    public void closewindow(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }

    public void openAnalytics(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/analytics.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Register User");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            closewindow(event);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opendurandCity(){




    }


    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
    }
}



