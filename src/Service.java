import java.util.*;
import java.util.stream.Collectors;

public class Service implements ISearcher {

    @Override
    public void refresh(String[] classNames, long[] modificationDates) {
        long startIndexingTime = System.currentTimeMillis();

        TreeMap<String, Long> map = new TreeMap<>();

        int classNamesLength = classNames.length;
        int modificationDatesLength = modificationDates.length;
        if (classNamesLength != modificationDatesLength) {
            System.out.println("Ошибка. Длина входящих данных отличается");
        } else {
            //putting classes and their dates to hashMap
            for (int i = 0; i < classNamesLength; i++) {
                map.put(classNames[i], modificationDates[i]);
            }

            DataContainer.setTreeMap(map);

            long endIndexingTime = System.currentTimeMillis();
            System.out.println("Indexing time: " + (endIndexingTime - startIndexingTime));
        }
    }

    @Override
    public String[] guess(String start) {
        //searching classes with the same prefix and return string array
        return getArrayByPrefix(DataContainer.getTreeMap(), start);
    }

    private String[] getArrayByPrefix (TreeMap<String, Long> treeMap, String prefix) {
        String[] results = new String[12];

        Map<String, Long> subMap = treeMap.subMap(prefix, prefix + Character.MAX_VALUE);

        ArrayList<String> arrayList = subMap.entrySet().stream()
                .sorted(comparator())
                .limit(12)
                .map(Map.Entry::getKey)
                .collect(Collectors.toCollection(ArrayList<String>::new));

        results = arrayList.toArray(results);

        return results;
    }

    private Comparator<? super Map.Entry<String, Long>> comparator() {
        return new Comparator<Map.Entry<String, Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                //firstly compares dates, if dates are similar - compares class names
                int compareResult = o1.getValue().compareTo(o2.getValue());
                if (compareResult == 0) {
                    return o1.getKey().compareTo(o2.getKey());
                }
                return compareResult;
            }
        };
    }
}
