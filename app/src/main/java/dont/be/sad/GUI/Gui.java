package dont.be.sad.GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.nio.file.Files;
import java.nio.file.Paths;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import dont.be.sad.entries.Quote;

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
        primaryStage.show();
        
        Controller control = loader.getController();
        GraphicsContext graphicsContext = control.PIKA2.getGraphicsContext2D();
        graphicsContext.setLineWidth(control.thickness);
        
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();
        Quote jsonstuff = gson.fromJson(Files.readString(Paths.get(getClass().getClassLoader().getResource("Quotes.json").toURI())), Quote.class);

        
        //Painting
        control.PIKA2.setOnMousePressed(e -> {
            if(!control.eraser.isSelected()) {
                graphicsContext.setLineWidth(control.thickness);
                graphicsContext.setStroke(control.IRO.getValue());
                graphicsContext.beginPath();
                graphicsContext.lineTo(e.getX(), e.getY());
            } else {
                double lineWidth = graphicsContext.getLineWidth();
                graphicsContext.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
            }
        });

        control.PIKA2.setOnMouseDragged(e -> {
            if(!control.eraser.isSelected()) {
                graphicsContext.lineTo(e.getX(), e.getY());
            } else {
                double lineWidth = graphicsContext.getLineWidth();
                graphicsContext.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
            }
        });
                
        control.PIKA2.setOnMouseReleased(e -> {
            if(!control.eraser.isSelected()) {
                graphicsContext.lineTo(e.getX(), e.getY());
                graphicsContext.stroke();
                graphicsContext.closePath();
            } else {
                double lineWidth = graphicsContext.getLineWidth();
                graphicsContext.clearRect(e.getX() - lineWidth / 2, e.getY() - lineWidth / 2, lineWidth, lineWidth);
            }
        });
    }
}
