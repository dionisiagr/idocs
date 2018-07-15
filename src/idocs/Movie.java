package idocs;

import java.util.Comparator;

/**
 *
 * @author Dionisia Chinou
 * @version 1.0
 */
public class Movie {
    
    private String movieName; //the name of the movie
    private short movieLength; //the length of the movie in minutes
    private String genre; //the genre of movie

    public Movie(String movieName, short movieLength, String genre) {
        this.movieName = movieName;
        this.movieLength = movieLength;
        this.genre = genre;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public short getMovieLength() {
        return movieLength;
    }

    public void setMovieLength(short movieLength) {
        this.movieLength = movieLength;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    @Override
    public String toString() {
        return "Movie{" + "movieName=" + movieName + ", movieLength=" + movieLength + ", genre=" + genre + '}';
    }
    
    
    /**
     *Comparator for sorting the list by method
     */
    public static Comparator<Movie> OrderComparator = (Movie s1, Movie s2) -> s1.getGenre().compareTo(s2.getGenre());
}
