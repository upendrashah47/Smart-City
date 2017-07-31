package com.us.smartcity.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;

public class Pref {
    private static SharedPreferences sharedPreferences = null;

    public static void openPref(Context context) {
        sharedPreferences = context.getSharedPreferences(Config.PREF_FILE,
                Context.MODE_PRIVATE);
    }

    // String get Pref value
    public static String getString(Context context, String key,
                                   String defaultValue) {
        Pref.openPref(context);
        String result = Pref.sharedPreferences.getString(key, defaultValue);
        Pref.sharedPreferences = null;
        return result;
    }

    // String set Pref value
    public static void setString(Context context, String key, String value) {
        Pref.openPref(context);
        Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
        prefsPrivateEditor.putString(key, value);
        prefsPrivateEditor.commit();
        prefsPrivateEditor = null;
        Pref.sharedPreferences = null;
    }

    // Integer get Pref value
    public static int getInt(Context context, String key, int defaultValue) {
        Pref.openPref(context);
        int result = Pref.sharedPreferences.getInt(key, defaultValue);
        Pref.sharedPreferences = null;
        return result;
    }

    // integer set Pref value
    public static void setInt(Context context, String key, int value) {
        Pref.openPref(context);
        Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
        prefsPrivateEditor.putInt(key, value);
        prefsPrivateEditor.commit();
        prefsPrivateEditor = null;
        Pref.sharedPreferences = null;
    }


    // integer get Pref value
    public static boolean getBoolean(Context context, String key, boolean defaultValue) {
        Pref.openPref(context);
        boolean result = Pref.sharedPreferences.getBoolean(key, defaultValue);
        Pref.sharedPreferences = null;
        return result;
    }

    // integer set Pref value
    public static void setBoolean(Context context, String key, boolean value) {
        Pref.openPref(context);
        Editor prefsPrivateEditor = Pref.sharedPreferences.edit();
        prefsPrivateEditor.putBoolean(key, value);
        prefsPrivateEditor.commit();
        prefsPrivateEditor = null;
        Pref.sharedPreferences = null;
    }
}