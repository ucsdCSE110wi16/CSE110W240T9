package com.cse110.apk404.myCalendar.eventListHandler;

import android.content.Context;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.AbstractCollection;

public class CalendarObjectListOutputStream {

	private ObjectOutputStream objOut;
	
	public CalendarObjectListOutputStream(String filename, Context context) throws IOException {
		FileOutputStream fo = context.openFileOutput(filename, Context.MODE_PRIVATE);;
		this.objOut = new ObjectOutputStream(fo);     
	}
	
	public boolean writeList(CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> list) {
		if (list == null) {
			return false;
		}
			
		boolean result = true; 
		try {
			this.objOut.writeObject(list);
			objOut.flush();
		} catch (IOException e) {
			result = false;
		}
		
	    return result;
	}
	
	public void close() throws IOException {
		this.objOut.close();
	}
}