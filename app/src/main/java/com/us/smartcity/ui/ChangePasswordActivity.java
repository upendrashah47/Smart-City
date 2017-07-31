package com.us.smartcity.ui;

/**
 * Created by Bhaskar on 21-11-2015.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.us.smartcity.R;

public class ChangePasswordActivity extends AppCompatActivity implements View.OnClickListener {
    private Context context;
    private Intent intent;
    private EditText edtOldPassword;
    private EditText edtNewPassword;
    private EditText edtConfirmPassword;

    private Button btnConfirm;
    private Button btnCancle;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password_activity);
        context = ChangePasswordActivity.this;

        findViewById();
    }

    @Override
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

    public void findViewById() {

        edtOldPassword = (EditText) findViewById(R.id.edtOldPassword);
        edtNewPassword = (EditText) findViewById(R.id.edtNewPassword);
        edtConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
        btnConfirm = (Button) findViewById(R.id.btnConfirm);
        btnCancle = (Button) findViewById(R.id.btnCancle);

        btnConfirm.setOnClickListener(this);
        btnCancle.setOnClickListener(this);
    }
}

