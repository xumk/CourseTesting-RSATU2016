package controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

/**
 * Created by Алексей on 13.06.2016.
 */
public class RegistryController implements Initializable {
    public static Stage STAGE;
    public static Pane parentPane;
    public static boolean isSystem;

    public Button reset;
    public Button signUp;
    public TextField userName;
    public PasswordField password;
    public TextField firstName;
    public TextField lastName;
    public TextField middleName;
    public DatePicker birthDate;
    public Button back;
    public ComboBox gender;

    private ObservableList<String> genders = FXCollections.observableArrayList("Мужской", "Женский");


    public RegistryController() {

    }

    public void initialize(URL location, ResourceBundle resources) {
        this.gender.setValue("Мужской");
        this.gender.setItems(this.genders);
    }

    public void backAction() {
        STAGE.close();
        parentPane.setDisable(false);
    }

    public void resetAction() {
        this.userName.clear();
        this.firstName.clear();
        this.lastName.clear();
        this.middleName.clear();
        this.password.clear();
        this.birthDate.setValue(null);
    }

    public void singUpAction() {
    }
}

