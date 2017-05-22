package dao;

import models.Token;
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
 * 13.05.2017
 * TokenNamedJdbcTemplate
 *
 * @author Aivar Yusupov
 * @version v0.1 /
 */

 public class TokenNamedJdbcTemplate implements BaseTokenDao {

    //language=SQL
    private final String SQL_SELECT_ALL = "SELECT * FROM access_token";
    //language=SQL
 	private final String SQL_SELECT_TOKEN_BY_ID = "SELECT * FROM token WHERE id = :id";
    //language=SQL
    private final String SQL_INSERT_TOKEN = "INSERT INTO access_token(id, token) VALUES (:id, :token)";
    //language=SQL
    private final String SQL_UPDATE_TOKEN_BY_ID = "UPDATE access_token SET user_id = :user_id WHERE user_id = :user_id";
    //language=SQL
    private final String SQL_DELETE_TOKEN_BY_ID = "DELETE FROM access_token WHERE id = :id";
    //language=SQL
    private final String SQL_SELECT_BY_TOKEN = "SELECT * FROM token WHERE token = :token";

	private NamedParameterJdbcTemplate namedJdbcTemplate;
	private UsersDao usersDao;

    public TokenNamedJdbcTemplate(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    private RowMapper<Token> tokenRowMapper = new RowMapper<Token>() {
        public Token mapRow(ResultSet resultSet, int i) throws SQLException {
            User user = usersDao.find(resultSet.getInt("user_id"));
            Token token = new Token.Builder()
                    .id(resultSet.getInt(1))
                    .user(user)
                    .token(resultSet.getString("token"))
                    .build();
            return token;
        }
    };

    public Token find(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        List<Token> token = namedJdbcTemplate.query(SQL_SELECT_TOKEN_BY_ID, params, tokenRowMapper);
        return token.get(0);
    }

    public int save(Token model) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("user_id", model.getUser())
                .addValue("token", model.getToken());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_INSERT_TOKEN, params, holder, new String[]{"id"});
        Number number = holder.getKey();
        return number.intValue();
    }

    public void update(Token model) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", model.getId());
        params.put("user", model.getUser());
        params.put("token", model.getToken());
        namedJdbcTemplate.update(SQL_UPDATE_TOKEN_BY_ID, params);
    }

    public void delete(int id) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("id", id);
        namedJdbcTemplate.update(SQL_DELETE_TOKEN_BY_ID, params);
    }

    public List<Token> findAll() {
        return namedJdbcTemplate.query(SQL_SELECT_ALL, tokenRowMapper);
    }

    public Token findByToken(String token) {
        Map<String, Object> params = new HashMap<String, Object>();
        params.put("token", token);
        List<Token> tokenList = namedJdbcTemplate.query(SQL_SELECT_BY_TOKEN, params, tokenRowMapper);
        return tokenList.get(0);
    }

 }