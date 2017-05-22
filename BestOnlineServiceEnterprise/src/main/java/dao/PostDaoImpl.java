package dao;

import models.Post;
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
 * 16.05.2017
 * PostDaoImpl @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public class PostDaoImpl implements PostDao {

    private NamedParameterJdbcTemplate namedJdbcTemplate;

    //language=SQL
    private static final String SQL_SAVE = "INSERT INTO post (idPost,date,textPost,imagePost)" +
            "VALUES :idPost,:date,:textPost,:imagePost";
    //language=SQL
    private static final String SQL_UPDATE = "UPDATE post SET date = :date,textPost = :textPost,imagePost = :imagePost " +
            "WHERE idPost = :idPost";
    //language=SQL
    private static final String SQL_FIND = "SELECT * FROM post WHERE idPost = :idPost";
    //language=SQL
    private static final String SQL_FIND_ALL ="SELECT * FROM post";
    //language=SQL
    private static final String SQL_DELETE ="DELETE FROM post WHERE idPost = :idPost";


    private RowMapper<Post> postRowMapper = new RowMapper<Post>() {
        public Post mapRow(ResultSet resultSet, int i) throws SQLException {
            return new Post.Builder()
                    .idPost(resultSet.getInt(1))
                    .data(resultSet.getString("data"))
                    .textPost(resultSet.getString("textPost"))
                    .imagePost(resultSet.getString("imagePost"))
                    .build();


        }
    };


    public Post find(int id) {
        Map<String, Object> params = new HashMap<>();
        params.put("idPost", id);
        return (Post) namedJdbcTemplate.query(SQL_FIND, params, postRowMapper);
    }

    public int save(Post post) {
        MapSqlParameterSource params = new MapSqlParameterSource()
                .addValue("data", post.getData())
                .addValue("textPost", post.getTextPost())
                .addValue("imagePost", post.getImagePost());
        final KeyHolder holder = new GeneratedKeyHolder();
        namedJdbcTemplate.update(SQL_SAVE, params, holder, new String[]{"id"});
        Number generetedId = holder.getKey();
        return generetedId.intValue();
    }

    public void update(Post post) {
        Map<String,Object> params = new HashMap<>();
        params.put("idPost", post.getIdPost());
        params.put("data", post.getData());
        params.put("textPost", post.getTextPost());
        params.put("imagePost", post.getImagePost());
        namedJdbcTemplate.update(SQL_UPDATE,params);

    }

    public void delete(int idPost) {
        Map<String,Object> params = new HashMap<>();
        params.put("idPost",idPost);
        namedJdbcTemplate.update(SQL_DELETE,params);

    }

    public List<Post> findAll() {
        return namedJdbcTemplate.query(SQL_FIND_ALL,postRowMapper);
    }

    public List<Post> findAllPosts() {
        return null;
    }

    public List<Post> findAllByAuthor() {
        return null;
    }

    @Override
    public List<Post> findAllPostByUser(String name) {
        return null;
    }

}
