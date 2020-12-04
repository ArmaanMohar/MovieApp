package Database;


import java.sql.Connection;
import java.sql.DriverManager;
import java.util.*;
import java.time.*;

import Controller.AccountController;
import Controller.CancelTicketController;
import Controller.MakePaymentController;
import Controller.SelectionController;
import Model.Coupon;
import Model.Movie;
import Model.RegisteredUser;
import Model.Seat;
import Model.Showtime;
import Model.Ticket;
import Model.User;
import View.WelcomeForm;

import java.sql.*;

/**
 * 
 */
public class DatabaseManager {
    private String username;
    private String passcode;
    private static Connection connection;
    private Statement st;
    private ResultSet rs;
    private AccountController myAccount;
    private SelectionController mySelection;
    private MakePaymentController myPayment;
    private CancelTicketController cancelTicket;
    private WelcomeForm myWelcome;
    private boolean userLoggedIn;

    /**
     * Default constructor
     */
    public DatabaseManager() {
        DBManager();     
    }
    
    public void login() {
    	myAccount = new AccountController(this);
    }
    
    public void welcomeForm(User user, AccountController ac) {
    	myWelcome = new WelcomeForm( user, ac);
    }
    
    public void cancelTicketForm(User user) {
    	cancelTicket = new CancelTicketController(this, user);
    }
    
    public void makePayment(Movie movie, Showtime time, Seat seat, User user) {
    	this.myPayment = new MakePaymentController(this, movie, time, seat, user);
    }
    
    public void showMovies(User user) {
    	mySelection = new SelectionController(user, this);
    }

    public static void main(String[] args) {
        try {
//            RegularUser temp = new RegularUser();
//            RegisteredUser rTemp = new RegisteredUser("teddy", "bear", "yyc", "btc");
//            Movie newMovie = new Movie("lavagirl", 1);
//            //Showtime newTime = new Showtime(3);
//           // Ticket newTicket = new Ticket(newMovie, "12345", 2);
//            //Ticket addNewTicket = new Ticket(newMovie, "12", 3);
            //Coupon dummyCoup = new Coupon(rTemp);
        DatabaseManager myDatabase = new DatabaseManager();
        //myDatabase.getUserInfo();
       //myDatabase.validateLogin("armoon", "armoon");
        //myDatabase.readMovies();
        //myDatabase.readShowtime(newMovie);
        //myDatabase.readSeats(newTime);
        //myDatabase.getTicket(newTicket);
        //myDatabase.addTicket(addNewTicket);
        //myDatabase.getTicket(addNewTicket);
        //myDatabase.cancelTicket(newTicket);
        //myDatabase.addCoupon(dummyCoup);
        //myDatabase.checkCoupon("98410");
    } catch (Exception e) {
        e.printStackTrace();
    }
        
    }
    
    public ArrayList<Seat> getTakenSeats(Movie movie, Showtime time){
    	ArrayList<Seat> seats = new ArrayList<Seat>();
    	String number="";
    	try {
    		String sql = "SELECT seatNumber FROM SEAT WHERE showtimeID=(?) AND movie=(?)";
    		PreparedStatement pStat = connection.prepareStatement(sql);
    		pStat.setInt(1, time.getID());
    		pStat.setInt(2, movie.getID());
    		rs = pStat.executeQuery();
    		if(!rs.next()) {
    			System.out.println("All seats Available");
    		} else {
    			do {
    				number = rs.getString("seatNumber");
    				seats.add(new Seat(number));
    			}while(rs.next());
    		}
    	}catch(SQLException e) {
    		e.printStackTrace();
    	}
    	return seats;
    }

