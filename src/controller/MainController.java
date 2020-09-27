package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Database;

import java.io.IOException;

/**
 * The Main Controller
 */
public class MainController {

    private Object Stage;
    private final Database database = new Database();

    /**
     * Exits the Program
     */
    public void exitprogram() {
        database.endProgram();
        System.exit(0);
    }

    /**
     * Displays the Register Menu
     *
     * @param event the event
     */
    public void regOption(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainRegister.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Register User");
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
     * Displays the Login Menu
     *
     * @param event the event
     */
    public void userLogin(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/mainLogin.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Login User");
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
     * Closes the Event
     *
     * @param event the event
     */
    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }
}
