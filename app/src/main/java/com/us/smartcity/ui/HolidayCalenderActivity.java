package com.us.smartcity.ui;

/**
 * Created by Bhaskar on 02-11-2015.
 */

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;
import android.widget.CalendarView;

import com.us.smartcity.R;

public class HolidayCalenderActivity extends AppCompatActivity {

    CalendarView calendar;

    @Override

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //sets the main floating_button of the activity
        setContentView(R.layout.holiday_calender_activity);

        //initializes the calendarview

        // initializeCalendar();

    }

}



