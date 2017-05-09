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
    void delete (int friend);
    void add (int friend);
    void add (String posts);
    void delete (String posts);
    void update (Profile users);
    void save (Profile users);

}
