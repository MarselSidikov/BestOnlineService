package dao;

import models.Post;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * 16.05.2017
 * PostDaoImpl @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public class PostDaoImpl implements PostDao {

    private NamedParameterJdbcTemplate namedJdbcTemplate;


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
        return null;
    }

    public int save(Post model) {
        return 0;
    }

    public void update(Post model) {

    }

    public void delete(int id) {

    }

    public List<Post> findAll() {
        return null;
    }

    public List<Post> findAllPosts() {
        return null;
    }

    public List<Post> findAllByAuthor() {
        return null;
    }
}
