package View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.SwingConstants;

import Controller.SelectionController;
import Model.Movie;
import Model.Showtime;

public class BrowseShowtimes {

	private JFrame frmShowtimes;
	private SelectionController mySelection;
	private ArrayList<Showtime> times;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrowseShowtimes window = new BrowseShowtimes();
					window.frmShowtimes.setVisible(true);
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
	public BrowseShowtimes(SelectionController select, Movie m) {
		this.mySelection = select;
		times = mySelection.getTimes(m);
		for(Showtime s: times) {
			System.out.println("ID: " + s.getID());
		}
		
		initialize();
	}
	
    /**
     * @return
     */
    public ArrayList<String> listShowtimes() {
        // TODO implement here
        return null;
    }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmShowtimes = new JFrame();
		frmShowtimes.setTitle("Showtimes");
		frmShowtimes.setBounds(100, 100, 707, 497);
		frmShowtimes.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmShowtimes.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		frmShowtimes.getContentPane().add(panel);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("SHOWTIMES");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 20));
		lblNewLabel.setBounds(284, 26, 114, 52);
		panel.add(lblNewLabel);
		
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		int y=120;
		
		for(int i=0; i < times.size(); i++) {
			labels.add(i, new JLabel(times.get(i).getTime()));
			labels.get(i).setFont(new Font("Tahoma", Font.PLAIN, 18));
			labels.get(i).setBounds(124, y, 180, 28);
			panel.add(labels.get(i));
			
			buttons.add(i, new JButton("SELECT"));
			buttons.get(i).setBounds(361, y , 88, 28);
			panel.add(buttons.get(i));
			
			final int c = i;
			buttons.get(i).addActionListener((ActionEvent e) ->{
				showSeats(times.get(c));
				frmShowtimes.dispose();
				
			});
			
			y += 70;
		}
		
		//placeholders for showtimes until this connects to database
//		JLabel lblNewLabel_1 = new JLabel("12:00");
//		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
//		lblNewLabel_1.setBounds(154, 116, 120, 40);
//		panel.add(lblNewLabel_1);
//		
//		JLabel lblNewLabel_1_1 = new JLabel("3:00");
//		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 19));
//		lblNewLabel_1_1.setBounds(154, 194, 120, 40);
//		panel.add(lblNewLabel_1_1);
//		
//		JLabel lblNewLabel_1_2 = new JLabel("5:00");
//		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 19));
//		lblNewLabel_1_2.setBounds(154, 271, 120, 40);
//		panel.add(lblNewLabel_1_2);
//		
//		JLabel lblNewLabel_1_3 = new JLabel("7:00");
//		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 19));
//		lblNewLabel_1_3.setBounds(154, 346, 120, 40);
//		panel.add(lblNewLabel_1_3);
//		
//		JButton btnNewButton = new JButton("SELECT");
//		btnNewButton.setVerticalAlignment(SwingConstants.BOTTOM);
//		btnNewButton.setBounds(444, 116, 105, 27);
//		panel.add(btnNewButton);
//		btnNewButton.addActionListener((ActionEvent e) -> {
//			frmShowtimes.dispose();
//			showSeats(lblNewLabel_1.getText());
//		});
//		
//		JButton btnNewButton_1 = new JButton("SELECT");
//		btnNewButton_1.setVerticalAlignment(SwingConstants.BOTTOM);
//		btnNewButton_1.setBounds(444, 194, 105, 27);
//		panel.add(btnNewButton_1);
//		btnNewButton_1.addActionListener((ActionEvent e) -> {
//			
//			showSeats(lblNewLabel_1.getText());
//		});
//		
//		JButton btnNewButton_1_1 = new JButton("SELECT");
//		btnNewButton_1_1.setVerticalAlignment(SwingConstants.BOTTOM);
//		btnNewButton_1_1.setBounds(444, 271, 105, 27);
//		panel.add(btnNewButton_1_1);
//		btnNewButton_1_1.addActionListener((ActionEvent e) -> {
//			showSeats(lblNewLabel_1.getText());
//		});
//		
//		JButton btnNewButton_1_2 = new JButton("SELECT");
//		btnNewButton_1_2.setVerticalAlignment(SwingConstants.BOTTOM);
//		btnNewButton_1_2.setBounds(444, 346, 105, 27);
//		panel.add(btnNewButton_1_2);
//		btnNewButton_1_2.addActionListener((ActionEvent e) -> {
//			showSeats(lblNewLabel_1.getText());
//		});
//		
		JButton btnNewButton_2 = new JButton("BACK");
		btnNewButton_2.setBounds(32, 422, 105, 27);
		panel.add(btnNewButton_2);
		btnNewButton_2.addActionListener((ActionEvent e) ->{
			frmShowtimes.dispose();
			back();
		});
		
		frmShowtimes.setVisible(true);
	}
	
	public void showSeats(Showtime showtime) {
		mySelection.getSeats(showtime);
	}
	
	public void back() {
		mySelection.movie();
	}

}