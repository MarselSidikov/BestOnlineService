package dao;

import models.Product;


import org.springframework.jdbc.core.JdbcTemplate;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.SQLException;
import javax.sql.DataSource;
import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.sun.xml.internal.ws.policy.sourcemodel.wspolicy.XmlToken.Name;

/**
 * Created by Марат on 10.05.2017.
 */
public class ProductJdbcTemplate implements ProductDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public ProductJdbcTemplate(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM Product";
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

    private RowMapper<Product> productRowMapper = new RowMapper<Product>() {
        public Product mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Product.Builder()
                    .id(resultSet.getInt(1))
                    .name(resultSet.getString("name"))
                    .dateRelease(resultSet.getString("dateRelease"))
                    .manufacturer(resultSet.getString("manufacturer"))
                    .price(resultSet.getInt("price"))
                    .build();
        }
    };

/*
        public Product find(int id) {
            List<Product> products = jdbcTemplate.query(SQL_SELECT_PRODUCT_BY_ID, productRowMapper, id);
            return products.get(0);
        }



        public int save(Product model) {
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("name", Product.getName())
                    .addValue("dateRelease", Product.getDateRelease())
                    .addValue("manufacturer", Product.getManufacturer())
                    .addValue("price", Product.getPrice());
            final KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(SQL_INSERT_PRODUCT, params, holder, new String[]{"id"});
            Number generetedId = holder.getKey();
            return generetedId.intValue();
        }

        public void update(Product model) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", Product.getId());
            params.put("name", Product.getName());
            params.put("dateRelease", Product.getDateRelease());
            params.put("genre", Product.getManufacturer());
            params.put("country", Product.getPrice());

            ProductJdbcTemplate.update(SQL_UPDATE_PRODUCT_BY_ID, params);
        }


        public void delete(int id) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            JdbcTemplate.update(SQL_DELETE_PRODUCT_BY_ID, params);
        }


        public List<Product> findAll() {
            return JdbcTemplate.query(SQL_SELECT_ALL, productRowMapper);

        }

        public List<Product> findById(int id) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            List<Product> product = JdbcTemplate.query(SQL_SELECT_PRODUCT_BY_ID, params, productRowMapper);
        }

        public List<Product> findByManufacturer(String manufacturer) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("manufacturer", manufacturer);
            return JdbcTemplate.query(SQL_SELECT_PRODUCT_BY_MANUFACTURER, params, productRowMapper);
        }

        public List<Product> findByDateRelease(String dateRelease) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("dateRelease", dateRelease);
            return JdbcTemplate.query(SQL_SELECT_PRODUCT_BY_DATE_RELEASE, params, productRowMapper);
        }

        public List<Product> findByPrice(int price) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("Price", price);
            return JdbcTemplate.query(SQL_SELECT_PRODUCT_BY_PRICE, params, productRowMapper);
        }

    } */




