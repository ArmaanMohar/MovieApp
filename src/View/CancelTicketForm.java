package View;
import java.awt.EventQueue;
import java.awt.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

import Controller.CancelTicketController;
import Model.User;

public class CancelTicketForm {

	private JFrame frame;
	private CancelTicketController cancelCon;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CancelTicketForm window = new CancelTicketForm();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	*/

	/**
	 * Create the application.
	 */
	public CancelTicketForm(CancelTicketController con) {
		this.cancelCon = con;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame("Cancel Ticket");
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel cancel = new JLabel("Enter Ticket ID: ");
		cancel.setBounds(90, 50, 250, 50);
		frame.add(cancel);
		JTextArea textArea = new JTextArea();
		textArea.setBounds(100, 100, 200, 25);
		frame.add(textArea);
		
		JButton btnNewButton = new JButton("Cancel Ticket");
		btnNewButton.addActionListener((ActionEvent e) -> {
			ArrayList<String> results = cancel(textArea.getText());
			
				if(	results.get(0).equals("true") ) {
					//createCoupon();
				JOptionPane.showMessageDialog(null, "Ticket Cancelled \n Coupon Code is: " +results.get(1));
				
			} else if( results.get(0).equals("time")){
				JOptionPane.showMessageDialog(null, "Error: Showtime is less than 72 hours away");
			} else {
				JOptionPane.showMessageDialog(null, "Could Not Find Ticket: " + textArea.getText());
			}
		});
		btnNewButton.setBounds(185, 172, 121, 38);
		frame.getContentPane().add(btnNewButton);
		
		JButton btnNewButton_2 = new JButton("BACK");
		btnNewButton_2.setBounds(30, 172, 121, 38);
		frame.add(btnNewButton_2);
		btnNewButton_2.addActionListener((ActionEvent e) ->{
			frame.dispose();
			back();
		});
		
		frame.setVisible(true);
	}
	
	public void createCoupon(User u) {
		//cancelCon.
	}
	
	public ArrayList<String> cancel(String ID) {
		return cancelCon.cancelTicket(ID);
	}
	
	public void back() {
		cancelCon.back();
	}
}