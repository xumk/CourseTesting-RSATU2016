package ui.RegistryUiTest;

import javafx.scene.control.Label;
import org.junit.Assert;
import org.junit.Test;
import ui.custom.LoginUiCustomTest;

import static org.loadui.testfx.GuiTest.find;

/**
 * Created by Алексей on 26.12.2016.
 */
public class DatabaseAndRegistryUiTest extends LoginUiCustomTest {

    @Test
    public void userExistsTest() {
        clickOn(registry);
        clickOn("#login").write("Aleksey");
        clickOn("#password").write("tesPass");
        clickOn("#signUp");
        Label outputErrorText = find("#outputErrorText");
        Assert.assertEquals(outputErrorText.getText(), "Пользователь с логином: Aleksey существует!");
        closeRegistryWindowAndTest();
    }


}
