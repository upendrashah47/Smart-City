package com.us.smartcity.ui;

/**
 * Created by Bhaskar on 06-11-2015.
 */

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;

import com.us.smartcity.R;
import com.us.smartcity.utils.Log;
import com.us.smartcity.utils.Utils;

public class SplashActivity extends BaseActivity {
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = SplashActivity.this;

//        Log.WHICH_HOST = Utils.getResourceSting(context, R.string.apiHostData);
        Utils.systemUpgrade(SplashActivity.this);
        Utils.getDeviceID(context);
//        if (Utils.isOnline(context)) {
//            syncAPI();
//        } else {
        try {
            Thread splashThread = new Thread() {
                public void run() {
                    try {
                        sleep(3000);
                    } catch (InterruptedException e) {
                        Log.error(getClass().getName() + ":splashThread", e);
                    }
                    handler.sendEmptyMessage(0);
                }
            };
            splashThread.start();
        } catch (Exception e) {
            Log.error(this.getClass() + "::splashThread::", e);
        } finally {
            Utils.freeMemory();
        }
//        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_splash;
    }

    private Handler handler = new Handler() {
        @Override
        public void handleMessage(android.os.Message msg) {

//            syncAPI();
            Intent intent = new Intent(context, WeatherActivity.class);
            startActivity(intent);

        }
    };

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return super.onCreateOptionsMenu(menu);
    }
}