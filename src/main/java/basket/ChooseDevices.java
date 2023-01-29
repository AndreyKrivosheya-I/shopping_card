package basket;

import shoppingCart.Devices;
import shoppingCart.TypeOfDevices;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class ChooseDevices {

    /* Info about device into storage. Use for communicate with JSON/SQL file */
    private final Map<Object, Object> mapStorage = new HashMap<>();

    /* Info about device into cart */
    private CustomersBasket customersBasket;

    public ChooseDevices(){
        /* Read "storage file" by JSON (SQL) for fill mapStorage */
        defaultFillStorage();
        // fillStorageFromJSON();

        /* Read "cart file" by JSON (SQL) for fill customersBasket */
        defaultFillCart();
        // fillCartFromJSON();
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

                ((Devices) mapStorage.get(typeDevise)).setAmount(((Devices) mapStorage.get(typeDevise)).getAmount() - amount);
                customersBasket.add(new Devices(typeDevise, ((Devices) mapStorage.get(typeDevise)).getPrice(), amount));
            } while (true);

        } catch (IOException e) {
            System.out.println('\n' + e.getMessage());
            return null;
        }

        return mapStorage;
    }

    /* Fill storage`s map with default values */
    @Deprecated
    private void defaultFillStorage(){
        mapStorage.put(TypeOfDevices.HEADPHONE, new Devices(TypeOfDevices.HEADPHONE, 50., 2));
        mapStorage.put(TypeOfDevices.LAPTOP, new Devices(TypeOfDevices.LAPTOP, 500., 4));
        mapStorage.put(TypeOfDevices.PHONE, new Devices(TypeOfDevices.PHONE, 100., 6));
    }

    /* Fill storage`s map with values from JSON file */
    private void fillStorageFromJSON(){
        /* Enter code for parse JSON file and fill mapStorage */
    }

    /* Fill storage`s map with values from SQL file */
    private void fillStorageFromSQL(){
        /* Enter code for parse SQL file and fill mapStorage */
    }

    /* Fill cart`s object with default values */
    @Deprecated
    private void defaultFillCart(){
        customersBasket = new CustomersBasket(
                new Devices(TypeOfDevices.HEADPHONE, 50., 1),
                new Devices(TypeOfDevices.LAPTOP, 400., 2)
        );
    }

    /* Fill cart`s object with values from JSON file */
    private void fillCartFromJSON(){
        /* Enter code for parse JSON file and fill customersBasket */
    }

    /* Fill cart`s object with values from SQL file */
    private void fillCartFromSQL(){
        /* Enter code for parse SQL file and fill customersBasket */
    }

    /* Update file.json (storage and cart) */
    private void updateStorageAndCartJSON(){
        /* Enter your code */
    }

    /* Update file.sql (storage and cart) */
    private void updateStorageAndCartSQL(){
        /* Enter your code for update SQL database */
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
