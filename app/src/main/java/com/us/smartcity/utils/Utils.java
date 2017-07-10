package com.us.smartcity.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

import com.us.smartcity.ui.SignupActivity;

import java.util.regex.Pattern;

/**
 * Created by user on 28/6/17 in SmartCity.
 */

public class Utils {

    public static final Pattern EMAIL_PATTERN = Pattern.compile("[a-zA-Z0-9+._%-+]{1,100}" + "@" + "[a-zA-Z0-9][a-zA-Z0-9-]{0,10}" + "(" + "." + "[a-zA-Z0-9][a-zA-Z0-9-]{0,20}" + ")+");

    public static final Pattern PASSWORD_PATTERN = Pattern.compile("[a-zA-Z0-9+_.]{4,16}");

    public static final Pattern USERNAME_PATTERN = Pattern.compile("[a-z]");

    public static final Pattern MOBILE_PATTERN = Pattern.compile("\\+?\\d[\\d -]{8,12}\\d");

    public static void showAlert(Context context, String title, String mesg, String buttonText) {
        AlertDialog alertDialog = new AlertDialog.Builder(context).create();
        alertDialog.setTitle(title);
        alertDialog.setMessage(mesg);
        alertDialog.setButton(buttonText, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                //dismiss the dialog
            }
        });
        alertDialog.show();
    }

    public static String getResourceString(Context context, int resId) {
        return context.getResources().getString(resId);
    }
}
