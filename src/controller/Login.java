package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Database;

import java.io.IOException;

public class Login {

    @FXML
    private TextField inputuser;
    @FXML
    private PasswordField inputpass;

    Database db = new Database();
    User user = new User();

    public void loginuser(ActionEvent event) {
        String creds;

        if(db.checkusername(inputuser.getText())){
            if(db.confirmpass(inputuser.getText(),inputpass.getText())){
                closewindow(event);

                creds = db.credcheck(inputuser.getText());

                if(creds.equals("citizen")){
                    user.setusername(inputuser.getText());
                    citizen(event);
                }
                if(creds.equals("official")){
                    user.setusername(inputuser.getText());
                    citizen(event);
                    gov(event);
                }
                if(creds.equals("tracer")){
                    user.setusername(inputuser.getText());
                    citizen(event);
                    tracer(event);
                }
            }
            else{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Login Error");
                alert.setContentText("Incorrect Password!");

                alert.showAndWait();
            }
        }
        else{
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setTitle("Login Error");
            alert1.setContentText("Incorrect Username!");

            alert1.showAndWait();
        }
    }

    public void citizen(ActionEvent event){
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/userview.fxml"));
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

    public void gov(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govview.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Government Menu");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tracer(ActionEvent event) {
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

    public void closewindow(ActionEvent event) {
        ((Node)(event.getSource())).getScene().getWindow().hide();
    }
}
