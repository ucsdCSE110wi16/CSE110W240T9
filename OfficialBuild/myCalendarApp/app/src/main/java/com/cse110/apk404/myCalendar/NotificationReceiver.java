package com.cse110.apk404.myCalendar;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.TaskStackBuilder;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

public class NotificationReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        long id = intent.getLongExtra("id", 0);
        CalendarEvent event = EventListHandler.getEventById(id);
        String name = "";
        if (event != null)
            name = event.getName();
        boolean isStatic = intent.getBooleanExtra("isStatic", true);
        String description = "";
        if (isStatic) {
            description = "will start in 5 minutes";
        } else {
            int due = intent.getIntExtra("due", 0);
            switch (due) {
                case 0: description = "is due in five minutes"; break;
                case 1: description = "is due in one hour"; break;
                case 2: description = "is due in ten hours"; break;
            }

        }

        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(context)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle(name)
                        .setContentText(description);

        // Creates an explicit intent for an Activity in your app
        Intent resultIntent = new Intent(context, DetailActivity.class);
        resultIntent.putExtra("ID", id);

        // The stack builder object will contain an artificial back stack for the started Activity.
        // This ensures that navigating backward from the Activity leads out of
        // your application to the Home screen.
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(context);

        // Adds the back stack for the Intent (but not the Intent itself)
        stackBuilder.addParentStack(DetailActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0, PendingIntent.FLAG_UPDATE_CURRENT);

        // Receive notification manager and message builder
        mBuilder.setContentIntent(resultPendingIntent);
        NotificationManager mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // mId allows you to update the notification later on.
        mNotificationManager.notify((int) id, mBuilder.build());
    }

}
