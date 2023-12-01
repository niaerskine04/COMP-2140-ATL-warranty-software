import java.awt.*;
import java.awt.event.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
import javax.swing.*;

public class ShowNotification extends JFrame {
    private JButton cmdDeleteNotification = new JButton("Delete Notification");
    private JButton cmdClose = new JButton("Close");

    private JPanel pnlCommand = new JPanel();
    private JPanel pnlDisplay = new JPanel();
    private DefaultListModel<String> notificationListModel = new DefaultListModel<>();
    private JList<String> notificationList = new JList<>(notificationListModel);

    public ShowNotification() {
        setTitle("Notification Display");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Add components to panels
        pnlCommand.add(cmdDeleteNotification);
        pnlCommand.add(cmdClose);

        // Add action listeners
        cmdDeleteNotification.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteSelectedNotification();
            }
        });

        cmdClose.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Close the frame
            }
        });

        // Set up display panel with a scroll pane
        JScrollPane scrollPane = new JScrollPane(notificationList);
        pnlDisplay.setLayout(new BorderLayout());
        pnlDisplay.add(scrollPane, BorderLayout.CENTER);

        // Add panels to the frame
        add(pnlCommand, BorderLayout.NORTH);
        add(pnlDisplay, BorderLayout.CENTER);

        // Load notifications from a file
        loadNotificationsFromFile();

        setSize(400, 300);
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private void deleteSelectedNotification() {
        int selectedIndex = notificationList.getSelectedIndex();
        if (selectedIndex != -1) {
            notificationListModel.remove(selectedIndex);
            saveNotificationsToFile();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a notification to delete.");
        }
    }

    private void loadNotificationsFromFile() {
        // Load notifications from a file
    
        String filePath = "ATLNotificationsMockdata.txt";
    
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                notificationListModel.addElement(line);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    

    private void saveNotificationsToFile() {
        // Save notifications to a file

        try (FileWriter writer = new FileWriter("ATLNotificationsMockdata.txt")) {
            for (int i = 0; i < notificationListModel.size(); i++) {
                writer.write(notificationListModel.get(i) + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // add to check warranty

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            ShowNotification notificationDisplay = new ShowNotification();
            notificationDisplay.setVisible(true);
        });
    }
}
