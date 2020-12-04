package Model;
import java.util.*;

/**
 * 
 */
public class RegisteredUser extends User {

    /**
     * Default constructor
     */
    public RegisteredUser(String username, String name, String address, String card ) {
        super();
        super.setUserStatus(true);
        this.username = username;
        this.name = name;
        this.address = address;
        this.cardInfo = card;
    }
    
    @Override
    public String toString() {
    	return username;
    }

    

   
    public String getCardInfo(){
        return cardInfo;
    }

    public String getEmail(){
        return address;
    }
    

    /**
     * 
     */
    private String name;

    /**
     * 
     */
    private String address;

    /**
     * 
     */
    private String cardInfo;

    /**
     * 
     */
    private String username;

    



}