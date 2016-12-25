import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.BeforeClass;
import org.junit.Test;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxRobot;

import java.io.IOException;

/**
 * Created by Алексей on 22.12.2016.
 */
public class MainWindowUiTest extends GuiTest {
    private static FxRobot robot;

    @BeforeClass
    public static void createRobot() {
        robot = new FxRobot();
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
    public void testWriteInTextArea() {
        robot.clickOn("#textArea").write("авыаыва");
        robot.clickOn("#encodeMenu");
        robot.clickOn("#decodeMenu");
    }
}
