package Controller;

import java.util.*;

import Database.DatabaseManager;
import Model.Movie;
import Model.Payment;
import Model.Seat;
import Model.Showtime;
import Model.Ticket;
import Model.User;
import Model.globals;
import View.MakePaymentForm;

/**
 * 
 */
public class MakePaymentController implements globals{

    /**
     * Default constructor
     */
    public MakePaymentController(DatabaseManager data, Movie movie, Showtime time, Seat seat, User user) {
        this.DB = data;
        this.user = user;
    	this.ticket = new Ticket(movie, time, seat);
    	this.makePayment = new MakePaymentForm(this.ticket, this, user);
    }
    
    private DatabaseManager DB;

    private User user;

    /**
     * 
     */
    private Payment payment;

    /**
     * 
     */
    private MakePaymentForm makePayment;

    /**
     * 
     */
    private Ticket ticket;


    public void done(){
        DB.login();
    }





    /**
     * @param ticket 
     * @return
     */
    public float buyTicket(Ticket ticket, String code) {
       return DB.addTicket(ticket, code);
    }

    /**
     * @return
     */
    public void generateTicket() {
        // TODO implement here
        
    }

}