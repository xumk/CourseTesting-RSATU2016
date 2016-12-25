package utils.alert;

import javafx.scene.control.Alert;

/**
 * Created by Алексей on 25.12.2016.
 */
public class InformAlert extends Alert {
    public InformAlert(String message) {
        super(Alert.AlertType.INFORMATION);
        setTitle("Сообщение");
        setHeaderText(null);
        setContentText(message);
    }
}
