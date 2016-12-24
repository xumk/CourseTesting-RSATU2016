package controller;

import database.dao.DAO;
import database.entity.User;
import javafx.collections.ObservableList;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.util.Callback;
import utils.Method;
import utils.UtilFunctions;

import java.net.URL;
import java.util.Arrays;
import java.util.Collections;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableArrayList;
import static utils.UtilFunctions.isNull;

/**
 * Created by Алексей on 13.06.2016.
 */
public class RegistryController implements Initializable {
    public static DAO<User> USER_DAO;
    public static Stage STAGE;
    public static Pane parentPane;

    public Button reset;
    public Button signUp;
    public TextField login;
    public PasswordField password;
    public TextField firstName;
    public TextField lastName;
    public Button back;
    public ComboBox<Method> firstMethod;
    public ComboBox<Method> secondMethod;
    public Label outputErrorText;

    private final ObservableList<Method> methods;
    private StringBuilder errorText;


    public RegistryController() {
        methods = observableArrayList(
                new Method("MonoAlphabet", "моноалфавитная замена"),
                new Method("HomophonyReplacement", "гомофоническая замена"),
                new Method("PolyalphabeticReplacement", "полиалфавитная замена"),
                new Method("PoligrammnayaReplacement", "полиграммная замена"),
                new Method("VerticalPermutation", "вертикальная перестановка с ключом"),
                new Method("BitRevers", "побитовая перестановка"),
                new Method("VizhinerMethod", "код Виженера"),
                new Method("XOR", "гоммирование с ключом")
        );
    }

    public void initialize(URL location, ResourceBundle resources) {
        setSettingComboBox(firstMethod);
        setSettingComboBox(secondMethod);
    }

    private void setSettingComboBox(ComboBox<Method> comboBox) {
        comboBox.setItems(methods);
        comboBox.getSelectionModel().selectFirst(); //select the first element
        comboBox.setCellFactory(new Callback<ListView<Method>, ListCell<Method>>() {
            @Override
            public ListCell<Method> call(ListView<Method> p) {
                return new ListCell<Method>() {
                    @Override
                    protected void updateItem(Method t, boolean bln) {
                        super.updateItem(t, bln);
                        if (t != null) {
                            setText(t.getMethodCaption());
                        } else {
                            setText(null);
                        }
                    }
                };
            }
        });
    }

    public void backAction() {
        STAGE.close();
        parentPane.setDisable(false);
    }

    public void resetAction() {
        this.login.clear();
        this.firstName.clear();
        this.lastName.clear();
        this.password.clear();
    }

    public void singUpAction() {
        if (!isValidFields()) {
            outputErrorText.setText("Не заполнены обязательные поля: " + errorText.toString());
            outputErrorText.setTextFill(Color.FIREBRICK);
            return;
        }
        if (isExistsUser()) {
            outputErrorText.setText("Пользователь с логином: " + login.getText() + " существует!");
            outputErrorText.setTextFill(Color.FIREBRICK);
            return;
        }
        User user = createUser();
        USER_DAO.addObject(user);
        backAction();
    }

    private User createUser() {
        User user = new User();
        user.setLogin(login.getText());
        user.setPassword(UtilFunctions.md5Custom(password.getText()));
        String firstName = isNull(this.firstName.getText())? "" : this.firstName.getText();
        String lastName =isNull(this.lastName.getText()) ? "" : this.lastName.getText();
        user.setFirstName(firstName);
        user.setLastName(lastName);
        Method first, second;
        first = firstMethod.getValue();
        second = secondMethod.getValue();
        if (first.equals(second)) {
            user.setMethods(Collections.singletonList(first.getMethodName()));
        } else {
            user.setMethods(Arrays.asList(first.getMethodName(), second.getMethodName()));
        }
        return user;
    }

    private boolean isValidFields() {
        boolean result = true;
        errorText = new StringBuilder();
        if (login.getText().isEmpty()) {
            errorText.append("Логин; ");
            result = false;
        }
        if (password.getText().isEmpty()) {
            errorText.append("Пароль; ");
            result = false;
        }
        return result;
    }

    private boolean isExistsUser() {
        User user = USER_DAO.getEntityByStringProperty("login", login.getText());
        return user != null;
    }
}

