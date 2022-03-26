package dont.be.sad.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Gui extends Application {
    public static void main(String[] args) {
        launch(args);    
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Your Journal :)");
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("MainScene.fxml"));
        Scene mainScene = new Scene(root, 500, 500);
        primaryStage.setScene(mainScene);

        primaryStage.show();
    }
}
