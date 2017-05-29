package ru.itis.dao.impl;

import ru.itis.dao.ProductDao;
import ru.itis.models.Product;


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

import static javax.swing.UIManager.get;

/**
 * Created by Марат on 10.05.2017.
 */
public class ProductDaoJdbcImpl implements ProductDao {

    private JdbcTemplate jdbcTemplate;
    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public ProductDaoJdbcImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }
    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM PRODUCT";
    //language=SQL
    private final String SQL_SELECT_PRODUCT_BY_ID = "SELECT * FROM PRODUCT WHERE id = id";

    //language=SQL
    private final String SQL_SELECT_PRODUCT_BY_NAME = "SELECT * FROM PRODUCT WHERE name = ?";

    //language=SQL
    private final String SQL_SELECT_PRODUCT_BY_MANUFACTURER = "SELECT * FROM PRODUCT WHERE manufacturer = ?";

    //language=SQL
    private final String SQL_SELECT_PRODUCT_BY_DATE_RELEASE = "SELECT * FROM PRODUCT WHERE date_release = ?";

    //language=SQL
    private final String SQL_SELECT_PRODUCT_BY_PRICE = "SELECT * FROM PRODUCT WHERE price = ?";

    //language=SQL
    private final String SQL_SELECT_PRODUCT_BY_NAME_AND_PRICE = "SELECT * FROM PRODUCT " +
            "WHERE name = :name AND price = :price";


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


        public Product find(int id) {
            List<Product> products = jdbcTemplate.query(SQL_SELECT_PRODUCT_BY_ID, productRowMapper, id);
            return products.get(0);
        }



        public int save(Product model) {
            MapSqlParameterSource params = new MapSqlParameterSource()
                    .addValue("name", model.getName())
                    .addValue("dateRelease", model.getDateRelease())
                    .addValue("manufacturer", model.getManufacturer());
                    /*.addValue("price", model.getPrice());*/
            final KeyHolder holder = new GeneratedKeyHolder();
            jdbcTemplate.update(SQL_INSERT_PRODUCT, params, holder, new String[]{"id"});
            Number generetedId = holder.getKey();
           /* Number setId Number.intValue();*/
            return generetedId.intValue();
        }

        public void update(Product model) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", model.getId());
            params.put("name", model.getName());
            params.put("dateRelease", model.getDateRelease());
            params.put("manufacturer", model.getManufacturer());
            params.put("Price", model.getPrice());

            namedJdbcTemplate.update(SQL_UPDATE_PRODUCT_BY_ID, params);
        }


        public void delete(int id) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("id", id);
            namedJdbcTemplate.update(SQL_DELETE_PRODUCT_BY_ID, params);
        }
    public List<Product> findByName(String name) {
        Map<String,Object> params = new HashMap<String,Object>();
        params.put("name",name);
        return namedJdbcTemplate.query(SQL_SELECT_PRODUCT_BY_NAME,params,productRowMapper);
    }


        public List<Product> findAll() {
            return namedJdbcTemplate.query(SQL_SELECT_ALL, productRowMapper);

        }

        public List<Product> findByManufacturer(String manufacturer) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("manufacturer", manufacturer);
            return namedJdbcTemplate.query(SQL_SELECT_PRODUCT_BY_MANUFACTURER, params, productRowMapper);
        }

        public List<Product> findByDateRelease(String dateRelease) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("dateRelease", dateRelease);
            return namedJdbcTemplate.query(SQL_SELECT_PRODUCT_BY_DATE_RELEASE, params, productRowMapper);
        }

        public List<Product> findByPrice(int price) {
            Map<String, Object> params = new HashMap<String, Object>();
            params.put("Price", price);
            return namedJdbcTemplate.query(SQL_SELECT_PRODUCT_BY_PRICE, params, productRowMapper);
        }


    public List<Product> findAllProductsByNameAndPrice(String name, int price) {
        Map<String, Object> params = new HashMap<>();
        params.put("name", name);
        params.put("price", price);
        return namedJdbcTemplate.query(SQL_SELECT_PRODUCT_BY_NAME_AND_PRICE, params, productRowMapper);
    }

    }




