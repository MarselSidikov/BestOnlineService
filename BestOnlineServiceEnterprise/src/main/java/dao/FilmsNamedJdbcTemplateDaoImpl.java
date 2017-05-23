package dao;

import models.Film;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 10.05.2017
 * FilmsNamedJdbcTemplateDaoImpl @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public class FilmsNamedJdbcTemplateDaoImpl implements FilmsDao {

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public FilmsNamedJdbcTemplateDaoImpl(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM films";
    // language=SQL
    private final String SQL_SELECT_FILM_BY_ID = "SELECT * FROM films WHERE id = :id";
    // language=SQL
    private final String SQL_SELECT_FILMS_BY_NAME = "SELECT * FROM films WHERE name = :name";
    // language=SQL
    private final String SQL_SELECT_FILMS_BY_COUNTRY = "SELECT * FROM films WHERE country = :country";
    // language=SQL
    private final String SQL_SELECT_FILMS_BY_PRODUCER = "SELECT * FROM films WHERE producer = :producer";
    // language=SQL
    private final String SQL_SELECT_FILMS_BY_GENRE = "SELECT * FROM films WHERE genre = :genre";
    // language=SQL
    private final String SQL_INSERT_FILM = "INSERT INTO films(name,releasedate,genre,country,producer,lasting,description,actors,picture) VALUES " +
            "(:name , :releasedate , :genre , :country , :producer , :lasting , :description , :actors , :picture)";
    // language=SQL
    private final String SQL_UPDATE_FILM_BY_ID = "UPDATE films SET name = :name , releasedate = :releasedate ," +
            " genre = :genre , country = :country , producer = :producer , lasting = :lasting , description = :description , actors = :actors , picture = :picture WHERE id = :id ";
    // language=SQL
    private final String SQL_DELETE_FILM_BY_ID = "DELETE FROM films WHERE id = :id";

    //language=SQL
    private final String SQL_SELECT_FILMS_BY_ACTORS = "SELECT * FROM films WHERE actors = :actors";

    private RowMapper<Film> movieRowMapper = new RowMapper<Film>() {
        public Film mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Film.Builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString("name"))
                    .releaseDate(resultSet.getString("releasedate"))
                    .genre(resultSet.getString("genre"))
                    .country(resultSet.getString("country"))
                    .producer(resultSet.getString("producer"))
                    .lasting(resultSet.getInt("lasting"))
                    .description(resultSet.getString("description"))
                    .actors(resultSet.getString("actors"))
                    .picture(resultSet.getString("picture"))
                    .build();
        }
    };

    public Film find(int id) {
        Map<String, Object> params= new HashMap<String,Object>();
        params.put("id",id);
        List<Film> movies = namedJdbcTemplate.query(SQL_SELECT_FILM_BY_ID,params,movieRowMapper);
        return movies.get(0);
    }

    public List<Film> findByName(String name) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",name);
        return namedJdbcTemplate.query(SQL_SELECT_FILMS_BY_NAME,params,movieRowMapper);
    }

    public List<Film> findByCountry(String country) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("country",country);
        return namedJdbcTemplate.query(SQL_SELECT_FILMS_BY_COUNTRY,params,movieRowMapper);
    }

    public List<Film> findByProducer(String producer) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("producer",producer);
        return namedJdbcTemplate.query(SQL_SELECT_FILMS_BY_PRODUCER,params,movieRowMapper);
    }

    public List<Film> findByGenre(String genre) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("genre",genre);
        return namedJdbcTemplate.query(SQL_SELECT_FILMS_BY_GENRE,params,movieRowMapper);
    }

    public List<Film> findByActors(String actorsName) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("actors",actorsName);
        return namedJdbcTemplate.query(SQL_SELECT_FILMS_BY_ACTORS,params,movieRowMapper);
    }

    public int save(Film film) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", film.getName())
                .addValue("releasedate", film.getReleaseDate())
                .addValue("genre", film.getGenre())
                .addValue("country", film.getCountry())
                .addValue("producer", film.getProducer())
                .addValue("lasting", film.getLasting())
                .addValue("description", film.getDescription())
                .addValue("actors", film.getActors())
                .addValue("picture", film.getPicture());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_INSERT_FILM, params, holder, new String[]{"id"});
        Number generetedId = holder.getKey();
        return generetedId.intValue();


    }

    public void update(Film film) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("id" , film.getId());
        params.put("name" , film.getName());
        params.put("releasedate" , film.getReleaseDate());
        params.put("genre" , film.getGenre());
        params.put("country" , film.getCountry());
        params.put("producer" , film.getProducer());
        params.put("lasting" , film.getLasting());
        params.put("description" , film.getDescription());
        params.put("actors" , film.getActors());
        params.put("picture" , film.getPicture());

        namedJdbcTemplate.update(SQL_UPDATE_FILM_BY_ID,params);
    }

    public void delete(int id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        namedJdbcTemplate.update(SQL_DELETE_FILM_BY_ID,params);
    }

    public List<Film> findAll() {
        return namedJdbcTemplate.query(SQL_SELECT_ALL,movieRowMapper);
    }
}
