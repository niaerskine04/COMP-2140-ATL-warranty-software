import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.List;

public class HomeScreenGUI extends JFrame {
    private static final String USER_FILE_PATH = "CustomerFile.txt";
    private static final String WORKER_FILE_PATH = "worker_credentials.txt";

    // Create components
    private JPanel titleP = new JPanel();
    private JPanel displayP = new JPanel();
    private JPanel buttonsP = new JPanel();

    private JLabel welcomeLabel = new JLabel("WELCOME");
    private JButton customerButton = new JButton("Customer Login");
    private JButton workerButton = new JButton("Employee Login");
    private JButton loginButton = new JButton("Login");
    private JButton CloseButton = new JButton("X");
    private TicketListing tlisting;
    
    public HomeScreenGUI() {
        // Set frame properties
        setSize(500, 200);
        // Create SlideShow
        List<ImageIcon> imageIcons = new ArrayList<>();

        imageIcons.add(new ImageIcon(/* insert picture path */));
        imageIcons.add(new ImageIcon(/* insert picture path */));

        SlideShow ss = new SlideShow(imageIcons, 3000);
        add(ss, BorderLayout.CENTER);
        // setLayout(new BorderLayout());
        // setBackground(Color.WHITE);
        setTitle("ATL APPLIANCES LIMITED");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Title panel properties
        // titleP.setForeground(Color.WHITE);
        titleP.add(welcomeLabel);
        welcomeLabel.setForeground(Color.RED);
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 50));

        // Buttons panel properties
        buttonsP.setPreferredSize(new Dimension(100, 100));
        // buttonsP.setBackground(Color.RED);

        // Format for buttons
        loginButton.setBackground(Color.BLUE);
        loginButton.setForeground(Color.WHITE);
        CloseButton.setBackground(Color.BLUE);
        CloseButton.setForeground(Color.WHITE);

        // Sets action listeners to buttons
        loginButton.addActionListener(new LoginListener());
        CloseButton.addActionListener(new CloseListener());

        // Adds the buttons to the buttons panel
        buttonsP.add(loginButton, BorderLayout.CENTER);
        buttonsP.add(CloseButton, BorderLayout.SOUTH);

        // Displays panels on frame
        displayP.add(buttonsP);
        add(titleP, BorderLayout.NORTH);
        add(displayP, BorderLayout.CENTER);

        setVisible(true);
    }

    private class LoginListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            showLogin();
        }
    }

    private class CloseListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> createAndShowGUI());
    }

    private static void createAndShowGUI() {
        new HomeScreenGUI();
    }

    private void showLogin() {
        JFrame loginF = new JFrame("Login");
        JPanel buttons = new JPanel();

        loginF.setSize(500, 200);
        loginF.setLocationRelativeTo(null); // Center the frame on the screen

        // Format for buttons
        customerButton.setBackground(Color.BLUE);
        customerButton.setForeground(Color.WHITE);
        workerButton.setBackground(Color.BLUE);
        workerButton.setForeground(Color.WHITE);

        buttons.setSize(100, 100);
        // buttons.setLayout(new BorderLayout());
        buttons.add(customerButton, BorderLayout.NORTH);
        buttons.add(workerButton, BorderLayout.SOUTH);
        buttons.setBackground(Color.RED);

        loginF.add(buttons, BorderLayout.SOUTH);
        loginF.setVisible(true);
        loginF.setBackground(Color.WHITE);

        customerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle customer login button click
                showCustomerLoginPrompt();
            }
        });

        workerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle worker login button click
                showWorkerLoginPrompt();
            }
        });
    }

    private void showCustomerLoginPrompt() {
        JFrame loginFrame = new JFrame("Login");
        String customerID = JOptionPane.showInputDialog(loginFrame, "Enter User ID:");
        String password = JOptionPane.showInputDialog(loginFrame, "Enter Password:");

        // Perform authentication based on the entered ID and password
        if (authenticateUser(customerID, password)) {
        JOptionPane.showMessageDialog(loginFrame, "Login successful!");
        openCustomerscreen(customerID);
        } else {
            JOptionPane.showMessageDialog(loginFrame, "Login failed. Invalid credentials.");
        }
    }

    private void showWorkerLoginPrompt() {
        JFrame loginFrame = new JFrame("Login");
        String workerID = JOptionPane.showInputDialog(loginFrame, "Enter ID:");
        String password = JOptionPane.showInputDialog(loginFrame, "Enter Password:");

        //Perform authentication based on the entered ID and password
        if (authenticateWorker(workerID, password)) {
        JOptionPane.showMessageDialog(loginFrame, "Login successful!");
        openCustomerscreen(Customer.ID);

        } else {
            JOptionPane.showMessageDialog(loginFrame, "Login failed. Invalid credentials.");
        }
    }

    private boolean authenticateUser(String userID, String password) {
        Scanner scanner = null;
        try {
            scanner = new Scanner(new File(USER_FILE_PATH));
            while (scanner.hasNext()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");
    
                String iD = tokens[0];
                String pw = tokens[2];
    
                if (iD.equals(userID) && pw.equals(password)) {
                    return true;
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return false;
    }
    
    /*
        try (BufferedReader reader = new BufferedReader(new
            FileReader(USER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into user ID and password
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String storedUserID = parts[0].trim();
                    String storedPassword = parts[2].trim();
        
                    // Check if the user ID and password match
                    if (storedUserID.equals(userID) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
        e.printStackTrace();
        }
        return false; 
    }
    */
    
    
    
    private boolean authenticateWorker(String workerID, String password) {
        try (BufferedReader reader = new BufferedReader(new FileReader(WORKER_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Split the line into user ID and password
                String[] parts = line.split(",");
                if (parts.length == 2) {
                    String storedUserID = parts[0].trim();
                    String storedPassword = parts[1].trim();

                    // Check if the user ID and password match
                    if (storedUserID.equals(workerID) && storedPassword.equals(password)) {
                        return true;
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

public class CheckIDAndPassword {

    public static void main(String[] args) {
        // Specify the path to your file
        String filePath = "CustomerFile";

        // Specify the target user ID and password to check
        String targetUserId = "620001";
        String targetPassword = "Letm3$"; // Password to check

        // Check if the ID and password match in the file
        boolean credentialsMatch = checkIDAndPassword(filePath, targetUserId, targetPassword);

        if (credentialsMatch) {
            System.out.println("ID and password match in the file.");
        } else {
            System.out.println("ID and password do not match or user not found in the file.");
        }
    }

    // Function to check if the ID and password match in the file
    private static boolean checkIDAndPassword(String filePath, String targetUserId, String targetPassword) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check if the line contains the target user ID
                if (line.contains("ID Number:" + targetUserId)) {
                    // Check the next line for the password
                    String nextLine = reader.readLine();
                    if (nextLine != null && nextLine.contains("Password:" + targetPassword)) {
                        return true; // ID and password match
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false; // ID and password do not match or user not found
    }
}


    private void openCustomerscreen(String userID) {
        JFrame mainFrame = new JFrame("Welcome " + userID);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel b = new JPanel();

        // Create buttons for the main application window
        JButton warrantyB = new JButton("Get Warranty Plan");
        JButton productLB = new JButton("See Personal Product List");
        JButton scheduleB = new JButton("Schedule Servicing");
        JButton notifB = new JButton("Notifications");
        JButton ticket = new JButton("Add Support Ticket");

        // Add buttons to the main frame
        b.setLayout(new GridLayout(2, 1));
        b.add(warrantyB);
        b.add(productLB);
        b.add(scheduleB);
        b.add(notifB);
        b.add()

        // Set frame properties
        mainFrame.setSize(300, 200);
        mainFrame.setLocationRelativeTo(null); // Center the frame on the screen
        mainFrame.add(b);
        mainFrame.setVisible(true);

        // private List<String> getProductList(String userID) {
        //     List<String> productList = new ArrayList<>();
    
        //     // Assuming each user has a file named with their userID, e.g., "620000.txt"
        //     String filePath = userID + ".txt";
    
        //     try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
        //         String line;
        //         while ((line = reader.readLine()) != null) {
        //             productList.add(line);
        //         }
        //     } catch (IOException e) {
        //         e.printStackTrace(); // Handle the exception according to your application's requirements
        //     }
    
        //     return productList;
        // }

        warrantyB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show table or list of availiable warranty;
                SelectWarranty warrantyB = new SelectWarranty("customerid");
            }
        });

        productLB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle register button click
                ProductList List = new ProductList("620000");
            }
        });

        scheduleB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle register button click
                ServiceSchedulerGUI service = new ServiceSchedulerGUI();
            }
        });

        notifB.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle register button click
                ShowNotification notifi = new ShowNotification();
            }
        });
        ticket.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle register button click
                AddVehicle addv = new AddVehicle(tlisting);  
                System.out.println(addv);          
            }
        });
    }
}
