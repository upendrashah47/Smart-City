package com.us.smartcity.ui;

/**
 * Created by Bhaskar on 02-11-2015.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.us.smartcity.R;

public class ForgotPasswordActivity extends AppCompatActivity {

    private Context context;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgot_password_activity);
        context = ForgotPasswordActivity.this;
    }


    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send:
                intent = new Intent(context, WeatherActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.cancle:
                intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }


}
