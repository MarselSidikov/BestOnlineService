package dao;

import models.Post;
import models.Profile;
import models.User;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 08.05.2017
 * ProfileDaoImpl @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */


public class ProfileDaoImpl implements ProfileDao {

    private NamedParameterJdbcTemplate namedJdbcTemplate;



    //language=SQL
    final String SQL_SELECT_POST_BY_ID = "SELECT * profil WHERE id = ? ";
    //language=SQL
    //final String SQL_SELECT_INSERT_POST = "INSERT INTO profil ()";



   private RowMapper <Profile> profileRowMapper = new RowMapper<Profile>() {

       public Profile mapRow(ResultSet resultSet, int i) throws SQLException {
           return new Profile.Builder()
                   .id(resultSet.getInt(1))
                   .firstNameUser(resultSet.getString("firstNameUser"))
                   .lastNameUser(resultSet.getString("lastNameUser"))
                   .ageUser(resultSet.getInt("ageUser"))
                   .city(resultSet.getString("city"))
                   .image(resultSet.getString("image"))
                   .posts((List<Post>)resultSet.getObject("posts"))
                   .friends((List<User>)resultSet.getObject("friends"))
                   .build();
       }
   };



    public Profile find(int id) {
        return null;
    }

    public int save(Profile model) {
        return 0;
    }

    public void update(Profile model) {

    }

    public void delete(int id) {

    }

    public List<Profile> findAll() {
        return null;
    }

    public List<Post> findAllPosts() {
        return null;
    }

    public List<Post> findAllByAuthor() {
        return null;
    }

    public List<User> findAllFriends() {
        return null;
    }

    public User findFriend() {
        return null;
    }
}
