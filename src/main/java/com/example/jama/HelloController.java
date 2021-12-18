package com.example.jama;

import java.io.IOException;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    ImageView image_view;

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button buy_button;

    @FXML
    private Button cash_add_button;

    @FXML
    private Button parameters_button;

    @FXML
    private Button sell_button;

    @FXML
    private Button reference_button;


    @FXML
    void initialize() {

        Image myImage = new Image(Objects.requireNonNull(getClass().getResourceAsStream("currencies.png")));
        image_view.setImage(myImage);

        cash_add_button.setOnAction(actionEvent -> {
            cash_add_button.getScene().getWindow().hide();
            load_window("add_cash-view.fxml");
        });

        reference_button.setOnAction(actionEvent -> {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Справка.");
            alert.setHeaderText(null);
            alert.setContentText("Кассир - Тулекеев Темирлан" +
                    "№1 г. Бишкек, ул. Малдыбаева,");
            alert.showAndWait();
        });

        sell_button.setOnAction(actionEvent -> {
            sell_button.getScene().getWindow().hide();
            load_window("sells-view.fxml");
        });
    }

    private void load_window(String url) {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(HelloController.class.getResource(url));

        try {
            loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        Parent root = loader.getRoot();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.show();
    }

}
