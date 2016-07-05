import java.util.LinkedHashMap;
import java.util.Map;

public class DataContainer {
    public static Map<String, Long> getClassMap() {
        return classMap;
    }

    public static void setClassMap(Map<String, Long> classMap) {
        DataContainer.classMap = classMap;
    }

    private static Map<String, Long> classMap = new LinkedHashMap<>();
}
