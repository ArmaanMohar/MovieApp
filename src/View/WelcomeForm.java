package View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;

import Controller.AccountController;
import Model.User;

import java.awt.BorderLayout;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;

public class WelcomeForm {

	private JFrame frmWelcomeScreen;
	private User user;
	private AccountController myAccount;

//	/**
//	 * Launch the application.
//	 
//	public static void main(String[] args) {
//		EventQueue.invokeLater(new Runnable() {
//			public void run() {
//				try {
//					WelcomeForm window = new WelcomeForm();
//					window.frmWelcomeScreen.setVisible(true);
//				} catch (Exception e) {
//					e.printStackTrace();
//				}
//			}
//		});
//	}
	

	/**
	 * Create the application.
	 */
	public WelcomeForm(User user, AccountController ac) {
		this.user = user;
		this.myAccount = ac;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmWelcomeScreen = new JFrame();
		frmWelcomeScreen.setTitle("Welcome Screen");
		frmWelcomeScreen.setBounds(100, 100, 494, 288);
		frmWelcomeScreen.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmWelcomeScreen.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnNewButton = new JButton("View Movies");
		btnNewButton.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmWelcomeScreen.dispose();
				view();
			}
		});
		btnNewButton.setBounds(53, 100, 127, 44);
		panel.add(btnNewButton);
		
		JButton btnCancelTicket = new JButton("Cancel Ticket");
		btnCancelTicket.setFont(new Font("Tahoma", Font.PLAIN, 16));
		btnCancelTicket.setBounds(262, 100, 127, 44);
		btnCancelTicket.addActionListener((ActionEvent e) ->{
			frmWelcomeScreen.dispose();
			cancel(this.user);
		});
		panel.add(btnCancelTicket);
		
		JButton btnNewButton_5 = new JButton("BACK");
		btnNewButton_5.setBounds(50, 50, 88, 37);
		panel.add(btnNewButton_5);
		btnNewButton_5.addActionListener((ActionEvent e) ->{
			frmWelcomeScreen.dispose();
			back();
		});
		
		frmWelcomeScreen.setVisible(true);
	}
	
	public void cancel(User u) {
		myAccount.cancel(u);
	}
	
	public void back() {
		myAccount.login();
	}
	
	public void view() {
		myAccount.showMovies(this.user);
	}
}
