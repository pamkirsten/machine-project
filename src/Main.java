import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import model.Database;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/mainMenu.fxml"));
        stage.setTitle("COVID Tracker");
        stage.setScene(new Scene(root, 600, 600));
        stage.getIcons().add(new Image("/icon.png"));
        stage.setResizable(false);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.show();
    }

    public static void main(String[] args) {
        Database db = new Database();
        db.startProgram();

        launch(args);
    }
}
