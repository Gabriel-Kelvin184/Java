import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class Stock_market extends JFrame {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/stock_market";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "9894625158";

    private JButton displayButton;
    private JButton addStockButton;
    private JTextArea resultArea;

    public Stock_market() {
        setTitle("Stock Market App");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        displayButton = new JButton("Display Stock Data");
        addStockButton = new JButton("Add Stock");
        resultArea = new JTextArea();

        displayButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayStockData();
            }
        });

        addStockButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addStock();
            }
        });

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(displayButton);
        buttonPanel.add(addStockButton);

        JScrollPane scrollPane = new JScrollPane(resultArea);

        // Use BorderLayout here
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(BorderLayout.NORTH, buttonPanel);
        getContentPane().add(BorderLayout.CENTER, scrollPane);

        setLocationRelativeTo(null); // Center the frame on the screen
        setVisible(true);
    }

    private void displayStockData() {
        resultArea.setText(""); // Clear previous content

        try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
             Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery("SELECT * FROM stocks")) {

            while (resultSet.next()) {
                int stockId = resultSet.getInt("stock_id");
                String stockName = resultSet.getString("stock_name");
                double stockPrice = resultSet.getDouble("stock_price");

                resultArea.append("Stock ID: " + stockId + "\n");
                resultArea.append("Stock Name: " + stockName + "\n");
                resultArea.append("Stock Price: $" + stockPrice + "\n");
                resultArea.append("\n");
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            resultArea.setText("Error: " + ex.getMessage());
        }
    }

    private void addStock() {
        JTextField idField = new JTextField();
        JTextField nameField = new JTextField();
        JTextField priceField = new JTextField();

        JPanel panel = new JPanel(new GridLayout(0, 2));
        panel.add(new JLabel("Stock ID:"));
        panel.add(idField);
        panel.add(new JLabel("Stock Name:"));
        panel.add(nameField);
        panel.add(new JLabel("Stock Price:"));
        panel.add(priceField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Add Stock",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);

        if (result == JOptionPane.OK_OPTION) {
            try (Connection connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
                 PreparedStatement preparedStatement = connection.prepareStatement(
                         "INSERT INTO stocks (stock_id, stock_name, stock_price) VALUES (?, ?, ?)")) {

                int stockId = Integer.parseInt(idField.getText());
                String stockName = nameField.getText();
                double stockPrice = Double.parseDouble(priceField.getText());

                preparedStatement.setInt(1, stockId);
                preparedStatement.setString(2, stockName);
                preparedStatement.setDouble(3, stockPrice);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    JOptionPane.showMessageDialog(null, "Stock added successfully!");
                    displayStockData(); // Refresh the display
                } else {
                    JOptionPane.showMessageDialog(null, "Failed to add stock.");
                }

            } catch (SQLException | NumberFormatException ex) {
                ex.printStackTrace();
                JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Stock_market();
            }
        });
    }
}
