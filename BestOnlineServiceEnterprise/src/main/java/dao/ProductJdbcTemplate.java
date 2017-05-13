package dao;

import models.Product;


import org.springframework.jdbc.core.JdbcTemplate;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import javax.sql.DataSource;
import java.sql.*;
import java.util.List;

/**
 * Created by Марат on 10.05.2017.
 */
public class ProductJdbcTemplate implements ProductDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedTemplate;

    public ProductJdbcTemplate(DataSource dataSource) {
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
    private final String SQL_INSERT_PRODUCT = "INSERT INTO PRODUCT(name, manufacturer, date_release, price)"
            + "VALUES(:name, :manufacturer, :date_release, :price)";

    // language=SQL
    private final String SQL_UPDATE_PRODUCT_BY_ID = "UPDATE Product SET name = :name ," +
            " manufacturer = :manufacturer , date_release = :date_release , price = :price WHERE id = :id";

    // language=SQL
    private final String SQL_DELETE_PRODUCT_BY_ID = "DELETE FROM PRODUCT WHERE id = :id";

    private RowMapper<Product> rowMapper = new RowMapper<Product>() {
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Product.Builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .dateRelease(resultSet.getString("date_release"))
                    .manufacturer(resultSet.getString("manufacturer"))
                    .price(resultSet.getInt("price"))
                    .build();
        }
    };

    public Product find(int id) {
        List<Product> products = jdbcTemplate.query(SQL_SELECT_PRODUCT_BY_ID, rowMapper, id);
        return products.get(0);
    }

    public int save(Product model) {
        return 0;
    }

    public void update(Product model) {

    }

    public void delete(int id) {

    }

    public List<Product> findAll() {
        return null;
    }

    public List<Product> findById(int id) {
        return null;
    }

    public List<Product> findByManufacturer(String manufacturer) {
        return null;
    }

    public List<Product> findByDateRelease(String dateRelease) {
        return null;
    }

    public List<Product> findByPrice(int price) {
        return null;
    }
}




