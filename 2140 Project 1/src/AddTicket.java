import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.Random;


public class AddTicket extends JFrame{
   
	private JButton cmdSave			= new JButton("Save");
	private JButton cmdClose  		= new JButton("X");

	private JFrame av;

	private JPanel pnlCommand		= new JPanel();
    private JPanel pnlDisplay		= new JPanel();

	//private JTextField ticketNumber;        
    private JTextField title;
    private JTextField name;
	private JTextField priority;
    private JTextField description;
    private JTextField contactInfo;
    private JTextField status;

	private TicketListing listing; 
    //private Ticket t; 

    	/**
	 * Creates a frame that allows sellers to enter details relating tickets they which to sell
	 * @param listing
	 */
	public AddTicket(TicketListing listing)
	{
		this.listing = listing;

		setBackground(Color.WHITE);
		setTitle("ADD SUPPORT TICKET");
		
	//	pnlDisplay.add(new JLabel("Ticket Number:")); 
	//	ticketNumber = new JTextField(20);
	//	pnlDisplay.add(ticketNumber);

		pnlDisplay.add(new JLabel("Ticket Title:"));
		title= new JTextField(10);
		pnlDisplay.add(title);

        pnlDisplay.add(new JLabel("Customer Name:"));
		name= new JTextField(10);
		pnlDisplay.add(name);

		pnlDisplay.add(new JLabel("Ticket Priority:"));
		priority = new JTextField(10);
        //should be a dropdown table with radio high or low
		pnlDisplay.add(priority);

		pnlDisplay.add(new JLabel(" Description:"));
		description = new JTextField(10);
		pnlDisplay.add(description);

		pnlDisplay.add(new JLabel("Email or Phone Number:"));
		contactInfo = new JTextField(10);
		pnlDisplay.add(contactInfo);

		pnlDisplay.add(new JLabel("Status:"));
	    pnlDisplay.add(new JLabel("Pending"));
	    pnlDisplay.add(status);

		
		pnlDisplay.setLayout(new GridLayout(12,2));
	
		cmdSave.setBackground(Color.GRAY);
        cmdSave.setForeground(Color.WHITE);
        cmdClose.setBackground(Color.GRAY);
        cmdClose.setForeground(Color.PINK);

		pnlCommand.add(cmdSave);
        pnlCommand.add(cmdClose);

		add(pnlDisplay, BorderLayout.CENTER);
        add(pnlCommand, BorderLayout.SOUTH);

	pack();
        setVisible(true);

		cmdSave.addActionListener(new SaveActionListener());
        cmdClose.addActionListener(new CloseListener());

		av = this;
	}

    
	private class SaveActionListener implements ActionListener
	{
		/**
		 * Action Listener that save ticket details enter by the user
		 */
		public void actionPerformed(ActionEvent c) 
		{   int tticketNumber = ticketNumberGenerator();
        	String ttitle = " ";
        	String tpriority = " ";
            String tname = " ";
            String tcontactInfo = " ";
			String tdescription = " ";
        	String tstatus = "Pending";
        

			try{
               ttitle = title.getText();
				tname = name.getText();
                tpriority = priority.getText();
				tdescription = description.getText();
				tcontactInfo= contactInfo.getText();
				
				if (tname.isEmpty()) 
                	showMessage("Name of Customer required");
				if (ttitle.isEmpty()) 
                	showMessage("Title number required");
				if (tpriority.isEmpty()) 
                	showMessage("Ticket's Priority required");
				if (tcontactInfo.isEmpty()) 
                	showMessage("Customer's Contact Deatils required");
				if (tdescription.isEmpty()) 
                	showMessage("Ticket's Description required ");
			}catch (NumberFormatException e)
			{
			}finally
			{          
                pnlDisplay.add(new JLabel("Your ticket number is"+ (tticketNumber + "" )));
                pnlDisplay.add(new JLabel("Your ticket status is"+ (tstatus )));
                
				listing.addTicket(new Ticket(tticketNumber,ttitle,tname ,tcontactInfo,tdescription, tpriority,tstatus));
			}
			new Ticket();
			av.setVisible(false);
			}
		}

        public int ticketNumberGenerator() {
            // Generate a random ticket number
            int min = 10000; // Minimum ticket number
            int max = 999999999; // Maximum ticket number
            Random random = new Random();
            int ticketNumber = random.nextInt(max - min + 1) + min;
            return ticketNumber;
        }

        

			/**
			 * Creates a frame to display the error message to the user.
			 * @param message
			 */
			public void showMessage(String message){
			JFrame frame = new JFrame("Error");
			
				JOptionPane.showMessageDialog(frame, message,"Error", JOptionPane.ERROR_MESSAGE);
				
				frame.setSize(350,350);
				frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				frame.setVisible(true);
			}

			private class CloseListener implements ActionListener
			{
				public void actionPerformed(ActionEvent cl)
				{
					av.setVisible(false);
				}
			}


}
