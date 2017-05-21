package services.impl;

import dao.FilmsDao;
import dao.FilmsNamedJdbcTemplateDaoImpl;
import models.Film;
import services.AfishaService;

import java.util.List;

/**
 * 21.05.2017
 * AfishaServiceImpl @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public class AfishaServiceImpl implements AfishaService {
    private FilmsDao filmsDao;

    public AfishaServiceImpl() {
        this.filmsDao = new FilmsNamedJdbcTemplateDaoImpl();
    }

    @Override
    public void register(Film film) {
        filmsDao.save(film);
    }

    @Override
    public Film findById(int id) {
        return filmsDao.find(id);
    }

    @Override
    public List<Film> findByName(String name) {
        return filmsDao.findByName(name);
    }

    @Override
    public List<Film> findByCountry(String country) {
        return filmsDao.findByCountry(country);
    }

    @Override
    public List<Film> findByProducer(String producer) {
        return filmsDao.findByProducer(producer);
    }

    @Override
    public List<Film> findByGenre(String genre) {
        return filmsDao.findByGenre(genre);
    }

    @Override
    public List<Film> findByActors(String actorsName) {
        return filmsDao.findByActors(actorsName);
    }

    @Override
    public List<Film> findAll() {
        return filmsDao.findAll();
    }
}
