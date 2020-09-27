package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Case;
import model.Database;
import model.Establishment;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The type Citizen controller.
 */
public class CitizenController {

    private static String username;
    private final Database database = new Database();
    private final Establishment establishment = new Establishment();
    private final Case cases = new Case();
    @FXML
    private PasswordField regpass1;
    @FXML
    private TextField firstName;
    @FXML
    private TextField middleName;
    @FXML
    private TextField lastName;
    @FXML
    private TextField homeAdd;
    @FXML
    private TextField workAdd;
    @FXML
    private TextField phoneNum;
    @FXML
    private TextField emailAdd;
    @FXML
    private Button reportPositive;
    @FXML
    private DatePicker date;
    @FXML
    private DatePicker dateReported;
    @FXML
    private TextField code;

    /**
     * Close.
     *
     * @param event the event
     */
    public void close(ActionEvent event) {
        ((Node) (event.getSource())).getScene().getWindow().hide();
    }

    /**
     * Stringerror.
     */
    public void stringerror() {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("User Input Error");
        alert.setContentText("Input contains invalid characters!");
        alert.showAndWait();
    }

    /**
     * Passworderror.
     *
     * @param s the s
     */
    public void passworderror(String s) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setHeaderText(null);
        alert.setTitle("Password Error");
        alert.setContentText(s);
        alert.showAndWait();
    }

    /**
     * Findspace boolean.
     *
     * @param s the s
     * @return the boolean
     */
    public boolean findspace(String s) {
        Pattern pattern = Pattern.compile("\\s");
        Matcher matcher = pattern.matcher(s);
        boolean found = matcher.find();
        return found;
    }

    /**
     * Checkpassword boolean.
     *
     * @return the boolean
     */
    public boolean checkpassword() {
        if (regpass1.getText().length() < 6) {
            passworderror("Password must be at least 6 characters!");
            return false;
        } else if (!checkpassvalid()) {
            passworderror("Password must contain a digit or a spchar!");
            return false;
        } else if (findspace(regpass1.getText()) || regpass1.getText().contains(":") || regpass1.getText().contains(",")) {
            passworderror("Password contains invalid char!");
            return false;
        }
        return true;
    }

    /**
     * Checkpassvalid boolean.
     *
     * @return the boolean
     */
    public boolean checkpassvalid() {
        Pattern pass = Pattern.compile("[$&+;=\\\\?@#|/'<>^*()%!-]");
        Matcher m = pass.matcher(regpass1.getText());
        boolean result = m.find();

        if (result) {
            return true;
        } else return regpass1.getText().matches(".*\\d.*");
    }

    /**
     * Checkuserinfo boolean.
     *
     * @return the boolean
     */
    public boolean checkuserinfo() {
        if (firstName.getText().contains(":") || firstName.getText().contains(",") ||
                middleName.getText().contains(":") || middleName.getText().contains(",") ||
                lastName.getText().contains(":") || lastName.getText().contains(",") ||
                homeAdd.getText().contains(":") ||
                workAdd.getText().contains(":") ||
                findspace(phoneNum.getText()) || phoneNum.getText().contains(":") || phoneNum.getText().contains(",") || (!phoneNum.getText().matches("[0-9]+")) ||
                findspace(emailAdd.getText()) || emailAdd.getText().contains(":") || emailAdd.getText().contains(",") ||
                findspace(regpass1.getText()) || regpass1.getText().contains(":") || regpass1.getText().contains(",") || !checkpassword()) {
            stringerror();
            return false;
        }
        return true;
    }

    /**
     * Sets .
     *
     * @param user the user
     */
    public void setusername(String user) {
        username = user;
    }

    /**
     * Save user info.
     *
     * @param event the event
     */
    public void saveUserInfo(ActionEvent event) {
        if (checkuserinfo()) {
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setHeaderText(null);
            alert1.setTitle("Confirmation Dialog");
            alert1.setContentText("Are you sure with this?");

            Optional<ButtonType> result = alert1.showAndWait();
            if (result.get() == ButtonType.OK) {
                database.updateAccountsData(username, regpass1.getText(), firstName.getText(), middleName.getText(), lastName.getText(), homeAdd.getText(), workAdd.getText(), phoneNum.getText(), emailAdd.getText());
                close(event);
            }
        }
    }

    /**
     * Update info.
     *
     * @param event the event
     */
    public void updateInfo(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/citizenUpdateInput.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Update User Information");
            stage.setScene(new Scene(root, 600, 600));
            stage.getIcons().add(new Image("/icon.png"));
            stage.getIcons().add(new Image("/icon.png"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Open check in.
     *
     * @param event the event
     */
    public void openCheckIn(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/citizenCheckIn.fxml"));
            javafx.stage.Stage stage = new Stage();
            stage.setTitle("Check-In");
            stage.setScene(new Scene(root, 600, 600));
            stage.getIcons().add(new Image("/icon.png"));
            stage.setResizable(false);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Check in.
     *
     * @param event the event
     */
    public void checkIn(ActionEvent event) {
        String tempstring1 = date.getValue().toString();
        String[] fdate = tempstring1.split("-");
        tempstring1 = fdate[1] + "," + fdate[2] + "," + fdate[0];

        String tempstring2 = LocalTime.now().toString();
        String[] ftime = tempstring2.split(":");
        tempstring2 = ftime[0] + ftime[1];

        establishment.setCheckInTime(tempstring2);
        establishment.setCheckInDate(tempstring1);
        establishment.setUsername(username);
        establishment.setEstCode(code.getText());

        database.createEstablishment(establishment);

        close(event);
    }

    /**
     * Main menu.
     *
     * @param event the event
     */
    public void mainMenu(ActionEvent event) {
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
     * Back user.
     *
     * @param event the event
     */
    public void backUser(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/citizenMenu.fxml"));
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
     * Open report.
     *
     * @param event the event
     */
    public void openReport(ActionEvent event) {
        Parent root;
        try {
            root = FXMLLoader.load(getClass().getClassLoader().getResource("view/citizenReport.fxml"));
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
     * Report positive.
     *
     * @param event the event
     */
    public void reportPositive(ActionEvent event) {
        String temp = dateReported.getValue().toString();
        String[] fdate = temp.split("-");
        temp = fdate[1] + "," + fdate[2] + "," + fdate[0];
        int casenum = database.getAssignedCaseNum();
        cases.setUsername(username);
        cases.setCasenum(casenum);
        cases.setDateReported(temp);
        database.createCase(cases);
        database.setPositive(username);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);
        alert.setTitle("Positive Confirmation");
        alert.setContentText("\nYour Assigned Case Number is: " + database.getTotalCases() + "\n Date Reported: " + database.getDateReported(username));
        alert.showAndWait();

        database.updateCasesFile();
        if (database.checkNotify(username) != 0) {
            reportPositive.setDisable(false);
        }

        else{

        reportPositive.setDisable(true);}
    }

}
