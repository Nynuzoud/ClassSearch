import javax.swing.*;


public class MainWindow extends JFrame {
    private JTextField textField1;
    private JList<String> list1;
    private JPanel MainPanel;
    private JButton startIndexingButton;

    private String textToFind;
    private Service service;
    private InputData inputData;

    public void MainForm() {
        //set up form
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buildWindow();

        //init class with main logic
        service = new Service();

        //init class with data for testing
        inputData = new InputData(service);
        inputData.setDataToService();

        //init listener for input text field
        initTextFieldListener();

        textToFind = null;

        //init refresh button
        startIndexingButtonSetActionListener();
    }

    private void startIndexingButtonSetActionListener() {
        startIndexingButton.addActionListener(e -> inputData.setDataToService());
    }

    private void initTextFieldListener() {
        textField1.addActionListener(e -> {
            textToFind = textField1.getText();
            if (textToFind != null) {
                long startSearchTime = System.currentTimeMillis();
                //getting result search result
                list1.setListData(inputData.startSearch(textToFind));
                long endSearchTime = System.currentTimeMillis();
                System.out.println("Searching time: " + (endSearchTime - startSearchTime));
            }
        });
    }

    private void buildWindow() {
        setSize(600, 600);
        add(MainPanel);
        setVisible(true);
    }
}
