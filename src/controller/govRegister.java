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
import model.Citizen;
import model.Database;
import model.Government;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class govRegister {

    @FXML
    private TextField txtfieldUsername;
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
    private TextField txtCity;
    @FXML
    private TextField email;

    private Database db = new Database();

    private static Government newuser = new Government();


    public void checkUser() {

        if (db.checkRole(txtfieldUsername.getText()) != 1) {
            labelcheckUser.setText("Username unique!");


        } else {
            labelcheckUser.setText("Username not unique!");

        }

    }

    public String randompass() {
        return "passwordtest";

    }

    public void creategovAcc(ActionEvent event) {

        String newPass = randompass();

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Password Confirmation");
        alert.setContentText("User : "+txtfieldUsername.getText()+"'s new password is: " + newPass);
        alert.showAndWait();


        newuser.setUsername(txtfieldUsername.getText());
        newuser.setPassword(newPass);

        if(db.checkRole(txtfieldUsername.getText()) != 0){

           registerGov(event);

        }

        else{
            db.newacct(newuser);
            closewindow(event);
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
        if (result.get() == ButtonType.OK) {
            // ... user chose OK
            newuser.setFirstname(first.getText());
            newuser.setMiddlename(middle.getText());
            newuser.setLastname(last.getText());
            newuser.setHomeadress(home.getText());
            newuser.sethCity(txtCity.getText());
            newuser.setWorkadress(work.getText());
            newuser.setPhonenum(phone.getText());
            newuser.setEmail(email.getText());

            db.newacct(newuser);
            goback(event);

        } else {
            // ... user chose CANCEL or close the dialog

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

    public void closewindow(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
