package services;

import models.Post;

import java.util.List;

/**
 * 27.05.2017
 * PostService @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public interface PostService {
    Post find(int id);
    int save(Post model);
    void update(Post model);
    void delete(int id);
    List<Post> findAll();
    List<Post> findAllPosts();
    List<Post> findAllByAuthor(String name);
    List<Post> findAllPostByUser(String name);
}
