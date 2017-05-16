package dao;

import models.Login;
import models.Token;
import models.UserAuth;
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
 * 13.05.2017
 * LoginNamedJdbcTemplate
 *
 * @author Aivar Yusupov
 * @version v0.1 /
 */

 public class LoginNamedJdbcTemplate implements BaseLoginDao {

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM service_login";
    //language=SQL
 	private final String SQL_SELECT_LOGIN_BY_ID = "SELECT * FROM service_login WHERE id = :id";
    //language=SQL
    private final String SQL_INSERT_LOGIN = "INSERT INTO service_login(login, password) VALUES (:login, :password)";
    //language=SQL
    private final String SQL_UPDATE_LOGIN_BY_ID = "";
    //language=SQL
    private final String SQL_DELETE_LOGIN_BY_ID = "DELETE FROM service_login WHERE id = :id";
    //language=SQL
    private final String SQL_SELECT_BY_LOGIN = "SELECT * FROM service_login WHERE login = :login";

	private NamedParameterJdbcTemplate namedJdbcTemplate;

    public LoginNamedJdbcTemplate(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<Login> loginRowMapper = new RowMapper<Login>() {
        public Login mapRow(ResultSet resultSet, int i) throws SQLException {
            Login login = new Login.Builder()
                    .id(resultSet.getInt(1))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .build();
            return login;
        }
    };

    public Login find(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        List<Login> login = namedJdbcTemplate.query(SQL_SELECT_LOGIN_BY_ID, params, loginRowMapper);
        return login.get(0);
    }

    public int save(Login model) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("login", model.getLogin())
                .addValue("password", model.getPassword());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_INSERT_LOGIN, params, holder, new String[]{"id"});
        Number number = holder.getKey();
        return number.intValue();
    }

    public void update(Login model) {
        Map<String, Object> params = new HashMap<String, Object>();
        /*

         */
        namedJdbcTemplate.update(SQL_UPDATE_LOGIN_BY_ID, params);
    }

    public void delete(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        namedJdbcTemplate.update(SQL_DELETE_LOGIN_BY_ID, params);
    }

    public List<Token> findAll() {
        return namedJdbcTemplate.query(SQL_SELECT_ALL, loginRowMapper);
    }

    public Login findByLogin(String login) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("login", login);
        List<Login> login = namedJdbcTemplate.query(SQL_SELECT_BY_LOGIN, params, loginRowMapper);
        return login.get(0);
    }

 }