package idocs;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Dionisia Chinou
 * @version 1.0
 */
public class ReadFiles {
     /*
    read file and create Person pbject
     */
    public Person readFile(String filePath) throws IOException, ParseException {
        File file = new File(filePath);        
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file), "UTF-8"))) {
            String currentLine = reader.readLine();
            Person person = new Person(file.getName());
            List<MovieWatched> moviesWatched = new ArrayList<>();
            while (currentLine != null) {
                moviesWatched.add(MovieParser.parse(currentLine));
                currentLine = reader.readLine();
            }
            person.setMoviesWatched(moviesWatched);
            return person;
        }
    }
}
