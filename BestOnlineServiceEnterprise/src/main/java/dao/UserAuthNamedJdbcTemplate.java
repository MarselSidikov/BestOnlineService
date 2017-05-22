package dao;

import models.Token;
import models.User;
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
 * UserAuthNamedJdbcTemplate
 *
 * @author Aivar Yusupov
 * @version v0.1 /
 */
public class UserAuthNamedJdbcTemplate implements BaseUserAuthDao {

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM service_login";
    //language=SQL
    private final String SQL_SELECT_LOGIN_BY_ID = "SELECT * FROM service_login WHERE user_id = :user_id";
    //language=SQL
    private final String SQL_INSERT_LOGIN = "INSERT INTO service_login(login,password) VALUES (:login, :password)";
    //language=SQL
    private final String SQL_UPDATE_LOGIN_BY_ID = "UPDATE service_login SET login = :login, password = :password, " +
            "WHERE user_id = :user_id ";
    //language=SQL
    private final String SQL_DELETE_LOGIN_BY_ID = "DELETE FROM service_login WHERE id = :id";
    //language=SQL
    private final String SQL_SELECT_USER_BY_LOGIN = "SELECT * FROM service_login WHERE login = :login";
    //language=SQL
    private final String SQL_SELECT_USER_BY_TOKEN = "SELECT * FROM service_login WHERE auth_token = auth_token";

    private NamedParameterJdbcTemplate namedJdbcTemplate;
    private UsersDao usersDao;
    private BaseTokenDao tokenDao;

    public UserAuthNamedJdbcTemplate(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }


    private RowMapper<UserAuth> userAuthRowMapper = new RowMapper<UserAuth>() {
        public UserAuth mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = usersDao.find(resultSet.getInt("user_id"));
            Token token = tokenDao.find(resultSet.getInt("auth_token"));
            UserAuth userAuth = new UserAuth.Builder()
                    .id(resultSet.getInt(1))
                    .user(user)
                    .token(token)
                    .build();
            return userAuth;
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
                .addValue("user", model.getUser())
                .addValue("token", model.getToken());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_INSERT_LOGIN, params, holder, new String[]{"id"});
        Number number = holder.getKey();
        return number.intValue();
    }

    public void update(UserAuth model) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", model.getId());
        params.put("user", model.getUser());
        params.put("token", model.getToken());
        namedJdbcTemplate.update(SQL_UPDATE_LOGIN_BY_ID, params);
    }

    public void delete(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        namedJdbcTemplate.update(SQL_DELETE_LOGIN_BY_ID, params);
    }

    public List<UserAuth> findAll() {
        return namedJdbcTemplate.query(SQL_SELECT_ALL, userAuthRowMapper);
    }
}
