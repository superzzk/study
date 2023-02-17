package redis_study.chapter.six;

import java.util.HashMap;
import java.util.Map;
public class Argument {
    private Map<String, String> argumentMap = new HashMap<>();
    public Argument(String args) {
        String[] arguments = args.split(":");
        for (String argument : arguments) {
            String key = argument.split("=")[0];
            String value = argument.split("=")[1];
            argumentMap.put(key, value);
        }
    }
    public String getValue(String key) {
        return argumentMap.get(key);
    }
    public Map<String, String> getAttributes() {
        return argumentMap;
    }
}