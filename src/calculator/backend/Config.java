package calculator.backend;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Config {
    public static boolean isDarkMode;
    public static Map<String, List<List<Double>>> subjects = new HashMap<>();

    public static void initialize() {
        JSONParser parser = new JSONParser();
        try {
            Path filePath = Paths.get("config.json").toAbsolutePath();
            
            FileReader reader = new FileReader(filePath.toString());
            JSONObject config = (JSONObject) parser.parse(reader);

            isDarkMode = (boolean) config.get("isDarkMode");
            JSONObject subjectsJSON = (JSONObject) config.get("subjects");
            
            subjectsJSON.forEach((k, v) -> {
                String subject = (String) k;
                JSONArray gradingSystemJSON = (JSONArray) v;
    
                List<List<Double>> gradingSystem = new ArrayList<>();
                for (int i = 0; i < gradingSystemJSON.size(); i++) {
                    JSONArray rangeList = (JSONArray) gradingSystemJSON.get(i);
                    
                    List<Double> ranges = new ArrayList<>();
                    for (int j = 0; j < rangeList.size(); j++) {
                        ranges.add((double) rangeList.get(j));
                    }
                    gradingSystem.add(rangeList);
                }
                subjects.put(subject, gradingSystem);
            });
        } catch (Exception e) {
            createDefaults();
        }
    }

    public static void createDefaults() {
        JSONObject defaultConfig = new JSONObject();

        JSONObject subjectObject = new JSONObject();
        JSONArray gradingSystemArray = new JSONArray();

        JSONArray rangeArray = new JSONArray();
        rangeArray.add(98.0);
        rangeArray.add(100.0);
        rangeArray.add(1.00);
        gradingSystemArray.add(rangeArray);

        subjectObject.put("CCC102", gradingSystemArray);
        
        defaultConfig.put("subjects", subjectObject);
        defaultConfig.put("isDarkMode", false);

        try (FileWriter file = new FileWriter("config.json")) {
            file.write(defaultConfig.toString());
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        initialize();
    }
}