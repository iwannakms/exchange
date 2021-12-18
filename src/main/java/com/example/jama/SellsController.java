package com.example.jama;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class SellsController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_button;

    @FXML
    private ToggleGroup currency_group;

    @FXML
    private RadioButton eur_radio;

    @FXML
    private RadioButton kgs_radio;

    @FXML
    private Button parameters_button;

    @FXML
    private Button reference_button;

    @FXML
    private TextField sum_field;

    @FXML
    private RadioButton rub_radio;

    @FXML
    private Button submit_button;

    @FXML
    private RadioButton usd_radio;

    @FXML
    private TableView<Cash> table_cash;

    @FXML
    private TableColumn<Cash, Float> kgs_col;

    @FXML
    private TableView<Cash> currency_table;

    @FXML
    private TableColumn<Cash, String> currency_col;

    ObservableList<Cash> cash_list = FXCollections.observableArrayList();
    ObservableList<Cash> currency_list = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        back_button.setOnAction(actionEvent -> {
            back_button.getScene().getWindow().hide();
            load_window("hello-view.fxml");
        });

        DB handler = new DB();
        try {
            ResultSet result = handler.getCash();
            show_table_cash(result);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        submit_button.setOnAction(actionEvent -> {
            float amount = Float.parseFloat(sum_field.getText());
            try {
                if(kgs_radio.isSelected())
                    handler.updateCashSells("KGS", amount);
                else if(rub_radio.isSelected())
                    handler.updateCashSells("RUB", amount);
                else if(usd_radio.isSelected())
                    handler.updateCashSells("USD", amount);
                else if(eur_radio.isSelected())
                    handler.updateCashSells("EUR", amount);

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Готово.");
                alert.setHeaderText(null);
                alert.setContentText("Касса успешно обновлена.");
                alert.showAndWait();
            } catch (SQLException| ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                ResultSet result = handler.getCash();
                show_table_cash(result);
            } catch (ClassNotFoundException | SQLException e) {
                e.printStackTrace();
            }
        });
    }

    private void show_table_cash(ResultSet cash) {

        currency_table.getItems().clear();
        String[] array_currency = {"KGS", "RUB", "USD", "EUR"};
        for (int i = 0; i < 4; i++) {
            currency_list.add(new Cash(array_currency[i], 0));
        }
        currency_col.setCellValueFactory(new PropertyValueFactory<Cash, String>("name"));
        currency_table.setItems(currency_list);

        cash_list.clear(); // ОЧИСТКА СПИСКА КЛИЕНТОВ

        try {
            while (cash.next()) {

                float amount = cash.getFloat(3);
                cash_list.add(new Cash(null, amount));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        kgs_col.setCellValueFactory(new PropertyValueFactory<Cash, Float>("amount"));
        table_cash.setItems(cash_list);
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
