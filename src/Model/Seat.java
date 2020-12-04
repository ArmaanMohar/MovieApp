package Model;
import java.util.*;

/**
 * 
 */
public class Seat {

    /**
     * Default constructor
     */
    public Seat(String num) {
    	this.seatNum = num;
    }
    
    public String getSeatNumber() {
    	return seatNum;
    }

    /**
     * 
     */
    private String seatNum;

    /**
     * 
     */
    private boolean isTaken;

    private String seatID;

    



}