package dao;

import models.Film;

import java.util.List;

/**
 * 08.05.2017
 * BaseFilmsDao @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public interface BaseFilmsDao extends BaseDao<Film> {

    List<Film> findByName(String name);

    List<Film> findByCountry(String country);

    List<Film> findByProducer(String producer);

    List<Film> findByGenre(String genre);

    List<Film> findByActors(String actorsName);

}
