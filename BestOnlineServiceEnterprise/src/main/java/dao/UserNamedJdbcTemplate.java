package dao;

import models.User;
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
 * 24.05.2017
 * UserNamedJdbcTemplate
 *
 * @author Aivar Yusupov
 * @version 0.1
 */
public class UserNamedJdbcTemplate implements UsersDao {

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM user_data";
    //language=SQL
    private final String SQL_SELECT_USER_BY_ID = "SELECT * FROM user_data WHERE id = :id";
    //language=SQL
    private final String SQL_INSERT_USER = "INSERT INTO user_data(login,password) VALUES (:login, :password)";
    //language=SQL
    private final String SQL_UPDATE_USER_BY_ID = "UPDATE user_data SET login = :login, password = :password, " +
            "WHERE id = :id ";
    //language=SQL
    private final String SQL_DELETE_USER_BY_ID = "DELETE FROM user_data WHERE id = :id";
    //language=SQL
    private final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM user_data WHERE login = :login";

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public UserNamedJdbcTemplate(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<User> userRowMapper = new RowMapper<User>() {
        public User mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = new User.Builder()
                    .id(resultSet.getInt(1))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .name(resultSet.getString("name"))
                    .age(resultSet.getInt("age"))
                    .build();
            return user;
        }
    };

    @Override
    public User find(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        List<User> user = namedJdbcTemplate.query(SQL_SELECT_USER_BY_ID, params, userRowMapper);
        return user.get(0);
    }

    @Override
    public int save(User user) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("login", user.getLogin())
                .addValue("password", user.getPassword())
                .addValue("name", user.getName())
                .addValue("age", user.getAge());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_INSERT_USER, params, holder, new String[]{"id"});
        Number number = holder.getKey();
        return number.intValue();
    }

    @Override
    public void update(User user) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", user.getId());
        params.put("login", user.getLogin());
        params.put("password", user.getPassword());
        params.put("name", user.getName());
        params.put("age", user.getAge());
        namedJdbcTemplate.update(SQL_UPDATE_USER_BY_ID, params);
    }

    @Override
    public void delete(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        namedJdbcTemplate.update(SQL_DELETE_USER_BY_ID, params);
    }

    @Override
    public List<User> findAll() {
        return namedJdbcTemplate.query(SQL_SELECT_ALL, userRowMapper);
    }


    @Override
    public User findUserByLogin() {
        return null;
    }

    @Override
    public List<User> findUsersByName() {
        return null;
    }

    @Override
    public List<User> findUsersByAge() {
        return null;
    }
}

