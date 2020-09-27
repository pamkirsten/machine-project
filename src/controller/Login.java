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

import java.io.IOException;

public class Login {

    @FXML
    private TextField inputuser;
    @FXML
    private PasswordField inputpass;

    Database db = new Database();
    User user = new User();
    tracerController tracer = new tracerController();
    govController official = new govController();


    public void showAlert(int check) {
        if (check == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Positive Case Detected");
            String wDate = db.getwarningDate(inputuser.getText());
            String wEst = db.getwarningEst(inputuser.getText());
            alert.setContentText("\n" + "Positive case detected on " + wDate + " in " + wEst + ". " +
                    "We are advising you to go through testing as soon as possible " +
                    "and report through this app should you test positive.");

            alert.showAndWait();
        }
        if (check == 2) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("WARNING");
            alert.setContentText("You have not took a COVID 19 test for the past 14 days. Please take one immediately. ");

            alert.showAndWait();
        }

    }

    public void loginuser(ActionEvent event) {
        int check = -999;
        int notify = 0;


        if (db.checkusername(inputuser.getText())) {
            if (db.confirmpass(inputuser.getText(), inputpass.getText())) {

                closewindow(event);

                check = db.checkRole(inputuser.getText());
                notify = db.checkNotify(inputuser.getText());

                if (check == 0) {
                    user.setusername(inputuser.getText());
                    showAlert(notify);
                    citizenMenu(event);

                }
                if (check == 1) {
                    user.setusername(inputuser.getText());
                    govController.setusername(inputuser.getText());
                    showAlert(notify);
                    citizenMenu(event);
                    governmentMenu(event);
                }
                if (check == 2) {
                    user.setusername(inputuser.getText());
                    tracerController.setusername(inputuser.getText());
                    showAlert(notify);
                    citizenMenu(event);
                    tracerMenu(event);
                }
            } else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setTitle("Login Error");
                alert.setContentText("Incorrect Password!");

                alert.showAndWait();
            }
        } else {
            Alert alert1 = new Alert(Alert.AlertType.ERROR);
            alert1.setHeaderText(null);
            alert1.setTitle("Login Error");
            alert1.setContentText("Incorrect Username!");

            alert1.showAndWait();
        }
    }

    public void citizenMenu(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/userMenu.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("User Menu");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void governmentMenu(ActionEvent event) {

        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/govMenu.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Government Menu");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void tracerMenu(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/tracerMenu.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Contact Tracer Menu");
            stage.setScene(new Scene(root, 600, 600));
            stage.setResizable(false);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void goback(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainmenu.fxml"));
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
