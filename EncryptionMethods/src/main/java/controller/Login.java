package controller;

import database.dao.Dao;
import database.entity.User;
import database.service.DaoFactory;
import database.service.DataBaseService;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import org.hibernate.SessionFactory;
import utils.UtilFunctions;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static utils.UtilFunctions.isNullString;

/**
 * @author Алексей
 */
public class Login extends Application {

    private static int count = 0;
    private EventHandler<ActionEvent> exitHandler;
    private WindowEvent exitWindow;
    public static GridPane GRID;
    public Stage primaryStage;
    public static Dao<User> userDao;
    public static SessionFactory sessionFactory;

    {
        exitWindow = new WindowEvent(
                primaryStage,
                WindowEvent.WINDOW_CLOSE_REQUEST
        );
        exitHandler = event -> primaryStage.fireEvent(exitWindow);
    }

    private static void createEntityForDateBaseWork() {
        DataBaseService dataBaseService = DataBaseService.instanceDataBaseService();
        sessionFactory = dataBaseService.getSessionFactory();
        userDao = DaoFactory.getInstance(sessionFactory).getUserDao();
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setOnCloseRequest(we -> sessionFactory.close());
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Окно авторизации");

        GRID = new GridPane();
        GRID.setAlignment(Pos.CENTER);
        GRID.setVgap(10);
        GRID.setHgap(10);
        GRID.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Добро пожаловать");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        GRID.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name: ");
        GRID.add(userName, 0, 1);

        TextField userTextField = new TextField();
        userTextField.setId("login");
        GRID.add(userTextField, 1, 1);

        Label password = new Label("Password: ");
        GRID.add(password, 0, 2);

        PasswordField pwBox = new PasswordField();
        pwBox.setId("password");
        GRID.add(pwBox, 1, 2);

        Button sign = new Button("Авторизоваться");
        sign.setId("authorization");
        Button exit = new Button("Выход");
        exit.setId("exit");
        Button registration = new Button("Регистрация");
        registration.setId("checkIn");
        registration.setOnAction(moveToRegistryWindow());

        HBox hbSign = new HBox(10);
        hbSign.setAlignment(Pos.BOTTOM_LEFT);
        hbSign.getChildren().add(sign);
        hbSign.getChildren().add(registration);
        hbSign.getChildren().add(exit);
        GRID.add(hbSign, 1, 5);

        final Text actionTarget = new Text();
        actionTarget.setId("resultAuthorization");
        GRID.add(actionTarget, 1, 6);

        exit.setOnAction(exitHandler);

        sign.setOnAction(event -> {
            actionTarget.setFill(Color.FIREBRICK);
            String name = userTextField.getText();
            User user = userDao.getEntityByStringProperty("login", name);
            if (user == null) {
                actionTarget.setText("Такого пользователя не существует");
                return;
            }
            String realPassword = user.getPassword();
            String pass = UtilFunctions.md5Custom(pwBox.getText());
            if (!realPassword.equals(pass)) {
                ++count;
                if (count == 3) {
                    primaryStage.fireEvent(exitWindow);
                }
                actionTarget.setText("Не верный пароль");
                return;
            }
            actionTarget.setFill(Color.GREEN);
            actionTarget.setText("Пароль верный");
            Parent root = null;
            Stage mainWindowStage = new Stage();
            try {
                MainWindowController.stage = mainWindowStage;
                MainWindowController.sessionFactory = sessionFactory;
                MainWindowController.nameMethods = user.getMethods();
                String firstName = isNullString(user.getFirstName()) ? "Неизвестный" : user.getFirstName();
                mainWindowStage.setTitle("Добро пожаловать, " + firstName);

                root = FXMLLoader.load(this.getClass()
                        .getResource("/fxml/MainWindow.fxml")
                );

                primaryStage.close(); // закрытие формы авторизации
                Scene scene = new Scene(root, 400, 400);

                mainWindowStage.setScene(scene);
                mainWindowStage.show();
            } catch (IOException ex) {
                Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
            }
        });

        Scene scene = new Scene(GRID, 500, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    private EventHandler<ActionEvent> moveToRegistryWindow() {
        return event -> {
            Parent root = null;
            try {
                GRID.setDisable(true);
                root = FXMLLoader.load(this.getClass()
                        .getResource("/fxml/Registry.fxml"));
                Scene scene = new Scene(root, 600.0D, 400.0D);
                Stage registryStage = new Stage();
                registryStage.setScene(scene);
                RegistryController.STAGE = registryStage;
                RegistryController.parentPane = GRID;
                RegistryController.userDao = userDao;
                registryStage.show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        };
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        createEntityForDateBaseWork();
        launch(args);
    }

}
