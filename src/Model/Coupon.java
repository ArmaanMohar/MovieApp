package Model;


import java.util.*;

/**
 * 
 */
public class Coupon {

    /**
     * Default constructor
     */
    public Coupon(User u) {
        Random rand = new Random();
        String ID = String.valueOf(rand.nextInt(100000));
        this.code = ID;
        this.amount = (float) globals.ticketPrice;
        calculateAmount(u);
        
    }

    public String getCode(){
        return code;
    }

    public float getAmount(){
        return amount;
    }

    /**
     * 
     */
    private String code;

    /**
     * 
     */
    private float amount;



    /**
     * @param user 
     * @return
     */
    public void calculateAmount(User user) {
        if(!user.getUserStatus()){
            amount = (float) (amount * 0.85);
        }
    }


}