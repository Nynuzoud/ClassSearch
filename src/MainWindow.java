import javax.swing.*;


public class MainWindow extends JFrame {
    private JTextField textField1;
    private JList list1;
    private JPanel MainPanel;

    private String textToFind;

    public void MainForm() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buildWindow();

        initTextFieldListener();

        textToFind = null;
    }

    private void initTextFieldListener() {
        textField1.addActionListener(e -> {
            textToFind = textField1.getText();
            System.out.println(textToFind);
        });
    }

    private void buildWindow() {
        setSize(1400, 600);
        add(MainPanel);
        setVisible(true);
    }
}
