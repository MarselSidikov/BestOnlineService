package dao;

import models.Token;
import models.UserAuth;
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
 * 13.05.2017
 * UserAuthNamedJdbcTemplate
 *
 * @author Aivar Yusupov
 * @version v0.1 /
 */
public class UserAuthNamedJdbcTemplate implements BaseUserAuthDao {

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    public UserAuthNamedJdbcTemplate() {
        ApplicationContext context = new ClassPathXmlApplicationContext("ru.itis\\spring\\context.xml");
        DataSource dataSource = context.getBean(DataSource.class);
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM login";
    //language=SQL
    private final String SQL_SELECT_LOGIN_BY_ID = "SELECT * FROM logins WHERE user_id = :user_id";
    //language=SQL
    private final String SQL_INSERT_LOGIN = "INSERT INTO logins(login,password) VALUES (:login, :password)";
    //language=SQL
    private final String SQL_UPDATE_LOGIN_BY_ID = "UPDATE logins SET login = :login, password = :password, " +
            "WHERE user_id = :user_id ";
    //language=SQL
    private final String SQL_DELETE_LOGIN_BY_ID = "DELETE FROM logins WHERE user_id = :user_id";
    //language=SQL
    private final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM logins WHERE login = :login";
    //language=SQL
    private final String SQL_SELECt_USER_BY_TOKEN = "SELECT * FROM logins WHERE auth_token = auth_token";


    private RowMapper<UserAuth> userAuthRowMapper = new RowMapper<UserAuth>() {
        public UserAuth mapRow(ResultSet resultSet, int i) throws SQLException {
            return null;
                    /*new UserAuth.Builder()
                    .id(resultSet.getInt(1))
                    .login(resultSet.getString("login"))
                    .password(resultSet.getString("password"))
                    .token(resultSet.getObject("token"))
                    .build();*/
        }
    };

    public UserAuth find(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        List<UserAuth> userAuth = namedJdbcTemplate.query(SQL_SELECT_LOGIN_BY_ID, params, userAuthRowMapper);
        return userAuth.get(0);
    }

    public int save(UserAuth model) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("login", model.getLogin())
                .addValue("password", model.getPassword());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_INSERT_LOGIN, params, holder, new String[]{"id"});
        Number number = holder.getKey();
        return number.intValue();
    }

    public void update(UserAuth model) {
        Map<String,Object> params = new HashMap<String, Object>();
        /*
            
         */
        namedJdbcTemplate.update(SQL_UPDATE_LOGIN_BY_ID,params);
    }

    public void delete(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("user_id", id);
        namedJdbcTemplate.update(SQL_DELETE_LOGIN_BY_ID, params);
    }

    public List<UserAuth> findAll() {
        return namedJdbcTemplate.query(SQL_SELECT_ALL, userAuthRowMapper);
    }

    public UserAuth findByLogin(String login) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("login", login);
        List<UserAuth> userAuths = namedJdbcTemplate.query(SQL_SELECT_USER_BY_LOGIN, params, userAuthRowMapper);
        return userAuths.get(0);
    }

    public UserAuth findByToken(Token token) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("auth_token", token);
        List<UserAuth> userAuth = namedJdbcTemplate.query(SQL_SELECt_USER_BY_TOKEN, params, userAuthRowMapper);
        return userAuth.get(0);
    }
}
