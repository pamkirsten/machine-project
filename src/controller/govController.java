package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.Stage;
import model.Database;
import model.Government;


import java.io.IOException;

public class govController {


    private Database db = new Database();
    private Government dgov = new Government();



    @FXML
    private TextField txtfieldUsername;
    @FXML
    private Button btnCheckUser;
    @FXML
    private Button btnCreate;
    @FXML
    private Label labelPass;
    @FXML
    private Label labelcheckUser;


    public int checkUser(TextField txtfieldUsername) {

        if (db.regusername(txtfieldUsername.getText())) {
            labelcheckUser.setText("Username unique!");
            return 1;
        } else {
            labelcheckUser.setText("Username not unique!");
            return 0;
        }
    }

    public String randompass(){
        return "passwordtest";

    }

    public void creategovAcc() {
        if (checkUser(txtfieldUsername) == 1){

        }

        /*dgov.setUsername(username);
        dcase.setDateReported(temp);
        db.newcase(dcase);
        db.setPositive(username);*/


    }


    public void opencreateGov() {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/createGov.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("User Menu");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void opencreateTracer() {

    }

    public void openTerminate() {

    }

    public void openAnalytics() {

    }


}



