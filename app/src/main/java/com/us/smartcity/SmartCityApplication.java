package com.us.smartcity;

import android.app.Application;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;
import com.us.smartcity.utils.Config;

/**
 * Created by user on 29/6/17 in SmartCity.
 */

public class SmartCityApplication extends Application {

    private static final String TAG = SmartCityApplication.class.getSimpleName();
    private RequestQueue mRequestQueue = null;
    private static SmartCityApplication mInstance = null;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;
        // MultiDex.install(this);
        if (!Config.isFBSDKinit) {
//            FBSDKinit(mInstance);
        }
    }

    public static synchronized SmartCityApplication getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

//    public <T> void addToRequestQueue(Request<T> req) {
//        req.setTag(TAG);
//        getRequestQueue().add(req);
//    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
