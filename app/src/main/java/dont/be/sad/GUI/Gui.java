package dont.be.sad.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

public class Gui extends Application {
    public static void main(String[] args) {
        launch(args);    
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Your Journal :)");
        FXMLLoader loader = new FXMLLoader(getClass().getClassLoader().getResource("MainScene.fxml"));
        Parent root = loader.load();
        Scene mainScene = new Scene(root, 847, 500);
        primaryStage.setScene(mainScene);

        Controller control = loader.getController();
        GraphicsContext graphicsContext = control.PIKA2.getGraphicsContext2D();
        graphicsContext.setLineWidth(control.thickness);
        
        control.PIKA2.setOnMousePressed(e -> {
            if(!control.eraser.isSelected()) {
                graphicsContext.setStroke(control.IRO.getValue());
                graphicsContext.beginPath();
                graphicsContext.lineTo(e.getX(), e.getY());
                System.out.println("yo angelo");
            } else {
                double lineWidth = graphicsContext.getLineWidth();
                graphicsContext.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
            }
        });
        
        primaryStage.show();
    }
}
