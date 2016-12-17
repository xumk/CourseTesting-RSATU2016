package login;

import javafx.application.Application;
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

import java.io.IOException;
import java.util.HashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Алексей
 */
public class Login extends Application {

    private static final HashMap<String, String> logPassMap;
    private static int count = 0;

    static {
        logPassMap = new HashMap<>();
        logPassMap.put("Ивашин", "yui");
        logPassMap.put("Распопова", "ghj");
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Окно авторизации");

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setVgap(10);
        grid.setHgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text sceneTitle = new Text("Добро пожаловать");
        sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grid.add(sceneTitle, 0, 0, 2, 1);

        Label userName = new Label("User Name: ");
        grid.add(userName, 0, 1);

        TextField userTextField = new TextField();
        grid.add(userTextField, 1, 1);

        Label password = new Label("Password: ");
        grid.add(password, 0, 2);

        PasswordField pwBox = new PasswordField();
        grid.add(pwBox, 1, 2);

        Button sign = new Button("Авторизоваться");
        Button exit = new Button("Выход");
        Button registration = new Button("Регистрация");

        HBox hbSign = new HBox(10);
        hbSign.setAlignment(Pos.BOTTOM_LEFT);
        hbSign.getChildren().add(sign);
        hbSign.getChildren().add(registration);
        hbSign.getChildren().add(exit);
        grid.add(hbSign, 1, 5);

        final Text actionTarget = new Text();
        grid.add(actionTarget, 1, 6);

        exit.setOnAction(event -> {
            primaryStage.close();
        });

        sign.setOnAction(event -> {
            actionTarget.setFill(Color.FIREBRICK);
            String name = userTextField.getText();
            if (!logPassMap.containsKey(name)) {
                actionTarget.setText("Такого пользователя не существует");
            } else {
                String passMap = logPassMap.get(name);
                String pass = pwBox.getText();
                if (!passMap.equals(pass)) {
                    ++count;
                    if (count == 3) {
                        primaryStage.close();
                    }
                    actionTarget.setText("Не верный пароль");
                } else {
                    actionTarget.setFill(Color.GREEN);
                    actionTarget.setText("Пароль верный");
                    primaryStage.close(); // закрытие формы авторизации
                    Parent root = null;

                    Stage stageM = new Stage();

                    try {
                        switch (name) {
                            case "Ивашин":
                                MainWindowController.nameMethod1 = "MonoAlphabet";
                                MainWindowController.nameMethod2 = "BitRevers";
                                stageM.setTitle("Welcome, Alex");
                                break;
                            case "Распопова":
                                stageM.setTitle("Welcome, Masha");
                                MainWindowController.nameMethod1 = "PoligrammnayaReplacement";
                                MainWindowController.nameMethod2 = "VerticalPermutation";
                        }
                        root = FXMLLoader.load(this.getClass()
                                .getResource("/fxml/MainWindow.fxml")
                        );
                        Scene scene = new Scene(root, 400, 400);
                        MainWindowController.STAGE = stageM;
                       
                        stageM.setScene(scene);
                        stageM.show();
                    } catch (IOException ex) {
                        Logger.getLogger(Login.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    //TODO: сделать создание окна работы с кодами
                }
            }
        });

        Scene scene = new Scene(grid, 500, 275);
        primaryStage.setScene(scene);

        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

}
