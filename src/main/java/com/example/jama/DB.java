package com.example.jama;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Alert;

import java.sql.*;
import java.time.LocalDate;

public class DB extends Configs{

    Connection dbConnection;

    public Connection getDbConnection() throws ClassNotFoundException, SQLException {

        String connectionString = "jdbc:mysql://" + dbHost + ":" + dbPort + "/" + dbname;
        Class.forName("com.mysql.jdbc.Driver");
        dbConnection = DriverManager.getConnection(connectionString, dbUser, dbPassword);

        return dbConnection;
    }

    ResultSet getCash() throws ClassNotFoundException, SQLException {
        String select = "SELECT * FROM cash";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    ResultSet selectSells() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM currency_solt";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    ResultSet selectPurchase() throws ClassNotFoundException, SQLException {
        String sql = "SELECT * FROM currency_purchase";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();

        return resultSet;
    }

    void updateCash(String currency, float amount) throws SQLException, ClassNotFoundException {
        ObservableList<Float> currency_list = FXCollections.observableArrayList();
        ResultSet resultSet;
        if(currency.equals("KGS")){
            currency_list.clear();
            String select = "SELECT * FROM cash";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                float result_amount = resultSet.getFloat(3);
                currency_list.add(result_amount);
            }
            float updated_amount = currency_list.get(0) + amount;
            String update = "UPDATE cash set amount=? WHERE idcurrency=1";
            PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
            preparedStatement1.setFloat(1, updated_amount);
            preparedStatement1.executeUpdate();
        }
        else if(currency.equals("RUB")){
            currency_list.clear();
            String select = "SELECT * FROM cash";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                float result_amount = resultSet.getFloat(3);
                currency_list.add(result_amount);
            }
            float updated_amount = currency_list.get(1) + amount;
            String update = "UPDATE cash set amount=? WHERE idcurrency=2";
            PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
            preparedStatement1.setFloat(1, updated_amount);
            preparedStatement1.executeUpdate();
        }
        else if(currency.equals("USD")){
            currency_list.clear();
            String select = "SELECT * FROM cash";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                float result_amount = resultSet.getFloat(3);
                currency_list.add(result_amount);
            }
            float updated_amount = currency_list.get(2) + amount;
            String update = "UPDATE cash set amount=? WHERE idcurrency=3";
            PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
            preparedStatement1.setFloat(1, updated_amount);
            preparedStatement1.executeUpdate();
        }
        else if(currency.equals("EUR")){
            currency_list.clear();
            String select = "SELECT * FROM cash";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                float result_amount = resultSet.getFloat(3);
                currency_list.add(result_amount);
            }
            float updated_amount = currency_list.get(3) + amount;
            String update = "UPDATE cash set amount=? WHERE idcurrency=4";
            PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
            preparedStatement1.setFloat(1, updated_amount);
            preparedStatement1.executeUpdate();
        }
    }

    void updateCashSells(String currency, float amount, String currency2, float amount2) throws SQLException, ClassNotFoundException {
        ObservableList<Float> currency_list = FXCollections.observableArrayList();
        ResultSet resultSet;
        if(currency.equals("KGS")){
            currency_list.clear();
            String select = "SELECT * FROM cash";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                float result_amount = resultSet.getFloat(3);
                currency_list.add(result_amount);
            }
            float updated_amount = currency_list.get(0) - amount;
            String update = "UPDATE cash set amount=? WHERE idcurrency=1";
            PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
            preparedStatement1.setFloat(1, updated_amount);
            preparedStatement1.executeUpdate();

            String insert = "INSERT INTO currency_solt (name, amount, date) VALUES (?,?,?)";
            PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
            preparedStatement2.setString(1, currency2);
            preparedStatement2.setFloat(2, amount2);
            preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
            preparedStatement2.executeUpdate();
        }
        else if(currency.equals("RUB")){
            currency_list.clear();
            String select = "SELECT * FROM cash";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                float result_amount = resultSet.getFloat(3);
                currency_list.add(result_amount);
            }

            if (amount <= currency_list.get(1)) {
                float updated_amount = currency_list.get(1) - amount;
                String update = "UPDATE cash set amount=? WHERE idcurrency=2";
                PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
                preparedStatement1.setFloat(1, updated_amount);
                preparedStatement1.executeUpdate();

                String insert = "INSERT INTO currency_solt (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency2);
                preparedStatement2.setFloat(2, amount2);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Готово.");
                alert.setHeaderText(null);
                alert.setContentText("Касса успешно обновлена.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("В кассе недостаточно средств для выполнения обмена.");
                alert.showAndWait();
            }

        }
        else if(currency.equals("USD")){
            currency_list.clear();
            String select = "SELECT * FROM cash";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                float result_amount = resultSet.getFloat(3);
                currency_list.add(result_amount);
            }
            if (amount <= currency_list.get(2)) {
                float updated_amount = currency_list.get(2) - amount;
                String update = "UPDATE cash set amount=? WHERE idcurrency=3";
                PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
                preparedStatement1.setFloat(1, updated_amount);
                preparedStatement1.executeUpdate();

                String insert = "INSERT INTO currency_solt (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency2);
                preparedStatement2.setFloat(2, amount2);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Готово.");
                alert.setHeaderText(null);
                alert.setContentText("Касса успешно обновлена.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("В кассе недостаточно средств для выполнения обмена.");
                alert.showAndWait();
            }

        }
        else if(currency.equals("EUR")){
            currency_list.clear();
            String select = "SELECT * FROM cash";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                float result_amount = resultSet.getFloat(3);
                currency_list.add(result_amount);
            }
            if (amount <= currency_list.get(3)) {
                float updated_amount = currency_list.get(3) - amount;
                String update = "UPDATE cash set amount=? WHERE idcurrency=4";
                PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
                preparedStatement1.setFloat(1, updated_amount);
                preparedStatement1.executeUpdate();

                String insert = "INSERT INTO currency_solt (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency2);
                preparedStatement2.setFloat(2, amount2);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Готово.");
                alert.setHeaderText(null);
                alert.setContentText("Касса успешно обновлена.");
                alert.showAndWait();
            } else {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Ошибка");
                alert.setHeaderText(null);
                alert.setContentText("В кассе недостаточно средств для выполнения обмена.");
                alert.showAndWait();
            }
        }
    }

    void updateCashPurchase(String currency, float amount, String currency2, float amount2) throws SQLException, ClassNotFoundException {
        ObservableList<Float> currency_list = FXCollections.observableArrayList();
        ResultSet resultSet;
        if(currency.equals("KGS")){
            currency_list.clear();
            String select = "SELECT * FROM cash";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                float result_amount = resultSet.getFloat(3);
                currency_list.add(result_amount);
            }
            float updated_amount = currency_list.get(0) + amount;
            String update = "UPDATE cash set amount=? WHERE idcurrency=1";
            PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
            preparedStatement1.setFloat(1, updated_amount);
            preparedStatement1.executeUpdate();

            if (currency2.equals("RUB")) {
                float updated_amount1 = currency_list.get(1) - amount2;
                String update1 = "UPDATE cash set amount=? WHERE idcurrency=2";
                PreparedStatement prSt_rub = getDbConnection().prepareStatement(update1);
                prSt_rub.setFloat(1, updated_amount1);
                prSt_rub.executeUpdate();

                String insert = "INSERT INTO currency_purchase (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency);
                preparedStatement2.setFloat(2, amount);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

            } else if (currency2.equals("USD")) {
                float updated_amount1 = currency_list.get(2) - amount2;
                String update2 = "UPDATE cash set amount=? WHERE idcurrency=3";
                PreparedStatement prSt_eur = getDbConnection().prepareStatement(update2);
                prSt_eur.setFloat(1, updated_amount1);
                prSt_eur.executeUpdate();

                String insert = "INSERT INTO currency_purchase (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency);
                preparedStatement2.setFloat(2, amount);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

            } else if (currency2.equals("EUR")) {
                float updated_amount1 = currency_list.get(3) - amount2;
                String update3 = "UPDATE cash set amount=? WHERE idcurrency=4";
                PreparedStatement prSt_usd = getDbConnection().prepareStatement(update3);
                prSt_usd.setFloat(1, updated_amount1);
                prSt_usd.executeUpdate();

                String insert = "INSERT INTO currency_purchase (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency);
                preparedStatement2.setFloat(2, amount);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Готово.");
            alert.setHeaderText(null);
            alert.setContentText("Касса успешно обновлена.");
            alert.showAndWait();

        } else if(currency.equals("RUB")){
            currency_list.clear();
            String select = "SELECT * FROM cash";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                float result_amount = resultSet.getFloat(3);
                currency_list.add(result_amount);
            }
            float updated_amount = currency_list.get(1) + amount;
            String update = "UPDATE cash set amount=? WHERE idcurrency=2";
            PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
            preparedStatement1.setFloat(1, updated_amount);
            preparedStatement1.executeUpdate();

            if (currency2.equals("KGS")) {
                float updated_amount1 = currency_list.get(0) - amount2;
                String update1 = "UPDATE cash set amount=? WHERE idcurrency=1";
                PreparedStatement prSt_rub = getDbConnection().prepareStatement(update1);
                prSt_rub.setFloat(1, updated_amount1);
                prSt_rub.executeUpdate();

                String insert = "INSERT INTO currency_purchase (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency);
                preparedStatement2.setFloat(2, amount);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

            } else if (currency2.equals("USD")) {
                float updated_amount1 = currency_list.get(2) - amount2;
                String update2 = "UPDATE cash set amount=? WHERE idcurrency=3";
                PreparedStatement prSt_eur = getDbConnection().prepareStatement(update2);
                prSt_eur.setFloat(1, updated_amount1);
                prSt_eur.executeUpdate();

                String insert = "INSERT INTO currency_purchase (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency);
                preparedStatement2.setFloat(2, amount);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

            } else if (currency2.equals("EUR")) {
                float updated_amount1 = currency_list.get(3) - amount2;
                String update3 = "UPDATE cash set amount=? WHERE idcurrency=4";
                PreparedStatement prSt_usd = getDbConnection().prepareStatement(update3);
                prSt_usd.setFloat(1, updated_amount1);
                prSt_usd.executeUpdate();

                String insert = "INSERT INTO currency_purchase (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency);
                preparedStatement2.setFloat(2, amount);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Готово.");
            alert.setHeaderText(null);
            alert.setContentText("Касса успешно обновлена.");
            alert.showAndWait();
        }
        else if(currency.equals("USD")){
            currency_list.clear();
            String select = "SELECT * FROM cash";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                float result_amount = resultSet.getFloat(3);
                currency_list.add(result_amount);
            }
            float updated_amount = currency_list.get(2) + amount;
            String update = "UPDATE cash set amount=? WHERE idcurrency=3";
            PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
            preparedStatement1.setFloat(1, updated_amount);
            preparedStatement1.executeUpdate();

            if (currency2.equals("KGS")) {
                float updated_amount1 = currency_list.get(0) - amount2;
                String update1 = "UPDATE cash set amount=? WHERE idcurrency=1";
                PreparedStatement prSt_rub = getDbConnection().prepareStatement(update1);
                prSt_rub.setFloat(1, updated_amount1);
                prSt_rub.executeUpdate();

                String insert = "INSERT INTO currency_purchase (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency);
                preparedStatement2.setFloat(2, amount);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

            } else if (currency2.equals("RUB")) {
                float updated_amount1 = currency_list.get(1) - amount2;
                String update2 = "UPDATE cash set amount=? WHERE idcurrency=2";
                PreparedStatement prSt_eur = getDbConnection().prepareStatement(update2);
                prSt_eur.setFloat(1, updated_amount1);
                prSt_eur.executeUpdate();

                String insert = "INSERT INTO currency_purchase (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency);
                preparedStatement2.setFloat(2, amount);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

            } else if (currency2.equals("EUR")) {
                float updated_amount1 = currency_list.get(3) - amount2;
                String update3 = "UPDATE cash set amount=? WHERE idcurrency=4";
                PreparedStatement prSt_usd = getDbConnection().prepareStatement(update3);
                prSt_usd.setFloat(1, updated_amount1);
                prSt_usd.executeUpdate();

                String insert = "INSERT INTO currency_purchase (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency);
                preparedStatement2.setFloat(2, amount);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Готово.");
            alert.setHeaderText(null);
            alert.setContentText("Касса успешно обновлена.");
            alert.showAndWait();
        }
        else if(currency.equals("EUR")){
            currency_list.clear();
            String select = "SELECT * FROM cash";
            PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
            resultSet = preparedStatement.executeQuery();

            while(resultSet.next()){
                float result_amount = resultSet.getFloat(3);
                currency_list.add(result_amount);
            }
            float updated_amount = currency_list.get(3) + amount;
            String update = "UPDATE cash set amount=? WHERE idcurrency=4";
            PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
            preparedStatement1.setFloat(1, updated_amount);
            preparedStatement1.executeUpdate();

            if (currency2.equals("RUB")) {
                float updated_amount1 = currency_list.get(1) - amount2;
                String update1 = "UPDATE cash set amount=? WHERE idcurrency=2";
                PreparedStatement prSt_rub = getDbConnection().prepareStatement(update1);
                prSt_rub.setFloat(1, updated_amount1);
                prSt_rub.executeUpdate();

                String insert = "INSERT INTO currency_purchase (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency);
                preparedStatement2.setFloat(2, amount);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

            } else if (currency2.equals("USD")) {
                float updated_amount1 = currency_list.get(2) - amount2;
                String update2 = "UPDATE cash set amount=? WHERE idcurrency=3";
                PreparedStatement prSt_eur = getDbConnection().prepareStatement(update2);
                prSt_eur.setFloat(1, updated_amount1);
                prSt_eur.executeUpdate();

                String insert = "INSERT INTO currency_purchase (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency);
                preparedStatement2.setFloat(2, amount);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

            } else if (currency2.equals("KGS")) {
                float updated_amount1 = currency_list.get(0) - amount2;
                String update3 = "UPDATE cash set amount=? WHERE idcurrency=1";
                PreparedStatement prSt_usd = getDbConnection().prepareStatement(update3);
                prSt_usd.setFloat(1, updated_amount1);
                prSt_usd.executeUpdate();

                String insert = "INSERT INTO currency_purchase (name, amount, date) VALUES (?,?,?)";
                PreparedStatement preparedStatement2 = getDbConnection().prepareStatement(insert);
                preparedStatement2.setString(1, currency);
                preparedStatement2.setFloat(2, amount);
                preparedStatement2.setDate(3, Date.valueOf(LocalDate.now()));
                preparedStatement2.executeUpdate();

            }

            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Готово.");
            alert.setHeaderText(null);
            alert.setContentText("Касса успешно обновлена.");
            alert.showAndWait();
        }
    }
}
