package utils.alert;

import javafx.scene.control.Alert;

/**
 * Created by Алексей on 25.12.2016.
 */
public class ErrorAlert extends Alert {

    public ErrorAlert(String message) {
        super(AlertType.ERROR);
        setTitle("Ошибка");
        setHeaderText(null);
        setContentText(message);
    }
}
