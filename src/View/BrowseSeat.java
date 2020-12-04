package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JToggleButton;

import Controller.SelectionController;
import Model.Movie;
import Model.Seat;
import Model.Showtime;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import java.awt.Canvas;
import java.awt.Color;
/**
 * 
 */
public class BrowseSeat {
    
    /**
     * @return
     */
    public String listAvailableSeats() {
        // TODO implement here
        return "";
    }
    
    
	private JFrame frmSelectSeat;
	private SelectionController mySelection;
	private Movie movie;
	private Showtime time;
	private ArrayList<Seat> taken;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrowseSeat window = new BrowseSeat();
					window.frmSelectSeat.setVisible(true);
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
	public BrowseSeat(SelectionController select, Movie movieName, Showtime time) {
		this.mySelection = select;
		this.movie = movieName;
		this.time = time;
		taken = mySelection.getTakenSeats(movie, time);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmSelectSeat = new JFrame();
		frmSelectSeat.setTitle("Select Seat");
		frmSelectSeat.setBounds(100, 100, 512, 521);
		frmSelectSeat.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmSelectSeat.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		
		char c ='A';
		int j=1;
		int x = 77;
		int y= 111;
		
		
		for(int i=0; i < 15; i++) {
			
			if(i%5 == 0 && i > 0) {
				j = 1;
				c++;
				x = 77;
				y += 70;
			}
			
			buttons.add(i, new JButton(c + Integer.toString(j) ));
			buttons.get(i).setBounds(x, y , 59, 59);
			panel.add(buttons.get(i));
			for( Seat s: taken) {
				if(buttons.get(i).getText().equals(s.getSeatNumber())){
					buttons.get(i).setBackground(Color.red);
					buttons.get(i).setEnabled(false);
				}
			}
			
			int k = i;
			buttons.get(i).addActionListener((ActionEvent e) ->{
				frmSelectSeat.dispose();
				continueToPayment(new Seat(buttons.get(k).getText()));
			});
			
			j++;
			x +=73;
		}
		
		if(! mySelection.getUser().getUserStatus()) {
			for(int m=13; m < 15; m++) {
				buttons.get(m).setEnabled(false);
				
			}
		}
		
		
		
//		JButton btnNewButton = new JButton("A1");
//		btnNewButton.setBounds(77, 111, 59, 59);
//		panel.add(btnNewButton);
//		
//		JButton btnA = new JButton("A2");
//		btnA.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				frmSelectSeat.dispose();
//				continueToPayment(btnA.getText());
//			}
//		});
//		btnA.setBounds(146, 111, 59, 59);
//		panel.add(btnA);
//		
//		JButton btnA_1 = new JButton("A3");
//		btnA_1.setBounds(215, 111, 59, 59);
//		panel.add(btnA_1);
//		
//		JButton btnA_2 = new JButton("A4");
//		btnA_2.setBounds(284, 111, 59, 59);
//		panel.add(btnA_2);
//		
//		JButton btnA_3 = new JButton("A5");
//		btnA_3.setBounds(353, 111, 59, 59);
//		panel.add(btnA_3);
//		
//		JButton btnB = new JButton("B1");
//		btnB.setBounds(77, 181, 59, 59);
//		panel.add(btnB);
//		
//		JButton btnB_1 = new JButton("B2");
//		btnB_1.setBounds(146, 181, 59, 59);
//		panel.add(btnB_1);
//		
//		JButton btnNewButton_8 = new JButton("B3");
//		btnNewButton_8.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//				
//			}
//		});
//		btnNewButton_8.setBounds(215, 181, 59, 59);
//		panel.add(btnNewButton_8);
//		
//		JButton btnNewButton_7_1 = new JButton("B4");
//		btnNewButton_7_1.setBounds(284, 181, 59, 59);
//		panel.add(btnNewButton_7_1);
//		
//		JButton btnNewButton_7_2 = new JButton("B5");
//		btnNewButton_7_2.setBounds(353, 181, 59, 59);
//		panel.add(btnNewButton_7_2);
//		
//		JButton btnNewButton_7_4 = new JButton("C1");
//		btnNewButton_7_4.setBounds(77, 249, 59, 59);
//		panel.add(btnNewButton_7_4);
//		
//		JButton btnNewButton_7_5 = new JButton("C2");
//		btnNewButton_7_5.setBounds(146, 249, 59, 59);
//		panel.add(btnNewButton_7_5);
//		
//		JButton btnNewButton_9 = new JButton("D1");
//		btnNewButton_9.setBounds(77, 319, 59, 59);
//		panel.add(btnNewButton_9);
//		
//		JButton btnNewButton_1_1 = new JButton("D2");
//		btnNewButton_1_1.setBounds(146, 319, 59, 59);
//		panel.add(btnNewButton_1_1);
//		
//		JButton btnNewButton_2_1 = new JButton("D3");
//		btnNewButton_2_1.setBounds(215, 319, 59, 59);
//		panel.add(btnNewButton_2_1);
//		
//		JButton btnNewButton_3_1 = new JButton("D4");
//		btnNewButton_3_1.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent e) {
//			}
//		});
//		btnNewButton_3_1.setBounds(284, 319, 59, 59);
//		panel.add(btnNewButton_3_1);
//		
//		JButton btnNewButton_4_1 = new JButton("D5");
//		btnNewButton_4_1.setBounds(353, 319, 59, 59);
//		panel.add(btnNewButton_4_1);
//		
//		JButton btnNewButton_7_4_1 = new JButton("C3");
//		btnNewButton_7_4_1.setBounds(215, 249, 59, 59);
//		panel.add(btnNewButton_7_4_1);
//		
//		JButton btnNewButton_7_5_1 = new JButton("C4");
//		btnNewButton_7_5_1.setBounds(284, 249, 59, 59);
//		panel.add(btnNewButton_7_5_1);
//		
//		JButton btnNewButton_7_4_2 = new JButton("C5");
//		btnNewButton_7_4_2.setBounds(353, 249, 59, 59);
//		panel.add(btnNewButton_7_4_2);
//		
		Canvas canvas = new Canvas();
		canvas.setBackground(Color.BLACK);
		canvas.setBounds(77, 47, 335, 14);
		panel.add(canvas);
		
		JLabel lblNewLabel = new JLabel("SCREEN");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		lblNewLabel.setBounds(212, 11, 87, 33);
		panel.add(lblNewLabel);
		
		JButton btnNewButton_1 = new JButton("Back");
        btnNewButton_1.setBounds(23, 437, 89, 23);
        panel.add(btnNewButton_1);
        btnNewButton_1.addActionListener((ActionEvent) -> {
        	frmSelectSeat.dispose();
        	back();
        });
        
        frmSelectSeat.setVisible(true);
	}
	
	public void continueToPayment(Seat seatNum) {
		mySelection.payment(seatNum);
	}
	
	public void back() {
		mySelection.showtimes(movie);
	}
}