package dao;

import models.User;

/**
 * 06.05.2017
 * UsersDao
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public interface UsersDao extends BaseDao<User> {
    int likesCount(int id);
    int viewsCount(int id);
}
