package dont.be.sad.entries;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;

public class Checker {
    public static void check() throws IOException {
        Path entryFolder = Paths.get(System.getProperty("user.home"), ".journalEntries");
        if(!Files.exists(entryFolder)) {
            Files.createDirectories(entryFolder);
        }
        
        Path settings = Paths.get(entryFolder.toString(), "Settings.json");
        if(!Files.exists(settings)) {
            Files.createFile(settings);
            LocalDate nDate = LocalDate.now();
            String date = String.format("{ \n \t\"LastDate\" : %d\n}", nDate.getDayOfYear());
            Files.write(settings, date.getBytes());
        }
    }
}
