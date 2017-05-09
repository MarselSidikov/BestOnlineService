package dao;

import models.Movie;

import java.util.List;

/**
 * 08.05.2017
 * BaseAfishaDao @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public interface BaseAfishaDao {
    Movie find(int id);

    List<Movie> findByName(String name);

    List<Movie> findByCountry(String country);

    List<Movie> findByProducer(String producer);

    List<Movie> findByGenre(String genre);

    List<Movie> findByActors(String actorsName);

    int save(Movie movie);

    void update(Movie movie);

    void delete(int id);


}
