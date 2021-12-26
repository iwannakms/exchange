package com.example.jama;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class PurchaseTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_button;

    @FXML
    private TableView<Purchase> purchase_table;

    @FXML
    private TableColumn<Purchase, String> currency_column;

    @FXML
    private TableColumn<Purchase, Date> date_column;

    @FXML
    private TableColumn<Purchase, Float> sum_column;

    ObservableList<Purchase> purchase_list = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        back_button.setOnAction(actionEvent -> {
            back_button.getScene().getWindow().hide();
            load_window("currency_purchase-view.fxml");
        });

        DB handler = new DB();
        try {
            ResultSet result_purchase = handler.selectPurchase();
            show_table_sells(result_purchase);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void show_table_sells(ResultSet purchase) {

        purchase_list.clear(); // ОЧИСТКА СПИСКА КЛИЕНТОВ

        try {
            while (purchase.next()) {
                String name = purchase.getString(2);
                float amount = purchase.getFloat(3);
                Date date = purchase.getDate(4);
                purchase_list.add(new Purchase(name, amount, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        currency_column.setCellValueFactory(new PropertyValueFactory<Purchase, String>("name"));
        sum_column.setCellValueFactory(new PropertyValueFactory<Purchase, Float>("amount"));
        date_column.setCellValueFactory(new PropertyValueFactory<Purchase, Date>("date"));
        purchase_table.setItems(purchase_list);
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

