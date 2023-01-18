package writerToFileJSON;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import shoppingCart.HeadPhone;
import shoppingCart.LapTop;
import shoppingCart.Phone;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;

public class WriterToFileJSON {

    public void writeToJSONFile(Map<String, Object> map) {

        ClassLoader classLoader = WriterToFileJSON.class.getClassLoader();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<String, Object> mapJSON = new LinkedHashMap<>(map);


        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/mapJson.json"), mapJSON);

            mapJSON = objectMapper.readValue(new File(Objects.requireNonNull(classLoader.getResource("mapJSON.json")).getFile()),
                    new TypeReference<Map<String, Object>>() {
                    }
            );

            for (Map.Entry<String, Object> entry : mapJSON.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
