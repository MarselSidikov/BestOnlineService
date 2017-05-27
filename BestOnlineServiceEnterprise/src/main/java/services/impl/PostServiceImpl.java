package services.impl;

import dao.PostDao;
import models.Post;
import services.PostService;

import java.util.List;

/**
 * 27.05.2017
 * PostServiceImpl @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public class PostServiceImpl implements PostService {

    private PostDao  postDao;

    public PostServiceImpl(PostDao postDao) {
        this.postDao = postDao;
    }

    @Override
    public Post find(int id) {
        return postDao.find(id);
    }

    @Override
    public int save(Post model) {
        return postDao.save(model);
    }

    @Override
    public void update(Post model) {
        postDao.update(model);

    }

    @Override
    public void delete(int id) {
        postDao.delete(id);

    }

    @Override
    public List<Post> findAll() {
        return postDao.findAll();
    }

    @Override
    public List<Post> findAllPosts() {
        return postDao.findAllPosts();
    }

    @Override
    public List<Post> findAllByAuthor(String name) {
        return postDao.findAllByAuthor(name);
    }

    @Override
    public List<Post> findAllPostByUser(String name) {
        return postDao.findAllPostByUser(name);
    }
}
