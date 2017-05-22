package dao;

import models.Post;
import models.Profile;
import models.User;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 08.05.2017
 * ProfileDaoImpl @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */


public class ProfileDaoImpl implements ProfileDao {

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    private PostDao postDao ;


    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO profile (id ,firstNameUser,lastNameUser,ageUser,city,image)" +
            "VALUES(:id ,:firstNameUser,:lastNameUser,:ageUser,:city,:image)" ;
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE profile SET firstNameUser = :firstNameUser,lastNameUser = :lastNameUser," +
            "ageUser = :ageUser,city = :city,image = :image WHERE id = :id";
    //language=SQL
    private static final String SQL_FIND = "SELECT * FROM profile WHERE id = :id";
    //language=SQL
    private static final String SQL_FIND_ALL ="SELECT * FROM profile";
    //language=SQL
    private static final String SQL_DELETE ="DELETE FROM profile WHERE id = :id";



   private RowMapper <Profile> profileRowMapper = new RowMapper<Profile>() {

       public Profile mapRow(ResultSet resultSet, int i) throws SQLException {
           List<Post> posts = postDao.findAllPostByUser(resultSet.getString("userName"));
           return new Profile.Builder()
                   .id(resultSet.getInt(1))
                   .nameUser(resultSet.getString("firstNameUser"))
                   .ageUser(resultSet.getInt("ageUser"))
                   .city(resultSet.getString("city"))
                   .image(resultSet.getString("image"))
                   .posts(posts)
                   .friends((List<User>)resultSet.getObject("friends"))
                   .build();
       }
   };





    public Profile find(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("id", id);
        return (Profile) namedJdbcTemplate.query(SQL_FIND, params, profileRowMapper);
    }

    public int save(Profile profile) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("nameUser",profile.getNameUser())
                .addValue("ageUser", profile.getAgeUser())
                .addValue("city",profile.getCity())
                .addValue("image",profile.getImage());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_SAVE, params, holder, new String[]{"id"});
        Number generetedId = holder.getKey();
        return generetedId.intValue();

    }

    public void update(Profile profile) {
        Map<String,Object> params = new HashMap<>();
        params.put("id", profile.getId());
        params.put("nameUser",profile.getNameUser());
        params.put("ageUser", profile.getAgeUser());
        params.put("city", profile.getCity());
        params.put("image", profile.getImage());
        namedJdbcTemplate.update(SQL_UPDATE,params);


    }

    public void delete(int id) {
        Map<String,Object> params = new HashMap<>();
        params.put("id",id);
        namedJdbcTemplate.update(SQL_DELETE,params);

    }

    public List<Profile> findAll() {
        return namedJdbcTemplate.query(SQL_FIND_ALL,profileRowMapper);
    }


    @Override
    public List<User> findAllFriends() {
        return null;
    }

    @Override
    public User findFriend(String name) {
        return null;
    }
}
