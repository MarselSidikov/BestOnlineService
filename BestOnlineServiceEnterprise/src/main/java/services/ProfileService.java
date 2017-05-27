package services;

import models.Profile;

import java.util.List;

/**
 * 06.05.2017
 * ProfileService
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface ProfileService {
    Profile find(int id);
    int save(Profile model);
    void update(Profile model);
    void delete(int id);
    List<Profile> findAll();
}
