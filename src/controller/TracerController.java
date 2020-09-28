package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Case;
import model.Establishment;
import model.Government;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The Tracer Controller
 */
public class TracerController {

    /** Exposed Establishments **/
    private static ArrayList<Establishment> exposed = new ArrayList<>();
    /** Username **/
    private static String username;
    /** Government **/
    private final Government government = new Government();
    /** Case Number **/
    @FXML
    private TextField tsNum;
    /** Days before Date Reported **/
    @FXML
    private TextField tsX;
    /** Cases **/
    @FXML
    private ListView<Integer> listCases;
    /** Usernames **/
    @FXML
    private ListView<String> tsUser;
    /** Establishment Codes **/
    @FXML
    private ListView<String> tsCode;
    /** Dates **/
    @FXML
    private ListView<String> tsDate;
    /** Times **/
    @FXML
    private ListView<String> tsTime;

    /**
     * Sets the username variable into the username of the logged in.
     *
     * @param user the username
     */
    public static void setusername(String user) {
        username = user;
    }

    /**
     * Displays the Show Cases Menu
     *
     * @param event the event
     */
    public void viewShowCases(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/tracerShow.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Show Cases");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();

            close(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays all the Cases assigned to the logged in Contact Tracer
     */
    public void showCases() {
        listCases.getItems().clear();
        ArrayList<Case> cases;
        cases = government.getCases(username);

        for (int i = 0; i < cases.size(); i++) {
            listCases.getItems().add(cases.get(i).getCasenum());
        }
    }

    /**
     * Displays a listing of all Citizens who have been in contact
     * with the given Case Number
     */
    public void traceSpecific() {
        tsUser.getItems().clear();
        tsCode.getItems().clear();
        tsDate.getItems().clear();
        tsTime.getItems().clear();

        int xNum = 8;

        // Check if User has already been Traced
        // Check if X is at least 0, if false, using 8 as default.
        if (government.checkIfTraced(Integer.parseInt(tsNum.getText()))) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Case Error");
            alert.setContentText("Case has already been traced!");
            alert.showAndWait();
        } else if (tsX.getText() == null || tsX.getText().trim().isEmpty() || Integer.parseInt(tsX.getText().trim()) < 0) {
            xNum = 8;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Input Error");
            alert.setContentText("Error. Using 8 as default!");
            alert.showAndWait();
        } else {
            xNum = Integer.parseInt(tsX.getText().trim());
        }

        int casenumChk = 0;
        ArrayList<Case> cases;
        cases = government.getCases(username);

        // Check if the Case Number input belongs to the Contact Tracer
        for (int i = 0; i < cases.size(); i++) {
            if (tsNum.getText().equals(Integer.toString(cases.get(i).getCasenum()))) {
                casenumChk = 1;
            }
        }

        ArrayList<Establishment> possiblyexposed = new ArrayList<>();

        if (casenumChk == 1) { // Trace Users
            possiblyexposed = government.getRecords(tsNum.getText(), xNum);
        }

        // Sets the status of case to Traced
        government.setTraced(tsNum.getText());

        // Print Possibly Exposed here
        for (int j = 0; j < possiblyexposed.size(); j++) {
            String code = possiblyexposed.get(j).getEstCode();
            tsUser.getItems().add(possiblyexposed.get(j).getUsername());
            tsCode.getItems().add(code);
            tsDate.getItems().add(possiblyexposed.get(j).getCheckInDate());
            tsTime.getItems().add(possiblyexposed.get(j).getCheckInTime());
        }

        exposed = possiblyexposed;
    }

    /**
     * Displays the Trace Specific Menu
     *
     * @param event the event
     */
    public void viewTraceSpecific(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/tracerTrace.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Trace Specific User");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();

            close(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Inform all citizens who have been in contact with the Positive Case
     */
    public void informCitizens() {
        for (int i = 0; i < exposed.size(); i++) {
            government.notifyUsers(exposed.get(i).getUsername(), exposed.get(i).getEstCode(), exposed.get(i).getCheckInDate());
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("INFORM CITIZENS");
        alert.setContentText("Citizens have been informed.");
        alert.showAndWait();

        exposed.clear();
    }

    /**
     * Displays the Main Menu of the Program
     *
     * @param event the event
     */
    public void mainMenu(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainMenu.fxml"));
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
     * Returns the User to the Tracer Menu
     *
     * @param event the event
     */
    public void backTracerMenu(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/tracerMenu.fxml"));
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
     * Closes the event
     *
     * @param event the event
     */
    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
