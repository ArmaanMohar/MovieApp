import Controller.AccountController;
import Database.DatabaseManager;

public class MovieApp {
    private DatabaseManager DB;
    private AccountController account;
    private boolean loggedIn;

    public MovieApp(){
        this.DB = new DatabaseManager();
    }

    public void run(){
        DB.login();
    }

    public static void main(String[] args) {
        MovieApp app = new MovieApp();
        app.run();
    }
    
}
