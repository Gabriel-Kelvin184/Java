import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StockMarketApp {
    private JFrame frame;
    private JTextField symbolField;
    private JTextArea resultArea;

    public StockMarketApp() {
        frame = new JFrame("Stock Market App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(400, 300);
        frame.setLayout(null);

        JLabel symbolLabel = new JLabel("Enter Stock Symbol:");
        symbolLabel.setBounds(20, 20, 150, 20);
        frame.add(symbolLabel);

        symbolField = new JTextField();
        symbolField.setBounds(180, 20, 100, 20);
        frame.add(symbolField);

        JButton searchButton = new JButton("Search");
        searchButton.setBounds(300, 20, 80, 20);
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                searchStock();
            }
        });
        frame.add(searchButton);

        resultArea = new JTextArea();
        resultArea.setBounds(20, 60, 360, 180);
        frame.add(resultArea);

        frame.setVisible(true);
    }

    private void searchStock() {
        String symbol = symbolField.getText();
        resultArea.setText("Fetching stock information for symbol: " + symbol);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new StockMarketApp();
            }
        });
    }
}
