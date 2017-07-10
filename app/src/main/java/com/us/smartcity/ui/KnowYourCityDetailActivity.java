package com.us.smartcity.ui;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.us.smartcity.R;

/**
 * Created by Upen on 3/7/17 in SmartCity.
 */

public class KnowYourCityDetailActivity extends AppCompatActivity {

    private Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.know_your_city_detail_activity);
        context = KnowYourCityDetailActivity.this;
    }
}
