package dao;

import models.Movie;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
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
 * AfishaNamedJdbcTemplateDaoImpl @author Ayupov Ayaz (First Software Engineering Platform)
 *
 * @version v1.0 /
 */
public class AfishaNamedJdbcTemplateDaoImpl implements BaseAfishaDao {

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public AfishaNamedJdbcTemplateDaoImpl() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ru.itis\\spring\\context.xml");
        DataSource dataSource = context.getBean(DataSource.class);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    // language=SQL
    private final String SQL_SELECT_MOVIE_BY_ID = "SELECT * FROM afisha WHERE id = :id";
    // language=SQL
    private final String SQL_SELECT_MOVIE_BY_NAME = "SELECT * FROM afisha WHERE name = :name";
    // language=SQL
    private final String SQL_SELECT_MOVIE_BY_COUNTRY = "SELECT * FROM afisha WHERE country = :country";
    // language=SQL
    private final String SQL_SELECT_MOVIE_BY_PRODUCER = "SELECT * FROM afisha WHERE producer = :producer";
    // language=SQL
    private final String SQL_SELECT_MOVIE_BY_GENRE = "SELECT * FROM afisha WHERE genre = :genre";
    // language=SQL
    private final String SQL_INSERT_MOVIE = "INSERT INTO afisha(name,releasedate,genre,country,producer,lasting,description,actors,picture) VALUES " +
            "(:name , :releasedate , :genre , :country , :producer , :lasting , :description , :actors , :picture)";
    // language=SQL
    private final String SQL_UPDATE_MOVIE_BY_ID = "UPDATE afisha SET name = :name , releasedate = :releasedate ," +
            " genre = :genre , country = :country , producer = :producer , lasting = :lasting , description = :description , actors = :actors , picture = :picture WHERE id = :id ";
    // language=SQL
    private final String SQL_DELETE_MOVIE_BY_ID = "DELETE FROM afisha WHERE id = :id";

    //language=SQL
    private final String SQL_SELECT_MOVIE_BY_ACTORS = "SELECT * FROM afisha WHERE actors = :actors";

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
        Map<String, Object> params= new HashMap<String,Object>();
        params.put("id",id);
        List<Movie> movies = namedJdbcTemplate.query(SQL_SELECT_MOVIE_BY_ID,params,movieRowMapper);
        return movies.get(0);
    }

    public List<Movie> findByName(String name) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",name);
        return namedJdbcTemplate.query(SQL_SELECT_MOVIE_BY_NAME,params,movieRowMapper);
    }

    public List<Movie> findByCountry(String country) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("country",country);
        return namedJdbcTemplate.query(SQL_SELECT_MOVIE_BY_COUNTRY,params,movieRowMapper);
    }

    public List<Movie> findByProducer(String producer) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("producer",producer);
        return namedJdbcTemplate.query(SQL_SELECT_MOVIE_BY_PRODUCER,params,movieRowMapper);
    }

    public List<Movie> findByGenre(String genre) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("genre",genre);
        return namedJdbcTemplate.query(SQL_SELECT_MOVIE_BY_GENRE,params,movieRowMapper);
    }

    public List<Movie> findByActors(String actorsName) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("actors",actorsName);
        return namedJdbcTemplate.query(SQL_SELECT_MOVIE_BY_ACTORS,params,movieRowMapper);
    }

    public int save(Movie movie) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", movie.getName())
                .addValue("releasedate", movie.getReleaseDate())
                .addValue("genre", movie.getGenre())
                .addValue("country", movie.getCountry())
                .addValue("producer", movie.getProducer())
                .addValue("lasting", movie.getLasting())
                .addValue("description", movie.getDescription())
                .addValue("actors", movie.getActors())
                .addValue("picture", movie.getPicture());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_INSERT_MOVIE, params, holder, new String[]{"id"});
        Number generetedId = holder.getKey();
        return generetedId.intValue();


    }

    public void update(Movie movie) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("id" , movie.getId());
        params.put("name" , movie.getName());
        params.put("releasedate" , movie.getReleaseDate());
        params.put("genre" , movie.getGenre());
        params.put("country" , movie.getCountry());
        params.put("producer" , movie.getProducer());
        params.put("lasting" , movie.getLasting());
        params.put("description" , movie.getDescription());
        params.put("actors" , movie.getActors());
        params.put("picture" , movie.getPicture());

        namedJdbcTemplate.update(SQL_UPDATE_MOVIE_BY_ID,params);
    }

    public void delete(int id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        namedJdbcTemplate.update(SQL_DELETE_MOVIE_BY_ID,params);
    }
}
