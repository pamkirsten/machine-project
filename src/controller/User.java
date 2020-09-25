package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Case;
import model.Citizen;
import model.Database;
import model.Visit;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class User {

    @FXML private PasswordField regpass1;
    @FXML private PasswordField regpass2;
    @FXML private PasswordField oldpass;
    @FXML private TextField first;
    @FXML private TextField middle;
    @FXML private TextField last;
    @FXML private TextField home;
    @FXML private TextField work;
    @FXML private TextField phone;
    @FXML private TextField email;

    @FXML private DatePicker date;
    @FXML private DatePicker dateReported;
    @FXML private TextField code;
    private static int casenum;


    private Database db = new Database();
    private Visit dbv = new Visit();
    private Case dcase = new Case();


    private static String username;

    public void stringerror() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("User Input Error");
        alert.setContentText("Input cannot contain a space, colon, or comma!");
        alert.showAndWait();
    }

    public void passworderror(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Password Error");
        alert.setContentText(s);
        alert.showAndWait();
    }

    public boolean findspace(String s) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(s);
        boolean found = matcher.find();
        return found;
    }

    public boolean checkpassword() {
        if (regpass1.getText().length() < 6){
            passworderror("Password must be at least 6 characters!");
            return false;
        } else if (!checkpassvalid()) {
            passworderror("Password must contain a digit or a spchar!");
            return false;
        } else if (findspace(regpass1.getText()) || regpass1.getText().contains(":") || regpass1.getText().contains(",")){
            passworderror("Password contains invalid char!");
            return false;
        }
        return true;
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

    public boolean checkuserinfo() {
        if (first.getText().contains(":") || first.getText().contains(",") ||
                middle.getText().contains(":") || middle.getText().contains(",") ||
                last.getText().contains(":") || last.getText().contains(",") ||
                home.getText().contains(":") ||
                work.getText().contains(":") ||
                findspace(phone.getText()) || phone.getText().contains(":") || phone.getText().contains(",") ||
                findspace(email.getText()) || email.getText().contains(":") || email.getText().contains(",") ||
                findspace(regpass1.getText()) || regpass1.getText().contains(":") || regpass1.getText().contains(",") || checkpassword()){
            stringerror();
            return false;
        }
        return true;
    }

    public void setusername(String user){
        this.username = user;
    }

    public void saveaction(ActionEvent event){
        if (checkuserinfo()) {
            if (db.confirmpass(username, oldpass.getText())) {
                if (db.regpassword(regpass1.getText(), regpass2.getText())) {
                    Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
                    alert1.setHeaderText(null);
                    alert1.setTitle("Confirmation Dialog");
                    alert1.setContentText("Are you ok with this?");

                    Optional<ButtonType> result = alert1.showAndWait();
                    if (result.get() == ButtonType.OK) {
                        // ... user chose OK
                        db.updateacct(username, regpass1.getText(), first.getText(), middle.getText(), last.getText(), home.getText(), work.getText(), phone.getText(), email.getText());
                        closewindow(event);
                    } else {
                        // ... user chose CANCEL or closed the dialog
                    }
                } else {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setHeaderText(null);
                    alert.setTitle("Update Error");
                    alert.setContentText("Password not matched!");

                    alert.showAndWait();
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Update Error");
                alert.setContentText("Incorrect Password!");

                alert.showAndWait();
            }
        }
    }

    public void updatewindow(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/userupdateview.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Update User Information");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkincode(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/codeview.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Check-In");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void checkin(ActionEvent event){

        String temp = date.getValue().toString();
        String[] fdate = temp.split("-");
        temp = fdate[1] + "," + fdate[2] + "," + fdate[0];

        String temp1 = LocalTime.now().toString();
        String[] ftime = temp1.split(":");
        temp1 = ftime[0] + ftime[1];

        dbv.setTime(temp1);
        dbv.setDate(temp);
        dbv.setUser(username);
        dbv.setCode(code.getText());

        db.newvisit(dbv);

        closewindow(event);
    }

    public void goback(ActionEvent event){
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
    public void openReport(ActionEvent event){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/reportPositive.fxml"));
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

    public void reportPositive(ActionEvent event){
        String temp = dateReported.getValue().toString();
        String[] fdate = temp.split("-");
        temp = fdate[1] + "," + fdate[2] + "," + fdate[0];

        dcase.setUsername(username);
        dcase.setDateReported(temp);
        db.newcase(dcase);
        db.setPositive(username);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Positive Confirmation");
        alert.setContentText("\nYour Assigned Case Number is: " + db.getCaseNum(username) +"\n Date Reported: "+ db.getDateReported(username));
        alert.showAndWait();

        db.savecases();
    }


    public void testing(){
        System.out.println(username);
    }

    public void closewindow(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
