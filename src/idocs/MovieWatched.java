package idocs;

import java.util.Comparator;

/**
 *
 * @author Dionisia Chinou
 * @version 1.0
 */
public class MovieWatched {
    
    private String viewedDate; //Date that movie was viewed
    private Movie movie; //movie wathed
    private short minutesWatched; //number of minutes wathed by person

    public MovieWatched() {
    }
    
    public MovieWatched(String viewedDate, Movie movie, short minutesWatched) {
        this.viewedDate = viewedDate;
        this.movie = movie;
        this.minutesWatched = minutesWatched;
    }

    public String getViewedDate() {
        return viewedDate;
    }

    public void setViewedDate(String viewedDate) {
        this.viewedDate = viewedDate;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public short getMinutesWatched() {
        return minutesWatched;
    }

    public void setMinutesWatched(short minutesWatched) {
        this.minutesWatched = minutesWatched;
    }
    
    public float getAverage(){
        try{
            return (this.getMinutesWatched()*100)/this.getMovie().getMovieLength();
        }catch (NullPointerException |ArithmeticException e){
            return 0;
        }
    }

    @Override
    public String toString() {
        return "MovieWatched{" + "viewedDate=" + viewedDate + ", movie=" + movie + ", minutesWatched=" + minutesWatched + '}';
    }
    
    /**
     *Comparator for sorting the list by method
     */
    public static Comparator<MovieWatched> OrderComparator = (MovieWatched s1, MovieWatched s2) -> (s1.getMinutesWatched()-s2.getMinutesWatched());
    
    //sort by average
    public static Comparator<MovieWatched> AverageComparator = (MovieWatched s1, MovieWatched s2) -> (short)(s1.getAverage()-s2.getAverage());
}
