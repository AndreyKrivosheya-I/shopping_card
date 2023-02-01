package basket;

import dataWorker.sql.SQLWorker;
import shoppingCart.Devices;
import shoppingCart.TypeOfDevices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChooseDevices {

    /* Info about device into storage. Use for communicate with JSON/SQL file */
    private Map<Object, Object> mapStorage = new HashMap<>();

    /* Info about device into cart */
    private CustomersBasket customersBasket;

    /* Class for connect (select and update) info from sql file */
    private SQLWorker sqlWorker;

    public ChooseDevices(){
        fillDataFromSQL();
        fillDataDefault();
    }

    public Map<Object, Object> choose(){

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))) {

            do {
                System.out.println("Укажите желаемую для покупки технику:\n"
                        + "Чтобы выбрать товар введите его полное имя, в зависимости от Ваших пожеланий:\n" +
                        "1 - HeadPhone;\n" +
                        "2 - Phone;\n" +
                        "3 - LapTop.");

                String device = bufferedReader.readLine().toUpperCase(Locale.ROOT);
                if (device.isEmpty()) break;
                if (Arrays.stream(TypeOfDevices.values()).noneMatch(type -> type.toString().equalsIgnoreCase(device))){
                    System.out.println("Введенно некорекное название товара. Попробуйте ещё раз, пожалуйста");
                    continue;
                }
                TypeOfDevices typeDevise = TypeOfDevices.valueOf(device);

                System.out.printf("Спасибо! Вы выбрали тип техники: %10s", device + "\n");
                System.out.println("Укажите количество желаемого товара (натуральные числа без лишних обозначений)");

                int amount;
                String strAmount = bufferedReader.readLine();
                while ((amount = tryParse(strAmount)) == -1){
                    System.out.println("Введенно число в неправильном формате.\nПожалуйста укажите натуральное число");
                    strAmount = bufferedReader.readLine();
                }

                if (((Devices) mapStorage.get(typeDevise)).getAmount() < amount){
                    System.out.println("На данный момент нет необходимого количества нужных вам товаров.\n" +
                            "Их осталось" + ((Devices) mapStorage.get(typeDevise)).getAmount() + '\n' +
                            "Приносим извинения за продоставленные неудобства, давайте вернёмся к выбору товаров.");
                    continue;
                }

                if(updateStorageAndCartSQL(typeDevise, amount)){
                    ((Devices) mapStorage.get(typeDevise)).setAmount(((Devices) mapStorage.get(typeDevise)).getAmount() - amount);
                    customersBasket.add(new Devices(typeDevise, ((Devices) mapStorage.get(typeDevise)).getPrice(), amount));
                }
                else
                    System.out.println("Возникли неполадки, повторите попытку позже");

            } while (true);

        } catch (IOException e) {
            System.out.println('\n' + e.getMessage());
            return null;
        }

        return mapStorage;
    }



    /* Fill storage`s map and cart`s object with values from JSON file */
    private void fillDataFromJSON(){
        /* Enter your code */
    }

    /* Fill storage`s map and cart`s object with values from SQL file */
    private void fillDataFromSQL(){
        sqlWorker = new SQLWorker(
                Objects.requireNonNull(ChooseDevices.class.getResource("/database/dataSQL.db")).getPath());
        mapStorage = sqlWorker.getStorageData();
        customersBasket = sqlWorker.getCartData();
    }

    /* Fill storage`s map and cart`s object with default values */
    @Deprecated
    private void fillDataDefault(){
        mapStorage.put(TypeOfDevices.HEADPHONE, new Devices(TypeOfDevices.HEADPHONE, 50., 2));
        mapStorage.put(TypeOfDevices.LAPTOP, new Devices(TypeOfDevices.LAPTOP, 500., 4));
        mapStorage.put(TypeOfDevices.PHONE, new Devices(TypeOfDevices.PHONE, 100., 6));

        customersBasket = new CustomersBasket(
                new Devices(TypeOfDevices.HEADPHONE, 50., 1),
                new Devices(TypeOfDevices.LAPTOP, 400., 2)
        );
    }

    /* Update file.json (storage and cart) */
    private boolean updateStorageAndCartJSON(){
        /* Enter your code */
        return false;
    }

    /* Update file.db (storage and cart) */
    private boolean updateStorageAndCartSQL(TypeOfDevices type, int deltaAmount){
        return sqlWorker.updateData(type, deltaAmount);
    }

    /* Parse String to positive int (natural number) and return -1, if String have invalid format */
    private int tryParse(String s){
        if (s == null) return -1;

        try {
            int temp = Integer.parseInt(s);
            return temp > 0 ? temp : -1;
        }
        catch (NumberFormatException e){
            return -1;
        }
    }
}
