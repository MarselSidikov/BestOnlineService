package jdbc;

import dao.ProductDao;
import models.Product;


import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Марат on 10.05.2017.
 */
public class ProductJdbcTemplate implements ProductDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;

    public void setDataSource(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //language=SQL
    private final String SQL_SELECT_PRODUCT_BY_ID = "SELECT * FROM PRODUCT WHERE id = ?";

    //language=SQL
    private final String SQL_SELECT_PRODUCT_BY_NAME = "SELECT * FROM PRODUCT WHERE name = ?";

    //language=SQL
    private final String SQL_SELECT_PRODUCT_BY_MANUFACTURER = "SELECT * FROM PRODUCT WHERE manufacturer = ?";

    //language=SQL
    private final String SQL_SELECT_PRODUCT_BY_DATE_RELEASE = "SELECT * FROM PRODUCT WHERE date_release = ?";

    //language=SQL
    private final String SQL_SELECT_PRODUCT_BY_PRICE = "SELECT * FROM PRODUCT WHERE price = ?";




    //language=SQL
    private final String SQL_INSERT_PRODUCT = "INSERT INTO PRODUCT(id, name, manufacturer, date_release, price)"
            + "VALUES (id, name, manufacturer, date_release, price)";

    // language=SQL
    private final String SQL_UPDATE_PRODUCT_BY_ID = "UPDATE Product SET name = :name ," +
            " manufacturer = :manufacturer , date_release = :date_release , price = :price WHERE id = :id ";

    //

    // language=SQL
    private final String SQL_DELETE_PRODUCT_BY_ID = "DELETE FROM PRODUCT WHERE id = :id";

    private RowMapper<Product> rowMapper = new RowMapper<Product>() {
        @Override
        public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
            Product book = new Product();
            Product.setId(rs.getInt("id"));
            book.setComment(rs.getString("comment"));
            book.setDateRelease(rs.getDate("date_release"));
            book.setTitle(rs.getString("title"));
            book.setAuthorId(rs.getInt("author_id"));
            return book;
        }
    };

    @Override
    public void insert(Product product) {
        String sql = "INSERT INTO product(id, date_release, manufacturer, price) VALUES(?,?,?,?,?)";
        jdbcTemplate.update(sql, getPreparedStatementSetter(product));
    }

    @Override
    public void update(Product product) {
        String sql = "UPDATE book SET title=?, comment=?, date_release=?, author_id=? WHERE id=?";
        jdbcTemplate.update(sql, getPreparedStatementSetter(product));
    }

    @Override
    public void delete(Product product) {
        jdbcTemplate.update("DELETE FROM product WHERE id=?", product.getId());
    }

    @Override
    public Product getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM Product WHERE id=?", rowMapper, id);
    }

    @Override
    public List<Product> getAll() {
        return jdbcTemplate.query("SELECT * FROM Product", rowMapper);
    }

    @Override
    public List<Product> findByCriteria(BookSearchCriteria criteria) {
        if (criteria.isEmpty()) {
            return getAll();
        }
        String sql = "SELECT * FROM book WHERE true";
        if (criteria.getComment() != null) {
            sql += " AND comment=:comment";
        }
        if (criteria.getTitle() != null) {
            sql += " AND title=:title";
        }
        if (criteria.getMaxDateRelease() != null) {
            sql += " AND date_release<:maxDateRelease";
        }
        if (criteria.getMinDateRelease() != null) {
            sql += " AND date_release>:minDateRelease";
        }
        if (criteria.getAuthorId() != null) {
            sql += " AND author_id=:authorId";
        }
        BeanPropertySqlParameterSource namedParameters = new BeanPropertySqlParameterSource(criteria);
        return namedTemplate.query(sql, namedParameters, rowMapper);
    }

    private PreparedStatementSetter getPreparedStatementSetter(final Product product) {
        return new PreparedStatementSetter() {
            @Override
            public void setValues(PreparedStatement ps) throws SQLException {
                int i = 0;
                ps.setString(++i, product.getTitle());
                ps.setString(++i, book.getComment());
                ps.setDate(++i, new java.sql.Date(book.getDateRelease().getTime()));
                ps.setInt(++i, book.getAuthorId());
                ps.setInt(++i, book.getId());
            }
        };
    }

}


