package idocs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 * @author Dionisia Chinou
 * @version 1.0
 */
public class Person {

    private String name; //person's name
    private List<MovieWatched> moviesWatched; //movies watced by the person 

    public Person(String name) {
        this.name = name;
    }

    public Person(String name, List<MovieWatched> moviesWatched) {
        this.name = name;
        this.moviesWatched = moviesWatched;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<MovieWatched> getMoviesWatched() {
        return moviesWatched;
    }

    public void setMoviesWatched(List<MovieWatched> moviesWatched) {
        this.moviesWatched = moviesWatched;
    }

    public List<MovieWatched> getFavoriteMovies() {
        Collections.sort(this.getMoviesWatched(), MovieWatched.AverageComparator);
        return IntStream.range(0, this.getMoviesWatched().size() - 1)
                .filter(i -> this.getMoviesWatched().get(i).getAverage() < this.getMoviesWatched().get(i + 1).getAverage())
                .mapToObj(this.getMoviesWatched()::get)
                .collect(Collectors.toList());
    }

}
