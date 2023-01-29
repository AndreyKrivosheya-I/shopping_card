package dataWorker.sql;

import basket.CustomersBasket;
import shoppingCart.Devices;
import shoppingCart.TypeOfDevices;

import java.sql.*;

public class SQLWorker {

    private String URL = "jdbc:sqlite:";
    private Connection connection;

    public SQLWorker(String dbPath){
        URL += dbPath;

        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(dbPath);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\nYou could not connect with database. Path to file: " + URL);
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public CustomersBasket getCartData(){
        CustomersBasket customersBasket = new CustomersBasket();

        try (Statement statement = connection.createStatement()){

            String query = String.format("SELECT d.%s, d.%s, c.%s FROM %s c INNER JOIN %s d USING(%s);",
                    SQLConst.DEVICE_NAME, SQLConst.DEVICE_PRICE, SQLConst.CART_AMOUNT,
                    SQLConst.CART_TABLE_NAME, SQLConst.DEVICE_TABLE_NAME, SQLConst.DEVICE_ID);

            ResultSet res = statement.executeQuery(query);

            while (res.next()){
                Devices device;
                try {
                    device = new Devices(
                            TypeOfDevices.valueOf(res.getString(SQLConst.DEVICE_NAME)),
                            res.getDouble(SQLConst.DEVICE_PRICE),
                            res.getInt(SQLConst.CART_AMOUNT)
                    );
                }
                catch (IllegalArgumentException e){
                    System.out.println(e.getMessage() +
                            "\nInvalid name device: " + res.getString(SQLConst.DEVICE_NAME));
                    continue;
                }
                customersBasket.add(device);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return customersBasket;
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
