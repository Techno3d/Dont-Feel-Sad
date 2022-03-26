package dont.be.sad;

import java.io.IOException;

import dont.be.sad.GUI.Gui;
import dont.be.sad.entries.Checker;
import dont.be.sad.entries.EntryHelper;

public class App {
    public String getGreeting() {
        return "Hello World!";
    }

    public static void main(String[] args) {
        try {
            Checker.check();
            EntryHelper.makeEntry();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Gui.main(args);
    }
}
