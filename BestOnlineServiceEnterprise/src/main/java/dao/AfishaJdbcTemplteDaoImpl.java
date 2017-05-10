package dao;

import models.Movie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import javax.sql.DataSource;
import java.sql.*;
import java.util.Date;
import java.util.List;

/**
 * 08.05.2017
 * AfishaJdbcTemplteDaoImpl @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public class AfishaJdbcTemplteDaoImpl implements BaseAfishaDao {

    private JdbcTemplate jdbcTemplate;


    // language=SQL
    private final String SQL_SELECT_MOVIE_BY_ID = "SELECT * FROM afisha WHERE id = ?";
    // language=SQL
    private final String SQL_SELECT_MOVIE_BY_NAME = "SELECT * FROM afisha WHERE name = ?";
    // language=SQL
    private final String SQL_SELECT_MOVIE_BY_COUNTRY = "SELECT * FROM afisha WHERE country = ?";
    // language=SQL
    private final String SQL_SELECT_MOVIE_BY_PRODUCER = "SELECT * FROM afisha WHERE producer = ?";
    // language=SQL
    private final String SQL_SELECT_MOVIE_BY_GENRE = "SELECT * FROM afisha WHERE genre = ?";
    // language=SQL
    private final String SQL_INSERT_MOVIE = "INSERT INTO afisha(name,releaseDate,genre,country,producer,lasting,description,actors,picture) VALUES " +
            "(? , ? , ? , ? , ? , ? , ? , ? , ?)";
    // language=SQL
    private final String SQL_UPDATE_MOVIE_BY_ID = "UPDATE afisha SET name = ? , releasedate = ? , " +
            "genre = ? , country = ? , producer = ? , lasting = ? , description = ? , actors = ? , picture = ? WHERE id = ? ";
    // language=SQL
    private final String SQL_DELETE_MOVIE_BY_ID = "DELETE FROM afisha WHERE id = ?";

    //language=SQL
    private final String SQL_SELECT_MOVIE_BY_ACTORS = "SELECT * FROM afisha WHERE actors = ?";

    public AfishaJdbcTemplteDaoImpl() {
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("ru.itis\\spring\\context.xml");
         DataSource dataSource = (DataSource) applicationContext.getBean(DataSource.class);
         this.jdbcTemplate = new JdbcTemplate(dataSource);
    }
    private RowMapper<Movie> movieRowMapper = new RowMapper<Movie>() {
        public Movie mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Movie.Builder()
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

    public Movie find(int id) {
        List<Movie> movies = jdbcTemplate.query(SQL_SELECT_MOVIE_BY_ID,movieRowMapper,id);
        return movies.get(0);
    }

    public List<Movie> findByName(String name) {
        return jdbcTemplate.query(SQL_SELECT_MOVIE_BY_NAME,movieRowMapper,name);
    }

    public List<Movie> findByCountry(String country) {
        return jdbcTemplate.query(SQL_SELECT_MOVIE_BY_COUNTRY,movieRowMapper,country);
    }

    public List<Movie> findByProducer(String producer) {
        return jdbcTemplate.query(SQL_SELECT_MOVIE_BY_PRODUCER,movieRowMapper,producer);
    }

    public List<Movie> findByGenre(String genre) {
        return jdbcTemplate.query(SQL_SELECT_MOVIE_BY_GENRE,movieRowMapper,genre);
    }

    public List<Movie> findByActors(String actorsName) {
        return jdbcTemplate.query(SQL_SELECT_MOVIE_BY_ACTORS,movieRowMapper,actorsName);
    }

    public int save( Movie movie) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        // параметры:
        final String name = movie.getName();
        final String releaseDate = movie.getReleaseDate();
        final String genre = movie.getGenre();
        final String country = movie.getCountry();
        final String producer = movie.getProducer();
        final Double lasting = movie.getLasting();
        final String description = movie.getDescription();
        final String actors = movie.getActors();
        final String picture = movie.getPicture();

        jdbcTemplate.update(
                new PreparedStatementCreator() {
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {

                        PreparedStatement ps =
                                connection.prepareStatement(SQL_INSERT_MOVIE, new String[]{"id"});
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

    public void update(Movie movie) {
        final int id = movie.getId();
        final String name = movie.getName();
        final String releaseDate = movie.getReleaseDate();
        final String genre = movie.getGenre();
        final String country = movie.getCountry();
        final String producer = movie.getProducer();
        final Double lasting = movie.getLasting();
        final String description = movie.getDescription();
        final String actors = movie.getActors();
        final String picture = movie.getPicture();
        jdbcTemplate.update(new PreparedStatementCreator() {
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(SQL_UPDATE_MOVIE_BY_ID);
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
                                connection.prepareStatement(SQL_DELETE_MOVIE_BY_ID);
                        ps.setInt(1,deleteId);
                        return ps;
                    }
                }
        );
    }
}
