package dataWorker.workingWithJSONFiles;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WriterToFileJSON {
    public final String pathToTheStorageJSONFile = "src/main/resources/jsonFile/mapJsonStorage.json";
    public final String pathToTheBasketJSONFile = "src/main/resources/jsonFile/mapJsonBasket.json";

    // Method for write to file.json
    public void writeToJSONFile(Map<Object, Object> map, String path) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<Object, Object> mapJSON = new LinkedHashMap<>(map);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File(path), mapJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
