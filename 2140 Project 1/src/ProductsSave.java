import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductsSave {
    public static void main(String[] args) {
        // Sample data: Customer ID and associated products
        String customerId = "123";
        List<String> products = getProductsForCustomer(customerId);

        // Specify the file path
        String filePath = "customer_products.txt";

        // Save product information to the file
        saveToFile(filePath, products);

        System.out.println("Product information saved to file: " + filePath);
    }

    private static List<String> getProductsForCustomer(String customerId) {
        // Replace this with your logic to fetch products associated with the customer ID
        List<String> products = new ArrayList<>();
        if ("123".equals(customerId)) {
            products.add("Product1");
            products.add("Product2");
            products.add("Product3");
        }
        // Add more products or fetch them from a database

        return products;
    }

    private static void saveToFile(String filePath, List<String> products) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write product information to the file
            for (String product : products) {
                writer.write(product + "\n");
            }
        } catch (IOException e) {
            // Handle the exception more gracefully, e.g., log or display a message
            e.printStackTrace();
        }
    }
}
