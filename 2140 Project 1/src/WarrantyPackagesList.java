import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class WarrantyPackagesList extends JFrame {

    public WarrantyPackagesList() {
        setTitle("Warranty Select");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create data for the table
        Object[][] data = {
                { "Regular Warranty One", "Three Appliances", "Price" },
                { "Regular Warranty Two", "Three Appliances", "Price" },
        };

        // Create column names
        String[] columnNames = { "Warranty Name", "Appliances Included", "Price" };

        // Create a DefaultTableModel and JTable with the data and column names
        DefaultTableModel model = new DefaultTableModel(data, columnNames);
        JTable table = new JTable(model);

        // Create a scroll pane to hold the table
        JScrollPane scrollPane = new JScrollPane(table);

        // Add the scroll pane to the frame
        add(scrollPane);

        // Set frame properties
        setSize(400, 300);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            WarrantyPackagesList warrantyList = new WarrantyPackagesList();
            warrantyList.setVisible(true);
        });
    }
}
/*
 * 
 * import java.awt.event.ActionEvent;
 * import java.awt.event.ActionListener;
 * 
 * import javax.swing.JButton;
 * import javax.swing.JComboBox;
 * import javax.swing.JFrame;
 * import javax.swing.JOptionPane;
 * import javax.swing.JPanel;
 * import javax.swing.JScrollPane;
 * import javax.swing.JTable;
 * import javax.swing.SwingUtilities;
 * 
 * public class WarrantyPackagesList extends JFrame {
 * private JButton cmdSelectWarranty = new JButton("Select Warranty");
 * 
 * public WarrantyPackagesList() {
 * // Set the frame title
 * setTitle("Warranty Select");
 * 
 * /*
 * Create sample data for the table
 * Object[][][][] data = {
 * {"John", "Lane", "Product","Current Warranty"},
 * };
 * 
 * // Create column names
 * String[] columnNames = {"First Name", "Last Name","Product Name",
 * "Current Warranty"};
 * 
 * // Create a JTable with the data and column names
 * JTable table = new JTable(data, columnNames);
 * 
 * // Create a scroll pane to hold the table (in case the data is too big to fit
 * in the window)
 * JScrollPane scrollPane = new JScrollPane(table);
 * 
 * // Add the scroll pane to the frame
 * add(scrollPane);
 * 
 * // Set frame properties
 * setSize(400,300);setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * setLocationRelativeTo(null); // Center the frame on the screen
 * }
 * 
 * /*
 * private JButton cmdRegularWarrantyOne = new JButton("Regular Warranty One");
 * private JButton cmdRegularWarrantyTwo = new JButton("Regular Warranty TWo");
 * private JButton cmdPremiumWarrantyOne = new JButton("Premium Warranty One");
 * private JButton cmdPremiumWarrantyTwo = new JButton("Premium Warranty TWo");
 * 
 * 
 * import javax.swing.JComboBox;
 * import javax.swing.JFrame;
 * import javax.swing.JOptionPane;
 * import javax.swing.JPanel;
 * import javax.swing.JScrollPane;
 * import javax.swing.JTable;
 * import javax.swing.SwingUtilities;
 * 
 * private class AddWarrantyActionListener implements ActionListener {
 * 
 * public WarrantyPackagesList() {
 * // Set the frame title
 * setTitle("Warranty Packages List");
 * 
 * // Create data for the table
 * Object[][][][]data = {
 * {"Regular Warranty One", "Three Appliances", "Price"},
 * {"Regular Warranty Two", "Three Appliances", "Price"},
 * };
 * 
 * // Create column names
 * String[] columnNames = {"First Name", "Last Name","Product Name",
 * "Current Warranty"};
 * 
 * // Create a JTable with the data and column names
 * JTable table = new JTable(data, columnNames);
 * 
 * // Create a scroll pane to hold the table (in case the data is too big to fit
 * in the window)
 * JScrollPane scrollPane = new JScrollPane(table);
 * 
 * // Add the scroll pane to the frame
 * add(scrollPane);
 * 
 * // Set frame properties
 * setSize(400, 300);
 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * setLocationRelativeTo(null); // Center the frame on the screen
 * }
 * 
 * private class SelectWarrantyActionListener implements ActionListener {
 * 
 * public class DropDownMenu extends JFrame {
 * 
 * public DropDownMenu() {
 * setTitle("Warrant Options");
 * setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 * 
 * // Sample data for the drop-down menu
 * String[] options = { "Regular Warreanty One", "Regular Warreanty Two",
 * "Premium Warreanty One",
 * "Premium Warreanty TWo" };
 * 
 * // Create a JComboBox with the sample data
 * JComboBox<String> comboBox = new JComboBox<>(options);
 * 
 * // Add an ActionListener to the JComboBox
 * comboBox.addActionListener(new ActionListener() {
 * 
 * @Override
 * public void actionPerformed(ActionEvent e) {
 * // Retrieve the selected item
 * String selectedOption = (String) comboBox.getSelectedItem();
 * 
 * // Display a message with the selected item
 * JOptionPane.showMessageDialog(DropDownMenu.this,
 * "Selected Option: " + selectedOption);
 * }
 * });
 * 
 * // Create a panel and add the JComboBox to it
 * JPanel panel = new JPanel();
 * panel.add(comboBox);
 * 
 * // Add the panel to the frame
 * add(panel);
 * 
 * // Set frame properties
 * setSize(300, 200);
 * setLocationRelativeTo(null);
 * }
 * 
 * public static void main(String[] args) {
 * SwingUtilities.invokeLater(() -> {
 * DropDownMenu warranty = new DropDownMenu();
 * warranty.setVisible(true);
 * });
 * }
 * 
 * }
 * 
 * }
 * 
 * public static void main(String[] args) {
 * SwingUtilities.invokeLater(() -> {
 * // Create and display the GUI
 * ProductList list = new ProductList();
 * example.setVisible(true);
 * });
 * }
 * }
 */
