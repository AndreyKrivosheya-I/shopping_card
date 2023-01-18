package workingWithJSONFiles;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonToken;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ChangeAmounOfDevice {

    public void changeAmountOfDevice() throws IOException {

        JsonFactory jsonFactory = new JsonFactory();

        ClassLoader classLoader = ChangeAmounOfDevice.class.getClassLoader();
        JsonParser jsonParser = jsonFactory.createParser(new File(Objects.requireNonNull(classLoader.getResource("mapJSON.json")).getFile()));

        while (jsonParser.nextToken() != null) {

            JsonToken token = jsonParser.getCurrentToken();

            if (token.equals(JsonToken.FIELD_NAME)) {
                String fieldName = jsonParser.getCurrentName();
                jsonParser.nextToken();

                if (fieldName.equals("amount")) {

                }
            }
        }
    }
}
