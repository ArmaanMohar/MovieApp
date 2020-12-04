package View;
import java.util.*;
import javax.swing.*;

import Controller.MakePaymentController;
import Model.RegisteredUser;
import Model.Ticket;
import Model.User;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * 
 */
public class MakePaymentForm extends JFrame{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JLabel instruction = new JLabel("Enter Payment Info");
	private JLabel cardLabel = new JLabel("Enter your credit card information");
	private JLabel emailLabel = new JLabel("Enter your email");
	private JLabel couponLabel = new JLabel("Enter your coupon code");
	private JLabel amountLabel = new JLabel("Price : $10");
	private JButton submit = new JButton ("Buy Ticket");
	private JButton back = new JButton ("Back");
	private JTextField card = new JTextField(10);
	private JTextField email = new JTextField(10);
	private JTextField coupon = new JTextField(10);
	private Ticket ticket;
	private String couponCode;
	private MakePaymentController pay;
	private User user;


	/**
     * Default constructor
     */
    public MakePaymentForm(Ticket myTicket, MakePaymentController payment, User user) {
		super("Payment Info");
		System.out.println("YOLO");
		this.ticket = myTicket;
		this.user = user;
    	this.pay = payment;
		setSize(400,200);
		setLayout(new BorderLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);   
		
		
		JPanel p = new JPanel();
		p.setLayout(new FlowLayout());
		// card.setBounds(50,50, 150,20);  
		// submit.setBounds(50,100,95,30); 
		// cardLabel.setBounds(50, 25, 200, 20);
		 p.add(amountLabel);
		 
		 
		 
		 JPanel p2 = new JPanel();
		 p2.setLayout(new FlowLayout());
		 p2.add(cardLabel);
		 p2.add(card);
		
		 
		 
		 JPanel p3 = new JPanel();
		 p3.setLayout(new FlowLayout());
		 p3.add(emailLabel);
		 p3.add(email);
		 p3.add(couponLabel);
		 p3.add(coupon);
		 
//		 void calculate(Person p) {
//			    ((Student)p).method();
//			}
		 
		 System.out.println(user.getUserStatus());
		 
		 if(this.user.getUserStatus()){
			 System.out.println("HELLO WORLD");
			 RegisteredUser temp = (RegisteredUser) user;
			 card.setText(temp.getCardInfo());
			 email.setText(temp.getEmail());
			 //System.out.println(temp.getCardInfo());
			 //System.out.println(temp.getEmail());
		 }
		 //JPanel p4 = new JPanel();
		 p3.add(submit);
		 submit.addActionListener((ActionEvent e) ->{
			 float price = submitPayment();
			 JOptionPane.showMessageDialog(null, "Price paid: " +price +"\n System Exiting....");
			 done();
			 dispose();
			 
		 });
		 
		 add(p, BorderLayout.NORTH);
		 add(p2, BorderLayout.CENTER);
		 add(p3, BorderLayout.SOUTH);
		 //add(p4, BorderLayout.LINE_END);
		 pack();
		 
		 if( ! coupon.getText().isEmpty()) {
			 couponCode = coupon.getText();
			 //verifyCoupon(couponCode);
		 }
		 
		 setVisible(true);
		 
		  
	}
	
	public void done(){
		pay.done();
	}
    
    
    public float submitPayment() {
    	System.out.println("Sending Payment To DB....");
    	System.out.println(ticket.getTicketID());
    	return pay.buyTicket(ticket, coupon.getText());
    	
    }
    
    /*
    public static void main(String[] args) {
    	MakePaymentForm mp = new MakePaymentForm();
    	mp.setLayout(null);  
		mp.setVisible(true);
    }
    */


    /**
     * 
     */
    public void displayForm() {
        // TODO implement here
    }

}