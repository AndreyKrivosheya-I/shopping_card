import shoppingCart.HeadPhone;
import shoppingCart.LapTop;
import shoppingCart.Phone;
import writerToFileJSON.WriterToFileJSON;

import java.util.*;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        HeadPhone headPhone = new HeadPhone();
        headPhone.setAmount(4);
        headPhone.setPrice(350);

        Phone phone = new Phone();
        phone.setAmount(3);
        phone.setPrice(150);

        LapTop lapTop = new LapTop();
        lapTop.setAmount(7);
        lapTop.setPrice(500);

        Map<String, Object> mapJSON = new LinkedHashMap<>();
        mapJSON.put("HeadPhones", new HeadPhone[]{headPhone});
        mapJSON.put("Phones", new Phone[]{phone});
        mapJSON.put("LapTop", new LapTop[]{lapTop});

        WriterToFileJSON writerToFileJSON = new WriterToFileJSON();
        writerToFileJSON.writeToJSONFile(mapJSON);


        Scanner scanner = new Scanner(System.in);
        System.out.println("Укажите желаемую для покупки технику:\n"
                + "Чтобы выбрать товар нажмите от 1 до 3, в зависимости от Ваших пожеланий:\n" +
                "1 - HeadPhones;\n" +
                "2 - Phones;\n" +
                "3 - LapTop.");

        while (scanner.hasNextLine()) {
            String device = scanner.next();
            System.out.println("Спасибо! Вы выбрали тип техники: " + device);
            Map<String, Consumer<Object>> map = Map.of(
                    "HeadPhones", (value) -> headPhone.setAmount(headPhone.getAmount() - 1),
                    "Phones",(value) -> phone.setAmount(phone.getAmount() - 1),
                    "LapTop",(value) -> lapTop.setAmount(lapTop.getAmount() - 1));
            Optional.ofNullable(map.get(device)).orElse((value) -> System.out.println("Извините, но это явно не число. Перезапустите программу и попробуйте снова!")).accept(device);


            writerToFileJSON.writeToJSONFile(mapJSON);

        }

    }
}
