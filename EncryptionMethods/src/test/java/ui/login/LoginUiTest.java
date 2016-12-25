package ui.login;

import javafx.scene.paint.Color;
import org.junit.Test;

import static controller.Login.sessionFactory;
import static org.junit.Assert.assertEquals;
import static org.loadui.testfx.GuiTest.waitUntil;
import static org.loadui.testfx.controls.impl.VisibleNodesMatcher.visible;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

/**
 * Created by Алексей on 20.12.2016.
 */
public class LoginUiTest extends LoginUiCustomTest {

    @Test
    public void thisUserDoesntExist() {
        clickOn(userName).write("NotUser");
        clickOn(password).write("1");
        clickOn(authorization);
        verifyThat(resultAuthorization, hasText("Такого пользователя не существует"));
    }

    @Test
    public void openMainWindowTest() {
        assertEquals(sessionFactory.isClosed(), false);
        clickOn(userName).write("Aleksey");
        clickOn(password).write("yui");
        clickOn(authorization);
        verifyThat(resultAuthorization, hasText("Пароль верный"));
        assertEquals(resultAuthorization.getFill(), Color.GREEN);
        waitUntil("#AnchorPane", visible());
    }

    @Test
    public void wrongAuthorizationTest() {
        assertEquals(sessionFactory.isClosed(), false);
        clickOn(userName).write("Aleksey");
        clickOn(password).write("retdfgdfh");
        clickOn(authorization);
        verifyThat(resultAuthorization, hasText("Не верный пароль"));
        assertEquals(resultAuthorization.getFill(), Color.FIREBRICK);
    }

    @Test
    public void openRegistryWindow() {
        assertEquals(sessionFactory.isClosed(), false);
        clickOn(registry);
        assertEquals(gridPane.isDisable(), true);
        waitUntil("#registryPane", visible());
        clickOn("#back");
    }

    @Test
    public void wrongLoginAuthorizationTest() {
        assertEquals(sessionFactory.isClosed(), false);
        clickOn("#login").write("ТестНик");
        clickOn("#password").write("tttt");
        clickOn("#authorization");
        verifyThat(resultAuthorization, hasText("Такого пользователя не существует"));
        assertEquals(resultAuthorization.getFill(), Color.FIREBRICK);
    }
}
