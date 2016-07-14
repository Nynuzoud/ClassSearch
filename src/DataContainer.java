import java.util.TreeMap;

public class DataContainer {

    private static TreeMap<String, Long> treeMap;

    public static void setTreeMap(TreeMap<String, Long> treeMap) {
        DataContainer.treeMap = treeMap;
    }

    public static TreeMap<String, Long> getTreeMap() {
        return treeMap;
    }
}
