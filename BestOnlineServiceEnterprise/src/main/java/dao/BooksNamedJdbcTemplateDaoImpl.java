package dao;

import models.Book;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.JdbcTemplate;
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
 * 11.05.2017
 *
 * @author Shaikhutdinov Artur (First Software Engineering Platform)
 * @version v1.0
 */
public class BooksNamedJdbcTemplateDaoImpl implements BaseBookDao {
    private NamedParameterJdbcTemplate namedJdbcTemplate;
    private JdbcTemplate template;

    public BooksNamedJdbcTemplateDaoImpl(DataSource dataSource) {
        this.template = new JdbcTemplate(dataSource);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM books";
    // language=SQL
    private final String SQL_SELECT_BOOK_BY_ID = "SELECT * FROM books WHERE id = :id";
    // language=SQL
    private final String SQL_SELECT_BOOKS_BY_NAME = "SELECT * FROM books WHERE name = :name";
    // language=SQL
    private final String SQL_SELECT_BOOKS_BY_AUTHOR = "SELECT * FROM books WHERE author = :author";
    // language=SQL
    private final String SQL_SELECT_BOOKS_BY_TYPE = "SELECT * FROM books WHERE type = :type";
    // language=SQL
    private final String SQL_SELECT_BOOKS_BY_GENRE = "SELECT * FROM books WHERE genre = :genre";
    //language=SQL
    private final String SQL_SELECT_BOOKS_BY_YEAROFISSUE = "SELECT * FROM books WHERE yearOfIssue = :yearOfIssue";
    // language=SQL
    private final String SQL_INSERT_BOOK = "INSERT INTO books(name, author, type, genre, publishingHouse, yearOfIssue, numberOfPages, language, descriprion) VALUES " +
            "(:name , :author , :type , :genre , :publishingHouse , :yearOfIssue , :numberOfPages , :language , :descriprion)";
    // language=SQL
    private final String SQL_UPDATE_BOOK_BY_ID = "UPDATE books SET name = :name , author = :author , type = :type , genre = :genre , " +
            "publishingHouse = :publishingHouse , yearOfIssue = :yearOfIssue , numberOfPages = :numberOfPages , language = :language , description = :descriprion WHERE id = :id ";
    // language=SQL
    private final String SQL_DELETE_BOOK_BY_ID = "DELETE FROM books WHERE id = :id";


    private RowMapper<Book> bookRowMapper = new RowMapper<Book>() {
        public Book mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Book.Builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString("name"))
                    .author(resultSet.getString("author"))
                    .type(resultSet.getString("type"))
                    .genre(resultSet.getString("genre"))
                    .publishingHouse(resultSet.getString("publishingHouse"))
                    .yearOfIssue(resultSet.getInt("yearOfIssue"))
                    .numberOfPages(resultSet.getInt("numberOfPages"))
                    .language(resultSet.getString("language"))
                    .description(resultSet.getString("description"))
                    .build();
        }
    };

    public Book find(int id) {
        Map<String, Object> params= new HashMap<String,Object>();
        params.put("id",id);
        List<Book> books = namedJdbcTemplate.query(SQL_SELECT_BOOK_BY_ID,params,bookRowMapper);
        return books.get(0);
    }



    public List<Book> findByName(String name) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",name);
        return namedJdbcTemplate.query(SQL_SELECT_BOOKS_BY_NAME,params,bookRowMapper);
    }

    public List<Book> findByAuthor(String author) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("author",author);
        return namedJdbcTemplate.query(SQL_SELECT_BOOKS_BY_AUTHOR,params,bookRowMapper);
    }

    public List<Book> findByType(String type) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("type",type);
        return namedJdbcTemplate.query(SQL_SELECT_BOOKS_BY_TYPE,params,bookRowMapper);
    }

    public List<Book> findByGenre(String genre) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("genre",genre);
        return namedJdbcTemplate.query(SQL_SELECT_BOOKS_BY_GENRE,params,bookRowMapper);
    }

    public List<Book> findByYearOfIssue(int yearOfIssue) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("yearOfIssue",yearOfIssue);
        return namedJdbcTemplate.query(SQL_SELECT_BOOKS_BY_YEAROFISSUE,params,bookRowMapper);
    }

    public int save(Book book) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("name", book.getName())
                .addValue("author", book.getAuthor())
                .addValue("type", book.getType())
                .addValue("genre", book.getGenre())
                .addValue("publishingHouse", book.getPublishingHouse())
                .addValue("yearOfIssue", book.getYearOfIssue())
                .addValue("numberOfPages", book.getNumberOfPages())
                .addValue("language", book.getLanguage())
                .addValue("description", book.getDescription());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_INSERT_BOOK, params, holder, new String[]{"id"});
        Number generetedId = holder.getKey();
        return generetedId.intValue();


    }

    public void update(Book book) {
        Map<String,Object> params = new HashMap<String, Object>();
        params.put("id" , book.getId());
        params.put("name" , book.getName());
        params.put("author" , book.getAuthor());
        params.put("type" , book.getType());
        params.put("genre" , book.getGenre());
        params.put("publishingHouse" , book.getPublishingHouse());
        params.put("yearOfIssue" , book.getYearOfIssue());
        params.put("numberOfPages" , book.getNumberOfPages());
        params.put("language" , book.getLanguage());
        params.put("description" , book.getDescription());

        namedJdbcTemplate.update(SQL_UPDATE_BOOK_BY_ID,params);
    }

    public void delete(int id) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("id",id);
        namedJdbcTemplate.update(SQL_DELETE_BOOK_BY_ID,params);
    }

    public List<Book> findAll() {
        return namedJdbcTemplate.query(SQL_SELECT_ALL,bookRowMapper);
    }
}