    public void deleteCoupon(String code){
        try {
            String sql = "DELETE FROM COUPON WHERE COUPON.couponID=(?)";
            PreparedStatement pStat = connection.prepareStatement(sql);
            pStat.setString(1, code);
            pStat.executeUpdate();
            System.out.println("Successfully Deleted Coupon");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public float checkCoupon(String code){
        String coupID;
        float amount=0;

        try {
            String sql = " SELECT * FROM COUPON WHERE COUPON.couponID=(?)";
            PreparedStatement pStat = connection.prepareStatement(sql);
            pStat.setString(1, code);
            rs = pStat.executeQuery();
            if(!rs.next()){
                System.out.println("No Coupon Found");
            } else{
                do {
                    coupID = rs.getString("couponID");
                    amount = rs.getFloat("amount");
                    
                    System.out.println("Coupon ID: "+coupID);
                    System.out.println("Amount: "+amount);
                } while (rs.next());

                deleteCoupon(coupID);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return amount;

    }

    public void addCoupon(Coupon coupon){
        try {
            String sql = "INSERT INTO COUPON(couponID, amount)"+ "VALUES(?,?)";
            PreparedStatement pStat = connection.prepareStatement(sql);
            pStat.setString(1, coupon.getCode());
            pStat.setFloat(2, coupon.getAmount());
            pStat.executeUpdate();
            
            
            System.out.println("Successfully Added Coupon");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<String> cancelTicket(String ticketID, User user){
    	int res;
    	String curr_time = "";
    	int ticket_time_id = -1;
    	String ticket_time = "";
    	ArrayList<String> results = new ArrayList<String>();
        try {
        	String sqlTime = "SELECT ticketTime FROM TICKET WHERE ticketID=(?)";
        	PreparedStatement pStatTime = connection.prepareStatement(sqlTime);
        	pStatTime.setString(1, ticketID);
        	rs = pStatTime.executeQuery();
        	if(!rs.next()) {
        		System.out.println("No Ticket Found");
        	} else {
        		do {
        			ticket_time_id = rs.getInt("ticketTime");
        		}while(rs.next());
        	}
        	
        	String sqlT = "SELECT time FROM SHOWTIMES WHERE showtimeID=(?)";
        	PreparedStatement pStatT = connection.prepareStatement(sqlT);
        	pStatT.setInt(1, ticket_time_id);
        	rs = pStatT.executeQuery();
        	if(!rs.next()) {
        		System.out.println("No SHowtime found");
        	}else {
        		do {
        			ticket_time = rs.getString("time");
        		}while(rs.next());
        	}
        	
        	
        	LocalDate obj = LocalDate.now();
        	LocalTime myObj = LocalTime.now();
        	
        	String [] ticketTime = ticket_time.split("-");
        	if(obj.getDayOfMonth()+3 >= Integer.parseInt(ticketTime[1])) {
        		results.add("time");
        		return results;
        	}
      	
        	
        	
            String sql = "DELETE FROM TICKET WHERE TICKET.ticketID=(?)";
            PreparedStatement pStat = connection.prepareStatement(sql);
            pStat.setString(1, ticketID);
            res= pStat.executeUpdate();
            if (res != 0 ) {
            	results.add("true");
            	Coupon temp = new Coupon(user);
            	results.add(temp.getCode());
            	addCoupon(temp);
            	return results;
            } else {
            	results.add("false");
            	return results;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            results.add("false");
            return results;
        }
        
    }

    public float addTicket(Ticket ticket, String code){
    	int movieID;
        int showtimeID = -1;
        float price = 10;
        float discount = 0;
        try {
            if(code != null){
             discount = checkCoupon(code);
            }
        	String check = "SELECT movieID FROM MOVIE WHERE name=(?) ";
        	PreparedStatement checker = connection.prepareStatement(check);
        	checker.setString(1, ticket.getMovie().getName());
        	rs = checker.executeQuery();
        	if( !rs.next()) {
        		System.out.println("No such movie found, cancelling ticket add...");
        	} else {
        		do {
        			movieID = rs.getInt("movieId");
        		}while(rs.next());
        		
        		String time = "SELECT showtimeID FROM SHOWTIMES WHERE movieID=(?) AND time=(?)";
        		PreparedStatement timer = connection.prepareStatement(time);
        		timer.setInt(1, movieID);
        		timer.setString(2, ticket.getTime().getTime());
        		rs = timer.executeQuery();
        		if(!rs.next()) {
        			System.out.println("No Showtime found, cancelling ticket add...");
        		}else {
        			do {
        				showtimeID = rs.getInt("showtimeID");
        			} while(rs.next());
        		}
        		ticket.setTicketID();
        		String sql = "INSERT INTO TICKET (ticketID, movieName, ticketTime) " + "VALUES(?,?, ?)";
                PreparedStatement pStat = connection.prepareStatement(sql);
                pStat.setString(1, ticket.getTicketID());
                pStat.setString(2, ticket.getMovie().getName());
                pStat.setInt(3, ticket.getTime().getID());
                
                
                String sql2 = "INSERT INTO SEAT (seatNumber, isTaken, showtimeID, ticketID, movie)" + "VALUES(?,?,?,?,?)";
                PreparedStatement pStat2 = connection.prepareStatement(sql2);
                pStat2.setString(1, ticket.getSeatNum().getSeatNumber());
                pStat2.setInt(2, 1);
                pStat2.setInt(3, showtimeID);
                pStat2.setInt(4,  Integer.parseInt(ticket.getTicketID()));
                pStat2.setInt(5, movieID);
                
                pStat.executeUpdate();
                
                pStat2.executeUpdate();
                
        		System.out.println("Payment Complete, Datbase Updated...");
        	}
        	
            
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return (price - discount);
    }

    public void getTicket(Ticket myTicket){
        String movieName = "";
        String ticketID = "";
        String showtimeID= "";
        String seatNum = "";
        String showtime = "";
        try {
            String sql = "SELECT TICKET.ticketID,TICKET.movieName,SEAT.seatNumber, SEAT.showtimeID FROM TICKET,SEAT WHERE TICKET.ticketID=(?) AND SEAT.ticketID=(?)";
            PreparedStatement pStat = connection.prepareStatement(sql);
            pStat.setString(1, myTicket.getTicketID());
            pStat.setString(2, myTicket.getTicketID());
            rs = pStat.executeQuery();
            if(!rs.next()){
                System.out.println("No Tickets Found ");
            }else{
                do {
                    ticketID = rs.getString("ticketID");
                    movieName = rs.getString("movieName");
                    seatNum = rs.getString("seatNumber");
                    showtimeID = rs.getString("showtimeID");
                    System.out.println("Ticket ID: "+ticketID);
                    System.out.println("Movie Name: " +movieName);
                    System.out.println("Seat Number: "+seatNum);
                } while (rs.next());
            }

            String sql2 = "SELECT SHOWTIMES.time FROM SHOWTIMES WHERE SHOWTIMES.showtimeID=(?)";
            PreparedStatement pStat2 = connection.prepareStatement(sql2);
            pStat2.setString(1, showtimeID);
            rs = pStat2.executeQuery();
            if(!rs.next()){
                System.out.println("No showtimes found for that Showtime ID");
            } else{
                do {
                    showtime = rs.getString("time");
                    System.out.println("Showtime: "+showtime);
                } while (rs.next());
            }
            
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void readSeats(Showtime myTime){
        String seat = "";
        try {
            String sql = "SELECT * FROM SEAT WHERE SEAT.showtimeID=(?)";
            PreparedStatement pStat = connection.prepareStatement(sql);
            pStat.setInt(1, myTime.getID());
            rs = pStat.executeQuery();
            if(!rs.next()){
                System.out.println("No Seats Found");
            }else{
                System.out.println("Showtime: " + myTime.getID());
                do {
                    seat = rs.getString("seatNumber");
                    System.out.println("Seat: " + seat);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<Showtime> readShowtime(Movie myMovie){
    	ArrayList<Showtime> times = new ArrayList<Showtime>();
        String time="";
        int showtimeID= -1;
        try {
            String sql = "SELECT * FROM SHOWTIMES WHERE SHOWTIMES.movieID=(?)";
            PreparedStatement pStat = connection.prepareStatement(sql);
            pStat.setInt(1, myMovie.getID());
            rs = pStat.executeQuery();
            if(!rs.next()){
                System.out.println("No Showtimes Found");
            }else{
                System.out.println("Movie: " + myMovie.getName());
                do {
                    time = rs.getString("time");
                    showtimeID = rs.getInt("showtimeID");
                    times.add(new Showtime(showtimeID, time));
                    System.out.println("Time: "+ time);
                    System.out.println("ShowtimeID: "+showtimeID);
                } while (rs.next());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return times;
    }

    public ArrayList<Movie> readMovies(){
    	ArrayList<Movie> movies = new ArrayList<Movie>();
        String movie = "";
        String timing = "";
        String id = "";
        String seat= "";
        try {
            String sql = "SELECT * FROM MOVIE";
            rs = st.executeQuery(sql);
            if(!rs.next()){
                System.out.println("No Movies found");
            } else{
                do {
                    movie = rs.getString("name");
                    //timing = rs.getString("time");
                    id = rs.getString("movieId");
                    //seat = rs.getString("seatNumber");
                    movies.add(new Movie(movie, Integer.parseInt(id)));
                    System.out.println("Name: " + movie);
                    //System.out.println("Time: "+timing);
                    System.out.println("Movie id: "+id);
                   // System.out.println("SeatNum: "+seat);
                } while (rs.next());
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return movies;
    }

    /**
     * 
     */
    private String key;

    /**
     * 
     */
    //public void Attribute1;

    public void DBManager() {
		username = "root";
		passcode = "root";

		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/theatre", username, passcode);
			System.out.println("CONNECTED TO DB \n");
			st = connection.createStatement();
		} catch (Exception e) {
			System.out.println("Cannot connect to DB \n");
			e.printStackTrace();
		}

    }
    

    /**
     * @param user 
     * @return
     */
    public void addNewAccount(User user) {
        
    }

    public RegisteredUser getUserInfo(String nameUser){
        String username = "";
        String name = "";
        String addy = "";
        String card = "";

        try {
            
        String sql = "SELECT username,name, address, cardInfo FROM REGISTERED_USER WHERE username=(?)";
        PreparedStatement pStat = connection.prepareStatement(sql); 
        pStat.setString(1, nameUser);
        rs = pStat.executeQuery();
        if(!rs.next()){
            System.out.println("No user found!");
        } else{
        	do {
        		username = rs.getString("username");
                name = rs.getString("name");
                addy = rs.getString("address");
                card = rs.getString("cardInfo");             
                 System.out.println(rs.getString("username"));
                 //System.out.println(rs.getString("password"));
                 System.out.println(rs.getString("name"));
                 System.out.println(rs.getString("cardInfo"));
        	}
        while(rs.next());
            
            // System.out.println(rs.getString("tickets"));
        
    }
    } catch (SQLException e) {
        e.printStackTrace();
    }
        RegisteredUser temp = new RegisteredUser(username, name, addy, card);
        System.out.println(temp.getEmail());
        System.out.println(name);
        System.out.println("IN DB");
    return temp;
    }

    

    /**
     * @param ticket 
     * @return
     */
    public void deleteTicket(Ticket ticket) {
        // TODO implement here
        
    }

    

    

    /**
     * @param coupon 
     * @return
     */
    public void deleteCoupon(Coupon coupon) {
        // TODO implement here
        
    }

    /**
     * @param username 
     * @param password 
     * @return
     */
    public boolean validateLogin(String username, String password) {
        String name = "";
        String myUsername = "";
        String address = "";
        String card = "";
        String tickets ="";
        //username = "armoon";
        //password = "armoon";
        boolean isValid = false;
        try {
            String sql = "SELECT * FROM theatre.registered_user WHERE username=(?) AND password=(?)";
            PreparedStatement pStat = connection.prepareStatement(sql);
            pStat.setString(1, username);
            pStat.setString(2, password);
            rs = pStat.executeQuery();
            if(!rs.next()){
                System.out.println("No User Found\n");
                return isValid;
            } else{                
                    do {
                        myUsername = rs.getString("username");
                        name = rs.getString("name");
                        address = rs.getString("address");
                        card = rs.getString("cardInfo");
                        tickets = rs.getString("tickets");
                    } while (rs.next());
                isValid = true;
            }
            

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return isValid;
    }

    /**
     * @param username 
     * @return
     */
    public boolean duplicateUsername(String username) {
        // TODO implement here
        return false;
    }

}