
/**
 * A class for storing user information
 */
public class CalendarUser {

	private String email = null;
	private String password = null;
	private byte[] data = null;
	
	/*
	 * create a new user, with data as byte array for user's event lists
	 */
	public CalendarUser(String email, String password, byte[] data) {
		this.setEmail(email);
		this.setPassword(password);
		this.setData(data);
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public byte[] getData() {
		return data;
	}

	public void setData(byte[] data) {
		this.data = data;
	}
}
