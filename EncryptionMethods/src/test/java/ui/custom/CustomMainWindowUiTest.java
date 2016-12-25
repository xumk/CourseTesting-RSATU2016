package ui.custom;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.loadui.testfx.GuiTest;
import org.testfx.api.FxRobot;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Алексей on 25.12.2016.
 */
public class CustomMainWindowUiTest extends GuiTest {
    protected static FxRobot robot;
    protected static List<String> methodNames;

    static {
        robot = new FxRobot();

        methodNames = Arrays.asList(
                "MonoAlphabet", "HomophonyReplacement", "PolyalphabeticReplacement",
                "PoligrammnayaReplacement", "VerticalPermutation", "BitRevers",
                "VizhinerMethod", "XOR"
        );
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
}
