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
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Database;
import model.Tracer;

import java.io.IOException;
import java.util.Optional;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Tracer register.
 */
public class TracerRegister {

    private static final Tracer tracerdata = new Tracer();
    private static String username;
    private final Database database = new Database();
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
    private TextField phoneNumber;
    @FXML
    private TextField emailAdd;

    /**
     * Sets the username variable into the username of the logged in.
     *
     * @param s the username
     */
    public void setUsernameRegister(String s) {
        username = s;
    }

    /**
     * Displays the Contact Tracer Registration Menu
     *
     * @param event the event
     */
    public void registerTracer(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govInputTrc.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Register Contact Tracer");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.getIcons().add(new Image("/icon.png"));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays an Error Prompt based on the String input
     *
     * @param s the error input
     */
    public void stringerror(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Error");
        alert.setContentText(s);
        alert.showAndWait();
    }

    /**
     * Checks if a String contains a space character
     *
     * @param s the String
     * @return the result if the String contains a space
     */
    public boolean findspace(String s) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(s);
        return matcher.find();
    }

    /**
     * Checkuserinfo boolean.
     *
     * @return the boolean
     */
    public boolean checkuserinfo() {
        if (firstName.getText().contains(":") || firstName.getText().contains(",") ||
                middleName.getText().contains(":") || middleName.getText().contains(",") ||
                lastName.getText().contains(":") || lastName.getText().contains(",") ||
                homeAdd.getText().contains(":") ||
                txtCity.getText().contains(":") ||
                workAdd.getText().contains(":") ||
                findspace(phoneNumber.getText()) || phoneNumber.getText().contains(":") || phoneNumber.getText().contains(",") || (!phoneNumber.getText().matches("[0-9]+")) ||
                findspace(emailAdd.getText()) || emailAdd.getText().contains(":") || emailAdd.getText().contains(",")) {
            stringerror("Input contains invalid characters!");
            return false;
        }
        return true;
    }

    /**
     * Checks all the User Input's validity
     *
     * @return the result based on the input
     */
    public int checkUser() {
        if (findspace(txtfieldUsername.getText()) || txtfieldUsername.getText().contains(":") || txtfieldUsername.getText().contains(",")) { // Invalid Username
            labelcheckUser.setText("Username contains invalid char!");
        } else if (database.checkUsernameEqual(txtfieldUsername.getText())) { // Return 2 if Account Username is Unique
            labelcheckUser.setText("Username unique!");
            return 2;
        } else if (txtfieldUsername.getText().equals(username)) {
            labelcheckUser.setText("Username cannot use own!");
        } else { // Existing - Check if Already a Tracer or Not
            if (database.checkRole(txtfieldUsername.getText()) == 2) { // Return 0 if Account Username is already a Tracer
                labelcheckUser.setText("Username is already a Tracer account!");
                return 0;
            } else {
                labelcheckUser.setText("Username will be used to create new Tracer!");
                return 1; // Return 1 if Account Username exist but is not a Tracer
            }
        }
        return 0;
    }

    /**
     * Generates a random password which is 6 characters and contains at least
     * one numerical value or special character
     *
     * @return the password
     */
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

    /**
     * Creates a new Tracer Account
     *
     * @param event the event
     */
    public void createtracerAcc(ActionEvent event) {
        int tracerInstance = checkUser();

        if (tracerInstance == 0) { // Account is already a Tracer
            stringerror("Account is already a Tracer!");
        } else if (tracerInstance == 1) { // Account Username exists but not a Tracer
            tracerdata.setUsername(txtfieldUsername.getText());
            database.createTracer(tracerdata);
            backtoGov(event);
        } else if (tracerInstance == 2) { // Account Username unique
            String newPass = randompass();

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Password Confirmation");
            alert.setContentText("User : " + txtfieldUsername.getText() + "'s new password is: " + newPass);
            alert.showAndWait();

            tracerdata.setUsername(txtfieldUsername.getText());
            tracerdata.setPassword(newPass);

            registerTracer(event);
        }
    }

    /**
     * Saves the User Information Input during Tracer Registration
     *
     * @param event the event
     */
    public void savetracerregister(ActionEvent event) {
        Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
        alert1.setHeaderText(null);
        alert1.setTitle("Confirmation Dialog");
        alert1.setContentText("Are you sure with this?");

        Optional<ButtonType> result = alert1.showAndWait();
        if (result.get() == ButtonType.OK && checkuserinfo()) {
            tracerdata.setFirstName(firstName.getText());
            tracerdata.setMiddleName(middleName.getText());
            tracerdata.setLastName(lastName.getText());
            tracerdata.setHomeAdd(homeAdd.getText());
            tracerdata.sethCity(txtCity.getText());
            tracerdata.setWorkAdd(workAdd.getText());
            tracerdata.setPhoneNumber(phoneNumber.getText());
            tracerdata.setEmailAdd(emailAdd.getText());

            database.createAccount(tracerdata);
            backtoGov(event);
        }
    }

    /**
     * Display a Confirmation Prompt to cancel Registration
     *
     * @param event the event
     */
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

    /**
     * Returns the user to Government Menu
     *
     * @param event the event
     */
    public void backtoGov(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govMenu.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("COVID Tracker");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.getIcons().add(new Image("/icon.png"));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            close(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Closes the Event
     *
     * @param event the event
     */
    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
