

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.AbstractCollection;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalendarS
 */
@WebServlet("/CalendarS")
public class CalendarS extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarS() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		PrintWriter out = response.getWriter();

		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String backup = request.getParameter("backup");
		String command = request.getParameter("command");
		        	
		if ((command != null) && (command.equals("checkWhetherUserExists"))) {
			
			Connection con = CalendarDB.initDBOnline();
			boolean notFound = SQLDatabaseAdapter.checkUserProfile(email, con);
			
			if (notFound) 
				out.append("userDoesNotExist");
			else
				out.append("userExists");
			
			out.close();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}
	
		
		if ((command != null) && (command.equals("createNewUser"))) {
		
			byte[] data = ByteArrayViaString.stringToByteArray(backup);
		    CalendarUser user = CalendarDB.createUser(email, password, data);
			
			Connection con = CalendarDB.initDBOnline();
			
			CalendarDB.addUser(user, con);
			
			out.append("success");
			out.close();
			
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}

		
		if (command.equals("checkPassword")) {
			
			Connection con = CalendarDB.initDBOnline();
			
			boolean correct = SQLDatabaseAdapter.checkPassword(email, password, con);
			if (correct)
				out.append("correctPassword");
			else 
				out.append("incorrectPassword");
			
			out.close();
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}
		
		if (command.equals("downloadData")) {
			
			Connection con = CalendarDB.initDBOnline();
			
			byte[] data = SQLDatabaseAdapter.retrieveLists(email, con);
			String output = ByteArrayViaString.byteArrayToString(data);
			out.append(output);

			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}
		
		if (command.equals("uploadData")) {
			Connection con = CalendarDB.initDBOnline();
			
			byte[] bytes = ByteArrayViaString.stringToByteArray(backup);
			SQLDatabaseAdapter.updateLists(email, con, bytes);
			
			out.append("sucess upload");
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return;
		}
		
		out.append("shit!!");
		out.close();
	}
}
