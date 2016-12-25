/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import encryptionMethods.base.SubstitutionCipher;
import encryptionMethods.bitrevers.BitReversCipher;
import encryptionMethods.monoAlphabet.MonoAlphabetCipher;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;
import org.hibernate.SessionFactory;
import utils.FileWorker;
import utils.alert.ErrorAlert;
import utils.alert.InformAlert;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.function.Consumer;
import java.util.function.Predicate;

import static utils.UtilFunctions.isNullString;

/**
 * FXML Controller class
 *
 * @author Алексей
 */
public class MainWindowController implements Initializable {

    public static SessionFactory sessionFactory;
    public static Stage stage;
    public static List<String> nameMethods;
    public MenuItem decodeMonoAlphabet;

    @FXML
    private TextArea textArea;
    @FXML
    private Menu encodeMenu;
    @FXML
    private Menu decodeMenu;

    private SubstitutionCipher<Integer> monoAlphabet = new MonoAlphabetCipher();
    private SubstitutionCipher<String> bitRevers = new BitReversCipher();
    private EventHandler<ActionEvent> nonMethodHandler;
    private Map<String, EventHandler<ActionEvent>> handlerMap = new HashMap<>();
    private Predicate<MenuItem> filterMethods;
    private Consumer<MenuItem> getDesiredMethods;

    public MainWindowController() {

    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initializationPrivateField();
        encodeMenu.getItems().stream().filter(filterMethods).forEach(getDesiredMethods);
        decodeMenu.getItems().stream().filter(filterMethods).forEach(getDesiredMethods);
        initializeStage();
    }

