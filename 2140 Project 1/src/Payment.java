import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JOptionPane;

//Making and processing payment for warranties for a customer
//Accepts an arraylist of warranties the customer wishes to purchase, allows the customer to enter card information, adds the warranties to the customer's account

public class Payment extends JFrame {
    private JButton cmdConfirm = new JButton("Confirm");
    private JButton cmdCancel = new JButton("Cancel");
    private JFrame frame;
    private JPanel pnlCmd = new JPanel();
    private JPanel pnlDsp = new JPanel();
    private JTextField cardName = new JTextField(15);
    private JTextField cardNumber = new JTextField(15);
    private JTextField expDate = new JTextField(7);
    private JTextField cvvNumber = new JTextField(15);
    private Customer customer;
    private ArrayList<Warranty> warranties;
    private double totalcost = 0;

    public Payment(Customer customer, ArrayList<Warranty> warranties){
        this.customer = customer;
        this.warranties = warranties;
        
        frame = this;

        setSize(500,300);
        setBackground(Color.WHITE);
        setTitle("CHECKOUT");

        pnlDsp.setLayout(new GridLayout(0,2));
        for (int i=0; i<warranties.size(); i++){
            pnlDsp.add(new JLabel("PLAN #" + (i+1) +": " + warranties.get(i).getWName()));
            pnlDsp.add(new JLabel("$" + warranties.get(i).getCost(),JLabel.RIGHT));
            totalcost += warranties.get(i).getCost();
        }
        pnlDsp.add(new JLabel("TOTAL COST: "));
        pnlDsp.add(new JLabel("$" + String.format("%.2f",totalcost),JLabel.RIGHT));
        pnlDsp.add(new JLabel(" "));
        pnlDsp.add(new JLabel(" "));
        pnlDsp.add(new JLabel("Cardholder's Name:"));
        pnlDsp.add(cardName);
        pnlDsp.add(new JLabel("Card Number:"));
        pnlDsp.add(cardNumber);
        pnlDsp.add(new JLabel("Expiry Date (MMYY): "));
        pnlDsp.add(expDate);
        pnlDsp.add(new JLabel("CVV:"));
        pnlDsp.add(cvvNumber);

        cmdConfirm.setBackground(Color.GREEN);
        cmdConfirm.setForeground(Color.BLACK);
        cmdCancel.setBackground(Color.RED);
        cmdCancel.setForeground(Color.BLACK);
        pnlCmd.add(cmdConfirm);
        pnlCmd.add(cmdCancel);
        add(pnlDsp,BorderLayout.CENTER);
        add(pnlCmd,BorderLayout.SOUTH);
        setVisible(true);
        cmdConfirm.addActionListener(new ConfirmActionListener());
        cmdCancel.addActionListener(new CancelActionListener());
    }
    private class ConfirmActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            String name="";
            String cNumber="";
            String expiry="";
            String cvv="";
            try{
                name = cardName.getText();
                cNumber = cardNumber.getText();
                expiry = expDate.getText();
                cvv = cvvNumber.getText();
                if (name.length() < 4 || !name.contains(" ")) {
                    showMessage("Invalid Name Entered");
                }
                else if (cNumber.length() < 15 || cNumber.length() > 16 || !isNumeric(cNumber)){
                    showMessage("Invalid Card Number Entered");
                }
                else if (!isValidDate(expiry)){
                    showMessage("Invalid Expiry Date Entered");
                }
                else if (cvv.length() < 3 || cvv.length() > 4 || !isNumeric(cvv)){
                    showMessage("Invalid CVV Entered");
                } else {
                    JOptionPane.showMessageDialog(null, "Transaction Successful", "SUCCESS", JOptionPane.PLAIN_MESSAGE);
                    ArrayList<Warranty> customerWarranties = customer.getWarranty();
                    for (Warranty warr: warranties){
                    customerWarranties.add(warr);
                    }
                    customer.setWarranty(customerWarranties);
                    frame.setVisible(false);
                }
            }catch (NumberFormatException nfe){}
        }
    }
    private class CancelActionListener implements ActionListener
    {
        public void actionPerformed(ActionEvent e)
        {
            frame.setVisible(false);
        }
    }
    public void showMessage(String msg){
        JOptionPane.showMessageDialog(null, msg, "ERROR", JOptionPane.ERROR_MESSAGE);
    }
    private boolean isNumeric(String str) {
        return str.matches("\\d+");
    }
    public static boolean isValidDate(String date) {
        Calendar today = Calendar.getInstance();
        today.set(Calendar.HOUR_OF_DAY, 0);
        today.set(Calendar.MINUTE, 0);
        today.set(Calendar.SECOND, 0);
        today.set(Calendar.MILLISECOND, 0);
        SimpleDateFormat dateFormat = new SimpleDateFormat("MMyy");
        try {
            Date parsedDate = dateFormat.parse(date);
            return parsedDate.equals(today.getTime()) || parsedDate.after(today.getTime());
        } catch (ParseException e) {
            return false;
        }
    }
}