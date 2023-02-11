import basket.ChooseDevices;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {

        ChooseDevices chooseDevices = new ChooseDevices();
        chooseDevices.choose();

//        /* Start main part of program */
//        ChooseDevices chooseDevices = new ChooseDevices();
//        CustomersBasket customersBasket = new CustomersBasket();
//
//        /* Enter your code */
//
//        Map<Object, Object> map = new LinkedHashMap<>(chooseDevices.choose());
//        WriterToFileJSON writerToFileJSON = new WriterToFileJSON();
//        ChangeAmountOfDevice changeAmountOfDevice = new ChangeAmountOfDevice();
//        ReaderFileJson readerFileJson = new ReaderFileJson();
//
//        writerToFileJSON.writeToJSONFile(map, writerToFileJSON.pathToTheStorageJSONFile);
//        changeAmountOfDevice.changeAmountOfDevice(customersBasket.getCart());
//        readerFileJson.readFileJson(writerToFileJSON.pathToTheBasketJSONFile);

    }
}