package dao;

import models.Post;

import java.util.List;

/**
 * 16.05.2017
 * PostDao @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public interface PostDao extends BaseDao<Post> {
    List<Post> findAllPosts();
    List<Post> findAllByAuthor(String name);
    List<Post> findAllPostByUser(String name);
}
