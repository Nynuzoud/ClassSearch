import java.util.LinkedHashMap;

public class DataContainer {
    public static LinkedHashMap<String, Long> getClassMap() {
        return classMap;
    }

    public static void setClassMap(LinkedHashMap<String, Long> classMap) {
        DataContainer.classMap = classMap;
    }

    private static LinkedHashMap<String, Long> classMap = new LinkedHashMap<>();
}
