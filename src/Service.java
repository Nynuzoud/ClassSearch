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
            for (int i = 0; i < classNamesLength; i++) {
                map.put(classNames[i], modificationDates[i]);
            }
            long endIndexingTime = System.currentTimeMillis();

            Comparator<? super Map.Entry<String, Long>> comparator = new Comparator<Map.Entry<String, Long>>() {
                @Override
                public int compare(Map.Entry<String, Long> o1, Map.Entry<String, Long> o2) {
                    int compareResult = o1.getValue().compareTo(o2.getValue());
                    if (compareResult == 0) {
                        return o1.getKey().compareTo(o2.getKey());
                    }
                    return compareResult;
                }
            };
            Map<String, Long> sortedMap = map.entrySet().stream()
                    .sorted(comparator)
                    .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue,
                            (e1, e2) -> e1, LinkedHashMap::new));

            DataContainer.setClassMap(sortedMap);

            System.out.println("Indexing time: " + (endIndexingTime - startIndexingTime));
        }
    }

    @Override
    public String[] guess(String start) {
        //return (String[]) sortedMap.keySet().toArray(new String[sortedMap.size()]);
        return DataContainer.getClassMap().keySet().toArray(new String[DataContainer.getClassMap().size()]);
        //return new String[0];
    }

    private String[] getArrayByPrefix (LinkedHashMap<String, Long> map, String prefix) {
        String[] result = null;

    }
}