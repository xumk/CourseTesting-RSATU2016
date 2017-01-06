package database;

import database.dao.UserDao;
import database.entity.User;
import database.service.DaoFactory;
import database.service.DataBaseService;
import database.utils.TestingDataBaseService;
import org.hibernate.SessionFactory;
import org.junit.*;

import java.util.Arrays;

import static org.junit.Assert.assertEquals;
import static utils.UtilFunctions.md5Custom;

/**
 * Created by Алексей on 06.01.2017.
 */
public class DataBaseTesting {
    private static DataBaseService dataBaseService;
    private static UserDao userDao;
    private User actualUser;

    @BeforeClass
    public static void createConnectionDatabase() {
        dataBaseService = TestingDataBaseService.instanceDataBaseService();
        SessionFactory sessionFactory = dataBaseService.getSessionFactory();
        userDao = DaoFactory.getInstance(sessionFactory).getUserDao();
    }

    @AfterClass
    public static void closeConnectionDatabase() {
        dataBaseService.closeSessionFactory();
    }

    @Before
    public void createTestUser() {
        actualUser = new User().setFirstName("Тест")
                .setLastName("Тестовый")
                .setLogin("test")
                .setPassword(md5Custom("1"))
                .setMethods(Arrays.asList("BitRevers", "PoligrammnayaReplacement"));
    }

    @After
    public void deleteTestUser() {
        userDao.deleteObject(1L);
    }

    @Test
    public void createUserTest() {
        User actualUser = new User().setFirstName("Тест")
                .setLastName("Тестовый")
                .setLogin("test")
                .setPassword(md5Custom("1"))
                .setMethods(Arrays.asList("BitRevers", "PoligrammnayaReplacement"));
        userDao.addObject(actualUser);
        User expectedUser = userDao.getEntityByStringProperty("login", "test");
        assertEquals(expectedUser, actualUser);
    }


}
