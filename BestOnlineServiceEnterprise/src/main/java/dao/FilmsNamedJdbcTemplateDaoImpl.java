package dao;

import models.Film;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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
    private JdbcTemplate jdbcTemplate;


    public FilmsNamedJdbcTemplateDaoImpl(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT films.id AS id, films.name AS film_name," +
            " films.releasedate AS releasedate, films.country AS country, " +
            "films.description AS description, films.lasting AS lasting," +
            " films.producer AS producer, films.picture AS picture, actors.id AS actor_id, actors.id_film AS actors_id_film," +
            " actors.actor_name AS actor_name, genres.genre AS genre, genres.id AS genre_id " +
            "FROM films JOIN actors ON films.id = actors.id_film \n " +
            "JOIN genres ON films.id = genres.id_film ";

    // language=SQL
    private final String SQL_SELECT_FILM_BY_ID = SQL_SELECT_ALL + "WHERE films.id = :id";

    // language=SQL
    private final String SQL_SELECT_FILMS_BY_NAME = SQL_SELECT_ALL + "WHERE films.name = :name";

    // language=SQL
    private final String SQL_SELECT_FILMS_BY_COUNTRY = SQL_SELECT_ALL + "WHERE films.country = :country";

    // language=SQL
    private final String SQL_SELECT_FILMS_BY_PRODUCER = SQL_SELECT_ALL + "WHERE films.producer = :producer";

    // language=SQL
    private final String SQL_SELECT_FILMS_BY_GENRE = SQL_SELECT_ALL + "WHERE genres.genre = :genre";

    //language=SQL
    private final String SQL_SELECT_FILMS_BY_ACTORS = SQL_SELECT_ALL + "WHERE actors.actor_name = :actor_name";

    // language=SQL
    private final String SQL_INSERT_FILM = "INSERT INTO films(name,releasedate,country,producer,lasting,description,picture) VALUES " +
            "(:film_name , :releasedate  , :country , :producer , :lasting , :description , :picture)";

    // language=SQL
    private final String SQL_UPDATE_FILM_BY_ID = "UPDATE films SET name = :name , releasedate = :releasedate ," +
            " country = :country , producer = :producer , lasting = :lasting , description = :description, picture = :picture" +
            " WHERE id = :id ";

    // language=SQL
    private final String SQL_DELETE_FILM_BY_ID = "DELETE FROM films WHERE id = :id";

    // language=SQL
    private final String SQL_INSER_ACTORS = "INSERT INTO actors ( id_film, actor_name) VALUES ( :id_film, :actor_name)";

    // language=SQL
    private final String SQL_DELETE_ACTORS_BY_FILM_ID = "DELETE FROM actors WHERE id_film = :id_film ";

    // language=SQL
    private final String SQL_DELETE_GENRES_BY_FILM_ID = "DELETE FROM genres WHERE id_film = :id_film ";

    //language=SQL
    private final String SQL_SELECT_ACTORS_ALL = "SELECT * FROM actors ";

    // language=SQL
    private final String SQL_INSER_GENRE = "INSERT INTO genres ( id_film, genre) VALUES ( :id_film, :genre)";


    private ResultSetExtractor <List<Film>> resultSetExtractor = new ResultSetExtractor<List<Film>>() {
        @Override
        public List<Film> extractData(ResultSet resultSet) throws SQLException, DataAccessException {

            Map<Integer, Film> films = new HashMap<>();

            // Map<id_actor , films>
            Map<Integer, Map<Integer, Film> > actors = new HashMap<>();

            //Map<id_genre , films>
            Map<Integer, Map<Integer, Film>> genres = new HashMap<>();

            while(resultSet.next()){
                //films:
                int filmsId = resultSet.getInt("id");
                if(films.get(filmsId) == null){
                    String filmName = resultSet.getString("film_name");
                    String releaseDate = resultSet.getString("releasedate");
                    String country = resultSet.getString("country");
                    String producer = resultSet.getString("producer");
                    int lasting = resultSet.getInt("lasting");
                    String description = resultSet.getString("description");
                    String picture = resultSet.getString("picture");

                    String actorsAsString = resultSet.getString("actor_name");
                    String genreAsString = resultSet.getString("genre");

                    String actorsAsArray[] = actorsAsString.split(" ");
                    String genresAsArray[] = genreAsString.split(" ");

                    List<String> film_actors = new ArrayList<>();
                    List<String> film_genres = new ArrayList<>();

                    for(int i = 0; i < actorsAsArray.length; i++){
                        film_actors.add(actorsAsArray[i]);
                    }
                    for(int i = 0; i < genresAsArray.length; i++){
                        film_genres.add(genresAsArray[i]);
                    }
                    // Создаем объект
                    Film newFilm = new Film.Builder()
                            .id(filmsId)
                            .name(filmName)
                            .releaseDate(releaseDate)
                            .country(country)
                            .producer(producer)
                            .description(description)
                            .lasting(lasting)
                            .picture(picture)
                            .actors(film_actors)
                            .genre(film_genres).build();
                    //кладем в Map
                    films.put(filmsId,newFilm);
                }
                //genres: Map<id_genre , films>
                int idGenre = resultSet.getInt("genre_id");
                String genre = resultSet.getString("genre");
                if(genres.get(idGenre) == null){
                    genres.put(idGenre, films);
                    films.get(filmsId).getGenres().add(genre);
                }


                if(genres.get(idGenre).get(filmsId) == null){
                    genres.get(idGenre).put(filmsId,films.get(filmsId));
                    films.get(filmsId).getGenres().add(genre);
                }

                //actors:
                int actorId = resultSet.getInt("actor_id");
                String actorName = resultSet.getString("actor_name");
                // если нет актера, то мы его создаем
                if(actors.get(actorId) == null){
                    actors.put(actorId, films);
                    films.get(filmsId).getActors().add(actorName);
                }
                // если нам попался существующий актер, который снимался и в других фильмах
                // то мы его добавляем в Map <filmsId, film>
                 if(actors.get(actorId).get(filmsId) == null){
                    actors.get(actorId).put(filmsId,films.get(filmsId));
                     films.get(filmsId).getActors().add(actorName);
                }
            }

            return new ArrayList<Film>(films.values());
        }
    };

     public Film find(int id) {
        Map<String, Object> params= new HashMap<String,Object>();
        params.put("id",id);
        List<Film> movies = namedJdbcTemplate.query(SQL_SELECT_FILM_BY_ID,params,resultSetExtractor);
        return movies.get(0);
    }

    public List<Film> findByName(String name) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("film_name",name);
        return namedJdbcTemplate.query(SQL_SELECT_FILMS_BY_NAME,params,resultSetExtractor);
    }

    public List<Film> findByCountry(String country) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("country",country);
        return namedJdbcTemplate.query(SQL_SELECT_FILMS_BY_COUNTRY,params,resultSetExtractor);
    }

    public List<Film> findByProducer(String producer) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("producer",producer);
        return namedJdbcTemplate.query(SQL_SELECT_FILMS_BY_PRODUCER,params,resultSetExtractor);
    }

    public List<Film> findByGenre(String genre) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("genre",genre);
        return namedJdbcTemplate.query(SQL_SELECT_FILMS_BY_GENRE,params,resultSetExtractor);
    }

    public List<Film> findByActors(String actorsName) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("actors_name",actorsName);
        return namedJdbcTemplate.query(SQL_SELECT_FILMS_BY_ACTORS,params,resultSetExtractor);
    }

    public int save(Film film) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("film_name", film.getName())
                .addValue("releasedate", film.getReleaseDate())
                .addValue("country", film.getCountry())
                .addValue("producer", film.getProducer())
                .addValue("lasting", film.getLasting())
                .addValue("description", film.getDescription())
                .addValue("picture", film.getPicture());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_INSERT_FILM, params, holder, new String[]{"id"});
        Number generetedId = holder.getKey();
        int idFilm = generetedId.intValue();
        // создаем актеров:
        List<String> actors = film.getActors();
        saveActors(actors,idFilm);
        // создаем жанры:
        List<String> genres = film.getGenres();
        saveGenre(genres,idFilm);
        return idFilm;
    }

    private void saveGenre(List<String> genres,int id_film){
         Map<String, Object> param = new HashMap<>();
         param.put("id_film", id_film);

         for(int i = 0; i < genres.size(); i++){
             param.put("genre",genres.get(i));
             namedJdbcTemplate.update(SQL_INSER_GENRE,param);
         }
    }

    private void saveActors(List<String> actors, int id_film){
            Map<String,Object> param = new HashMap<>();
            param.put("id_film",id_film);

           for(int i = 0; i < actors.size(); i ++){
                param.put("actor_name", actors.get(i));
                namedJdbcTemplate.update(SQL_INSER_ACTORS,param);
           }
        }

    private void updateActorsByIdFilm(List<String> actors, int id_films){
        //если стринг будет из 2х актеров, когда в таблице значений 3, то последний не изменится
        //по этому удаляем всех актеров по id фильма и записываем новые данные.
        deleteActorsByIdFilm(id_films);
        saveActors(actors,id_films);

    }
    private void updateGenreByIdFilm(List<String> genres, int idFilm){
        deleteGenreByIdFilm(idFilm);
        saveGenre(genres,idFilm);
    }

    private void deleteActorsByIdFilm(int idFilm){
        Map<String, Object> param = new HashMap<>();
        param.put("id_film",idFilm);
        namedJdbcTemplate.update(SQL_DELETE_ACTORS_BY_FILM_ID,param);
    }

    private void deleteGenreByIdFilm(int idFilm){
        Map<String, Object> param = new HashMap<>();
        param.put("id_film",idFilm);
        namedJdbcTemplate.update(SQL_DELETE_GENRES_BY_FILM_ID,param);
    }

    public void update(Film film) {
        Map<String,Object> params = new HashMap<String, Object>();
        int id_film = film.getId();
        params.put("id" , id_film);
        params.put("name" , film.getName());
        params.put("releasedate" , film.getReleaseDate());
        params.put("genre" , film.getGenres());
        params.put("country" , film.getCountry());
        params.put("producer" , film.getProducer());
        params.put("lasting" , film.getLasting());
        params.put("description" , film.getDescription());
        params.put("picture" , film.getPicture());
        namedJdbcTemplate.update(SQL_UPDATE_FILM_BY_ID,params);
        List<String> actors = film.getActors();
        updateActorsByIdFilm(actors, id_film);
        List<String> genre = film.getGenres();
        updateGenreByIdFilm(genre,id_film);

    }

    public void delete(int id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        namedJdbcTemplate.update(SQL_DELETE_FILM_BY_ID,params);
        deleteGenreByIdFilm(id);
        deleteActorsByIdFilm(id);
    }

    public List<Film> findAll() {
        return namedJdbcTemplate.query(SQL_SELECT_ALL,resultSetExtractor);
    }
}
