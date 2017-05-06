package services;

import dao.UsersDao;

/**
 * 06.05.2017
 * StatisticsServiceImpl
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class StatisticsServiceImpl implements StatisticsService {

    private UsersDao usersDao;

    public StatisticsServiceImpl(UsersDao usersDao) {
        this.usersDao = usersDao;
    }

    public double getUserActivityLevel(int userId) {
        int viewsCount = usersDao.viewsCount(userId);
        int likesCount = usersDao.likesCount(userId);

        return likesCount / (viewsCount * 1.0);
    }
}
