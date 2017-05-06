package services;

import dao.UsersDao;
import exceptions.UserNotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static junit.framework.TestCase.assertEquals;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

/**
 * 06.05.2017
 * StatisticsServiceImplTest
 *
 * @author Sidikov Marsel (First Software Engineering Platform)
 * @version v1.0
 */
public class StatisticsServiceImplTest {

    private final int USER_ID = 1;

    private StatisticsServiceImpl statisticsService;

    private UsersDao usersDao;

    @Before
    public void setUp() throws Exception {
        usersDao = Mockito.mock(UsersDao.class);

        // TODO: fix thenThrow
        // when(usersDao.viewsCount(anyInt())).thenThrow(new UserNotFoundException());
        // when(usersDao.likesCount(anyInt())).thenThrow(new UserNotFoundException());
        when(usersDao.likesCount(USER_ID)).thenReturn(40);
        when(usersDao.viewsCount(USER_ID)).thenReturn(80);

        statisticsService = new StatisticsServiceImpl(usersDao);
    }

    @Test
    public void getUserActivityLevel() throws Exception {
        double actual = statisticsService.getUserActivityLevel(USER_ID);
        double expected = 0.5;

        assertEquals(expected, actual);
    }

}