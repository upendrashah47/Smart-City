package com.us.smartcity.utils;

/**
 * Created by user on 28/6/17 in SmartCity.
 */

public class Log {
    public static boolean DO_SOP = true;

    public static void print(String mesg) {
        if (DO_SOP) {
            android.util.Log.v(Config.TAG, mesg);
        }
    }

    public static void print(String title, String mesg) {
        if (DO_SOP) {
            android.util.Log.v(title, mesg);
        }
    }

    public static void error(String title, Exception e) {
        if (DO_SOP) {
            Log.print("=========== ERROR =========");
            android.util.Log.e(Config.TAG, title, e);
        }
    }
}
