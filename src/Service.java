import java.util.*;
import java.util.stream.Collectors;

public class Service implements ISearcher {

    @Override
    public void refresh(String[] classNames, long[] modificationDates) {
        long startIndexingTime = System.currentTimeMillis();

        HashMap<String, Long> map = new HashMap<>();

        int classNamesLength = classNames.length;
        int modificationDatesLength = modificationDates.length;
        if (classNamesLength != modificationDatesLength) {
            System.out.println("Ошибка. Длина входящих данных отличается");
        } else {
            //putting classes and their dates to hashMap
            for (int i = 0; i < classNamesLength; i++) {
                map.put(classNames[i], modificationDates[i]);
            }

            //sorting data in map and collect it to linkedHashMap to save sort order
            LinkedHashMap<String, Long> sortedMap = map.entrySet().stream()
                    .sorted(comparator())
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));

            DataContainer.setClassMap(sortedMap);

            long endIndexingTime = System.currentTimeMillis();
            System.out.println("Indexing time: " + (endIndexingTime - startIndexingTime));
        }
    }

    @Override
    public String[] guess(String start) {
        //searching classes with the same prefix and return string array
        return getArrayByPrefix(DataContainer.getClassMap(), start);
    }

    private String[] getArrayByPrefix (LinkedHashMap<String, Long> map, String prefix) {
        String[] results = new String[12];

        int i = 0;
        for (String key : map.keySet()) {
            if (key.startsWith(prefix) && i < results.length) {
                results[i] = key;
                i++;
            } else {
                break;
            }
        }

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
