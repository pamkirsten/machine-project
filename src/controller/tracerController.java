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
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;
import javafx.stage.StageStyle;
import model.Case;
import model.Government;
import model.Visit;

public class tracerController {

    @FXML private TextField tsNum;
    @FXML private TextField tsX;

    @FXML private ListView<Integer> listCases;
    @FXML private ListView<String> tsUser;
    @FXML private ListView<String> tsCode;
    @FXML private ListView<String> tsDate;
    @FXML private ListView<String> tsTime;

    private final Government dbg = new Government();
    private static ArrayList<Visit> exposed = new ArrayList<>();
    private static String username;

    public static void setusername(String user){
        username = user;
    }

    public void viewShowCases(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/showCases.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Show Cases");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();
            //showCases();

            closewindow(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCases() {
        listCases.getItems().clear();
        ArrayList<Case> cases;
        cases = dbg.getCases(username);

        for (int i = 0; i < cases.size(); i++){
            listCases.getItems().add(cases.get(i).getCasenum());
            System.out.println("CASE NUM: " + cases.get(i).getCasenum());
        }
    }

    public void traceSpecific() {

        tsUser.getItems().clear();
        tsCode.getItems().clear();
        tsDate.getItems().clear();
        tsTime.getItems().clear();

        int xNum = Integer.parseInt(tsX.getText().trim());

        // Check if X is at least 0, if false, using 8 as default.
        if (tsX.getText().isEmpty() || xNum < 0){
            xNum = 8;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("Input Error");
            alert.setContentText("Error. Using 8 as default!");
            alert.showAndWait();
        }

        int casenumChk = 0;
        ArrayList<Case> cases;
        cases = dbg.getCases(username);

        System.out.println("\n\n\nREACHED HERE\n\n\n");
        System.out.println(cases.size());

        // Check if the Case Number input belongs to the Contact Tracer
        for (int i = 0; i < cases.size(); i++){
            if (tsNum.getText().equals(Integer.toString(cases.get(i).getCasenum()))){
                casenumChk = 1;
            }
        }

        ArrayList<Visit> possiblyexposed = new ArrayList<>();

        if (casenumChk == 1){ // Trace Users
            possiblyexposed = dbg.getRecords(tsNum.getText(), xNum);
        }

        // Sets the status of case to Traced
        dbg.setTraced(tsNum.getText());

        // Print Possibly Exposed here
        for (int j = 0; j < possiblyexposed.size(); j++){
            String code = possiblyexposed.get(j).getCode();
            tsUser.getItems().add(possiblyexposed.get(j).getUser());
            tsCode.getItems().add(code);
            tsDate.getItems().add(possiblyexposed.get(j).getDate());
            tsTime.getItems().add(possiblyexposed.get(j).getTime());

            System.out.println("USERNAME: " + possiblyexposed.get(j).getUser());
            System.out.println("CODE: " + possiblyexposed.get(j).getCode());
            System.out.println("DATE " + possiblyexposed.get(j).getDate());
            System.out.println("TIME " + possiblyexposed.get(j).getTime());
        }

        exposed = possiblyexposed;
    }

    public void viewTraceSpecific(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/viewTraceSpecific.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Trace Specific User");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();

            closewindow(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void informCitizens() {
        for (int i = 0; i < exposed.size(); i++){
            dbg.notifyUsers(exposed.get(i).getUser(), exposed.get(i).getCode(), exposed.get(i).getDate());
        }

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("INFORM CITIZENS");
        alert.setContentText("Citizens have been informed.");
        alert.showAndWait();

        exposed.clear();
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

    public void backTracerMenu(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/tracerMenu.fxml"));
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
