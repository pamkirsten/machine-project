package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Citizen;
import model.Database;

import java.io.IOException;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register {

    @FXML
    private Label labelcheckuser;
    @FXML
    private Label labelcheckpass;
    @FXML
    private TextField reguser;
    @FXML
    private TextField regpass1;
    @FXML
    private TextField regpass2;
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

    private Database db = new Database();

    private static Citizen newuser = new Citizen();

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

    public void checkusername() {
        if (findspace(reguser.getText()) || reguser.getText().contains(":") || reguser.getText().contains(",")){
            labelcheckuser.setText("Username contains invalid char!");
        } else if (db.regusername(reguser.getText())) {
            labelcheckuser.setText("Username unique!");
        } else {
            labelcheckuser.setText("Username not unique!");
        }
    }

    public boolean checkpassvalid() {
        Pattern pass = Pattern.compile("[$&+;=\\\\?@#|/'<>^*()%!-]");
        Matcher m = pass.matcher(regpass1.getText());
        boolean result = m.find();

        if (result){
            return true;
        } else if (regpass1.getText().matches(".*\\d.*")) {
            return true;
        }
        return false;
    }

    public void checkpassword() {
        if (regpass1.getText().length() < 6){
            labelcheckpass.setText("Password must be at least 6 characters!");
        } else if (!checkpassvalid()){
            labelcheckpass.setText("Password must contain a digit or a spchar!");
        } else if (findspace(regpass1.getText()) || regpass1.getText().contains(":") || regpass1.getText().contains(",")){
            labelcheckpass.setText("Password contains invalid char!");
        } else if (db.regpassword(regpass1.getText(), regpass2.getText())) {
            labelcheckpass.setText("Password matched!");
        } else {
            labelcheckpass.setText("Password not matched!");
        }
    }

    public void inputinfo(ActionEvent event) {
        checkusername();
        checkpassword();

        if (labelcheckuser.getText().equals("Username unique!")) {
            if (labelcheckpass.getText().equals("Password matched!")) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setHeaderText(null);
                alert1.setTitle("Confirmation Dialog");
                alert1.setContentText("Are you ok with this?");

                Optional<ButtonType> result = alert1.showAndWait();
                if (result.get() == ButtonType.OK) {
                    // ... user chose OK
                    newuser.setUsername(reguser.getText());
                    newuser.setPassword(regpass1.getText());
                    closewindow(event);
                    shownextwindow();
                } else {
                    // ... user chose CANCEL or closed the dialog
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Register Error");
                alert.setContentText("Password not matched!");

                alert.showAndWait();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Register Error");
            alert.setContentText("Use unique username!");

            alert.showAndWait();
        }
    }

    public void shownextwindow() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/userinformationview.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Register User Information");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void saveaction(ActionEvent event) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText(null);
        alert1.setTitle("Confirmation Dialog");
        alert1.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK && checkuserinfo()) {
            // ... user chose OK
            newuser.setFirstname(first.getText());
            newuser.setMiddlename(middle.getText());
            newuser.setLastname(last.getText());
            newuser.setHomeadress(home.getText());
            newuser.setWorkadress(work.getText());
            newuser.setPhonenum(phone.getText());
            newuser.setEmail(email.getText());

            db.newacct(newuser);

            goback(event);
        } else {
            // ... user chose CANCEL or closed the dialog

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
