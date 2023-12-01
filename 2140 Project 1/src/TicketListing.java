//import java.util.List;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

//import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.table.DefaultTableModel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.io.FileWriter;


public class TicketListing extends JFrame { // window to display Add ticket option to alt team
   private JButton cmdAddTicket         = new JButton("Sort By Year");
   private JButton cmdShowTickets;
   private JButton cmdClose            = new JButton("X");
    private JLabel prompt = new JLabel("View customer tickets below");

    //private TicketListing tlisting;        //list of ticket
    private ArrayList<Ticket> tlist;
    
    //private JFrame tl;

    private DefaultTableModel tablemodel;


    private JPanel pnlCommand           = new JPanel();
    private JPanel pnlDisplay            = new JPanel();
public TicketListing(){
    setVisible(true);

    setSize(400, 300);

    tablemodel = new DefaultTableModel();
    new GridLayout(2,1);
    //tlisting = this;
    tlist = new ArrayList<Ticket>();
    pnlCommand = new JPanel();
    pnlDisplay = new JPanel();
  
    cmdShowTickets = new JButton("Show Tickets");
    cmdClose   = new JButton("X");
    
    cmdShowTickets.addActionListener(new ShowListener());
    cmdClose.addActionListener(new CloseListener());

    pnlDisplay.add(prompt);

    pnlCommand.add(cmdShowTickets);
    pnlCommand.add(cmdClose);

    cmdAddTicket.setBackground(Color.GRAY);
    cmdAddTicket.setForeground(Color.WHITE);
    cmdShowTickets.setBackground(Color.GRAY);
    cmdShowTickets.setForeground(Color.WHITE);
    cmdClose.setBackground(Color.GRAY);
    cmdClose.setForeground(Color.PINK);
    
   pnlCommand.setPreferredSize(new Dimension(200,100));
    
    add(pnlDisplay, BorderLayout.NORTH);
    add(pnlCommand, BorderLayout.SOUTH);

    setVisible(true);
    //tl = this;
    // add details
}

/** 
 * Show Table Method 
 * Shows tickets added to the ticket table
 * @return ArrayList<ticket>
 */
public void showTable(ArrayList<Ticket> tlist)
    {
        for (Ticket t:tlist)
        {
            addToTable(t);
        }
    }

    /**
     * Add Ticket Method 
     * Adds tickets to the ticket table 
     * @param t
     */
    public void addTicket(Ticket t)
    {
        tlist.add(t);
        addToTable(t);
        saveToFile(t);
    }

        /**
    Save To File Methood
     @param Ticket t
     saves ticket data to file
    */
    public void saveToFile(Ticket t){
        try{
            FileWriter fw = new FileWriter("SupportTicket.dat",true);
            fw.write(t.toString()+"\n");
            fw.close();
        }catch(Exception e){
            System.out.println(e);
        }
    }

    /**
     * Add to Table 
     * adds ticket details to table 
     * @param t
     */
    public void addToTable(Ticket t)
    {
        String[] record = {"" +  t.getTicketNumber()+""+t.getTitle()+" "+  t.getName()+" "+t.getContactInfo()+" "+t.getDescription()+ " " + t.getPriority()+ " "+t.getStatus()};
     tablemodel.addRow(record);        
    }

        /**
     * Load Ticket Methods
     * loads ticket detaiils from the file so that the ticket can be added to the ticket table 
     * @param fileName
     * @return
     */
    public static ArrayList<Ticket> loadTickets(String fileName) 
    {
        Scanner scanner = null;
        ArrayList<Ticket> tickets = new ArrayList<>();
        try 
        {
            scanner = new Scanner(new File(fileName));
            while (scanner.hasNext()) 
            {
                String line = scanner.nextLine();
                String[] tokens = line.split(",");

                int ticketNumber  = Integer.parseInt(tokens[0]);
                String title= tokens[1];
                String name= tokens[2];
                String contactInfo = tokens[3];
                String description = tokens[4];
                String priority = tokens[5];
                String status = tokens [6];
            
                Ticket ticket = new Ticket(ticketNumber, title, name , contactInfo,description, priority , status);
                tickets.add(ticket);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
       System.out.println(tickets);
        return tickets;
    }

    // private class TicketListener implements ActionListener
    // {
    //     public void actionPerformed(ActionEvent c)
    //     {
    //         AddTicket addt = new AddTicket(tlisting);  
    //         System.out.println(addt);          
    //     }
    // }

    private class ShowListener implements ActionListener
    {
        public void actionPerformed(ActionEvent sh)
        {
           new ShowTicket();
        }
    }

    private class CloseListener implements ActionListener
    {
        public void actionPerformed(ActionEvent cl)
        {
           tl.setVisible(false);
        }
    }
    }
