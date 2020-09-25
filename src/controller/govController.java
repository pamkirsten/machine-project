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

public class govController {


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
    @FXML
    private Label labelNumofCases;


    // private String username = txtfieldUsername.getText();
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

    public void creategovAcc(ActionEvent event) {
        String newpass = "empty";
        if (check == 1) {

            dgov.setUsername(txtfieldUsername.getText());
            dgov.setPassword(randompass());
            if (db.checkRole(txtfieldUsername.getText()) != 0) {
                db.newgov(dgov);
            } else if (db.checkRole(txtfieldUsername.getText()) == 0) {
                registerGov(event);
            }

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

            closewindow(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void registerGov(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/registerGov.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("User Menu");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opencreateGov(ActionEvent event) {
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
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/createTracer.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("User Menu");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openTerminate() {

    }

    public void closewindow(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
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

    public void durandCity() {
        int numofCases = 0;
        numofCases = db.durAndCity(fieldCity.getText(), dateStart, dateEnd);
        String str1 = Integer.toString(numofCases);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Number of Positive Cases");
        alert.setContentText("Number of positive cases in the given city & duration: " + str1);
        alert.showAndWait();

    }

    public void cityCases() {
        int numofCases = 0;
        numofCases = db.CityCases(fieldCity.getText());
        String str1 = Integer.toString(numofCases);
        //labelNumofCases.setText(str1);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Number of Positive Cases");
        alert.setContentText("Number of positive cases in the given city: " + str1);
        alert.showAndWait();


    }

    public void durationCases() {

        int numofCases = 0;
        numofCases = db.givenDuration(dateStart, dateEnd);
        String str1 = Integer.toString(numofCases);
        // labelNumofCases.setText(str1);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Number of Positive Cases");
        alert.setContentText("Number of postivie cases in the duration: " + str1);
        alert.showAndWait();


    }


}



