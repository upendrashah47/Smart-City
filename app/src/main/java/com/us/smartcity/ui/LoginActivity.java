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
        setContentView(R.layout.activity_login);
        context = LoginActivity.this;

        findViewById();
    }

    @Override
    public void onClick(View v) {
        String validate;
        switch (v.getId()) {
            case R.id.btnLogin:
                validate = validation();
                if (validate == null) {
                    intent = new Intent(LoginActivity.this, HomeActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(context, validate, Toast.LENGTH_SHORT).show();
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

    public String validation() {
        String valid = null;
        if (edtMobileNo.getText().toString().trim().equals("")) {
            valid = getResources().getString(R.string.validemail);
            this.edtMobileNo.requestFocus();
            this.edtMobileNo.setSelection(this.edtMobileNo.length());
        } else if (edtMobileNo.getText().toString().trim().length() > 10) {
            valid = getResources().getString(R.string.invalidEmailLength);
            this.edtMobileNo.requestFocus();
            this.edtMobileNo.setSelection(this.edtMobileNo.length());
        } else if (edtPassword.getText().toString().trim().equals("")) {
            valid = getResources().getString(R.string.validblankpassword);
            this.edtPassword.requestFocus();
            this.edtPassword.setSelection(this.edtPassword.length());
        } else if (edtPassword.getText().toString().trim().length() < 8 || edtPassword.getText().toString().trim().length() > 20) {
            valid = getResources().getString(R.string.invalidnewpasswordlength);
            this.edtPassword.requestFocus();
            this.edtPassword.setSelection(this.edtPassword.length());
        }
        return valid;
    }

}
