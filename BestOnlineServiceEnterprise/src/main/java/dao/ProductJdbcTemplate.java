package dao;

import dao.ProductDao;
import models.Product;


import org.springframework.jdbc.core.JdbcTemplate;

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
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Product.Builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString("name"))
                    .date_release(resultSet.getString("date_release"))
                    .manufacturer(resultSet.getString("manufacturer"))
                    .Price(resultSet.getInt("price"));
        }


        public void insert(Product product) {
            String sql = "INSERT INTO product(id, date_release, manufacturer, price) VALUES(?,?,?,?,?)";
            jdbcTemplate.update(sql, (product));
        }


        public void update(Product product) {
            String sql = "UPDATE product SET title=?, comment=?, date_release=?, author_id=? WHERE id=?";
            jdbcTemplate.update(sql, (product));
        }


        public void delete(Product product) {
            jdbcTemplate.update("DELETE FROM product WHERE id=?", product.getId());
        }


        public Product getById(int id) {
            return jdbcTemplate.queryForObject("SELECT * FROM Product WHERE id=?", rowMapper, id);
        }


        public List<Product> getAll() {
            return jdbcTemplate.query("SELECT * FROM Product", rowMapper);
        }
    };
}




