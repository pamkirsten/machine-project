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
    private TextField usernameInput;
    @FXML
    private PasswordField passwordInput;

    Database database = new Database();
    citizenController newCitizenController = new citizenController();
    TracerController tracer = new TracerController();
    GovController official = new GovController();


    public void showAlert(int check) {
        if (check == 1) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setHeaderText(null);
            alert.setTitle("Positive Case Detected");
            String wDate = database.getwarningDate(usernameInput.getText());
            String wEst = database.getwarningEst(usernameInput.getText());
            alert.setContentText("WARNING: You may have been exposed to COVID-19. There was a positive case detected on "+wDate+" in " + wEst + ". " +
                    "Please take a COVID-19 test as soon as possible and report if you are positive. Thank you.");



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

    public void loginOption(ActionEvent event) {
        int check = -999;
        int notify = 0;


        if (database.checkusername(usernameInput.getText())) {
            if (database.confirmpass(usernameInput.getText(), passwordInput.getText())) {

                closewindow(event);

                check = database.checkRole(usernameInput.getText());
                notify = database.checkNotify(usernameInput.getText());

                String wEst = database.getwarningEst(usernameInput.getText());

                System.out.println(usernameInput.getText()+" "+notify+" "+ wEst);

                if (check == 0) {
                    newCitizenController.setusername(usernameInput.getText());
                    showAlert(notify);
                    citizenMenu(event);

                }
                if (check == 1) {
                    newCitizenController.setusername(usernameInput.getText());
                    GovController.setusername(usernameInput.getText());
                    showAlert(notify);
                    citizenMenu(event);
                    governmentMenu(event);
                }
                if (check == 2) {
                    newCitizenController.setusername(usernameInput.getText());
                    TracerController.setusername(usernameInput.getText());
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
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/citizenMenu.fxml"));
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

    public void mainMenu(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainMenu.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("COVID-19 Tracker");
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
