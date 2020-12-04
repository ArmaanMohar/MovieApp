package View;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextArea;

import Controller.AccountController;
import Model.RegisteredUser;
import Model.RegularUser;
import Model.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LoginForm {
    

	private AccountController account;
	
	private JFrame frmMovieAppLogin;

	
	/**
     * 
     */
    private String username;

    /**
     * 
     */
    private String password;

	/**
	 * Launch the application.
	 
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginForm window = new LoginForm();
					window.frmMovieAppLogin.setVisible(true);
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
	public LoginForm(AccountController ac) {
		this.account = ac;
		
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	public void initialize() {
		frmMovieAppLogin = new JFrame();
		frmMovieAppLogin.setTitle("Movie App Login");
		frmMovieAppLogin.setBounds(100, 100, 450, 300);
		frmMovieAppLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmMovieAppLogin.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.LIGHT_GRAY);
		panel.setBounds(0, 0, 434, 261);
		frmMovieAppLogin.getContentPane().add(panel);
		panel.setLayout(null);
		
		JTextArea textArea = new JTextArea(); //username
		textArea.setBounds(191, 39, 164, 22);
		panel.add(textArea);
		
		JTextArea textArea_1 = new JTextArea(); //password
		textArea_1.setBounds(191, 101, 164, 22);
        panel.add(textArea_1);
        String input = textArea_1.getText();
        System.out.println(input);
		
		JLabel lblNewLabel = new JLabel("Username");
		lblNewLabel.setBounds(69, 40, 79, 22);
        panel.add(lblNewLabel);
        String user = lblNewLabel.getText();
		
		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(69, 102, 79, 22);
		panel.add(lblPassword);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.setBounds(26, 157, 89, 23);
		panel.add(btnNewButton);
		System.out.println("Before Login Action Event");
        btnNewButton.addActionListener((ActionEvent e) -> {
			System.out.println("In Login Action Event");
				if(e.getSource() == btnNewButton){
                
                 username = textArea.getText();
                 password = textArea_1.getText();
                 
                 if (! validateLogin(username, password)){
					JOptionPane.showMessageDialog(null, "Login Failed!");
                 } else{
					 System.out.println("Log In Successful");
					 RegisteredUser registered = getUserInfo(username);
					 welcomePage(registered);
					 frmMovieAppLogin.dispose();
				 }
				}
            
            
        });
        
		
		JButton btnNewButton_1 = new JButton("Continue as Guest");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frmMovieAppLogin.dispose();
				RegularUser reg = new RegularUser();
				welcomePage(reg);
				
			}
		});
		btnNewButton_1.setBounds(125, 157, 139, 23);
        panel.add(btnNewButton_1);
        
        frmMovieAppLogin.setVisible(true);
        
	}
	
	public RegisteredUser getUserInfo(String username){
		return account.getUser(username);
	}
	
	public void welcomePage(User u) {
		account.welcome(u);
	}
	

    public boolean validateLogin(String username, String password){
        return account.validateLogin(username, password);
    }
    
    public void showMovies(User user) {
    	account.showMovies(user);
    }
    
}