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
    private ToggleGroup currency_group2;

    @FXML
    private RadioButton eur_radio;

    @FXML
    private RadioButton kgs_radio;

    @FXML
    private Button show_sells_button;

    @FXML
    private Button reference_button;

    @FXML
    private TextField sum_field;

    @FXML
    private RadioButton kgs_radio2;

    @FXML
    private RadioButton rub_radio2;

    @FXML
    private RadioButton usd_radio2;

    @FXML
    private RadioButton eur_radio2;

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

        show_sells_button.setOnAction(actionEvent -> {
            show_sells_button.getScene().getWindow().hide();
            load_window("soldTable-view.fxml");
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
                if(kgs_radio.isSelected()){
                    if(rub_radio2.isSelected()){
                        handler.updateCashSells("KGS", amount, "RUB", amount*0.87f);
                    }else if(usd_radio2.isSelected()){
                        handler.updateCashSells("KGS", amount, "USD", amount*0.012f);
                    }else if(eur_radio2.isSelected()){
                        handler.updateCashSells("KGS", amount, "EUR", amount*0.01f);
                    }
                }

                else if(rub_radio.isSelected()) {
                    if (kgs_radio2.isSelected()) {
                        handler.updateCashSells("RUB", amount, "KGS", amount * 1.14f);
                    } else if (usd_radio2.isSelected()) {
                        handler.updateCashSells("RUB", amount, "USD", amount * 0.013f);
                    } else if (eur_radio2.isSelected()) {
                        handler.updateCashSells("RUB", amount, "EUR", amount * 0.012f);
                    }
                }
                else if(usd_radio.isSelected()) {
                    if (kgs_radio2.isSelected()) {
                        handler.updateCashSells("USD", amount, "KGS", amount * 84.8f);
                    } else if (usd_radio2.isSelected()) {
                        handler.updateCashSells("USD", amount, "RUB", amount * 74.17f);
                    } else if (eur_radio2.isSelected()) {
                        handler.updateCashSells("USD", amount, "EUR", amount * 0.89f);
                    }
                }
                else if(eur_radio.isSelected()) {
                    if (kgs_radio2.isSelected()) {
                        handler.updateCashSells("EUR", amount, "KGS", amount * 95.31f);
                    } else if (usd_radio2.isSelected()) {
                        handler.updateCashSells("EUR", amount, "RUB", amount * 83.37f);
                    } else if (eur_radio2.isSelected()) {
                        handler.updateCashSells("EUR", amount, "USD", amount * 1.12f);
                    }
                }
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
