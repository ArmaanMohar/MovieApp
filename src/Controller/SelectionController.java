package Controller;

import java.util.*;

import Database.DatabaseManager;
import Model.Movie;
import Model.Seat;
import Model.Showtime;
import Model.Theatre;
import Model.User;
import View.BrowseMovies;
import View.BrowseSeat;
import View.BrowseShowtimes;
import View.BrowseTheatres;


/**
 * 
 */
public class SelectionController {

    /**
     * Default constructor
     */
    public SelectionController(User u, DatabaseManager d) {
        this.DB = d;
        this.user = u;
    	movie();
    }
    
    private DatabaseManager DB;
    private User user;

    /**
     * 
     */
    private Theatre theatre;

    /**
     * 
     */
    private BrowseMovies browseMovie;

    /**
     * 
     */
    private BrowseTheatres browseTheatre;

    /**
     * 
     */
    private BrowseSeat browseSeat;
    private String movieName;
    private Showtime showtime;
    private Seat seatNumber;
    private Movie movie;

    /**
     * 
     */
    private BrowseShowtimes browseShowtime;
    
    public User getUser() {
    	return this.user;
    }
    
    public ArrayList<Movie> getMovies(){
    	return this.DB.readMovies();
    }
    
    public ArrayList<Showtime> getTimes(Movie movie){
    	return DB.readShowtime(movie);
    }
    
    public ArrayList<Seat> getTakenSeats(Movie m, Showtime t){
    	return DB.getTakenSeats(m, t);
    }
    
    public void movie() {
    	this.browseMovie = new BrowseMovies(this);
    }
    
    public void showtimes(Movie movieName) {
    	this.movie = movieName;
    	this.browseShowtime = new BrowseShowtimes(this, movie);
    }
    
    public void getSeats(Showtime showtime) {
    	this.showtime = showtime;
    	this.browseSeat = new BrowseSeat(this, movie, showtime);
    }
    
    public void payment(Seat seatNum) {
    	this.seatNumber = seatNum;
    	//Ticket newTicket = new Ticket(movieName, showtime, seatNumber);
    	DB.makePayment(movie, showtime, seatNumber, this.user);
    	
    }
    
    public void back() {
    	DB.login();
    }




    public void read(){
        theatre = new Theatre();
    }



    /**
     * @param name 
     * @return
     */
    public void theatreChoice(String name) {
        // TODO implement here
        
    }

    /**
     * @param movieName 
     * @return
     */
    public void movieChoice(Movie movieName) {
        
        
    }

    /**
     * @param showtime 
     * @return
     */
    public void showtimeChoice(Showtime showtime) {
        // TODO implement here
        
    }

    /**
     * @param number 
     * @return
     */
    public void seatChoice(String number) {
        // TODO implement here
        
    }

    /**
     * @param movie 
     * @return
     */
    public void proceedtoCheckout(Movie movie) {
        // TODO implement here
        
    }

}