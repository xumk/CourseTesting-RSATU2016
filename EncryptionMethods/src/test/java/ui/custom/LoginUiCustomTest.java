package ui.custom;

import controller.Login;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.testfx.framework.junit.ApplicationTest;

import static controller.Login.GRID;
import static org.junit.Assert.assertEquals;
import static org.loadui.testfx.GuiTest.find;
import static org.loadui.testfx.GuiTest.waitUntil;
import static org.loadui.testfx.controls.impl.VisibleNodesMatcher.visible;

/**
 * Created by Алексей on 25.12.2016.
 */
public abstract class LoginUiCustomTest extends ApplicationTest {
    protected Text resultAuthorization;
    protected Button authorization;
    protected Button registry;
    protected TextField userName;
    protected PasswordField password;
    protected Stage stage;
    protected GridPane gridPane;

    @Override
    public void start(Stage stage) throws Exception {
        this.stage = stage;
        new Login().start(stage);
        gridPane = GRID;
        resultAuthorization = find("#resultAuthorization");
        authorization = find("#authorization");
        userName = find("#login");
        password = find("#password");
        registry = find("#checkIn");
    }

    protected void openRegistryWindowAndTest() {
        clickOn(registry);
        assertEquals(gridPane.isDisable(), true);
        waitUntil("#registryPane", visible());
    }

    protected void closeRegistryWindowAndTest() {
        clickOn("#back");
        assertEquals(gridPane.isDisable(), false);
    }
}
