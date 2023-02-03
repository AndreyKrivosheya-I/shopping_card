package dataWorker.workingWithJSONFiles;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Objects;

public class ReaderFileJson {

    //Method for read file JSON
    public void readFileJson(String path) {

        ClassLoader classLoader = WriterToFileJSON.class.getClassLoader();

        ObjectMapper objectMapper = new ObjectMapper();
        Map<Object, Object> mapJSON;

        try {
            mapJSON = objectMapper.readValue(new File(Objects.requireNonNull(classLoader.getResource("jsonFile/mapJsonBasket.json")).getFile()),
                    new TypeReference<>() {
                    }
            );

            for (Map.Entry<Object, Object> entry : mapJSON.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
