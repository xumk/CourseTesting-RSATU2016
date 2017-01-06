package ui.system;

import database.service.DaoFactory;
import database.service.DataBaseService;
import database.utils.TestingDataBaseService;
import javafx.scene.paint.Color;
import org.junit.After;
import ui.custom.LoginUiCustomTest;

import static org.junit.Assert.assertEquals;
import static org.loadui.testfx.GuiTest.waitUntil;
import static org.loadui.testfx.controls.impl.VisibleNodesMatcher.visible;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

/**
 * Created by Алексей on 06.01.2017.
 */
public class UserWorkingTest extends LoginUiCustomTest {
    protected String actualText = "специальныи текст для тестов";

    @After
    public void deleteTestUser() {
        userDao.deleteObject(1L);
    }

    @Override
    protected void createDatabaseConnection() {
        DataBaseService dataBaseService = TestingDataBaseService.instanceDataBaseService();
        sessionFactory = dataBaseService.getSessionFactory();
        userDao = DaoFactory.getInstance(sessionFactory).getUserDao();
    }

    protected void writeTextArea() {
        clickOn("#textArea").write(actualText);
    }

    protected void openMainWindowTest() {
        clickOn(authorization);
        verifyThat(resultAuthorization, hasText("Пароль верный"));
        assertEquals(resultAuthorization.getFill(), Color.GREEN);
        waitUntil("#AnchorPane", visible());
    }

}
