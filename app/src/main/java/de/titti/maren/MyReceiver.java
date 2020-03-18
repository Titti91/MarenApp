package de.titti.maren;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationManagerCompat;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class MyReceiver extends BroadcastReceiver {

    NotificationManagerCompat notificationManager;
    private NotificationManager notifManager;
        DateTime anniversary = new DateTime(2013, 1, 4,0,0);   //real value
//    DateTime anniversary = new DateTime(2019, 2, 18,0,0);  //testvalue
    DateTime currentTime = new DateTime();

    @Override
    public void onReceive(Context context, Intent intent) {
        //set notification chanel for android above 8
        if (notifManager == null) {
            notifManager = (NotificationManager)context.getSystemService(Context.NOTIFICATION_SERVICE);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel mChannel = notifManager.getNotificationChannel("love");
            if (mChannel == null) {
                mChannel = new NotificationChannel("love", "Love is in the air", importance);
//                mChannel.enableVibration(true);
//                mChannel.setVibrationPattern(new long[]{100, 200, 300, 400, 500, 400, 300, 200, 400});
                notifManager.createNotificationChannel(mChannel);
            }

        }


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
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "love")
                .setSmallIcon(R.drawable.notification_icon) //icon
                .setContentTitle("Hallo mein Schatz") //Title
                .setContentText("Heute beginnt ein weiterer glücklicher Monat mit dir!!!") //Content
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .setAutoCancel(true);

        notificationManager = NotificationManagerCompat.from(context);

        // notificationId is a unique int for each notification that you must define
        notificationManager.notify(01, builder.build());
    }
    private void sendYearNotification(Context context, PendingIntent pendingIntent){
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, "love")
                .setSmallIcon(R.drawable.notification_icon) //icon android.R.drawable.btn_star
                .setContentTitle("Ein ganz besonderer Tag") //Title
                .setContentText("Auf ein weiteres wundervolles Jahr!!!") //Content
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
