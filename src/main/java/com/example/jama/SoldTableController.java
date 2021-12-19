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

public class SoldTableController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button back_button;

    @FXML
    private TableView<Sells> solt_table;

    @FXML
    private TableColumn<Sells, String> currency_column;

    @FXML
    private TableColumn<Sells, Date> date_column;

    @FXML
    private TableColumn<Sells, Float> sum_column;

    ObservableList<Sells> sells_list = FXCollections.observableArrayList();

    @FXML
    void initialize() {

        back_button.setOnAction(actionEvent -> {
            back_button.getScene().getWindow().hide();
            load_window("sells-view.fxml");
        });

        DB handler = new DB();
        try {
            ResultSet result_sells = handler.selectSells();
            show_table_sells(result_sells);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

    }

    private void show_table_sells(ResultSet sells) {

        sells_list.clear(); // ОЧИСТКА СПИСКА КЛИЕНТОВ

        try {
            while (sells.next()) {
                String name = sells.getString(2);
                float amount = sells.getFloat(3);
                Date date = sells.getDate(4);
                sells_list.add(new Sells(name, amount, date));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        currency_column.setCellValueFactory(new PropertyValueFactory<Sells, String>("name"));
        sum_column.setCellValueFactory(new PropertyValueFactory<Sells, Float>("amount"));
        date_column.setCellValueFactory(new PropertyValueFactory<Sells, Date>("date"));
        solt_table.setItems(sells_list);
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
