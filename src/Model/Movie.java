package Model;
import java.util.*;

/**
 * 
 */
public class Movie {

    /**
     * Default constructor
     */
    public Movie(String name, int ID) {
        this.name = name;
        this.ID = ID;
    }
    
    

    public int getID(){
        return ID;
    }

    public String getName(){
        return name;
    }

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private ArrayList<Showtime> timing;

    /**
     * 
     */
    private String genre;

    /**
     * 
     */
    private int rating;

    private int ID;



}