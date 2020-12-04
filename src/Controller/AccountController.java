package Controller;


import java.util.*;

import Database.DatabaseManager;
import Model.RegisteredUser;
import Model.User;
import View.LoginForm;
import View.RegisterAccountForm;

import java.awt.EventQueue;

/**
 * 
 */
public class AccountController {

    /**
     * Default constructor
     */
    public AccountController(DatabaseManager data) {
        this.DB = data;
        login();
    }
    
    public void login() {
    	this.login = new LoginForm(this);
    }

    /**
     * 
     */
    private RegisterAccountForm registerAccount;

    /**
     * 
     */
    private LoginForm login;

    /**
     * 
     */
    private RegisteredUser myUser;

    /**
     * 
     */
    private boolean isSignedIn;

    private DatabaseManager DB;

    public RegisteredUser getUser(String username){
        return DB.getUserInfo(username);
    }
    
    public void welcome(User user) {
    	DB.welcomeForm(user, this);
    }

    public void cancel(User user) {
    	 DB.cancelTicketForm(user);
    }

    public boolean validateLogin(String username, String password){
        return DB.validateLogin(username, password);
    }
    
    public void showMovies(User user) {
    	DB.showMovies(user);
    }




    /**
     * @param user 
     * @return
     */
    public void makeNewAccount(RegisteredUser user) {
        // TODO implement here
        
    }

    /**
     * @return
     
    public void login(String username, String name, String address, String card, String tickets) {
        myUser = new RegisteredUser(username, name, address, card);
        System.out.println(username);
        System.out.println(name);
        System.out.println(address);
        System.out.println(card);
        System.out.println(tickets);
        System.out.println("User Status: " + myUser.getUserStatus());
        System.out.println("AC COntroller");
    }
    */

}