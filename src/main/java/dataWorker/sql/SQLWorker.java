package dataWorker.sql;

import basket.CustomersBasket;
import shoppingCart.Devices;
import shoppingCart.TypeOfDevices;

import java.sql.*;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class SQLWorker {

    private String URL = "jdbc:sqlite:";
    private Connection connection;

    public SQLWorker(String dbPath){
        URL += dbPath;

        try {
            connection = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage() + "\nYou could not connect with database. Path to file: " + URL);
        }
    }

    public Map<Object, Object> getStorageData(){
        Map<Object, Object> mapStorage = new HashMap<>();

        String query = String.format("SELECT d.%s, d.%s, s.%s FROM %s s INNER JOIN %s d USING(%s);",
                SQLConst.DEVICE_NAME, SQLConst.DEVICE_PRICE, SQLConst.STORAGE_AMOUNT,
                SQLConst.STORAGE_TABLE_NAME, SQLConst.DEVICE_TABLE_NAME, SQLConst.DEVICE_ID);

        try(Statement statement = connection.createStatement()){

            ResultSet res = statement.executeQuery(query);

            while (res.next()){
                Devices devices;
                TypeOfDevices type;

                try {
                    type = TypeOfDevices.valueOf(res.getString(SQLConst.DEVICE_NAME).toUpperCase(Locale.ROOT));
                }
                catch (IllegalArgumentException e){
                    System.out.println(e.getMessage() +
                            "\nInvalid name device: " + res.getString(SQLConst.DEVICE_NAME));
                    continue;
                }

                devices = new Devices(
                        type,
                        res.getDouble(SQLConst.DEVICE_PRICE),
                        res.getInt(SQLConst.STORAGE_AMOUNT)
                );

                mapStorage.put(type, devices);
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }

        return mapStorage;
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
                            TypeOfDevices.valueOf(res.getString(SQLConst.DEVICE_NAME).toUpperCase(Locale.ROOT)),
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

    public boolean updateData(TypeOfDevices type, int deltaAmount){
        String name = type.name().toLowerCase(Locale.ROOT);

        try (Statement statement = connection.createStatement()) {

            statement.execute("BEGIN TRANSACTION;");

            String queryFormat = "UPDATE %s SET %s = %s + %d WHERE %s = (SELECT %s FROM %s WHERE %s = '%s');";
            String queryUpdateStorage = String.format(queryFormat, SQLConst.STORAGE_TABLE_NAME,
                    SQLConst.STORAGE_AMOUNT, SQLConst.STORAGE_AMOUNT, -deltaAmount, SQLConst.STORAGE_DEVICE_ID,
                    SQLConst.DEVICE_ID, SQLConst.DEVICE_TABLE_NAME, SQLConst.DEVICE_NAME, name);
            String queryUpdateCart = String.format(queryFormat, SQLConst.CART_TABLE_NAME,
                    SQLConst.CART_AMOUNT, SQLConst.CART_AMOUNT, deltaAmount, SQLConst.CART_DEVICE_ID,
                    SQLConst.DEVICE_ID, SQLConst.DEVICE_TABLE_NAME, SQLConst.DEVICE_NAME, name);

            statement.execute(queryUpdateStorage);
            int changeInCart = statement.executeUpdate(queryUpdateCart);

            if (changeInCart == 0){
                String queryInsert = String.format("INSERT INTO %s(%s, %s) VALUES (%d, (SELECT %s FROM %s WHERE %s = '%s'));",
                        SQLConst.CART_TABLE_NAME, SQLConst.CART_AMOUNT, SQLConst.CART_DEVICE_ID, deltaAmount,
                        SQLConst.DEVICE_ID, SQLConst.DEVICE_TABLE_NAME, SQLConst.DEVICE_NAME, name);

                statement.execute(queryInsert);
            }

            statement.execute("COMMIT;");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return false;
        }

        return true;
    }

    public void close(){
        try {
            connection.close();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

}
