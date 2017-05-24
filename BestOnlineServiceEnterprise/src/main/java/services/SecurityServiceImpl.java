package services;

import dao.UserNamedJdbcTemplate;
import dao.UsersDao;
import models.User;

import java.util.List;

/**
 * 24.05.2017
 * SecurityServiceImpl
 *
 * @author Aivar Yusupov
 * @version 0.1
 */
public class SecurityServiceImpl implements SecurityService {
    private UsersDao usersDao;

    public SecurityServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    @Override
    public List<User> findAll() {
        return usersDao.findAll();
    }
}
