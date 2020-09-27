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
import model.Database;
import model.Government;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GovRegister {

    private static final Government newgovacc = new Government();
    private static String username;
    private final Database db = new Database();
    @FXML
    private TextField txtfieldUsername;
    @FXML
    private Label labelcheckUser;
    @FXML
    private TextField firstName;
    @FXML
    private TextField middleName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField homeAdd;
    @FXML
    private TextField txtCity;
    @FXML
    private TextField workAdd;
    @FXML
    private TextField phoneNum;
    @FXML
    private TextField emailAdd;

    public void setUsernameRegister(String s) {
        username = s;
    }

    public void registerGov(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govInputGov.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Register Contact Tracer");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void stringerror(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(s);
        alert.showAndWait();
    }

    public boolean findspace(String s) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    public boolean checkuserinfo() {
        if (firstName.getText().contains(":") || firstName.getText().contains(",") ||
                middleName.getText().contains(":") || middleName.getText().contains(",") ||
                lastName.getText().contains(":") || lastName.getText().contains(",") ||
                homeAdd.getText().contains(":") ||
                txtCity.getText().contains(":") ||
                workAdd.getText().contains(":") ||
                findspace(phoneNum.getText()) || phoneNum.getText().contains(":") || phoneNum.getText().contains(",") || (!phoneNum.getText().matches("[0-9]+")) ||
                findspace(emailAdd.getText()) || emailAdd.getText().contains(":") || emailAdd.getText().contains(",")) {
            stringerror("Input contains invalid characters!");
            return false;
        }
        return true;
    }

    public int checkUser() {
        if (findspace(txtfieldUsername.getText()) || txtfieldUsername.getText().contains(":") || txtfieldUsername.getText().contains(",")) { // Invalid Username
            labelcheckUser.setText("Username contains invalid char!");
        } else if (db.checkUsernameEqual(txtfieldUsername.getText())) { // Return 2 if Account Username is Unique
            labelcheckUser.setText("Username unique!");
            return 2;
        } else if (txtfieldUsername.getText().equals(username)) {
            labelcheckUser.setText("Username cannot use own!");
        } else { // Existing - Check if Already a Gov or Not
            if (db.checkRole(txtfieldUsername.getText()) == 1) { // Return 0 if Account Username is already an Official
                labelcheckUser.setText("Username is already an Official account!");
                return 0;
            } else {
                labelcheckUser.setText("Username will be used to create new Official!");
                return 1; // Return 1 if Account Username exist but is not an Official
            }
        }
        return 0;
    }

    public String randompass() {
        String alphaUpper = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String alphaLower = "abcdefghijklmnopqrstuvwxyz";
        String spChar = "%*^~!@#$%^&*(){}[];<>/?=-+";

        String password = "", temp = "";

        Random rand = new Random();
        int n, m;

        for (int i = 0; i < 5; i++) {
            m = rand.nextInt(2);
            n = rand.nextInt(26);
            switch (m) {
                case 0:
                    temp = String.valueOf(alphaUpper.charAt(n));
                    break;
                case 1:
                    temp = String.valueOf(alphaLower.charAt(n));
                    break;
            }
            password = password.concat(temp);
        }

        n = rand.nextInt(26);
        temp = String.valueOf(spChar.charAt(n));
        password = password.concat(temp);

        return password;
    }

    public void creategovAcc(ActionEvent event) {
        int govInstance = checkUser();

        if (govInstance == 0) { // Account is already an Official
            stringerror("Account is already an Official!");
        } else if (govInstance == 1) { // Account Username exists but not an Official
            newgovacc.setUsername(txtfieldUsername.getText());
            db.createOfficial(newgovacc);
            backtoGov(event);
        } else if (govInstance == 2) { // Account Username unique
            String newPass = randompass();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Password Confirmation");
            alert.setContentText("User : " + txtfieldUsername.getText() + "'s new password is: " + newPass);
            alert.showAndWait();

            newgovacc.setUsername(txtfieldUsername.getText());
            newgovacc.setPassword(newPass);

            registerGov(event);
        }
    }

    public void savegovregister(ActionEvent event) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText(null);
        alert1.setTitle("Confirmation Dialog");
        alert1.setContentText("Are you sure with this?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK && checkuserinfo()) {
            newgovacc.setFirstName(firstName.getText());
            newgovacc.setMiddleName(middleName.getText());
            newgovacc.setLastName(lastName.getText());
            newgovacc.setHomeAdd(homeAdd.getText());
            newgovacc.sethCity(txtCity.getText());
            newgovacc.setWorkAdd(workAdd.getText());
            newgovacc.setPhoneNumber(phoneNum.getText());
            newgovacc.setEmailAdd(emailAdd.getText());

            db.createAccount(newgovacc);
            backtoGov(event);
        }
    }

    public void cancelregister(ActionEvent event) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText(null);
        alert1.setTitle("Confirmation Dialog");
        alert1.setContentText("Cancel registration?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK) {
            backtoGov(event);
        }
    }

    public void backtoGov(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govMenu.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("COVID Tracker");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            close(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}