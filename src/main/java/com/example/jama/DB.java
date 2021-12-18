package com.example.jama;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

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

    ResultSet SelectCash() throws SQLException, ClassNotFoundException{
        String select = "SELECT * FROM cash";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(select);
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

    void updateCashSells(String currency, float amount) throws SQLException, ClassNotFoundException {
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
            float updated_amount = currency_list.get(1) - amount;
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
            float updated_amount = currency_list.get(2) - amount;
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
            float updated_amount = currency_list.get(3) - amount;
            String update = "UPDATE cash set amount=? WHERE idcurrency=4";
            PreparedStatement preparedStatement1 = getDbConnection().prepareStatement(update);
            preparedStatement1.setFloat(1, updated_amount);
            preparedStatement1.executeUpdate();
        }

        String insert = "INSERT INTO currency_solt (name, amount, date) VALUES (?,?,?)";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(insert);
        preparedStatement.setString(1, currency);
        preparedStatement.setFloat(2, amount);
        preparedStatement.setDate(3, Date.valueOf(LocalDate.now()));

        preparedStatement.executeUpdate();

    }


    void selectSells(float amount) throws SQLException, ClassNotFoundException {
        String sql = "SELECT * FROM currency_solt";
        PreparedStatement preparedStatement = getDbConnection().prepareStatement(sql);
        preparedStatement.executeUpdate();
    }

}
