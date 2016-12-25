package ui.mainwindow;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.TextArea;
import org.junit.Before;
import org.junit.Test;
import ui.custom.CustomMainWindowUiTest;

import java.io.IOException;

import static org.testfx.api.FxAssert.verifyThat;
import static org.testfx.matcher.base.NodeMatchers.hasText;

/**
 * Created by Алексей on 22.12.2016.
 */
public class MainWindowUiTest extends CustomMainWindowUiTest {
    private TextArea textArea;

    @Before
    public void findTextArea() {
        textArea = find("#textArea");
    }

    @Override
    protected Parent getRootNode() {
        Parent parent = null;
        try {
            parent = FXMLLoader.load(getClass().getResource("/fxml/MainWindow.fxml"));
            return parent;
        } catch (IOException ex) {
            // TODO ...
        }
        return parent;
    }

    @Test
    public void writeInTextAreaTest() {
        robot.clickOn(textArea).write("авыаыва");
        verifyThat(textArea, hasText("авыаыва"));
    }

    @Test
    public void clickAllEncodeMenuTest() {
        robot.clickOn("#encodeMenu");
        clickAllMethodsMenu("encode");
        robot.clickOn("#encodeMenu");
    }

    @Test
    public void clickAllDecodeMenuTest() {
        robot.clickOn("#decodeMenu");
        clickAllMethodsMenu("decode");
        robot.clickOn("#decodeMenu");
    }

    private static void clickAllMethodsMenu(String variantName) {
        String itemId;
        for (String name : methodNames) {
            itemId = "#" + variantName + name;
            robot.clickOn(itemId);
        }
    }
    @Test
    public void clearTextArea() {
        String testText = "текстовый текст, для проверки очистки области ввода текста";
        robot.clickOn(textArea).write(testText);
        verifyThat(textArea, hasText(testText));
        robot.clickOn("#fileMenu").clickOn("#clear");
        verifyThat(textArea, hasText(""));
    }
}
