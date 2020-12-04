package View;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import Controller.SelectionController;
import Model.Movie;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.ArrayList;

import javax.swing.JButton;

public class BrowseMovies {

	private JFrame frmBrowseMovies;
	private SelectionController mySelection;
	private ArrayList<Movie> movies;
	

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					BrowseMovies window = new BrowseMovies();
					window.frmBrowseMovies.setVisible(true);
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
	public BrowseMovies(SelectionController selection) {
		this.mySelection = selection;
		movies = mySelection.getMovies();
		//System.out.println(movies.toString());
		for(Movie m: movies) {
			System.out.println(m.getName() + "  ID: " + m.getID());
		}
		initialize();
	}
	
    public ArrayList<String> listMovies() {
        // TODO implement here
        return null;
    }

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmBrowseMovies = new JFrame();
		frmBrowseMovies.setTitle("Browse Movies");
		frmBrowseMovies.setBounds(100, 100, 680, 511);
		frmBrowseMovies.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frmBrowseMovies.getContentPane().add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("List of Movies");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel.setBounds(108, 40, 123, 28);
		lblNewLabel.setVerticalAlignment(SwingConstants.TOP);
		panel.add(lblNewLabel);
		int y=90;
		
		
		ArrayList<JLabel> labels = new ArrayList<JLabel>();
		ArrayList<JButton> buttons = new ArrayList<JButton>();
		
		
		for(int i=0; i < movies.size(); i++) {
			labels.add(i, new JLabel(movies.get(i).getName()));
			labels.get(i).setFont(new Font("Tahoma", Font.PLAIN, 18));
			labels.get(i).setBounds(124, y, 83, 28);
			panel.add(labels.get(i));
			
			buttons.add(i, new JButton("SELECT"));
			buttons.get(i).setBounds(361, y , 88, 28);
			panel.add(buttons.get(i));
			
			final int c = i;
			buttons.get(i).addActionListener((ActionEvent e) ->{
				showTimes(movies.get(c));
				frmBrowseMovies.dispose();
				
			});
			
			y += 60;
		}
		
		JButton btnNewButton_5 = new JButton("BACK");
		btnNewButton_5.setBounds(30, 426, 88, 37);
		panel.add(btnNewButton_5);
		btnNewButton_5.addActionListener((ActionEvent e) ->{
			frmBrowseMovies.dispose();
			back();
		});
		
		frmBrowseMovies.setVisible(true);
	}
	
	public void back() {
		mySelection.back();
	}
	
	public void showTimes(Movie movieName) {
		mySelection.showtimes(movieName);
	}
}