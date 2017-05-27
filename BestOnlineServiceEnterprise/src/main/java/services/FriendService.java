package services;

import models.Friend;

import java.util.List;

/**
 * 27.05.2017
 * FriendService @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public interface FriendService {
    Friend find(int id);
    int save(Friend model);
    void delete(int id);
    List<Friend> findAll();
    List<Friend> findAllFriends(String name);
    List<Friend> findFriend(String name);
    List<Friend> deleteFriend(String name);
}
