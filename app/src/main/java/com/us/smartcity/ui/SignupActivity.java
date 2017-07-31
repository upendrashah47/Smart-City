package com.us.smartcity.ui;

/**
 * Created by Bhaskar on 21-10-2015.
 */

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.us.smartcity.R;
import com.us.smartcity.database.DatabaseHelper;
import com.us.smartcity.utils.Utils;

import java.util.Calendar;

public class SignupActivity extends AppCompatActivity implements View.OnClickListener {

    private Context context;
    Button button;
    DatabaseHelper databaseHelper;
    static String TAG = SignupActivity.class.getName();

    private EditText edtFirstName, edtEmail, edtLastName, edtPassword, edtMobileNo, edtConfirmPassword;
    private Button btnCancel;
    private Button btnSignup;

    private ImageButton imgDOB;
    private Calendar cal;
    private int day;
    private int month;
    private int year;
    private EditText edtDOB;
    private CheckBox chkAgree;
    private Intent intent;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);
        context = SignupActivity.this;

        findViewById();
    }

    @Override
    @Deprecated
    protected Dialog onCreateDialog(int id) {
        return new DatePickerDialog(this, datePickerListener, year, month, day);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnSignup:
                String mesg = validate();
                if (mesg == null) {
                    Toast.makeText(SignupActivity.this, "THANK YOU FOR REGISTRATION", Toast.LENGTH_LONG).show();
                    intent = new Intent(getApplicationContext(), HomeActivity.class);
                    startActivity(intent);
                    finish();
                } else {
//                    Utils.showAlert(context, Utils.getResourceString(context, R.string.alert), mesg, Utils.getResourceString(context, R.string.ok));
                }
                break;

            case R.id.btnCancel:
                intent = new Intent(context, WeatherActivity.class);
                startActivity(intent);
                finish();
                break;

            case R.id.imgDOB:
                showDialog(0);
                break;
        }
    }

    public void findViewById() {
        edtFirstName = (EditText) findViewById(R.id.edtFirstName);
        edtLastName = (EditText) findViewById(R.id.edtLastName);
        edtEmail = (EditText) findViewById(R.id.edtEmail);
        edtPassword = (EditText) findViewById(R.id.edtPassword);
        edtConfirmPassword = (EditText) findViewById(R.id.edtConfirmPassword);
        edtMobileNo = (EditText) findViewById(R.id.edtMobileNo);
        edtDOB = (EditText) findViewById(R.id.edtDOB);
        btnCancel = (Button) findViewById(R.id.btnCancel);
        btnSignup = (Button) findViewById(R.id.btnSignup);
        chkAgree = (CheckBox) findViewById(R.id.chkAgree);
        imgDOB = (ImageButton) findViewById(R.id.imgDOB);

        cal = Calendar.getInstance();
        day = cal.get(Calendar.DAY_OF_MONTH);
        month = cal.get(Calendar.MONTH);
        year = cal.get(Calendar.YEAR);

        btnCancel.setOnClickListener(this);
        btnSignup.setOnClickListener(this);
        imgDOB.setOnClickListener(this);
    }

    public String validate() {
        String mesg = null;

        if (edtFirstName.getText().toString().trim().equals("")) {
            mesg = Utils.getResourceSting(context, R.string.enterFirstName);

        } else if (edtLastName.getText().toString().trim().equals("")) {
            mesg = Utils.getResourceSting(context, R.string.enterLastName);

        } else if (edtPassword.getText().toString().trim().equals("")) {
            mesg = Utils.getResourceSting(context, R.string.enterPassword);

        } else if (edtEmail.getText().toString().trim().equals("")) {
            mesg = Utils.getResourceSting(context, R.string.enterEmail);

        } else if (edtConfirmPassword.getText().toString().trim().equals("")) {
            mesg = Utils.getResourceSting(context, R.string.enterPassword);

        } else if (edtMobileNo.getText().toString().trim().equals("")) {
            mesg = Utils.getResourceSting(context, R.string.enterMobile);

        } else if (!edtConfirmPassword.getText().toString().trim().equals(edtPassword.getText().toString().trim())) {
            mesg = Utils.getResourceSting(context, R.string.unmatchPassword);

        } else if (!chkAgree.isChecked()) {
            mesg = Utils.getResourceSting(context, R.string.selectCheckbox);
        }
        return mesg;
    }

    private DatePickerDialog.OnDateSetListener datePickerListener = new DatePickerDialog.OnDateSetListener() {
        public void onDateSet(DatePicker view, int selectedYear,
                              int selectedMonth, int selectedDay) {
            edtDOB.setText(selectedDay + " / " + (selectedMonth + 1) + " / " + selectedYear);
        }
    };
}