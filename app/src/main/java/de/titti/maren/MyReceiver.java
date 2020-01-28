package de.titti.maren;

import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class MyReceiver extends BroadcastReceiver {

    NotificationManagerCompat notificationManager;
    //    DateTime anniversary = new DateTime(2013, 1, 4,0,0);   //real value
    DateTime anniversary = new DateTime(2019, 2, 28,0,0);  //testvalue
    DateTime currentTime = new DateTime();

    @Override
    public void onReceive(Context context, Intent intent) {

//intent to open App by clicking on notification/////
        // Create an explicit intent for an Activity in your app
        Intent openIntent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, openIntent, 0);


        Period p = calculateTime(anniversary, currentTime);

        if(p.getMonths() == 0){
            sendYearNotification(context, pendingIntent);
        }
        else if(p.getDays() == 0){
            sendMonthNotification(context,pendingIntent);
        }






    }

    private void sendMonthNotification(Context context, PendingIntent pendingIntent){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "test")
                .setSmallIcon(android.R.drawable.btn_star) //icon
                .setContentTitle("Juhu") //Title
                .setContentText("Ein weiterer glücklicher Monat mit dir") //Content
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(01, builder.build());
    }
    private void sendYearNotification(Context context, PendingIntent pendingIntent){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "test")
                .setSmallIcon(android.R.drawable.btn_star) //icon
                .setContentTitle("Jahrestag") //Title
                .setContentText("Ein ganzes glückliches Jahr mit dir") //Content
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(02, builder.build());
    }
    private Period calculateTime(
            DateTime anniversary,
            DateTime currentDate) {
        return new Period(anniversary, currentDate, PeriodType.yearMonthDay());
    }
}
