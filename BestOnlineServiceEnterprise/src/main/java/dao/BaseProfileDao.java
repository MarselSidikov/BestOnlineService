package dao;

import models.Profile;

import java.util.List;

/**
 * 09.05.2017
 * BaseProfileDao @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public interface BaseProfileDao {
    List<Profile> findAllByUsers(String users);
    Profile findFriend (int friend);
    void delete (int friend, String users, String posts);
    void add (int friend, String posts);
    void update (Profile users , Profile posts);
    void save (Profile users);

}
