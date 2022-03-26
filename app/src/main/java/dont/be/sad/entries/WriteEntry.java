package dont.be.sad.entries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class WriteEntry {
    
    public static void makeEntry() throws IOException {
        LocalDate nDate = LocalDate.now();
        Path settings = Paths.get(System.getProperty("user.home"), ".journalEntries/Settings.json");
        JSONObject file = (JSONObject) JSONValue.parse(Files.readString(settings));

        if(file.containsKey("LastDate")) {
            if (nDate.getDayOfYear() > (Long) file.get("LastDate")) {
                Path entry = Paths.get(System.getProperty("user.home"), ".journalEntries/" + nDate.toString() + ".json");
                Files.createFile(entry);
                String date = String.format("{ \n \t\"LastDate\" : %d\n}", nDate.getDayOfYear());
                Files.write(settings, date.getBytes());
            }
        }
    }
    
    public static void write(String entry, String date, String name) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();

        Gson gson = gsonBuilder.create();

        Entry newEntry = new Entry();
    }
    
}
