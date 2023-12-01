import javax.swing.*;
//import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
//import java.text.ParseException;
//import java.text.SimpleDateFormat;
//import java.util.Calendar;
//import java.util.Date;
//import java.nio.file.Path;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
//import java.nio.file.StandardOpenOption;
import java.util.List;

public class ServiceSchedulerGUI extends JFrame {
    private JComboBox<String> dateComboBox;
    private JComboBox<String> timeComboBox;
    private JButton scheduleButton = new JButton("Schedule Service");
    private JButton backButton = new JButton("Back");

    public ServiceSchedulerGUI() {
        setTitle("Service Scheduler");
    
        // Sample data for the date and time dropdowns
        String[] dateOptions = { "2023-12-01", "2023-12-04", "2023-12-05", "2023-12-06", "2023-12-07", "2023-12-08",
                "2023-12-11", "2023-12-12", "2023-12-13", "2023-12-14" };
        dateComboBox = new JComboBox<>(dateOptions);

        String[] timeOptions = { "11:00 AM", "01:00 PM", "03:00 PM", "05:00 PM", "07:00 PM" };
        timeComboBox = new JComboBox<>(timeOptions);

        JPanel panel = new JPanel();
        panel.add(new JLabel("Select Date: "));
        panel.add(dateComboBox);
        panel.add(new JLabel("Select Time: "));
        panel.add(timeComboBox);
        panel.add(scheduleButton);
        panel.add(backButton);

        add(panel);

        scheduleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                scheduleService();
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                setVisible(false);
            }
        });

        setSize(400, 150);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void scheduleService() {
        String selectedDate = (String) dateComboBox.getSelectedItem();
        String selectedTime = (String) timeComboBox.getSelectedItem();

        // Combine date and time for parsing
        // S tring combinedDateTime = selectedDate + " " +
        // convert12HrTo24Hr(selectedTime);

        // Check if the entered date and time are available, not a weekend, and fall
        // within working hours
        if (isDateTimeAvailable(selectedDate, selectedTime)) {
            JOptionPane.showMessageDialog(this, "Service scheduled for " + selectedDate + " at " + selectedTime);
        } else {
            JOptionPane.showMessageDialog(this,
                    "Selected date and time are not available or do not meet the criteria.");
        }
    }

    private boolean isDateTimeAvailable(String scheduledDate, String scheduledTime) {
        // Replace "schedule.txt" with your actual schedule file path
        String scheduleFilePath = "schedule.txt";

        try {
            // Read all lines from the schedule file

            List<String> lines = Files.readAllLines(Paths.get(scheduleFilePath));

            // Check if the combination of date and time exists in the schedule
            String dateTimeToCheck = "Service scheduled for " + scheduledDate + " at " + scheduledTime;
            return lines.stream().noneMatch(line -> line.contains(dateTimeToCheck));
        } catch (

        IOException e) {
            // Log or handle the exception appropriately
            e.printStackTrace();
            return false; // Consider unavailable in case of an exception
        }
    }

    // private String convert12HrTo24Hr(String time12Hr) {
    // try {
    // SimpleDateFormat sdf12Hr = new SimpleDateFormat("hh:mm a");
    // SimpleDateFormat sdf24Hr = new SimpleDateFormat("HH:mm");
    // Date date = sdf12Hr.parse(time12Hr);
    // return sdf24Hr.format(date);
    // } catch (ParseException e) {
    // e.printStackTrace();
    // return "";
    // }
    // }


}
