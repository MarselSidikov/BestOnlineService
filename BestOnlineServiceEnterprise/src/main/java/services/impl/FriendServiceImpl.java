package services.impl;

import dao.FriendDao;
import models.Friend;
import services.FriendService;

import java.util.List;

/**
 * 27.05.2017
 * FriendServiceImpl @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public class FriendServiceImpl implements FriendService {

    private FriendDao friendDao;

    @Override
    public Friend find(int id) {
        return friendDao.find(id);
    }

    @Override
    public int save(Friend model) {
        return friendDao.save(model);
    }


    @Override
    public void delete(int id) {
        friendDao.delete(id);

    }

    @Override
    public List<Friend> findAll() {
        return friendDao.findAll();
    }

    @Override
    public List<Friend> findAllFriends(String name) {
        return friendDao.findAllFriends(name);
    }

    @Override
    public List<Friend> findFriend(String name) {
        return friendDao.findFriend(name);
    }

    @Override
    public List<Friend> deleteFriend(String name) {
        return friendDao.deleteFriend(name);
    }
}
