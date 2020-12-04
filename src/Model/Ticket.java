package Model;
import java.util.*;

/**
 * 
 */
public class Ticket {
	private Movie movieName;
    private Showtime showtime;
    private Seat seatNum;
    /**
     * Default constructor
     */
    
    public Ticket(Movie movie, Showtime time, Seat seat) {
    	this.movieName = movie;
    	this.showtime = time;
    	this.seatNum = seat;
    }
    
    
    public void setTicketID() {
    	Random rand = new Random();
    	this.ticketID = String.valueOf(rand.nextInt(100000));
    }
    
    public Showtime getTime() {
    	return showtime;
    }
    
    public Seat getSeatNum() {
    	return seatNum;
    }
    

    public String getTicketID(){
        return ticketID;
    }

    public Movie getTicketMovie(){
        return movie;
    }

    public int getSeatID(){
        return seatID;
    }
    
    public Movie getMovie() {
    	return movieName;
    }

    /**
     * 
     */
    private String theatreName;

    /**
     * 
     */
    private String ticketID;

    /**
     * 
     */
    private Movie movie;

    /**
     * 
     */
    private Payment pay;

    private int seatID;







    /**
     * @param pay 
     * @return
     */
    public void buyTicket(Payment pay) {
        // TODO implement here
        
    }

}