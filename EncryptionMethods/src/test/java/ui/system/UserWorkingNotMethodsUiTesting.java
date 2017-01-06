package ui.system;

import controller.MainWindowController;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

/**
 * Created by Алексей on 06.01.2017.
 */
public class UserWorkingNotMethodsUiTesting extends UserWorkingTest {

    @Test
    public void fullWorkTest() {
        openRegistryWindowAndTest();
        registryTestUser();
        authorizationTestUser();
        assertEquals(MainWindowController.stage.getTitle(), "Добро пожаловать, Тест");
        writeTextArea();
        clickMethodTest("#encodeHomophonyReplacement");
        checkDialogAndClose();
        clickMethodTest("#encodeVizhinerMethod");
        checkDialogAndClose();
    }

    private void registryTestUser() {
        clickOn("#login").write("testUser2");
        clickOn("#password").write("1");
        clickOn("#firstName").write("Тест");
        clickOn("#lastName").write("Тестовый");
        clickOn("#firstMethod").clickOn("гомофоническая замена");
        clickOn("#secondMethod").clickOn("код Виженера");
        clickOn("#signUp");
    }

    private void authorizationTestUser() {
        clickOn(userName).write("testUser2");
        clickOn(password).write("1");
        openMainWindowTest();
    }

    private void clickMethodTest(String methodName) {
        clickOn("#encodeMenu").clickOn(methodName);
    }

    private void checkDialogAndClose() {
        verifyThat("#labelDialog", hasText("Метод не реализован"));
        clickOn("#okDialog");
    }
}
