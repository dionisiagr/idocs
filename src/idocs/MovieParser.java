package idocs;

import java.text.ParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author Dionisia Chinou
 * @version 1.0
 */
public class MovieParser {

    public static final String pattern = "^(\\d+)/(\\d+)/(\\d+) (.*) (\\d+)min (\\d+)min (\\S+)$";

    public static MovieWatched parse(String expression) throws ParseException {
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(expression);
     
        MovieWatched movieWatched = new MovieWatched();
        if (matcher.find()) {
            movieWatched.setViewedDate(matcher.group(1) + "/" + matcher.group(2) + "/" + matcher.group(3));
            movieWatched.setMovie(new Movie(matcher.group(4),Short.parseShort(matcher.group(5)),matcher.group(7)));
            movieWatched.setMinutesWatched(Short.parseShort(matcher.group(6)));
        }else{
            throw new ParseException(expression + " does not match ", 0);
        }
        
        return movieWatched;
    }
}
