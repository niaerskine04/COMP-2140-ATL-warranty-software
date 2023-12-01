import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SelectWarranty extends JFrame {
    private DefaultListModel<String> productListModel;
    private JList<String> productList;

    public SelectWarranty(String iD) {
        // Set the frame title
        setTitle("Product List");

        // Select warranty
        private JButton cmdSelectWarranty = new JButton("Select Warranty");
        cmdSelectWarranty.addActionListener(new SelectWarrantyActionListener());

        // Load product data based on the person's ID
        List<String> products = loadProductsForPerson(ID);

        // Create a DefaultListModel with the loaded data
        productListModel = new DefaultListModel<>();
        for (String product : products) {
            productListModel.addElement(product);
        }

        // Create a JList with the loaded data
        productList = new JList<>(productListModel);

        // Create a scroll pane to hold the list
        JScrollPane scrollPane = new JScrollPane(productList);

        // Create a button to add selected products to the warranty package
        JButton cmdAddWarranty = new JButton("Add Warranty");
        cmdAddWarranty.addActionListener(new AddWarrantyActionListener());

        // Create a panel to hold the components
        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(scrollPane, BorderLayout.CENTER);
        panel.add(cmdAddWarranty, BorderLayout.SOUTH);
        panel.add(cmdSelectWarranty, BorderLayout.SOUTH);
        // Add the panel to the frame
        add(panel);

        // Set frame properties
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen
    }

    private List<String> loadProductsForPerson(String personId) {
        List<String> productsList = new ArrayList<>();

        String filePath = "CustomerFile.txt";
    
        try {
            Scanner scanner = new Scanner(new File(filePath));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] values = line.split(",");
                if (values.length >= 8 && values[0].equals(personId)) {
                    String[] eighthToken = values[7].split("_");
                    for (String token : eighthToken) {
                        productsList.add(new String[]{token});
                    }
                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return productsList;
    }

    private class AddWarrantyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Get selected products
            int[] selectedIndices = productList.getSelectedIndices();
            StringBuilder selectedProducts = new StringBuilder("Selected Products:\n");
            for (int index : selectedIndices) {
                selectedProducts.append(productListModel.getElementAt(index)).append("\n");
            }

            // Display a message with the selected products
            // JOptionPane.showMessageDialog(ProductList.this, selectedProducts.toString());
        }
    }

    private class SelectWarrantyActionListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Open warranty select
            WarrantyPackagesList();
        }
    }
}
