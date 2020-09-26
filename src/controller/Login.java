package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Database;
import model.Tracer;

import java.io.IOException;

public class Login {

    @FXML
    private TextField inputuser;
    @FXML
    private PasswordField inputpass;

    Database db = new Database();
    User user = new User();
    tracerController tracer = new tracerController();


    public void loginuser(ActionEvent event) {
        int check = -999;

        if(db.checkusername(inputuser.getText())){
            if(db.confirmpass(inputuser.getText(),inputpass.getText())){

                closewindow(event);

                check = db.checkRole(inputuser.getText());

                if(check==0){
                    user.setusername(inputuser.getText());

                    citizen(event);
                }
                if(check==1){
                    user.setusername(inputuser.getText());
                  citizen(event);
                    gov(event);
                }
                if(check==2){
                    user.setusername(inputuser.getText());
                    tracerController.setusername(inputuser.getText());
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
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/tracerview.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Contact Tracer Menu");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
