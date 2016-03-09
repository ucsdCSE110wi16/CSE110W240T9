
import java.sql.Connection;

/**
 * A class for the server to handle database
 */
public class CalendarDB {

	/*
	 * initialize the connection to the online database (e.g. create table stuff)
	 */
	public static Connection initDBOnline() {
		
		Connection con = null;
		try {
			Class.forName("org.postgresql.Driver");
			con = SQLDatabaseAdapter.getConnection();
		} catch (Exception e1) {
			// nothing
		}
		
        SQLDatabaseAdapter.createTableIfNotExist(con);
        
        return con;
    }
	
	/*
	 * create a user with user's email, password and data
	 */
	public static CalendarUser createUser(String email, String password, byte[] data) {		   
		return new CalendarUser(email, password, data);
	}

	/*
	 *  add user to the database
	 */
	public static void addUser(CalendarUser user, Connection con) {
        SQLDatabaseAdapter.addUser(user, con);
	}
}
