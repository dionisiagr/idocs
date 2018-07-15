package idocs;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 *
 * @author Dionisia Chinou
 * @version 1.0
 */
public class IDocs {

    private List<Person> personsList;

    public IDocs(List<Person> personsList) {
        this.personsList = personsList;
    }

    public List<Person> getPersonsList() {
        return personsList;
    }

    public void setPersonsList(List<Person> personsList) {
        this.personsList = personsList;
    }

    /**
     * Question 1
     *
     * @return: an order list of the movies watched by all 3 persons combined,
     * ordered by minutes watched
     */
    public List<MovieWatched> orderList() {
        List<MovieWatched> moviesWatched = new ArrayList<>();
        personsList.stream().map((person) -> {
            Collections.sort(person.getMoviesWatched(), MovieWatched.OrderComparator);
            return person;
        }).forEach((person) -> {
            moviesWatched.addAll(person.getMoviesWatched());
        });
        Collections.sort(moviesWatched, MovieWatched.OrderComparator);

        return moviesWatched;
    }

    /**
     * Question 4
     */
    public List<String> recommendMovies() throws IndexOutOfBoundsException {
        List<String> favoriteMovies = new ArrayList<>();
        personsList.stream().forEach((person) -> {
            person.getFavoriteMovies().stream().forEach((movie) -> {
                favoriteMovies.add(movie.getMovie().getGenre());
            });
        });
        Set<String> set = new HashSet<>();
        set.addAll(favoriteMovies);
        favoriteMovies.clear();
        favoriteMovies.addAll(set);

        return favoriteMovies.subList(0, 2);
    }

    public void execute(String output) {

        try {
            PrintWriter writer = new PrintWriter(new File(output), "UTF-8");
            //output question 1
            List<MovieWatched> moviesWatched = this.orderList();
            writer.println("Question 1");
            moviesWatched.stream().forEach((movie) -> {
                writer.printf("%dmin %s\n", movie.getMinutesWatched(), movie.getMovie().getMovieName());
            });
            writer.println("\nQuestion 2");
            personsList.stream().map((person) -> {
                writer.printf("\nAverages for %s\n", person.getName());
                return person;
            }).forEach((person) -> {
                person.getMoviesWatched().stream().forEach((movie) -> {
                    writer.printf("%s %.0f%s\n", movie.getMovie().getMovieName(), movie.getAverage(), "%");
                });
            });

            writer.println("\nQuestion 3");
            personsList.stream().map((person) -> {
                writer.printf("\nFavorite Genres for %s\n", person.getName());
                return person;
            }).forEach((person) -> {
                person.getFavoriteMovies().stream().forEach((movie) -> {
                    writer.printf("%s %s\n", movie.getMovie().getMovieName(), movie.getMovie().getGenre());
                });
            });
            writer.println("\nQuestion 4");
            for (String movie : this.recommendMovies()) {
                writer.printf("%s\n", movie);
            }

            writer.close();
        } catch (FileNotFoundException | UnsupportedEncodingException e) {
        }

    }

    public static void main(String[] args) {
        try {
            if (args.length != 4) {
                throw new IllegalArgumentException();
            }
            //read input files
            ReadFiles readFiles = new ReadFiles();
            List<Person> personsList = new ArrayList<>();
            for (int i = 0; i < args.length - 1; i++) {
                personsList.add(readFiles.readFile(args[i]));
            }
            IDocs iDocs = new IDocs(personsList);

            iDocs.execute(args[3]);

        } catch (java.lang.ArrayIndexOutOfBoundsException | IllegalArgumentException e) {
            System.err.println("missing arguments : file_person1  file_person2 file_person 3 output_file");
            System.exit(1);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            System.exit(1);
        }

    }

}
