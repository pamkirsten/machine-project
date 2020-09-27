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

import model.Tracer;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class tracerRegister {

    private static final Tracer newuser = new Tracer();
    private final Database db = new Database();

    @FXML private TextField txtfieldUsername;
    @FXML private Label labelcheckUser;
    @FXML private TextField first;
    @FXML private TextField middle;
    @FXML private TextField last;
    @FXML private TextField home;
    @FXML private TextField txtCity;
    @FXML private TextField work;
    @FXML private TextField phone;
    @FXML private TextField email;

    public void registerTracer(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/registerTracer.fxml"));
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
        boolean found = matcher.find();
        return found;
    }

    public boolean checkuserinfo() {
        if (first.getText().contains(":") || first.getText().contains(",") ||
                middle.getText().contains(":") || middle.getText().contains(",") ||
                last.getText().contains(":") || last.getText().contains(",") ||
                home.getText().contains(":") ||
                txtCity.getText().contains(":") ||
                work.getText().contains(":") ||
                findspace(phone.getText()) || phone.getText().contains(":") || phone.getText().contains(",") || (!phone.getText().matches("[0-9]+")) ||
                findspace(email.getText()) || email.getText().contains(":") || email.getText().contains(",")) {
            stringerror("Input contains invalid characters!");
            return false;
        }
        return true;
    }

    public int checkUser() {
        if (findspace(txtfieldUsername.getText()) || txtfieldUsername.getText().contains(":") || txtfieldUsername.getText().contains(",")){ // Invalid Username
            labelcheckUser.setText("Username contains invalid char!");
        } else if (db.regusername(txtfieldUsername.getText())) { // Return 2 if Account Username is Unique
            labelcheckUser.setText("Username unique!");
            return 2;
        } else { // Existing - Check if Already a Tracer or Not
            labelcheckUser.setText("Username will be used to create new Tracer!");
            if (db.checkRole(txtfieldUsername.getText()) == 1){ // Return 0 if Account Username is already a Tracer
                return 0;
            } else {
                return 1; // Return 1 if Account Username exist but is not a Tracer
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

    public void createtracerAcc(ActionEvent event) {
        int tracerInstance = checkUser();

        if (tracerInstance == 0){ // Account is already a Tracer
            stringerror("Account is already a Tracer!");
        } else if (tracerInstance == 1){ // Account Username exists but not a Tracer
            newuser.setUsername(txtfieldUsername.getText());
            db.newtracer(newuser);
            goback(event);
        } else if (tracerInstance == 2){ // Account Username unique
            String newPass = randompass();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Password Confirmation");
            alert.setContentText("User : " + txtfieldUsername.getText() + "'s new password is: " + newPass);
            alert.showAndWait();

            newuser.setUsername(txtfieldUsername.getText());
            newuser.setPassword(newPass);

            registerTracer(event);
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
