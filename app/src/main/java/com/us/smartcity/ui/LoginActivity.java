package com.us.smartcity.ui;

/**
 * Created by Bhaskar on 21-10-2015.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.us.smartcity.R;
import com.us.smartcity.utils.Utils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    private Button btnLogin;
    private Button btnSignup;
    private EditText edtMobileNo;
    private EditText edtPassword;
    private Intent intent;
    private TextView txtForgotPassword;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_activity);
        context = LoginActivity.this;

        findViewById();
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnLogin:

                if (edtMobileNo.getText().toString().trim().equals("") || edtPassword.getText().toString().trim().equals("")) {
                    if (edtMobileNo.getText().toString().trim().equals("")) {
                        Utils.showAlert(context, Utils.getResourceString(context, R.string.alert), Utils.getResourceString(context, R.string.enterMobile), Utils.getResourceString(context, R.string.ok));

                    } else if (edtPassword.getText().toString().trim().equals("")) {
                        Utils.showAlert(context, Utils.getResourceString(context, R.string.alert), Utils.getResourceString(context, R.string.enterPassword), Utils.getResourceString(context, R.string.ok));

                    }
                } else if (edtMobileNo.getText().toString().trim().equals("9725611734") || edtPassword.getText().toString().trim().equals("upen")) {
                    Toast.makeText(context, "Thank you for login_activity Upen", Toast.LENGTH_SHORT).show();
                    intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else if (edtMobileNo.getText().toString().trim().equals("9662640428") || edtPassword.getText().toString().trim().equals("kinjal")) {
                    Toast.makeText(context, "Thank you for login_activity Kinjal", Toast.LENGTH_SHORT).show();
                    intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else if (edtMobileNo.getText().toString().trim().equals("7802805226") || edtPassword.getText().toString().trim().equals("rahul")) {
                    Toast.makeText(context, "Thank you for login_activity Rahul", Toast.LENGTH_SHORT).show();
                    intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(context, "You are not Registered", Toast.LENGTH_LONG).show();
                }
                finish();
                break;
            case R.id.btnSignup:
                intent = new Intent(context, SignupActivity.class);
                startActivity(intent);
                break;
            case R.id.txtForgotPassword:
                intent = new Intent(getApplicationContext(), ForgotPasswordActivity.class);
                startActivity(intent);
                break;
        }
    }

    public void findViewById() {
        edtMobileNo = (EditText) findViewById(R.id.edtMobileNo);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        txtForgotPassword = (TextView) findViewById(R.id.txtForgotPassword);

        btnLogin.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        txtForgotPassword.setOnClickListener(this);

    }
}
