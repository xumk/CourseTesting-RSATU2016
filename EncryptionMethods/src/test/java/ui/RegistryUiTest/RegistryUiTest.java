package ui.RegistryUiTest;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import org.junit.*;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxRobot;

import java.io.IOException;

/**
 * Created by Алексей on 26.12.2016.
 */
public class RegistryUiTest extends GuiTest {
    private static FxRobot robot;
    private Label outputErrorText;

    @BeforeClass
    public static void createRobot() {
        robot = new FxRobot();
    }

    @Before
    public void getOutputErrorText() {
        outputErrorText =  find("#outputErrorText");
    }

    @After
    public void clickResetField() {
        robot.clickOn("#reset");
    }

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/Registry.fxml"));
            return parent;
        } catch (IOException ex) {
            // TODO ...
        }
        return parent;
    }


    @Test
    public void emptyLoginAndPasswordFieldTest() {
        robot.clickOn("#signUp").sleep(500);
        Assert.assertEquals(outputErrorText.getText(), "Не заполнены обязательные поля: Логин; Пароль; ");
    }

    @Test
    public void emptyLoginFieldTest() {
        robot.clickOn("#login").write("notEmptyLogin");
        robot.clickOn("#signUp");
        Assert.assertEquals(outputErrorText.getText(), "Не заполнены обязательные поля: Пароль; ");
    }

    @Test
    public void emptyPasswordFieldTest() {
        robot.clickOn("#password").write("tesPass");
        robot.clickOn("#signUp");
        Assert.assertEquals(outputErrorText.getText(), "Не заполнены обязательные поля: Логин; ");
    }
}
