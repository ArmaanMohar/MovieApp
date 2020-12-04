package Model;
import java.util.*;

/**
 * 
 */
public class Showtime {

    /**
     * Default constructor
     */
    public Showtime(int id, String time) {
        this.ID = id;
        this.time = time;
    }

    /**
     * 
     */
    private String time;

    /**
     * 
     */
    private ArrayList<String> seats;

    private int ID;

    public int getID(){
        return ID;
    }
    
    public String getTime() {
    	return time;
    }




}