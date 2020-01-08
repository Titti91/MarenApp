package de.titti.maren;

import android.os.Bundle;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import org.joda.time.DateTime;
import org.joda.time.Period;
import org.joda.time.PeriodType;

public class MainActivity extends AppCompatActivity {

    TextView year;
    TextView month;
    TextView day;
    TextView yearText;
    TextView monthText;
    TextView dayText;


//    DateTime anniversary = new DateTime(2013, 1, 4,0,0);   //real value
    DateTime anniversary = new DateTime(2019, 12, 7,0,0);  //testvalue
    DateTime currentTime = new DateTime();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

       FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        year = (TextView)this.findViewById(R.id.yearValue);
        month = (TextView)this.findViewById(R.id.monthValue);
        day = (TextView)this.findViewById(R.id.dayValue);
        yearText = (TextView)this.findViewById(R.id.yearText);
        monthText = (TextView)this.findViewById(R.id.monthText);
        dayText = (TextView)this.findViewById(R.id.dayText);

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
