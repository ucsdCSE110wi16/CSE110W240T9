import java.util.ArrayList;

/**
 * A class for storing byte array via string to pass data from server side
 * to client side (Android device)
 */
public class ByteArrayViaString {

	/*
	 * convert byte array to string
	 */
	public static String byteArrayToString(byte[] bytes) {
		
		int size = bytes.length;
		String result = "";
		
		for (int i = 0; i < size; i++) {
			result += bytes[i];
			result += ",";
		}
		
		return result;
	}
	
	/*
	 * convert string to byte array
	 */
	public static byte[] stringToByteArray(String input) {
		
		ArrayList<Byte> array = new ArrayList<>();
		String tempString = "";
		for (int i = 0; i < input.length(); i++) {
			char tempChar = input.charAt(i);
			if (tempChar == ',') {
				array.add(Byte.parseByte(tempString));
				tempString = "";
			} else{
				tempString += tempChar;
			}		
		}
		
		byte[] result = new byte[array.size()];
		for (int i = 0; i < array.size(); i++)
			result[i] = array.get(i);
		
		return result;
	}
}
