package dao;

import models.Film;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * 08.05.2017
 * FilmsJdbcTemplteDaoImpl @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public class FilmsJdbcTemplteDaoImpl implements BaseFilmsDao {

    private JdbcTemplate jdbcTemplate;

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM films";
    // language=SQL
    private final String SQL_SELECT_FILM_BY_ID = "SELECT * FROM films WHERE id = ?";
    // language=SQL
    private final String SQL_SELECT_FILMS_BY_NAME = "SELECT * FROM films WHERE name = ?";
    // language=SQL
    private final String SQL_SELECT_FILMS_BY_COUNTRY = "SELECT * FROM films WHERE country = ?";
    // language=SQL
    private final String SQL_SELECT_FILMS_BY_PRODUCER = "SELECT * FROM films WHERE producer = ?";
    // language=SQL
    private final String SQL_SELECT_FILMS_BY_GENRE = "SELECT * FROM films WHERE genre = ?";
    // language=SQL
    private final String SQL_INSERT_FILM = "INSERT INTO films(name,releaseDate,genre,country,producer,lasting,description,actors,picture) VALUES " +
            "(? , ? , ? , ? , ? , ? , ? , ? , ?)";
    // language=SQL
    private final String SQL_UPDATE_FILM_BY_ID = "UPDATE films SET name = ? , releasedate = ? , " +
            "genre = ? , country = ? , producer = ? , lasting = ? , description = ? , actors = ? , picture = ? WHERE id = ? ";
    // language=SQL
    private final String SQL_DELETE_FILM_BY_ID = "DELETE FROM films WHERE id = ?";

    //language=SQL
    private final String SQL_SELECT_FILMS_BY_ACTORS = "SELECT * FROM films WHERE actors = ?";

    public FilmsJdbcTemplteDaoImpl() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ru.itis\\spring\\context.xml");
         DataSource dataSource = (DataSource) applicationContext.getBean(DataSource.class);
         this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private RowMapper<Film> movieRowMapper = new RowMapper<Film>() {
        public Film mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Film.Builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString("name"))
                    .releaseDate(resultSet.getString("releasedate"))
                    .genre(resultSet.getString("genre"))
                    .country(resultSet.getString("country"))
                    .producer(resultSet.getString("producer"))
                    .lasting(resultSet.getDouble("lasting"))
                    .description(resultSet.getString("description"))
                    .actors(resultSet.getString("actors"))
                    .picture(resultSet.getString("picture"))
                    .build();
         }
    };

    public Film find(int id) {
        List<Film> movies = jdbcTemplate.query(SQL_SELECT_FILM_BY_ID,movieRowMapper,id);
        return movies.get(0);
    }

    public List<Film> findByName(String name) {
        return jdbcTemplate.query(SQL_SELECT_FILMS_BY_NAME,movieRowMapper,name);
    }

    public List<Film> findByCountry(String country) {
        return jdbcTemplate.query(SQL_SELECT_FILMS_BY_COUNTRY,movieRowMapper,country);
    }

    public List<Film> findByProducer(String producer) {
        return jdbcTemplate.query(SQL_SELECT_FILMS_BY_PRODUCER,movieRowMapper,producer);
    }

    public List<Film> findByGenre(String genre) {
        return jdbcTemplate.query(SQL_SELECT_FILMS_BY_GENRE,movieRowMapper,genre);
    }

    public List<Film> findByActors(String actorsName) {
        return jdbcTemplate.query(SQL_SELECT_FILMS_BY_ACTORS,movieRowMapper,actorsName);
    }

    public int save( Film film) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // параметры:
        final String name = film.getName();
        final String releaseDate = film.getReleaseDate();
        final String genre = film.getGenre();
        final String country = film.getCountry();
        final String producer = film.getProducer();
        final Double lasting = film.getLasting();
        final String description = film.getDescription();
        final String actors = film.getActors();
        final String picture = film.getPicture();

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                        PreparedStatement ps =
                                connection.prepareStatement(SQL_INSERT_FILM, new String[]{"id"});
                        ps.setString(1, name);
                        ps.setString(2, releaseDate);
                        ps.setString(3, genre);
                        ps.setString(4, country);
                        ps.setString(5, producer);
                        ps.setDouble(6, lasting);
                        ps.setString(7, description);
                        ps.setString(8, actors);
                        ps.setString(9, picture);
                    return ps;
                    }
                }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    public void update(Film film) {
        final int id = film.getId();
        final String name = film.getName();
        final String releaseDate = film.getReleaseDate();
        final String genre = film.getGenre();
        final String country = film.getCountry();
        final String producer = film.getProducer();
        final Double lasting = film.getLasting();
        final String description = film.getDescription();
        final String actors = film.getActors();
        final String picture = film.getPicture();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_FILM_BY_ID);
                ps.setString(1, name);
                ps.setString(2, releaseDate);
                ps.setString(3, genre);
                ps.setString(4, country);
                ps.setString(5, producer);
                ps.setDouble(6, lasting);
                ps.setString(7, description);
                ps.setString(8, actors);
                ps.setString(9, picture);
                ps.setInt(  10, id);
                return ps;
            }
        });
    }

    public void delete(int id) {
        final int deleteId = id;
        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps =
                                connection.prepareStatement(SQL_DELETE_FILM_BY_ID);
                        ps.setInt(1,deleteId);
                        return ps;
                    }
                }
        );
    }

    public List<Film> findAll() {
       return jdbcTemplate.query(SQL_SELECT_ALL,movieRowMapper);
    }
}
