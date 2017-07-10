package com.us.smartcity.ui;

/**
 * Created by Bhaskar on 21-10-2015.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.Menu;
import android.view.View;

import com.us.smartcity.R;


public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private CardView cdvCitizen;
    private CardView cdvKnowYourCity;
    private CardView cdvOneTouchHelpline;
    private CardView cdvDownloadForm;
    private CardView cdvUserGuide;
    private CardView cdvFeedback;
    private CardView cdvChangePassword;
    private CardView cdvCityNewsEvents;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        context = HomeActivity.this;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.cdvCitizen:
                intent = new Intent(context, CitizenFragment.class);
                startActivity(intent);
                break;
            case R.id.cdvKnowYourCity:
                intent = new Intent(context, KnowYourCityFragment.class);
                startActivity(intent);
                break;
            case R.id.cdvCityNewsEvents:
                intent = new Intent(context, HolidayCalenderActivity.class);
                startActivity(intent);
                break;
            case R.id.cdvDownloadForm:
                intent = new Intent(context, DownloadFormActivity.class);
                startActivity(intent);
                break;
            case R.id.cdvFeedback:
                intent = new Intent(context, FeedbackActivity.class);
                startActivity(intent);
                break;
            case R.id.cdvOneTouchHelpline:
                intent = new Intent(context, OneTouchHelpFragment.class);
                startActivity(intent);
                break;
            case R.id.cdvUserGuide:
                intent = new Intent(context, UserGuideActivity.class);
                startActivity(intent);
                break;
            case R.id.cdvChangePassword:
                intent = new Intent(context, ChangePasswordActivity.class);
                startActivity(intent);
                break;
        }
    }
}




