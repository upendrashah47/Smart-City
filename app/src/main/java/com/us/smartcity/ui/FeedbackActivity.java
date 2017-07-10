package com.us.smartcity.ui;

/**
 * Created by Bhaskar on 02-11-2015.
 */

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Button;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.us.smartcity.R;

public class FeedbackActivity extends AppCompatActivity {
    Button button;
    private RatingBar ratingBar;
    private TextView txtRatingValue;
    private Button btnSubmit;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback_activity);
        addListenerOnRatingBar();
        addListenerOnButton();
    }

    private void addListenerOnButton() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
    }

    private void addListenerOnRatingBar() {
        ratingBar = (RatingBar) findViewById(R.id.ratingBar);
        txtRatingValue = (TextView) findViewById(R.id.txtRatingValue);

        //if rating value is changed,
        //display the current rating value in the result (textview) automatically
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            public void onRatingChanged(RatingBar ratingBar, float rating,
                                        boolean fromUser) {

                txtRatingValue.setText(String.valueOf(rating));

            }
        });
    }

    public void onClick(View v) {


        switch (v.getId()) {

            case R.id.send:
                Intent intent = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent);
                break;
            case R.id.cancle:
                Intent intent1 = new Intent(getBaseContext(), HomeActivity.class);
                startActivity(intent1);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }


}
