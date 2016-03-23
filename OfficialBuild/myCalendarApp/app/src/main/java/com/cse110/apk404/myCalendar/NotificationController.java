package com.cse110.apk404.myCalendar;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

public class NotificationController {

    private Context context;
    private AlarmManager alarmManager;
    private ArrayList<PendingIntent> list;

    public NotificationController(Context context) {
        this.context = context;
        this.alarmManager = (AlarmManager)context.getSystemService(context.ALARM_SERVICE);
        this.list = new ArrayList<>();
    }

    public void scheduleNotification() {

        for (int i = 0; i < list.size(); i++)
            alarmManager.cancel(list.get(i));
        list.clear();

        StaticEventList staticEventList = EventListHandler.getStaticList();
        ArrayList<StaticEvent> list1 = staticEventList.getList();

        for (int i = 0; i < list1.size(); i++) {
            StaticEvent event = list1.get(i);
            long id = event.getId();
            Calendar time = event.getStartTime();
            Calendar curr = Calendar.getInstance();
            curr.add(Calendar.MINUTE, 5);

            if (time.after(curr)) {
                long timeToNotify = time.getTimeInMillis() - 1000 * 60 * 5;
                Intent notifyEvent = new Intent(context, NotificationReceiver.class);
                notifyEvent.putExtra("id", id);
                notifyEvent.putExtra("isStatic", true);
                final int _id = (int) System.currentTimeMillis();
                PendingIntent notifyIntent = PendingIntent.getBroadcast(context, _id, notifyEvent, 0);
                list.add(notifyIntent);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, timeToNotify, notifyIntent);
            }
        }

//        DynamicEventList deadLineList = EventListHandler.getDeadlineList();
//        ArrayList<DynamicEvent> deadlineList = deadLineList.getList();
//        HashMap<Long, DynamicEvent> list2 = new HashMap<>();
//        for (int i = 0; i < deadlineList.size(); i++ ) {
//            DynamicEvent curr = deadlineList.get(i);
//            Long id = curr.getId();
//            if (!list2.containsKey(id))
//                list2.put(id, curr);
//        }

        ArrayList<DynamicEvent> list2 = EventListHandler.getDeadlineList().getList();
        Log.e("sizeOfList", ""+list2.size());
        for (DynamicEvent event : list2) {
            Calendar curr = Calendar.getInstance();
            long id = event.getId();
            Calendar deadline = event.getDeadline();

            // different cases to determine when to notify
            int size = 0;
            long[] toSubtract = new long[3];
            curr.add(Calendar.MINUTE, 5);
            if (deadline.after(curr)) {
                toSubtract[0] = 1000 * 60 * 5;
                curr.add(Calendar.MINUTE, -5);
                curr.add(Calendar.HOUR, 1);
                size++;
            }
            if (deadline.after(curr)) {
                toSubtract[1] = 1000 * 60 * 60;
                curr.add(Calendar.HOUR, 9);
                size++;
            }
            if (deadline.after(curr)) {
                toSubtract[2] = 1000 * 60 * 60 * 10;
                size++;
            }

            // send the notification alarm
            for (int j = 0; j < size; j++) {
                long timeToNotify = deadline.getTimeInMillis() - toSubtract[j];
                Intent notifyEvent = new Intent(context, NotificationReceiver.class);
                notifyEvent.putExtra("id", id);
                notifyEvent.putExtra("isStatic", false);
                notifyEvent.putExtra("due", j);
                final int _id = (int) System.currentTimeMillis();
                PendingIntent notifyIntent = PendingIntent.getBroadcast(context, _id, notifyEvent, 0);
                Log.e("inside schedule dl", "" + id); /////////////////////////////////////////////////////////////
                list.add(notifyIntent);
                alarmManager.set(AlarmManager.RTC_WAKEUP, timeToNotify, notifyIntent);
            }
        }

        Log.e("after size is ", ""+list.size());
    }
}
