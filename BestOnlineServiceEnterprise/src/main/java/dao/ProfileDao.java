package dao;

import javafx.geometry.Pos;
import models.Post;
import models.Profile;
import models.User;

import java.util.List;

/**
 * 09.05.2017
 * ProfileDao @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public interface ProfileDao extends BaseDao<Profile>{

    List<User> findAllFriends();
    User findFriend();

}
