package dont.be.sad.GUI;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;

import com.google.gson.JsonSyntaxException;

import dont.be.sad.entries.Entry;
import dont.be.sad.entries.EntryHelper;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
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
        PIKA2.setVisible(!KAKIMASU.isSelected());
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
                
            //Choice box fix
            ChangeListener<String> changeFile = new ChangeListener<String>() {
                @Override
                public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                    changeFile();
                }
            };
            araara.getSelectionModel().selectedItemProperty().addListener(changeFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
        
    @FXML
    public void saveMessage(Event e) {
        EntryHelper.write(PIKA.getText(), todaysEntry.date, todaysEntry.name, Paths.get(System.getProperty("user.home"), ".journalEntries/" + todaysEntry.name + ".json"));
    }
    
    @FXML
    public void back(Event e) {
        //Not Finished
    }

    @FXML
    public void next(Event e) {
        //Not Finished
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
        KAKIMASU.setText(!KAKIMASU.isSelected() ? "Draw" : "Type");
    }
    
    // @FXML
    public void changeFile() {
        try {
            todaysEntry = EntryHelper.read(Paths.get(System.getProperty("user.home"), ".journalEntries/" + araara.getSelectionModel().getSelectedItem() + ".json"));
            PIKA.setText(todaysEntry.message);
        } catch (JsonSyntaxException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        } catch (IOException e1) {
            // TODO Auto-generated catch block
            e1.printStackTrace();
        }
    }
}
