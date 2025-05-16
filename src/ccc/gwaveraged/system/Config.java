package ccc.gwaveraged.system;

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
    private static JSONObject config;
    public static boolean isDarkMode;
    public static Map<String, List<List<Double>>> gradingSystem = new HashMap<>();

    private static final String[] defaultSubjects = {"CCC102", "MAT101", "MAT061", "GEC103", "FIL102"};
    private static final double[][] defaultPercentageToPoint = {
        {98.0, 100.0, 1.0},
        {95.0, 97.99, 1.25},
        {92.0, 94.99, 1.5},
        {89.0, 91.99, 1.75},
        {85.0, 88.99, 2.0},
        {82.0, 84.99, 2.25},
        {80.0, 81.99, 2.5},
        {77.0, 79.99, 2.75},
        {75.0, 76.99, 3.0},
        {0.0, 74.99, 5.0}
    };

    public static void initialize() {
        JSONParser parser = new JSONParser();
        try {
            Path filePath = Paths.get("config.json").toAbsolutePath();
            
            FileReader reader = new FileReader(filePath.toString());
            config = (JSONObject) parser.parse(reader);

            isDarkMode = (boolean) config.get("isDarkMode");
            JSONObject subjectsJSON = (JSONObject) config.get("subjects");
            
            subjectsJSON.forEach((k, v) -> {
                String subject = (String) k;
                JSONArray gradingSystemJSON = (JSONArray) v;
    
                List<List<Double>> gradingSystemList = new ArrayList<>();
                for (int i = 0; i < gradingSystemJSON.size(); i++) {
                    JSONArray rangeList = (JSONArray) gradingSystemJSON.get(i);
                    
                    List<Double> ranges = new ArrayList<>();
                    for (int j = 0; j < rangeList.size(); j++) {
                        ranges.add((double) rangeList.get(j));
                    }
                    gradingSystemList.add(rangeList);
                }
                gradingSystem.put(subject, gradingSystemList);
            });
        } catch (Exception e) {
            createDefaults();
        }
    }

    
    public static void updateTheme() {
        config.put("isDarkMode", isDarkMode);

        try (FileWriter writer = new FileWriter("config.json")) {
            writer.write(beautifyJSON(config));
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void createDefaults() {
        JSONObject defaultConfig = new JSONObject();
        JSONObject subjectObject = new JSONObject();

        for (String subjectName : defaultSubjects) {
            JSONArray gradingSystemArray = new JSONArray();
            for (double[] range : defaultPercentageToPoint) {
                JSONArray rangeArray = new JSONArray();
                for (double percent : range) {
                    rangeArray.add(percent);
                }
                
                gradingSystemArray.add(rangeArray);
            }
            subjectObject.put(subjectName, gradingSystemArray);
        }

        defaultConfig.put("isDarkMode", false);
        defaultConfig.put("subjects", subjectObject);
        
        try (FileWriter file = new FileWriter("config.json")) {
            file.write(beautifyJSON(defaultConfig));
            file.flush();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        initialize();
    }

    private static String beautifyJSON(JSONObject jsonObject) {
        StringBuilder prettyJSON = new StringBuilder();
        prettyJSON.append("{\n");
    
        jsonObject.forEach((key, value) -> {
            prettyJSON.append("    \"").append(key).append("\": ");
            if (value instanceof JSONObject) {
                prettyJSON.append(beautifyJSON((JSONObject) value));
            } else if (value instanceof JSONArray) {
                prettyJSON.append(beautifyJSONArray((JSONArray) value));
            } else if (value instanceof String) {
                prettyJSON.append("\"").append(value).append("\"");
            } else {
                prettyJSON.append(value);
            }
            prettyJSON.append(",\n");
        });
    
        if (prettyJSON.length() > 2) {
            prettyJSON.setLength(prettyJSON.length() - 2);
        }
        prettyJSON.append("\n}");
        return prettyJSON.toString();
    }
    
    private static String beautifyJSONArray(JSONArray jsonArray) {
        StringBuilder prettyArray = new StringBuilder();
    
        boolean isFlatArray = jsonArray.stream().allMatch(item -> item instanceof Number);
    
        if (isFlatArray) {
            prettyArray.append("[");
            for (int i = 0; i < jsonArray.size(); i++) {
                prettyArray.append(String.format("%.2f", jsonArray.get(i)));
                if (i < jsonArray.size() - 1) {
                    prettyArray.append(", ");
                }
            }
            prettyArray.append("]");
        } else {
            prettyArray.append("[\n");
            jsonArray.forEach(item -> {
                prettyArray.append("        ");
                if (item instanceof JSONArray) {
                    prettyArray.append(beautifyJSONArray((JSONArray) item));
                } else if (item instanceof String) {
                    prettyArray.append("\"").append(item).append("\"");
                } else {
                    prettyArray.append(item);
                }
                prettyArray.append(",\n");
            });

            if (prettyArray.length() > 2) {
                prettyArray.setLength(prettyArray.length() - 2);
            }
            prettyArray.append("\n    ]");
        }

        return prettyArray.toString();
    }

}