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
import model.Case;
import model.Database;
import model.Government;
import java.io.IOException;
import java.util.ArrayList;

public class govController {

    private final Database db = new Database();
    private final Government dgov = new Government();

    @FXML private TextField txtfieldUsername;
    @FXML private Label labelcheckUser;

    @FXML private TextField usernameTerminate;
    @FXML private TextField fieldCity;
    @FXML private DatePicker dateStart;
    @FXML private DatePicker dateEnd;
    @FXML private DatePicker tuStart;
    @FXML private DatePicker tuEnd;
    @FXML private TextField txtCase;
    @FXML private TextField txtTracerUN;

    @FXML private ListView listUnassigned;

    @FXML private ListView tuNum;
    @FXML private ListView tuUser;
    @FXML private ListView tuStatus;

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

    public void mainmenu(ActionEvent event) {
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

    public void goback(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govMenu.fxml"));
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

    public void registerGov(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/registerGov.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("User Menu");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
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
            stage.initStyle(StageStyle.UNDECORATED);
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
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openTerminate(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/terminate.fxml"));
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

    public void closewindow(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    public void openTracingUpdates(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/tracingupdates.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Contact Tracing Updates");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            closewindow(event);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openAnalytics(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/analytics.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Analytics");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            closewindow(event);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tracingUpdates() {
        tuNum.getItems().clear();
        tuUser.getItems().clear();
        tuStatus.getItems().clear();
        ArrayList<Case> cases;

        cases = db.positivefromdaterange(tuStart, tuEnd);

        for (int i = 0; i < cases.size(); i++) {

            tuNum.getItems().add(cases.get(i).getCasenum());
            tuUser.getItems().add(cases.get(i).getTracerUsername());
            tuStatus.getItems().add(cases.get(i).getStatus());

            System.out.println("Case Num: " + cases.get(i).getCasenum());
            System.out.println("Contact Tracer: " + cases.get(i).getTracerUsername());
            System.out.println("Status: " + cases.get(i).getStatus());
            System.out.println("-------------------POSITIVE-------------------------");
        }

    }

    public void openUnassigned(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/unassignedMenu.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Show Unassigned Cases");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();

            closewindow(event);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void showUnassigned() {
        listUnassigned.getItems().clear();

        ArrayList<Case> unassigned = new ArrayList<>();

        unassigned = db.unassignedCases();

        System.out.println("-------------------UNASSIGNED CASES-------------------------");
        for (int i = 0; i < unassigned.size(); i++) {
            System.out.println("Case Num: " + unassigned.get(i).getCasenum());
            listUnassigned.getItems().add(unassigned.get(i).getCasenum());
        }
    }

    public void assignCase() {

        ArrayList<Case> unassigned = new ArrayList<>();

        unassigned = db.unassignedCases();
        check = 0;

        int caseNum = Integer.parseInt(txtCase.getText());

        for (int i = 0; i < unassigned.size(); i++) {
            if (caseNum == unassigned.get(i).getCasenum()) {
                if (db.checkRole(txtTracerUN.getText()) == 2) {
                    unassigned.get(i).setTracerUsername(txtTracerUN.getText());
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Confirmation");
                    alert.setContentText("TRACER HAS BEEN SUCCESSFULLY ASSIGNED!");
                    alert.showAndWait();

                    //System.out.println("SUCCESSFULLY ASSIGNED!");
                    check = 1;
                    db.savecases();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Confirmation");
                    alert.setContentText("USER IS NOT A TRACER!");
                    alert.showAndWait();

                    //System.out.println("USER IS NOT A TRACER!");
                    check = 2;
                }
            }
        }
        if (check == 0) {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmation");
            alert.setContentText("USER ALREADY HAS A TRACER ASSIGNED!");
            alert.showAndWait();

            //System.out.println("USER ALREADY HAS A TRACER ASSIGNED!");
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
        alert.setContentText("Number of positive cases in the duration: " + str1);
        alert.showAndWait();


    }

    public void confirmTermination(ActionEvent event) {
        int check;

        check = db.removeAccount(usernameTerminate.getText());

        //updatefiles

        if (check == 1) {

        } else {

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setHeaderText(null);
            alert.setTitle("Confirmation");
            alert.setContentText("Account has not been Sucessfully Terminated");
            alert.showAndWait();
        }

        closewindow(event);

    }


}



