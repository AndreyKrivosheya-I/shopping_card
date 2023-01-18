
import shoppingCart.Devices;
import shoppingCart.TypeOfDevices;
import workingWithJSONFiles.ChangeAmounOfDevice;
import workingWithJSONFiles.WriterToFileJSON;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) throws IOException {
        Map<Object, Object> mapJSON = new LinkedHashMap<>();

        mapJSON.put(TypeOfDevices.HEADPHONE, new Devices(TypeOfDevices.HEADPHONE, 50, 2));
        mapJSON.put(TypeOfDevices.LAPTOP, new Devices(TypeOfDevices.LAPTOP, 500, 4));
        mapJSON.put(TypeOfDevices.PHONE, new Devices(TypeOfDevices.PHONE, 100, 6));

        WriterToFileJSON writerToFileJSON = new WriterToFileJSON();
        writerToFileJSON.writeToJSONFile(mapJSON);

        ChangeAmounOfDevice changeAmounOfDevice = new ChangeAmounOfDevice();

        try( BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in))){
                do {
                    System.out.println("Укажите желаемую для покупки технику:\n"
                            + "Чтобы выбрать товар нажмите от 1 до 3, в зависимости от Ваших пожеланий:\n" +
                            "1 - HeadPhones;\n" +
                            "2 - Phones;\n" +
                            "3 - LapTop.");

                    String device = bufferedReader.readLine();

                    if (device == null) break;

                    System.out.println("Спасибо! Вы выбрали тип техники: " + device);

                    Map<Object, Consumer<Object>> map = Map.of(
                            "HeadPhones", (value) -> {
                                try {
                                    changeAmounOfDevice.changeAmountOfDevice();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            },
                            "Laptop", (value) -> {
                                try {
                                    changeAmounOfDevice.changeAmountOfDevice();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            },
                            "Phones", (value) -> {
                                try {
                                    changeAmounOfDevice.changeAmountOfDevice();
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            });

                    Optional.ofNullable(map.get(device)).orElse((value) -> System.out.println("Извините, но это явно не число. Перезапустите программу и попробуйте снова!")).accept(device);
                    writerToFileJSON.writeToJSONFile(mapJSON);

                } while (true);
            }
        }
    }