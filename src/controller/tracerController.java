package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Case;

import model.Database;
import model.Visit;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;




public class tracerController {

    private Database db = new Database();
    private static String username;

    public static void setusername(String user){
        username = user;
    }

    public void showCases() {

        db.showCases(username);


    }

    public void traceSpecific() {

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
