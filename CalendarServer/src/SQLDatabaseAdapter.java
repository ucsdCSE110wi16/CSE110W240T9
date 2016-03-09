
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * A controller class for handling SQLDatabase.
 * Containing methods to create table, check user's email and password, upload and
 * download user's data (byte array of event lists)
 */
public class SQLDatabaseAdapter {

    /*
     * Create SQL table  
	 */
    public static void createTableIfNotExist(Connection con) {

        String tableName = "calendar_user";
        Statement st = null;

        String createQuery = "CREATE TABLE IF NOT EXISTS " + tableName + 
                " (email text PRIMARY KEY, password text, data bytea)";

        try {
            st = con.createStatement();
            st.executeUpdate(createQuery);
        } catch (SQLException e) {
        	e.printStackTrace();
        }
    }

    /*
     * return whether a user has registered
     */
    public static boolean checkUserProfile(String email, Connection con) {

        String tableName = "calendar_user";
        ResultSet res = null;
        boolean notFound = false;
        PreparedStatement st = null;

        String selectQuery = "SELECT COUNT(*) FROM " + tableName + " WHERE email = ?";

        try {
            st = con.prepareStatement(selectQuery);
            st.setString(1, email);
            res = st.executeQuery();
            res.next();
            if (res.getInt(1) == 0)
            	notFound = true;
        } catch (SQLException e) {
        	e.printStackTrace();
        }

        return notFound;
    }

    /*
     * add a new user to the database
     */
    public static void addUser(CalendarUser user, Connection con) {

        String tableName = "calendar_user";
        String email = user.getEmail();
        String password = user.getPassword();
        String insertQuery = "INSERT INTO " + tableName + " (email, password, data) VALUES (?, ?, ?)";
        byte[] data = user.getData();
        
        try {
            PreparedStatement st = con.prepareStatement(insertQuery);
            st.setString(1, email);
            st.setString(2, password);
            st.setBytes(3, data);
            st.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }

    /*
     * retrieve event list object as byte array
     */
    public static byte[] retrieveLists(String email, Connection con) {
        String tableName = "calendar_user";
        String selectQuery = "SELECT data FROM " + tableName + " WHERE email = ?";
        PreparedStatement st = null;
        ResultSet res = null;

        try {
            st = con.prepareStatement(selectQuery);
            st.setString(1, email);
            res = st.executeQuery();
            res.next();
            byte[] bytes = res.getBytes(1);
            return bytes;
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return null;
    }

    /*
     * return whether a user's password is correct
     */
    public static boolean checkPassword(String email, String password, Connection con) {
        String tableName = "calendar_user";
        PreparedStatement st = null;
        String selectQuery = "SELECT password FROM " + tableName + " WHERE email = ?";

        ResultSet res = null;
        boolean correct = false;

        try {
            st = con.prepareStatement(selectQuery);
            st.setString(1, email);
            res = st.executeQuery();
            res.next();
            String pass = res.getString(1);
            if (pass.equals(password))
                correct = true;
        } catch (Exception e) {
        	e.printStackTrace();
        }

        return correct;
    }

    /*
     * update the specified user's event list (byte array as serialized object)
     */
    public static void updateLists(String email, Connection con, byte[] data) {
        String tableName = "calendar_user";
        String updateQuery = "UPDATE " + tableName + " SET data = ? WHERE email = ?";

        try {
            PreparedStatement st = con.prepareStatement(updateQuery);
            st.setBytes(1, data);
            st.setString(2, email);
            st.executeUpdate();
        } catch (Exception e) {
        	e.printStackTrace();
        }
    }
    
    /*
     * helper method to get connection to the database on server side
     */
	public static Connection getConnection() throws URISyntaxException, SQLException {
	    URI dbUri = new URI(System.getenv("DATABASE_URL"));

	    String username = dbUri.getUserInfo().split(":")[0];
	    String password = dbUri.getUserInfo().split(":")[1];
	    String dbUrl = "jdbc:postgresql://" + dbUri.getHost() + ':' + dbUri.getPort() + dbUri.getPath();

	    return DriverManager.getConnection(dbUrl, username, password);
	}
}
