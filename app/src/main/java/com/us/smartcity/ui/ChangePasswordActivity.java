package com.us.smartcity.ui;

/**
 * Created by Bhaskar on 21-11-2015.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Button;
import android.view.View;

import com.us.smartcity.R;

public class ChangePasswordActivity extends AppCompatActivity {
    private Context context;
    private Intent intent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_activity);
        context = ChangePasswordActivity.this;
    }

    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.btnConfirm:
                intent = new Intent(context, LoginActivity.class);
                startActivity(intent);
                break;
            case R.id.btnCancle:
                intent = new Intent(context, HomeActivity.class);
                startActivity(intent);
                break;
        }
    }
}

