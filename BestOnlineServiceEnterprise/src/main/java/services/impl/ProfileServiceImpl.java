package services.impl;

import dao.ProfileDao;
import models.Profile;
import services.ProfileService;

import java.util.List;

/**
 * 27.05.2017
 * ProfileServiceImpl @author Zavidonov Denis (First Software Engineering Platform)
 *
 * @version v1.0
 */
public class ProfileServiceImpl implements ProfileService {

    private ProfileDao profileDao;

    @Override
    public Profile find(int id) {
        return profileDao.find(id);
    }

    @Override
    public int save(Profile model) {
        return profileDao.save(model);
    }

    @Override
    public void update(Profile model) {
        profileDao.update(model);

    }

    @Override
    public void delete(int id) {
        profileDao.delete(id);

    }

    @Override
    public List<Profile> findAll() {
        return profileDao.findAll();
    }
}
