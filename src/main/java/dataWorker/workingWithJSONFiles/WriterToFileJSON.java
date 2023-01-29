package dataWorker.workingWithJSONFiles;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

public class WriterToFileJSON {

    // Method for write to file.json
    public void writeToJSONFile(Map<Object, Object> map) {

        ObjectMapper objectMapper = new ObjectMapper();
        Map<Object, Object> mapJSON = new LinkedHashMap<>(map);

        try {
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(new File("src/main/resources/mapJson.json"), mapJSON);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
