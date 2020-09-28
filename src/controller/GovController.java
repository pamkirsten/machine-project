package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Case;
import model.Database;

import java.io.IOException;
import java.util.ArrayList;

/**
 * The Government Controller.
 */
public class GovController {

    /** username of the user **/
    private static String username;
    /** Database of Accounts **/
    private final Database db = new Database();
    /** GovRegister Controller **/
    GovRegister temp = new GovRegister();
    /** TracerRegister Controller **/
    TracerRegister temp1 = new TracerRegister();

    /** Username to be Terminated **/
    @FXML
    private TextField usernameTerminate;
    /** City **/
    @FXML
    private TextField fieldCity;
    /** Start Date **/
    @FXML
    private DatePicker dateStart;
    /** End Date **/
    @FXML
    private DatePicker dateEnd;
    /** Start Date **/
    @FXML
    private DatePicker tuStart;
    /** End Date **/
    @FXML
    private DatePicker tuEnd;
    /** Case Number **/
    @FXML
    private TextField txtCase;
    /** Tracer Username **/
    @FXML
    private TextField txtTracerUN;
    /** Unassigned Cases **/
    @FXML
    private ListView<Integer> listUnassigned;
    /** Case Numbers **/
    @FXML
    private ListView<Integer> tuNum;
    /** Tracer Username **/
    @FXML
    private ListView<String> tuUser;
    /** Case Status **/
    @FXML
    private ListView<String> tuStatus;

    /**
     * Sets the username variable into the username of the logged in.
     *
     * @param user the username
     */
    public static void setusername(String user) {
        username = user;
    }

    /**
     * Displays the Main Menu of the Program
     *
     * @param event the event
     */
    public void mainmenu(ActionEvent event) {
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
     * Returns the User to the Government Menu
     *
     * @param event the event
     */
    public void backtoGov(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govMenu.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Government Menu");
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
     * Displays the Government Menu
     *
     * @param event the event
     */
    public void opencreateGov(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govCreateGov.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Create Official");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.getIcons().add(new Image("/icon.png"));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            temp.setUsernameRegister(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the Create Tracer Menu
     */
    public void opencreateTracer() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govCreateTracer.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Create Tracer");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.getIcons().add(new Image("/icon.png"));
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
            temp1.setUsernameRegister(username);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Displays the Terminate Accounts Menu
     *
     * @param event the event
     */
    public void openTerminate(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govTerminate.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Terminate Account");
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

    /**
     * Displays the Contact Tracing Updates Menu
     *
     * @param event the event
     */
    public void openTracingUpdates(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govUpdates.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Contact Tracing Updates");
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
     * Displays Analytics Menu
     *
     * @param event the event
     */
    public void openAnalytics(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govAnalytics.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Analytics");
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
     * Displays all the Positive Cases from a given date range
     */
    public void tracingUpdates() {
        tuNum.getItems().clear();
        tuUser.getItems().clear();
        tuStatus.getItems().clear();
        ArrayList<Case> cases;

        cases = db.getPositiveFromDateRange(tuStart, tuEnd);

        for (int i = 0; i < cases.size(); i++) {
            tuNum.getItems().add(cases.get(i).getCasenum());
            tuUser.getItems().add(cases.get(i).getTracerUsername());
            tuStatus.getItems().add(cases.get(i).getStatus());
        }
    }

    /**
     * Displays the Unassigned Cases Menu
     *
     * @param event the event
     */
    public void openUnassigned(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govUnassigned.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Show Unassigned Cases");
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
     * Displays the list of all the positive cases who have not been
     * assigned to a Contact Tracer
     */
    public void showUnassigned() {
        listUnassigned.getItems().clear();

        ArrayList<Case> unassigned;

        unassigned = db.getUnassignedCases();

        for (int i = 0; i < unassigned.size(); i++) {
            listUnassigned.getItems().add(unassigned.get(i).getCasenum());
        }
    }

    /**
     * Assigns a Positive Case to a Contact Tracer
     */
    public void assignCase() {
        ArrayList<Case> unassigned;

        unassigned = db.getUnassignedCases();
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

                    check = 1;
                    db.updateCasesFile();
                } else {
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setHeaderText(null);
                    alert.setTitle("Confirmation");
                    alert.setContentText("USER IS NOT A TRACER!");
                    alert.showAndWait();

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
        }
    }

    /**
     * Displays the Analytics based on the given Duration and City
     */
    public void durandCity() {
        int numofCases = 0;
        numofCases = db.checkDurAndCity(fieldCity.getText(), dateStart, dateEnd);
        String str1 = Integer.toString(numofCases);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Number of Positive Cases");
        alert.setContentText("Number of positive cases in the given city & duration: " + str1);
        alert.showAndWait();

    }

    /**
     * Displays the Analytics based on the given City
     */
    public void cityCases() {
        int numofCases = 0;
        numofCases = db.checkCityCases(fieldCity.getText());
        String str1 = Integer.toString(numofCases);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Number of Positive Cases");
        alert.setContentText("Number of positive cases in the given city: " + str1);
        alert.showAndWait();

    }

    /**
     * Displays the Analytics based on the given Duration
     */
    public void durationCases() {
        int numofCases = 0;
        numofCases = db.givenDuration(dateStart, dateEnd);
        String str1 = Integer.toString(numofCases);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Number of Positive Cases");
        alert.setContentText("Number of positive cases in the duration: " + str1);
        alert.showAndWait();
    }

    /**
     * Displays an Error Prompt based on the given String
     *
     * @param s error message
     */
    public void alertmessage(String s) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Confirmation");
        alert.setContentText(s);
        alert.showAndWait();
    }

    /**
     * Terminates an Account into a Citizen
     */
    public void confirmTermination(ActionEvent event) {
        int check = 0;
        if (usernameTerminate.getText().equals(username)) {
            alertmessage("Cannot Terminate own Account!");
        } else {
            check = db.removeAccount(usernameTerminate.getText());
            if (check == 0) {
                alertmessage("Account has NOT been Terminated!");
            } else {
                alertmessage("Account has been successfully Terminated!");
            }
        }
    }
}