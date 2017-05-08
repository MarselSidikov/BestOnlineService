package poster.dao;

import poster.models.Movie;

/**
 * 08.05.2017
 * BasePosterDao @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public interface BasePosterDao {
    Movie find(int id);

    Movie findByName(String name);

    Movie findByContry(String contry);

    Movie findByProducer(String producer);

    Movie findByGenre(String genre);

    void save(Movie movie);

    void update(Movie movie);

    void delete(int id);


}
