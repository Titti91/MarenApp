package de.titti.maren;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    TextView year;
    TextView month;
    TextView day;
    TextView yearText;
    TextView monthText;
    TextView dayText;

    TextView outroText;

    DateTime anniversary = new DateTime(2013, 1, 4,0,0);   //real value
//    DateTime anniversary = new DateTime(2019, 12, 7,0,0);  //testvalue
    DateTime currentTime = new DateTime();



    ///set Alarm/////
AlarmManager alarmMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        alarmMgr = (AlarmManager) getSystemService(ALARM_SERVICE);


// Hopefully your alarm will have a lower frequency than this!
        Intent intent = new Intent(this, MyReceiver.class);

        PendingIntent alarmIntent = PendingIntent.getBroadcast(this,112,intent,PendingIntent.FLAG_UPDATE_CURRENT);
        if (alarmIntent != null)
            alarmMgr.cancel(alarmIntent);
/*
1st Param : Context
2nd Param : Integer request code
3rd Param : Wrapped Intent
4th Intent: Flag
*/

//////////////real mgr with checking every Day/////
//        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
//                SystemClock.elapsedRealtime() + AlarmManager.INTERVAL_DAY,
//                AlarmManager.INTERVAL_DAY, alarmIntent);


        ////////////sample mgr with checking every minute/////
        alarmMgr.setInexactRepeating(AlarmManager.ELAPSED_REALTIME_WAKEUP,
                SystemClock.elapsedRealtime() + 15*60*1000,      //startzeit (30*1000)
                12*60*60*1000, alarmIntent);                           //intervall 30*1000


        outroText = findViewById(R.id.outroText);

        int[] outros = {R.string.outro0_text, R.string.outro1_text, R.string.outro2_text, R.string.outro3_text};

        Random r = new Random();
        outroText.setText(outros[r.nextInt(outros.length)]);

        year = findViewById(R.id.yearValue);
        month = findViewById(R.id.monthValue);
        day = findViewById(R.id.dayValue);
        yearText = findViewById(R.id.yearText);
        monthText = findViewById(R.id.monthText);
        dayText = findViewById(R.id.dayText);

        Period p = calculateTime(anniversary, currentTime);

        if(p.getYears() == 1){
            yearText.setText("Jahr");
        }
        if(p.getMonths() == 1){
            monthText.setText("Monat");
        }
        if(p.getDays() == 1){
            dayText.setText("Tag");
        }

        year.setText(p.getYears()+"");
        month.setText(p.getMonths()+"");
        day.setText(p.getDays()+"");

    }

    public Period calculateTime(
            DateTime anniversary,
            DateTime currentDate) {
        return new Period(anniversary, currentDate, PeriodType.yearMonthDay());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
