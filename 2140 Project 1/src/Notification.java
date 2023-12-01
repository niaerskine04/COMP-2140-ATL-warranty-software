import java.io.IOException;
//import java.io.File;
//import java.io.FileInputStream;
import java.time.LocalDate;
import java.time.Period;
//import javax.swing.*;
//import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;

public class Notification {

    private String title; // article title
    private String date;
    private String entry; // stores the message

    public Notification() {
    }

    /**
     * Base Person Constructor
     *
     * @param title
     * @param date
     * @param entry
     */

    public Notification(String title, String date, String entry) {
        this.title = title;
        this.date = date;
        this.entry = entry;
    }

    /**
     * getter for the title attributes
     *
     * @return String
     */

    public String getTitle() {
        return title;
    }

    /**
     * getter for the date attributes
     *
     * @return String
     */

    public String getDate() {
        return date;
    }

    /**
     * getter for the entry attributes
     *
     * @return String
     */
    public String getEntry() {
        return entry;
    }


public class WarrantyChecker {

    public void TimeLeftonWarranty() {
        String filePath = "ATL_CUSTOMER_WORKBOOK";

        // Specify the customer ID to search
        String customerId = Customer.ID; 
        
        // Specify the column names to read
        String dateColumnName = "Warranty Purchase Date";
        int warrantyDuration = 2;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            // Read the header line to get column indices
            String headerLine = reader.readLine();
            String[] columnNames = headerLine.split("\t");
            int dateColumnIndex = findColumnIndex(columnNames, dateColumnName);

            if (dateColumnIndex != -1) {
                // Iterate through rows and check warranty status
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] values = line.split("\t");

                    if (values.length > dateColumnIndex) {
                        //String customerId = values[0]; // Assuming the customer ID is in the first column
                        if (customerId.equals(Customer.ID)) {
                            LocalDate warrantyStart = LocalDate.parse(values[dateColumnIndex]);
                            LocalDate currentdate = LocalDate.now();
                            LocalDate WExpirationDate = warrantyStart.plusYears(warrantyDuration);

                            Period timeleft = Period.between(currentdate, WExpirationDate);

                            // Check if the warranty is expiring soon
                            if (timeleft.getMonths() < 2) {
                                System.out.println("Warranty for Customer ID " + customerId + " is expiring soon!");
                                System.out.println(timeleft);
                            }
                            break; // Exit the loop since the customer is found
                        }
                    }
                }
            }
        }catch (IOException e) {
            e.printStackTrace();
        }
    }

    private int findColumnIndex(String[] columnNames, String columnName) {
        for (int i = 0; i < columnNames.length; i++) {
            if (columnNames[i].equalsIgnoreCase(columnName)) {
                return i;
            }
        }
        // Return -1 if the column is not found
        return -1;
    }

}
} 

