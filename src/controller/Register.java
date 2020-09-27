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
    private Label labelUserChecker;
    @FXML
    private Label labelPasswordChecker;
    @FXML
    private TextField user;
    @FXML
    private TextField password1;
    @FXML
    private TextField password2;
    @FXML
    private TextField firstName;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField middleName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField homeAdd;
    @FXML
    private TextField workAdd;
    @FXML
    private TextField phoneAdd;
    @FXML
    private TextField Email;

    private Database database = new Database();

    private static Citizen citizen = new Citizen();

    public void stringerror() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Register Error");
        alert.setContentText("Input contains invalid characters!");
        alert.showAndWait();
    }

    public boolean findspace(String s) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(s);
        boolean found = matcher.find();
        return found;
    }

    public boolean checkInformation() {
        if (firstName.getText().contains(":") || firstName.getText().contains(",") ||
                middleName.getText().contains(":") || middleName.getText().contains(",") ||
                lastName.getText().contains(":") || lastName.getText().contains(",") ||
                homeAdd.getText().contains(":") ||
                txtCity.getText().contains(":") ||
                workAdd.getText().contains(":") ||
                findspace(phoneAdd.getText()) || phoneAdd.getText().contains(":") || phoneAdd.getText().contains(",") || (!phoneAdd.getText().matches("[0-9]+")) ||
                findspace(Email.getText()) || Email.getText().contains(":") || Email.getText().contains(",")) {
            stringerror();
            return false;
        }
        return true;
    }

    public void checkUsername() {
        if (findspace(user.getText()) || user.getText().contains(":") || user.getText().contains(",")){
            labelUserChecker.setText("Username contains invalid char!");
        } else if (database.regusername(user.getText())) {
            labelUserChecker.setText("Username unique!");
        } else {
            labelUserChecker.setText("Username not unique!");
        }
    }

    public boolean checkValidPass() {
        Pattern pass = Pattern.compile("[$&+;=\\\\?@#|/'<>^*()%!-]");
        Matcher m = pass.matcher(password1.getText());
        boolean result = m.find();

        if (result){
            return true;
        } else if (password1.getText().matches(".*\\d.*")) {
            return true;
        }
        return false;
    }

    public void checkPassword() {
        if (password1.getText().length() < 6){
            labelPasswordChecker.setText("Password must be at least 6 characters!");
        } else if (!checkValidPass()){
            labelPasswordChecker.setText("Password must contain a digit or a spchar!");
        } else if (findspace(password1.getText()) || password1.getText().contains(":") || password1.getText().contains(",")){
            labelPasswordChecker.setText("Password contains invalid char!");
        } else if (database.regpassword(password1.getText(), password2.getText())) {
            labelPasswordChecker.setText("Password matched!");
        } else {
            labelPasswordChecker.setText("Password not matched!");
        }
    }

    public void enterUserInfo(ActionEvent event) {
        checkUsername();
        checkPassword();

        if (labelUserChecker.getText().equals("Username unique!")) {
            if (labelPasswordChecker.getText().equals("Password matched!")) {
                Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                alert1.setHeaderText(null);
                alert1.setTitle("Confirmation Dialog");
                alert1.setContentText("Are you ok with this?");

                Optional<ButtonType> result = alert1.showAndWait();
                if (result.get() == ButtonType.OK) {
                    // ... user chose OK
                    citizen.setUsername(user.getText());
                    citizen.setPassword(password1.getText());
                    closewindow(event);
                    showNextWindow();
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

    public void showNextWindow() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainRegisterInput.fxml"));
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

    public void savingInfos(ActionEvent event) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText(null);
        alert1.setTitle("Confirmation Dialog");
        alert1.setContentText("Are you ok with this?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK && checkInformation()) {
            // ... user chose OK
            citizen.setFirstName(firstName.getText());
            citizen.setMiddleName(middleName.getText());
            citizen.setLastName(lastName.getText());
            citizen.setHomeAdd(homeAdd.getText());
            citizen.sethCity(txtCity.getText());
            citizen.setWorkAdd(workAdd.getText());
            citizen.setPhoneNumber(phoneAdd.getText());
            citizen.setEmailAdd(Email.getText());

            database.newAcct(citizen);

            mainMenu(event);
        } else {


        }
    }

    public void cancel(ActionEvent event) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText(null);
        alert1.setTitle("Confirmation Dialog");
        alert1.setContentText("Cancel registration?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK) {
            mainMenu(event);
        } else {
          // user closed the dialogue

        }
    }

    public void mainMenu(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainMenu.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("COVID Tracker");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
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
