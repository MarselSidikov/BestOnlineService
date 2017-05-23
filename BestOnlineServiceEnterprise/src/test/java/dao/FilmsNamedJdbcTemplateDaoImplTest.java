package dao;

import models.Film;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.dao.EmptyResultDataAccessException;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

/**
 * 21.05.2017
 * FilmsNamedJdbcTemplateDaoImplTest @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */

public class FilmsNamedJdbcTemplateDaoImplTest {

    private FilmsNamedJdbcTemplateDaoImpl filmsDao;

    private final int FILM_11_ID = 11;

    private final String FILM_11_NAME = "Guardians of the Galaxy Vol. 2";

    private final String FILM_COUNTRY = "USA";

    private final String FILM_PRODUCER = "James Gunn";

    private final String FILM_ACTORS =  "Chris Pratt";

    private final String FILM_GENRE = "Fantasy";

    private final Film FILM_11 = new Film.Builder()
            .id(11)
            .name("Guardians of the Galaxy Vol. 2")
            .genre("Fantasy")
            .country("USA")
            .lasting(135)
            .description("Peter Quill and his fellow Guardians are hired by a powerful alien race, the Sovereign, to protect their precious batteries from invaders.")
            .releaseDate("May 5th, 2017")
            .actors("Chris Pratt")
            .picture("default.jpg")
            .producer("James Gunn")
            .build();

    private final Film INSERT_FILM = new Film.Builder()
            .id(12)
            .name("King Arthur: Legend of the Sword")
            .genre("Fantasy")
            .country("USA")
            .lasting(128)
            .description("After the murder of his father, young Arthur's power-hungry uncle Vortigern seizes control of the crown. Robbed of his birthright, he grows up the hard way in the back alleys of the city, not knowing who he truly is.")
            .releaseDate("May 12th, 2017")
            .actors("Charlie Hunnam")
            .producer("Guy Ritchie")
            .picture("default.jpg")
            .build();
    private final Film FILM_5 = new Film.Builder()
            .id(5)
            .name("Baahubali 2: The Conclusion")
            .genre("Drama")
            .country("India")
            .lasting(165)
            .description("Great India Films")
            .releaseDate("April 28th, 2017")
            .actors("Prabhas")
            .picture("default.jpg")
            .producer("Arka Mediaworks")
            .build();

    private final Film UPDATE_FILM_11 = new Film.Builder()
            .id(11)
            .name("Guardians of the Galaxy")
            .genre(" Action")
            .country("United States")
            .lasting(122)
            .description("In 1988, following his mother's death, a young Peter Quill is abducted from Earth by the Ravagers, a group of space pirates led by Yondu Udonta.")
            .releaseDate("July 21, 2014")
            .actors("Zoe Saldana")
            .picture("default.jpg")
            .producer("James Gunn")
            .build();
    @Before
    public void setUp() throws Exception {
        GenericXmlApplicationContext context = new  GenericXmlApplicationContext();
        ConfigurableEnvironment environment = context.getEnvironment();
        environment.addActiveProfile("test");
        context.load("ru.itis\\spring\\context.xml");
        context.refresh();
        filmsDao = context.getBean(FilmsNamedJdbcTemplateDaoImpl.class);
    }

    @Test
    public void testFind() throws Exception {
        Film expected = FILM_11;
        Film actual = filmsDao.find(FILM_11_ID);

        assertEquals(expected,actual);
    }

    @Test
    public void findByName() throws Exception {
        List<Film> expected = new ArrayList<Film>();
        expected.add(FILM_11);
        List<Film> actual = filmsDao.findByName(FILM_11_NAME);
        assertEquals(expected,actual);
    }

    @Test
    public void findByCountry() throws Exception {
        List<Film> expected = new ArrayList<Film>();
        expected.add(FILM_11);
        List<Film> actual = filmsDao.findByCountry(FILM_COUNTRY);
        assertEquals(expected,actual);
    }

    @Test
    public void findByProducer() throws Exception {
        List<Film> expected = new ArrayList<>();
        expected.add(FILM_11);
        List<Film> actual = filmsDao.findByProducer(FILM_PRODUCER);
        assertEquals(expected,actual);
    }

    @Test
    public void findByGenre() throws Exception {
        List<Film> expected = new ArrayList<>();
        expected.add(FILM_11);
        List<Film> actual = filmsDao.findByGenre(FILM_GENRE);
        assertEquals(expected,actual);
    }

    @Test
    public void findByActors() throws Exception {
        List<Film> expected = new ArrayList<>();
        expected.add(FILM_11);
        List<Film> actual = filmsDao.findByActors(FILM_ACTORS);
        assertEquals(expected,actual);
    }

    @Test
    public void save() throws Exception {
        Film expected = INSERT_FILM;
        int id = filmsDao.save(INSERT_FILM);
        Film actual = filmsDao.find(id);
        assertEquals(expected,actual);
    }

    @Test
    public void update() throws Exception {
        Film expected = UPDATE_FILM_11;
        filmsDao.update(UPDATE_FILM_11);
        Film actual = filmsDao.find(FILM_11_ID);
        assertEquals(expected,actual);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void delete() throws Exception {
        filmsDao.delete(FILM_11_ID);
        filmsDao.find(FILM_11_ID);
    }

    @Test
    public void findAll() throws Exception {
        List<Film> expected = new ArrayList<>();
        expected.add(FILM_5);
        expected.add(FILM_11);
        List<Film> actual = filmsDao.findAll();
        assertEquals(expected,actual);

    }

}