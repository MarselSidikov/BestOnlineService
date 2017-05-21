package services;

import models.Film;

import java.util.List;

/**
 * 06.05.2017
 * AfishaService
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface AfishaService {

    void register(Film film);

    Film findById(int id);

    List<Film> findByName(String name);

    List<Film> findByCountry(String country);

    List<Film> findByProducer(String producer);

    List<Film> findByGenre(String genre);

    List<Film> findByActors(String actorsName);

    List<Film> findAll();

}
