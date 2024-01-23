import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MRMRS {
    private static JFrame mainFrame;
    private static JFrame moodFrame;
    private static JFrame songFrame;

    private static DefaultTableModel songTableModel;

    private static final String DB_URL = "jdbc:mysql://localhost:3306/mrms?useSSL=false";

    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "9894625158";

    public static void main(String[] args) {
        try {
            // Set Nimbus look and feel
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
            UIManager.put("nimbusBase", new Color(18, 30, 49));
            UIManager.put("nimbusBlueGrey", new Color(28, 41, 61));
            UIManager.put("control", new Color(44, 62, 80));
            UIManager.put("text", new Color(236, 240, 241));
            UIManager.put("nimbusSelectionBackground", new Color(52, 152, 219));
    
            // Create the database connection
            try (Connection connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD)) {
                // Check if the connection is successful
                if (connection != null) {
                    // Create the users table
                    createUsersTable(connection);
    
                    // Invoke Swing operations on the Event Dispatch Thread
                    SwingUtilities.invokeLater(() -> {
                        // Show the registration page
                        createAndShowRegisterPage(connection);
                    });
                    
                    // Keep the main thread running to allow Swing operations
                    Thread.currentThread().join();
                }
            }
        } catch (UnsupportedLookAndFeelException | ClassNotFoundException | InstantiationException | IllegalAccessException | SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
    
    
    private static void createUsersTable(Connection connection) {
        String createTableSQL = "CREATE TABLE IF NOT EXISTS users (" +
                "id INT PRIMARY KEY AUTO_INCREMENT," +
                "name VARCHAR(255) NOT NULL," +
                "language VARCHAR(255) NOT NULL," +
                "genre VARCHAR(255) NOT NULL)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(createTableSQL)) {
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createAndShowRegisterPage(Connection connection) {
        mainFrame = new JFrame("Register Page");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(4, 2));
        JTextField nameField = createStyledTextField();
        JTextField languageField = createStyledTextField();
        JTextField genreField = createStyledTextField();

        JButton registerButton = createStyledButton("Register", new Color(52, 152, 219));
        JButton returningUserButton = createStyledButton("Returning User", new Color(52, 152, 219));

        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertUserData(connection, nameField.getText(), languageField.getText(), genreField.getText());
                createAndShowMoodPage(nameField.getText(), languageField.getText(), genreField.getText());
            }
        });

        returningUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField usernameField = createStyledTextField();
                Object[] message = {
                        "Enter your username:", usernameField
                };

                int option = JOptionPane.showConfirmDialog(mainFrame, message, "Returning User", JOptionPane.OK_CANCEL_OPTION);
                if (option == JOptionPane.OK_OPTION) {
                    String username = usernameField.getText();
                    if (username != null && !username.isEmpty()) {
                        retrieveUserData(connection, username);
                        createAndShowMoodPage("Returning User: " + username, "", "");
                    }
                }
            }
        });

        panel.add(createStyledLabel("Name:"));
        panel.add(nameField);
        panel.add(createStyledLabel("Language:"));
        panel.add(languageField);
        panel.add(createStyledLabel("Genre:"));
        panel.add(genreField);
        panel.add(registerButton);
        panel.add(returningUserButton);

        mainFrame.getContentPane().add(panel);
        mainFrame.setSize(400, 200);
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }

    private static void insertUserData(Connection connection, String name, String language, String genre) {
        String insertSQL = "INSERT INTO users (name, language, genre) VALUES (?, ?, ?)";
        try (PreparedStatement preparedStatement = connection.prepareStatement(insertSQL)) {
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, language);
            preparedStatement.setString(3, genre);
            preparedStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void retrieveUserData(Connection connection, String username) {
        String selectSQL = "SELECT * FROM users WHERE name = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(selectSQL)) {
            preparedStatement.setString(1, username);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                String name = resultSet.getString("name");
                String language = resultSet.getString("language");
                String genre = resultSet.getString("genre");
                System.out.println("User Data: Name=" + name + ", Language=" + language + ", Genre=" + genre);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void createAndShowMoodPage(String name, String language, String genre) {
        moodFrame = new JFrame("Mood Selection");
        moodFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JPanel panel = new JPanel(new GridLayout(3, 2, 10, 10)); // Added gaps between components
        JButton happyButton = createStyledButtonWithBorder("Happy", new Color(52, 152, 219));
        JButton sadButton = createStyledButtonWithBorder("Sad", new Color(52, 152, 219));
        JButton calmButton = createStyledButtonWithBorder("Calm", new Color(52, 152, 219));
        JButton excitedButton = createStyledButtonWithBorder("Excited", new Color(52, 152, 219));
        JButton angryButton = createStyledButtonWithBorder("Angry", new Color(52, 152, 219));
        JButton relaxedButton = createStyledButtonWithBorder("Relaxed", new Color(52, 152, 219));

        happyButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowSongList("Happy Mood");
            }
        });

        sadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowSongList("Sad Mood");
            }
        });

        calmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowSongList("Calm Mood");
            }
        });

        excitedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowSongList("Excited Mood");
            }
        });

        angryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowSongList("Angry Mood");
            }
        });

        relaxedButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowSongList("Relaxed Mood");
            }
        });

        panel.add(happyButton);
        panel.add(sadButton);
        panel.add(calmButton);
        panel.add(excitedButton);
        panel.add(angryButton);
        panel.add(relaxedButton);

        moodFrame.getContentPane().add(panel);
        moodFrame.setSize(300, 200);
        moodFrame.setLocationRelativeTo(mainFrame);
        moodFrame.setVisible(true);
    }

    private static JButton createStyledButtonWithBorder(String text, Color bgColor) {
        JButton button = createStyledButton(text, bgColor);
        button.setBorder(BorderFactory.createLineBorder(Color.BLACK)); // Added border
        return button;
    }

    private static JButton createStyledButton(String text, Color bgColor) {
        JButton button = new JButton(text);
        button.setBackground(bgColor);
        button.setForeground(new Color(255, 255, 255));
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);
        return button;
    }

    private static JLabel createStyledLabel(String text) {
        JLabel label = new JLabel(text);
        label.setForeground(new Color(255, 255, 255));
        return label;
    }

    private static JTextField createStyledTextField() {
        JTextField textField = new JTextField();
        textField.setBackground(new Color(44, 62, 80));
        textField.setForeground(new Color(255, 255, 255)); // Set text color
        return textField;
    }

    private static void createAndShowSongList(String mood) {
        songFrame = new JFrame("Song List - " + mood);
        songFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Dummy data for the song list
        Object[][] data = {
                {"Song 1", "Artist 1", "3:45", "https://www.youtube.com/watch?v=abc123"},
                {"Song 2", "Artist 2", "4:20", "https://www.youtube.com/watch?v=def456"},
                {"Song 3", "Artist 3", "3:30", "https://www.youtube.com/watch?v=ghi789"}
        };

        // Column names for the table
        String[] columnNames = {"Song Title", "Artist", "Duration", "Link"};

        // Create a table model
        songTableModel = new DefaultTableModel(data, columnNames);

        // Create a JTable with the model
        JTable songTable = new JTable(songTableModel);
        songTable.setBackground(new Color(44, 62, 80)); // Set dark background
        songTable.getTableHeader().setBackground(new Color(52, 152, 219));
        songTable.getTableHeader().setForeground(Color.WHITE);

        // Customize the row height for better aesthetics
        songTable.setRowHeight(30);

        // Add the table to a scroll pane
        JScrollPane scrollPane = new JScrollPane(songTable);
        songFrame.getContentPane().add(scrollPane, BorderLayout.CENTER);

        // Create buttons for actions
        JButton playButton = createStyledButton("Play", new Color(46, 204, 113));
        JButton removeButton = createStyledButton("Remove", new Color(231, 76, 60));
        JButton addButton = createStyledButton("Add Song", new Color(52, 152, 219));

        // Add action listeners for the buttons
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement play action
                int selectedRow = songTable.getSelectedRow();
                if (selectedRow != -1) {
                    String songLink = (String) songTableModel.getValueAt(selectedRow, 3);
                    openWebPage(songLink);
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement remove action
                int selectedRow = songTable.getSelectedRow();
                if (selectedRow != -1) {
                    songTableModel.removeRow(selectedRow);
                } else {
                    JOptionPane.showMessageDialog(songFrame, "Select a song to remove.");
                }
            }
        });

        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Implement add song action
                // For simplicity, adding a dummy row
                songTableModel.addRow(new Object[]{"New Song", "New Artist", "3:00", "https://www.youtube.com/watch?v=jkl012"});
            }
        });

        // Create a panel for the buttons
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(playButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(addButton);

        // Add the button panel to the frame
        songFrame.getContentPane().add(buttonPanel, BorderLayout.SOUTH);

        songFrame.setSize(600, 400);
        songFrame.setLocationRelativeTo(moodFrame);
        songFrame.setVisible(true);
    }

    private static void openWebPage(String url) {
        try {
            Desktop.getDesktop().browse(new URI(url));
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}