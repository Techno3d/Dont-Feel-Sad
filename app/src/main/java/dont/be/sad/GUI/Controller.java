package dont.be.sad.GUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import dont.be.sad.entries.Entry;
import dont.be.sad.entries.EntryHelper;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ColorPicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleButton;

public class Controller {
    @FXML
    private ToggleButton KAKIMASU;
    @FXML
    public ColorPicker IRO;
    @FXML
    private Label helpMessage;
    @FXML
    private ChoiceBox<String> araara;
    @FXML
    private Button BACK;
    @FXML
    private Button FRONT;
    
    @FXML
    private TextArea PIKA;
    @FXML
    public Canvas PIKA2;
    private Entry todaysEntry;
    
    //Draw Tools
    @FXML
    public ToggleButton eraser;
    @FXML
    private Button plus;
    @FXML
    private Button minus;
    public int thickness;

    @FXML
    public void toggleWrite(Event e) {
        PIKA2.setVisible(KAKIMASU.isSelected());
        System.out.println(PIKA2.isVisible());
    }

    @FXML
    protected void initialize() {
        try {
            ArrayList<Path> files = new ArrayList<Path>();
            Files.list(Paths.get(System.getProperty("user.home"), ".journalEntries/")).forEach(files::add);
            for (Path file : files) {
                if(file.toString().toLowerCase().contains("settings")) {
                    files.remove(file);
                }
            }

            ArrayList<String> entries = new ArrayList<String>();
            for(Path file : files) {
                System.out.println(file.toString());
                entries.add(EntryHelper.read(file).name);
            }
            araara.getItems().addAll(entries);
            araara.setValue(LocalDate.now().toString());

            thickness = 10;
            for(Path file : files) {
                if(EntryHelper.read(file).name.equals(LocalDate.now().toString())) {
                    PIKA.setText(EntryHelper.read(file).message);
                    todaysEntry = EntryHelper.read(file);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    @FXML
    public void saveMessage(Event e) {
        EntryHelper.write(PIKA.getText(), todaysEntry.date, todaysEntry.name, Paths.get(System.getProperty("user.home"), ".journalEntries/" + LocalDate.now().toString() + ".json"));
    }
    
    @FXML
    public void back(Event e) {
        
    }

    @FXML
    public void next(Event e) {

    }

    @FXML
    public void setEraser(Event e) {
        if(eraser.isSelected()) {
            eraser.setText("Eraser");
        } else {
            eraser.setText("Draw");
        }
    }

    @FXML public void plusSize(Event e) {
        thickness += 5;        
    }

    @FXML
    public void minusSize(Event e) {
        thickness -= 5;
    }
    
    @FXML
    public void changeMode(Event e) {
        KAKIMASU.setText(KAKIMASU.isSelected() ? "Draw" : "Type");
    }
}
