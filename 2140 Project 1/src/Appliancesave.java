import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
public class Appliancesave {
    public static void main(String[] args) {
        // Sample item data
        String[] Appliances= {"ApplianceID1", "ApplianceID2", "ApplianceID3"};
       

        // Specify the file path
        String filePath = "appliances.txt";

        // Save item information to the file
        saveToFile(filePath, Appliances);

        System.out.println("Item information saved to file: " + filePath);
    }

    private static void saveToFile(String filePath, String[] Appliances) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            // Write item information to the file
            for (int i = 0; i < Appliances.length; i++) {
                writer.write(Appliances[i]  + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}