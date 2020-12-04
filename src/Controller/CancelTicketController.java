package Controller;

import java.util.*;

import Database.DatabaseManager;
import Model.Coupon;
import Model.User;
import Model.globals;
import View.CancelTicketForm;

/**
 * 
 */
public class CancelTicketController implements globals {

    /**
     * Default constructor
     */
    public CancelTicketController(DatabaseManager data, User user) {
    	this.DB = data;
    	this.cancelTicket = new CancelTicketForm(this);
    	this.user = user;
    }
    private DatabaseManager DB;
    private User user;

    /**
     * 
     */
    private CancelTicketForm cancelTicket;

    /**
     * 
     */
    private Coupon coupon;

    public void back() {
    	DB.login();
    }
    
    public ArrayList<String> cancelTicket(String ID) {
    	return DB.cancelTicket(ID, this.user);
    }




    /**
     * @param user 
     * @return
     */
    public void cancelTicket(User user) {
        // TODO implement here
        
    }

    /**
     * @param coupon 
     * @return
     */
    public void addCouponCode(Coupon coupon) {
        // TODO implement here
        
    }

    /**
     * @param coupon 
     * @return
     */
    public void deleteCoupon(Coupon coupon) {
        // TODO implement here
        
    }

}