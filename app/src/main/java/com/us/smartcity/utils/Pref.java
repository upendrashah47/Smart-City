package com.us.smartcity.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by user on 28/6/17 in SmartCity.
 */

public class Pref {

    private static SharedPreferences sharedPreferences = null;

    public static void openPref(Context context) {
        sharedPreferences = context.getSharedPreferences(Config.PREF, Context.MODE_PRIVATE);
    }

    public static String getString(Context context, String key, String defValue) {
        Pref.openPref(context);
        String result = Pref.sharedPreferences.getString(key, defValue);
        Pref.sharedPreferences = null;
        return result;
    }

    public static void setString(Context context, String key, String value) {
        Pref.openPref(context);
        SharedPreferences.Editor editor = Pref.sharedPreferences.edit();
        editor.putString(key, value);
        editor.commit();
        Pref.sharedPreferences = null;
    }

    public static int getInt(Context context, String key, int defValue) {
        Pref.openPref(context);
        int result = Pref.sharedPreferences.getInt(key, defValue);
        Pref.sharedPreferences = null;
        return result;
    }

    public static void setInt(Context context, String key, int value) {
        Pref.openPref(context);
        SharedPreferences.Editor editor = Pref.sharedPreferences.edit();
        editor.putInt(key, value);
        editor.commit();
        Pref.sharedPreferences = null;
    }

    public static boolean getBoolean(Context context, String key, boolean defValue) {
        Pref.openPref(context);
        boolean result = Pref.sharedPreferences.getBoolean(key, defValue);
        Pref.sharedPreferences = null;
        return result;
    }

    public static void setBoolean(Context context, String key, boolean value) {
        Pref.openPref(context);
        SharedPreferences.Editor editor = Pref.sharedPreferences.edit();
        editor.putBoolean(key, value);
        editor.commit();
        Pref.sharedPreferences = null;
    }
}