    private void initializationPrivateField() {
        //<editor-fold desc="Блок определения лямб для фильтрации списков меню шифрования/расшифрования" defaultstate="collapsed">
        filterMethods = menuItem -> {
            String id = menuItem.getId();
            return (nameMethods != null) && nameMethods.contains(id.substring(6));
        };
        getDesiredMethods = menuItem -> {
            String id = menuItem.getId();
            menuItem.setOnAction(getHandlerByMethodName(id));
            menuItem.setDisable(false);
        };
        //</editor-fold>
        //<editor-fold desc="Создание обработчика для ситуции когда метод не реализован" defaultstate="collapsed" >
        nonMethodHandler = event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Ввод ключа");
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setId("okDialog");
            buttonOk.setOnAction((ActionEvent evt) -> {
                dialog.close();
            });

            buttons.getChildren().addAll(buttonOk);
            Label label = new Label("Метод не реализован");
            label.setId("labelDialog");
            box.getChildren().addAll(label, buttons);
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        };
        //</editor-fold>
        //<editor-fold desc="Заполнения словаря обработчиков шифрования/расшифрования для реализованых методов"  defaultstate="collapsed">
        handlerMap.put("encodeMonoAlphabet", event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Окно ввода ключа");
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            TextField txtField = new TextField();
            txtField.setId("txtFieldDialog");
            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setId("okDialog");
            buttonOk.setOnAction((ActionEvent evt) -> {
                int key = Integer.valueOf(txtField.getText());
                monoAlphabet.calculationPrivateAlphabet(key);
                dialog.close();
                String codeText = monoAlphabet
                        .encodeText(textArea.getText().replaceAll("\n", " "));
                textArea.clear();
                textArea.setText(codeText);
            });
            Button buttonEx = new Button("Cancel");
            buttonEx.setId("cancelDialog");
            buttonEx.setOnAction(evt -> {
                dialog.close();
            });
            buttons.getChildren().addAll(buttonOk, buttonEx);
            Label label = new Label("Введите ключ");
            label.setId("labelDialog");
            box.getChildren().addAll(label, txtField, buttons);
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        });

        handlerMap.put("encodeBitRevers", event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Окно ввода ключа");
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            TextField txtField = new TextField();
            txtField.setId("txtFieldDialog");
            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setId("okDialog");
            buttonOk.setOnAction((ActionEvent evt) -> {
                bitRevers.calculationPrivateAlphabet(txtField.getText());
                dialog.close();
                String codeText = bitRevers
                        .encodeText(textArea.getText().replaceAll("\n", " "));
                textArea.clear();
                textArea.setText(codeText);
            });
            Button buttonEx = new Button("Cancel");
            buttonEx.setId("cancelDialog");
            buttonEx.setOnAction(evt -> {
                dialog.close();
            });
            buttons.getChildren().addAll(buttonOk, buttonEx);
            Label label = new Label("Введите ключ");
            label.setId("labelDialog");
            box.getChildren().addAll(label, txtField, buttons);
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        });

        handlerMap.put("decodeMonoAlphabet", event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Окно ввода ключа");
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            TextField txtField = new TextField();
            txtField.setId("txtFieldDialog");
            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setId("okDialog");
            buttonOk.setOnAction((ActionEvent evt) -> {
                int key = Integer.valueOf(txtField.getText());
                monoAlphabet.calculationPrivateAlphabet(key);
                dialog.close();
                String decodeText = monoAlphabet
                        .decodeText(textArea.getText().replaceAll("\n", " "));
                textArea.clear();
                textArea.setText(decodeText);
            });
            Button buttonEx = new Button("Cancel");
            buttonEx.setId("cancelDialog");
            buttonEx.setOnAction(evt -> {
                dialog.close();
            });
            buttons.getChildren().addAll(buttonOk, buttonEx);
            Label label = new Label("Введите ключ");
            label.setId("labelDialog");
            box.getChildren().addAll(label, txtField, buttons);
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        });

        handlerMap.put("decodeBitRevers", event -> {
            Stage dialog = new Stage();
            dialog.initStyle(StageStyle.UTILITY);
            dialog.setTitle("Окно ввода ключа");
            VBox box = new VBox();
            box.setAlignment(Pos.CENTER);
            HBox buttons = new HBox();
            TextField txtField = new TextField();
            buttons.setAlignment(Pos.CENTER);
            Button buttonOk = new Button("Ok");
            buttonOk.setId("okDialog");
            buttonOk.setOnAction((ActionEvent evt) -> {
                bitRevers.calculationPrivateAlphabet(txtField.getText());
                dialog.close();
                String decodeText = bitRevers
                        .decodeText(textArea.getText().replaceAll("\n", " "));
                textArea.clear();
                textArea.setText(decodeText);
            });
            Button buttonEx = new Button("Cancel");
            buttonEx.setId("cancelDialog");
            buttonEx.setOnAction(evt -> {
                dialog.close();
            });
            buttons.getChildren().addAll(buttonOk, buttonEx);
            Label label = new Label("Введите ключ");
            label.setId("labelDialog");
            box.getChildren().addAll(label, txtField, buttons);
            Scene scene = new Scene(box, 300, 100);
            dialog.setScene(scene);
            dialog.show();
        });
        //</editor-fold>
    }

    private void initializeStage() {
        if (stage == null) {
            stage = new Stage();
            stage.setTitle("Добро пожаловать,  Неизвестный");
        }
        ;
        stage.setOnCloseRequest(we -> sessionFactory.close());
    }


    private EventHandler<ActionEvent> getHandlerByMethodName(String methodName) {
        if (handlerMap.containsKey(methodName)) {
            return handlerMap.get(methodName);
        }
        return nonMethodHandler;
    }

    @FXML
    private void onActionProperty(ActionEvent event) {
        stage.fireEvent(new WindowEvent(
                stage,
                WindowEvent.WINDOW_CLOSE_REQUEST
        ));
    }

    @FXML
    private void onClearTextArea(ActionEvent event) {
        textArea.clear();
    }

    @FXML
    private void openFile(ActionEvent event) throws IOException {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения 
        fileChooser.setTitle("Open Document");//Заголовок диалога 
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");//Расширение 
        fileChooser.getExtensionFilters().add(extFilter);

        File file = fileChooser.showOpenDialog(stage);
        String str = FileWorker.readFile(file).toString();
        Alert alert = (isNullString(str) || str.equals("Файл не найден")) ?
                new ErrorAlert("Ошибка чтения файла: " + (isNullString(str) ? "Смотрите лог" : str))
                : new InformAlert("Файл прочитан.");
        textArea.setText(str);
        alert.showAndWait();
    }

    @FXML
    private void saveFile(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();//Класс работы с диалогом выборки и сохранения 
        fileChooser.setTitle("Save Document");//Заголовок диалога 
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("txt files (*.txt)", "*.txt");//Расширение 
        fileChooser.getExtensionFilters().add(extFilter);
        File file = fileChooser.showSaveDialog(stage);//Указываем текущую сцену
        boolean result = FileWorker.writeFile(file, textArea.getText());
        Alert alert = result ? new ErrorAlert("Ошибка записи в файл: Смотрите лог")
                : new InformAlert("Текст записан в файл");
        alert.showAndWait();
    }

    @FXML
    private void getHelpMethod(ActionEvent event) {
        Stage dialog = new Stage();
        dialog.initStyle(StageStyle.UTILITY);
        dialog.setTitle("Справка");
        VBox box = new VBox();
        box.setAlignment(Pos.CENTER);
        TextArea txtAreaHelp = new TextArea();
        txtAreaHelp.appendText("Моноалфавитная замена \n");
        txtAreaHelp.appendText(
                "При Моноалфавитной замене каждой букве открытого текста ставится соответствие одна буква \n"
                        + "закрытого текста из этого же алфавита. y_i=(K_1 X_i+K_2 ) mod n, где: n-длинна алфавита\n"
                        + "К_1 и К_2- это константы. К_1=1,К_2- это смещение \n"
                        + "сиволов закрытого алфавита относительно открытого алфавита, \n"
                        + "если К_1=1,К_2=3, то это так называемый код Цезаря. X_i- это код \n"
                        + "i символа открытого алфавита Y_i- это код i символа закрытого алфавита. \n"
        );
        txtAreaHelp.appendText("Побитовая перестановка\n");
        txtAreaHelp.appendText(
                "Несколько более сложной является побитовая перестановка, при которой в \n"
                        + "соответствии с вектором перестановки изменяются позиции разрядов двоичного кода \n"
                        + "символов открытого текста, обычно берутся ASCII коды. \n"
        );

        HBox buttons = new HBox();
        buttons.setAlignment(Pos.CENTER);
        Button buttonOk = new Button("Ok");
        buttonOk.setOnAction((ActionEvent evt) -> {
            dialog.close();
        });

        buttons.getChildren().addAll(buttonOk);
        box.getChildren().addAll(txtAreaHelp, buttons);
        Scene scene = new Scene(box, 600, 200);
        dialog.setScene(scene);
        dialog.show();
    }

}
