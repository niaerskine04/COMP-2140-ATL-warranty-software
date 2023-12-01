import javax.swing.*;
//import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.List;
import javax.swing.table.TableModel;
import javax.swing.table.DefaultTableModel;

public class ProductList extends JFrame {
    private JTable dataTable;
    private JScrollPane scrollPane;

    public ProductList(String customerId) {
        setTitle("Customer Information Table");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Read data from the file based on the customer ID
        List<String[]> data = readDataFromFile(customerId);

        // Convert data to a two-dimensional array for the table model
        Object[][] rowData = new Object[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            rowData[i] = data.get(i);
        }

        // Column names for the table
        String[] columnNames = { "Customer ID", "Product Name", "Warranty Status", "Price" };

        // Create a table model with data and column names
        TableModel model = new DefaultTableModel(rowData, columnNames);

        // Create a JTable with the model
        dataTable = new JTable(model);

        // Create a JScrollPane to hold the table
        scrollPane = new JScrollPane(dataTable);

        // Add the JScrollPane to the frame
        add(scrollPane);

        // Set frame properties
        setSize(400, 300);
        setLocationRelativeTo(null);
        setVisible(true);
    }

 
private List<String[]> readDataFromFile(String customerId) {
    List<String[]> data = new ArrayList<>();

    String filePath = "ATL_CUSTOMER_WORKBOOK.txt";

    try {
        Scanner scanner = new Scanner(new File(filePath));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine();
            String[] values = line.split(",");
            if (values.length >= 8 && values[0].equals(customerId)) {
                String[] eighthToken = values[7].split("_");
                for (String token : eighthToken) {
                    data.add(new String[]{token});
                }
            }
        }
        scanner.close();
    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }

    return data;
}

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new ProductList("123"));
    }
}
