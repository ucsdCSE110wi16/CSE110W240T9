import java.io.IOException;
import java.util.AbstractCollection;
import java.util.ArrayList;

/**
 * A mixture of unit tests for server side codes. Due to the deployment of server side code,
 * A complete unit test is not available, and most of the tests are behavior driven test 
 * by manually operations. E.g., creat event, register, login, via GUI.
 * 
 * A list of manual tests that are passed would be (just a few):
 * 1. test whether create new user successfully
 * 2. test whether user's data is stored online successfully
 * 3. test whether check user's password return the correct password
 * 4. test whether update user's data successfully
 * 5. test whether download user data successfully
 * 6. test whether find out the correct user information given user's email
 * 
 * And in this class are some extra unit tests that convince us the relevant algorithm works
 */
public class UnitTestsForCalendarServer {

	public static void main(String[] args) {
		
		// testing whether byte array converted to object correctly 
		String s = "-84,-19,0,5,115,114,0,19,106,97,118,97,46,117,116,105,108,46,65,114,114,97,121,76,105,115,116,120,-127,-46,29,-103,-57,97,-99,3,0,1,73,0,4,115,105,122,101,120,112,0,0,0,3,119,4,0,0,0,3,115,114,0,15,83,116,97,116,105,99,69,118,101,110,116,76,105,115,116,0,0,0,0,0,0,0,1,2,0,1,76,0,10,115,116,97,116,105,99,76,105,115,116,116,0,21,76,106,97,118,97,47,117,116,105,108,47,65,114,114,97,121,76,105,115,116,59,120,112,115,113,0,126,0,0,0,0,0,0,119,4,0,0,0,0,120,113,0,126,0,4,113,0,126,0,4,120,";
		byte[] bytes = ByteArrayViaString.stringToByteArray(s);
		System.out.println("length: "+bytes.length);
		Object o = null;
		try {
			o = Serializer.deserialize(bytes);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		
		
		// testing whether create user correctly
		ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> list 
			= new ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>>();
		EventListHandler.initStaticList();
	    CalendarUser user = null;
		try {
			user = CalendarDB.createUser("123", "456", Serializer.serialize(list));
		} catch (IOException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}	
	    byte[] b = user.getData();
		System.out.println(bytes.length);
		
		
		
		
		// testing whether serialization works and convert object to string and vice versa
		String str = null;
		try {
			str = ByteArrayViaString.byteArrayToString(Serializer.serialize(list));
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		String str1 = str + "";
		byte[] result = ByteArrayViaString.stringToByteArray(str1);
		Object out = null;
		try {
			out = Serializer.deserialize(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		ArrayList<String> list_res = (ArrayList<String>) out;
	
	}

}
