package dont.be.sad.entries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

public class EntryHelper {
    
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
                write("", LocalDate.now().toString(), LocalDate.now().toString(), entry);
            }
        }
    }
    
    public static void write(String entry, String date, String name, Path path) {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();

        Gson gson = gsonBuilder.create();

        Entry newEntry = new Entry(name, date, entry);
        String jsonString = gson.toJson(newEntry);
        
        try {
            Files.write(path, jsonString.getBytes());
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static Entry read(Path path) throws JsonSyntaxException, IOException {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setPrettyPrinting();

        Gson gson = gsonBuilder.create();
        return gson.fromJson(Files.readString(path), Entry.class);
    }
    
}
