package com.example.jama;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

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
    private TableColumn<Sells, String> date_column;

    @FXML
    private TableColumn<Sells, Float> sum_column;


    @FXML
    void initialize() {



    }

}

