package ui.system;

import controller.MainWindowController;
import javafx.scene.control.TextArea;
import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.loadui.testfx.GuiTest.find;

/**
 * Created by Алексей on 06.01.2017.
 */
public class UserWorkingUiTesting extends UserWorkingTest {
    @Test
    public void fullWorkTest() {
        openRegistryWindowAndTest();
        registryTestUser();
        authorizationTestUser();
        assertEquals(MainWindowController.stage.getTitle(), "Добро пожаловать, Неизвестный");
        writeTextArea();
        integrationCipherEqualsTest();
    }

    private void registryTestUser() {
        clickOn("#login").write("testUser");
        clickOn("#password").write("1");
        clickOn("#firstMethod").clickOn("побитовая перестановка");
        clickOn("#signUp");
    }

    private void authorizationTestUser() {
        clickOn(userName).write("testUser");
        clickOn(password).write("1");
        openMainWindowTest();
    }

    private void integrationCipherEqualsTest() {
        clickOn("#encodeMenu").clickOn("#encodeMonoAlphabet")
                .clickOn("#txtFieldDialog").write("24").clickOn("#okDialog");
        clickOn("#decodeMenu").clickOn("#decodeBitRevers")
                .clickOn("#txtFieldDialog").write("42351").clickOn("#okDialog");
        clickOn("#encodeMenu").clickOn("#encodeBitRevers")
                .clickOn("#txtFieldDialog").write("42351").clickOn("#okDialog");
        clickOn("#decodeMenu").clickOn("#decodeMonoAlphabet")
                .clickOn("#txtFieldDialog").write("24").clickOn("#okDialog");
        TextArea textArea = find("#textArea");
        Assert.assertEquals(textArea.getText(), actualText);
    }
}
