package dataWorker.workingWithJSONFiles;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;
import shoppingCart.Devices;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class ChangeAmountOfDevice {

    //Method which updates info
    public void changeAmountOfDevice(List<Devices> cart) throws IOException {

        ClassLoader classLoader = ChangeAmountOfDevice.class.getClassLoader();

        JsonFactory jsonFactory = new JsonFactory();
        JsonParser jsonParser = jsonFactory.createParser(new File(Objects.requireNonNull(classLoader.getResource("jsonFile/mapJsonStorage.json")).getFile()));

        // find token which is FIELD_NAME. if it`s true, we`ll find field amount and update info
        while (jsonParser.nextToken() != null) {
            JsonToken token = jsonParser.getCurrentToken();

            if (token.equals(JsonToken.FIELD_NAME)) {
                String fieldName = jsonParser.getCurrentName();
                jsonParser.nextToken();

                if (fieldName.equals("amount")) {
                    jsonParser.setCurrentValue(cart);
                }
            }
        }
    }
}
