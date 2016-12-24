import controller.Login;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.junit.Test;
import org.testfx.framework.junit.ApplicationTest;

import static org.junit.Assert.assertEquals;
import static org.loadui.testfx.GuiTest.find;
import static org.loadui.testfx.GuiTest.waitUntil;
import static org.loadui.testfx.controls.impl.VisibleNodesMatcher.visible;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

/**
 * Created by Алексей on 20.12.2016.
 */
public class LoginTest extends ApplicationTest {
    private Text resultAuthorization;
    private Button authorization;
    private Button registry;
    private TextField userName;
    private PasswordField password;
    private Stage stage;
    private GridPane gridPane;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        new Login().start(stage);
        gridPane = Login.GRID;
        resultAuthorization = find("#resultAuthorization");
        authorization = find("#authorization");
        userName = find("#login");
        password = find("#password");
        registry = find("#checkIn");
    }

    @Test
    public void openMainWindowTest() {
        clickOn(userName).write("Ивашин");
        clickOn(password).write("yui");
        clickOn(authorization);
        verifyThat(resultAuthorization, hasText("Пароль верный"));
        assertEquals(resultAuthorization.getFill(), Color.GREEN);
        waitUntil("#AnchorPane", visible());
    }

    @Test
    public void wrongAuthorizationTest() {
        clickOn(userName).write("Ивашин");
        clickOn(password).write("retdfgdfh");
        clickOn(authorization);
        verifyThat(resultAuthorization, hasText("Не верный пароль"));
        assertEquals(resultAuthorization.getFill(), Color.FIREBRICK);
    }

    @Test
    public void threeWrongAuthorizationTest() {
        for (int i = 0; i < 3; i++) {
            clickOn(userName).write("Ивашин");
            clickOn(password).write("tttt");
            clickOn(authorization).sleep(100);
            verifyThat(resultAuthorization, hasText("Не верный пароль"));
            assertEquals(resultAuthorization.getFill(), Color.FIREBRICK);
            userName.clear();
            password.clear();
        }
        assertEquals(stage.isShowing(), false);
    }

    @Test
    public void openRegistryWindow() {
        clickOn(registry);
        assertEquals(gridPane.isDisable(), true);
        waitUntil("#registryPane", visible());
        clickOn("#back");
    }

    @Test
    public void wrongLoginAuthorizationTest() {
        clickOn("#login").write("ТестНик");
        clickOn("#password").write("tttt");
        clickOn("#authorization");
        verifyThat(resultAuthorization, hasText("Такого пользователя не существует"));
        assertEquals(resultAuthorization.getFill(), Color.FIREBRICK);
    }
}
