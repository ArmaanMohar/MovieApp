package Model;
import java.util.*;

/**
 * 
 */
public abstract class User {

    /**
     * Default constructor
     */
    public User(){
        this.tickets = null;
        this.isRegistered = false;
    }

    public ArrayList<Ticket> getTickets(){
        return tickets;
    }

    public boolean getUserStatus(){
        return isRegistered;
    }

    public boolean addTicket(Ticket myTicket){
        boolean added = getTickets().add(myTicket);
        return added;
    }

    public void setUserStatus(boolean s){
        this.isRegistered = s;
    }

    public void buyTicket(Ticket t){}

    

    
    


    /**
     * 
     */
    private boolean isRegistered;

    /**
     * 
     */
    private ArrayList<Ticket> tickets;

}