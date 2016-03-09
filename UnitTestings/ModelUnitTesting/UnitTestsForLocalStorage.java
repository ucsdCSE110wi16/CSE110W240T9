import android.content.Context;
import android.util.Log;

import com.cse110.apk404.myCalendar.CalendarObject;
import com.cse110.apk404.myCalendar.CalendarObjectList;
import com.cse110.apk404.myCalendar.CalendarObjectListInputStream;
import com.cse110.apk404.myCalendar.CalendarObjectListOutputStream;
import com.cse110.apk404.myCalendar.EventListHandler;

import java.util.AbstractCollection;
import java.util.ArrayList;

/**
 * A class for unit testing of dababase in local storage, where due to the complexity of
 * the situations about our object storage (e.g., fours kinds of event list objects), it
 * is impossible to do unit testings for each senarios. Yet, based on our manually-tested
 * beharior-driven testing process via GUI, we pass all tests. Including situations like:
 * 1. create an static event, and kill the app; restart app, see whether the previous event is there.
 * 2. create an dynamic event, and kill the app; restart app, see whether the previous event is there. 
 *
 *
 * Some unit tests are available in form of codes, as follows.
 */
public class UnitTestsForLocalStorage {

	public static void main(String[] args) {

		String filename = null;  // this is for filenames


		// a fake Context object
		Context context = null;

		// COMPLILE ERROR DUE TO CONTEXT has explanation below
		// unit testing for output, write a file for storing object via local storage 
		try {
			CalendarObjectListOutputStream out = new CalendarObjectListOutputStream(filename, context);
			ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> list = new ArrayList<>();
			EventListHandler.initStaticList();
			EventListHandler.initDynamicList();
			list.add(EventListHandler.getStaticList());
			list.add(EventListHandler.getDynamicList());
			list.add(EventListHandler.getDeadlineList());
			list.add(EventListHandler.getFinishedDynamicList());
			out.writeList(list.get(0));
			out.writeList(list.get(1));
			out.writeList(list.get(2));
			out.writeList(list.get(3));
		} catch (Exception ee) {
			Log.e("error1", ee.toString());
		}
		
		
		// THE COMPILE ERROR IS FOR CONTEXT OBJECT!!!
		// unit testing for input, read from a local file to get objects as user's data
		// context is of type Context in Android, this code is tested, actually, 
		// by inserting into android codes of our apps; here it is nonsense to create context object 
		try {
			CalendarObjectListInputStream in = new CalendarObjectListInputStream(filename, context);
			ArrayList<CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject>> list1 = new ArrayList<>();
			CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> l1 = in.readList();
			list1.add(l1);
			CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> l2 = in.readList();
			list1.add(l2);
			CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> l3 = in.readList();
			list1.add(l3);
			CalendarObjectList<? extends AbstractCollection<? extends CalendarObject>, ? extends CalendarObject> l4 = in.readList();
			list1.add(l4);

		} catch (Exception e) {
			Log.e("error", e.getMessage());
		}
	}

}
