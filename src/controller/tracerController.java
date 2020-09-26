package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.ArrayList;

import model.Case;
import model.Database;
import model.Government;
import model.Visit;

public class tracerController {

    @FXML private TextField tsNum;
    @FXML private TextField tsX;

    private Database db = new Database();
    private Government dbg = new Government();
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
            showCases();

            closewindow(event);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showCases() {
        ArrayList<Case> cases;
        cases = dbg.getCases(username);

        for (int i = 0; i < cases.size(); i++){
            System.out.println("CASE NUM: " + cases.get(i).getCasenum());
        }
    }

    public void traceSpecific() {
        int xNum = Integer.parseInt(tsX.getText().trim());

        // Check if X is at least 0, if false, using 8 as default.
        if (tsX.getText().equals("") || xNum < 0){
            xNum = 8;

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setTitle("X Days Error");
            alert.setContentText("Input must be at least 0. Using 8 as default.");
            alert.showAndWait();
        }

        int casenumChk = 0;
        ArrayList<Case> cases;
        cases = dbg.getCases(username);

        // Check if the Case Number input belongs to the Contact Tracer
        for (int i = 0; i < cases.size(); i++){
            if (tsNum.getText().equals(Integer.toString(cases.get(i).getCasenum()))){
                casenumChk = 1;
            }
        }

        ArrayList<Visit> possiblyexposed = new ArrayList<>();

        if (casenumChk == 1){ // Trace Users
            db.traceUsers(tsNum.getText() , xNum);
        }

        // Sets the status of case to Traced
        //dbg.setTraced(tsNum.getText());

        // Print Possibly Exposed here
        for (int j = 0; j < possiblyexposed.size(); j++){
            System.out.println("USERNAME: " + possiblyexposed.get(j).getUser());
            System.out.println("CODE: " + possiblyexposed.get(j).getCode());
            System.out.println("DATE " + possiblyexposed.get(j).getDate());
            System.out.println("TIME " + possiblyexposed.get(j).getTime());
        }
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

    }

    public void goback(ActionEvent event) {
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

    public void closewindow(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

}
