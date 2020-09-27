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

import java.io.IOException;
import java.util.ArrayList;

public class GovController {

    GovRegister temp = new GovRegister();
    TracerRegister temp1 = new TracerRegister();
    private final Database db = new Database();

    private static String username;

    @FXML
    private TextField usernameTerminate;
    @FXML
    private TextField fieldCity;
    @FXML
    private DatePicker dateStart;
    @FXML
    private DatePicker dateEnd;
    @FXML
    private DatePicker tuStart;
    @FXML
    private DatePicker tuEnd;
    @FXML
    private TextField txtCase;
    @FXML
    private TextField txtTracerUN;
    @FXML
    private ListView<Integer> listUnassigned;
    @FXML
    private ListView<Integer> tuNum;
    @FXML
    private ListView<String> tuUser;
    @FXML
    private ListView<String> tuStatus;

    public static void setusername(String user) {
        username = user;
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

            closewindow(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opencreateGov(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govCreateGov.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("User Menu");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            temp.setUsernameRegister(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opencreateTracer() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govCreateTracer.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("User Menu");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            temp1.setUsernameRegister(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void openTerminate(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govTerminate.fxml"));
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
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govUpdates.fxml"));
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
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govAnalytics.fxml"));
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
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govUnassigned.fxml"));
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
        int check = 0;

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
                    db.saveCases();
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

    public void alertmessage(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmation");
        alert.setContentText(s);
        alert.showAndWait();
    }

    public void confirmTermination(ActionEvent event) {
        int check = 0;
        if (usernameTerminate.getText().equals(username)) {
            alertmessage("Cannot Terminate own Account!");
        } else {
            check = db.removeAccount(usernameTerminate.getText());
            if (check == 0) {
                alertmessage("Account has NOT been Terminated!");
            }
        }

        closewindow(event);
    }
}