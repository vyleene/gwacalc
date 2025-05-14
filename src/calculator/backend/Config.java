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

public class Config {
    public static String theme;
    public static Map<String, List<List<Double>>> subjects = new HashMap<>();

    public Config() {
        JSONParser parser = new JSONParser();
        try {
            Path filePath = Paths.get("config.json").toAbsolutePath();
            
            FileReader reader = new FileReader(filePath.toString());
            JSONObject config = (JSONObject) parser.parse(reader);
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
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
        }
    }

    public static void createDefaults() {
        // Existing method
    }
}