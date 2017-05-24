package dao;

import models.Friend;
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
 * 20.05.2017
 * FriendDaoImpl @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public class FriendDaoImpl implements FriendDao {

 private NamedParameterJdbcTemplate namedJdbcTemplate;

    public FriendDaoImpl(DataSource dataSource) {
        this.namedJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
    }

    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO friend(idFrien,nameFriend)" +
            "VALUES (:idFrind,:nameFriend)";
    //language=SQL
    private static final String SQL_DELETE = "DELETE FROM friend WHERE idFrinend = :idFrinend";

    //language=SQL
    private static final String SQL_FIND = "SELECT * FROM friend WHERE idFriend = :idFrinend";

    //language=SQL
    private static final String SQL_FIND_ALL = "SELECT * FROM friend";


    private RowMapper<Friend> friendRowMapper = new RowMapper<Friend>() {
        @Override
        public Friend mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Friend.Builder()
                    .idFrinend(resultSet.getInt(1))
                    .nameFriend(resultSet.getString("nameFriend"))
                    .build();
        }
    };

    @Override
    public Friend find(int id) {
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        return (Friend) namedJdbcTemplate.query(SQL_FIND, params, friendRowMapper);
    }

    @Override
    public int save(Friend friend) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("nameFriend",friend.getNameFriend());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_SAVE, params, holder,new java.lang.String[]{"idFriend"});
        Number generetedId = holder.getKey();
        return generetedId.intValue();

    }

    @Override
    public void update(Friend model) {
    }

    @Override
    public void delete(int id) {
        Map<String, Integer> params = new HashMap<>();
        params.put("id",id);
        namedJdbcTemplate.update(SQL_DELETE,params);


    }

    @Override
    public List<Friend> findAll() {
        return namedJdbcTemplate.query(SQL_FIND_ALL,friendRowMapper);
    }


    @Override
    public List<Friend> findAllFriends(String name) {
        return null;
    }

    @Override
    public List<Friend> findFriend(String name) {
        return null;
    }

    @Override
    public List<Friend> deleteFriend(String name) {
        return null;
    }
}
