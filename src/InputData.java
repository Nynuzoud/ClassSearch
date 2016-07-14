
public class InputData {

    private Service service;

    public InputData(Service service) {
        this.service = service;
    }

    //creating classes
    public void setDataToService() {

        int classCount = 100000;
        String[] classNames = new String[classCount];
        long[] modificationDates = new long[classCount];
        long currentMillis = System.currentTimeMillis();
        long currentMillisCountAgo = currentMillis - classCount;

        String testClassMainName = "testClassNaumen";
        String testClassExtension = ".java";

        for (int i = 0; i < classNames.length; i++) {
            classNames[i] = testClassMainName + i + testClassExtension;
            modificationDates[i] = currentMillisCountAgo;
        }

        service.refresh(classNames, modificationDates);
    }

    public String[] startSearch(String textToFind) {
        return service.guess(textToFind);
    }
}
