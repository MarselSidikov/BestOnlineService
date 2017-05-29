package dao;

import models.Friend;
import org.springframework.jdbc.core.RowMapper;


import java.util.List;

/**
 * 20.05.2017
 * FriendDao @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public interface FriendDao extends BaseDao<Friend> {
    List<Friend> findAllFriends(String name);

    List<Friend> findFriend(String name);

   // List<Friend> deleteFriend(String name);
  //List<Friend> findAge(int age);
    // List<Friend>findAllAge(int age);
}

