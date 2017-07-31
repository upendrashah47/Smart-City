/**
 * The MIT License (MIT)
 * <p>
 * Copyright (c) 2015 Yoel Nunez <dev@nunez.guru>
 * <p>
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * <p>
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * <p>
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.us.smartcity.ui;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.graphics.drawable.RoundedBitmapDrawable;
import android.support.v4.graphics.drawable.RoundedBitmapDrawableFactory;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.BitmapImageViewTarget;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;
import com.us.smartcity.R;
import com.us.smartcity.utils.Config;
import com.us.smartcity.utils.Pref;
import com.us.smartcity.weather.SettingsActivity;
import com.us.smartcity.weather.data.Channel;
import com.us.smartcity.weather.data.Condition;
import com.us.smartcity.weather.data.LocationResult;
import com.us.smartcity.weather.data.Units;
import com.us.smartcity.weather.listener.GeocodingServiceListener;
import com.us.smartcity.weather.listener.WeatherServiceListener;
import com.us.smartcity.weather.service.GoogleMapsGeocodingService;
import com.us.smartcity.weather.service.WeatherCacheService;
import com.us.smartcity.weather.service.YahooWeatherService;

public class WeatherActivity extends BaseActivity implements WeatherServiceListener, GeocodingServiceListener, LocationListener {

    private Context context;
    private Intent intent;
    private ImageView weatherIconImageView;
    private TextView temperatureTextView;
    private TextView conditionTextView;
    private TextView locationTextView;

    private YahooWeatherService weatherService;
    private GoogleMapsGeocodingService geocodingService;
    private WeatherCacheService cacheService;

    private ProgressDialog dialog;

    // weather service fail flag
    private boolean weatherServicesHasFailed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = WeatherActivity.this;

        createCustomFloatingButton();
        findViewById();


        weatherService = new YahooWeatherService(this);
        weatherService.setTemperatureUnit(Pref.getString(context, Config.PREF_TEMP_UNIT, ""));

        geocodingService = new GoogleMapsGeocodingService(this);
        cacheService = new WeatherCacheService(this);

        if (Pref.getBoolean(context, Config.PREF_NEED_SETUP, true)) {
            startSettingsActivity();
        }

    }

    @Override
    protected void onStart() {
        super.onStart();

        dialog = new ProgressDialog(this);
        dialog.setMessage(getString(R.string.loading));
        dialog.setCancelable(false);
        dialog.show();

        String location = null;

        if (Pref.getBoolean(context, Config.PREF_GEO_LOCATION, true)) {
            String locationCache = Pref.getString(context, Config.PREF_CACHED_LOCATION, "");

            if (locationCache == null) {
                getWeatherFromCurrentLocation();
            } else {
                location = locationCache;
            }
        } else {
            location = Pref.getString(context, Config.PREF_MANUAL_LOCATION, "");
        }

        if (location != "") {
            weatherService.refreshWeather(location);
        }
    }

    @Override
    public int getLayout() {
        return R.layout.activity_weather;
    }

    private void getWeatherFromCurrentLocation() {

//        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//            ActivityCompat.requestPermissions(this, new String[]{
//                    Manifest.permission.ACCESS_FINE_LOCATION,
//            }, GET_WEATHER_FROM_CURRENT_LOCATION);
//
//            return;
//        }

        // system's LocationManager
        final LocationManager locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean isNetworkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean isGPSEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);

        // medium accuracy for weather, good for 100 - 500 meters
        Criteria locationCriteria = new Criteria();

        if (isNetworkEnabled) {
            locationCriteria.setAccuracy(Criteria.ACCURACY_COARSE);
        } else if (isGPSEnabled) {
            locationCriteria.setAccuracy(Criteria.ACCURACY_FINE);
        }

        locationManager.requestSingleUpdate(locationCriteria, this, null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.weather, menu);
        return true;
    }

    private void startSettingsActivity() {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.currentLocation:
                dialog.show();
                getWeatherFromCurrentLocation();
                return true;
            case R.id.settings:
                startSettingsActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void serviceSuccess(Channel channel) {
        dialog.hide();

        Condition condition = channel.getItem().getCondition();
        Units units = channel.getUnits();
        Condition[] forecast = channel.getItem().getForecast();

        int resourceId = getResources().getIdentifier("drawable/icon_" + condition.getCode(), null, getPackageName());

        @SuppressWarnings("deprecation")
        Drawable weatherIconDrawable = getResources().getDrawable(resourceId);

        weatherIconImageView.setImageResource(resourceId);

        String temperatureLabel = getString(R.string.temperature_output, condition.getTemperature(), units.getTemperature());

        temperatureTextView.setText(temperatureLabel);
        conditionTextView.setText(condition.getDescription());
        locationTextView.setText(channel.getLocation());

//        for (int day = 0; day < forecast.length; day++) {
//            if (day >= 5) {
//                break;
//            }
//
//            Condition currentCondition = forecast[day];
//
//            int viewId = getResources().getIdentifier("forecast_" + day, "id", getPackageName());
//            WeatherConditionFragment fragment = (WeatherConditionFragment) getSupportFragmentManager().findFragmentById(viewId);
//
//            if (fragment != null) {
//                fragment.loadForecast(currentCondition, channel.getUnits());
//            }
//        }

        cacheService.save(channel);
    }

    @Override
    public void serviceFailure(Exception exception) {
        // display error if this is the second failure
        if (weatherServicesHasFailed) {
            dialog.hide();
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_LONG).show();
        } else {
            // error doing reverse geocoding, load weather data from cache
            weatherServicesHasFailed = true;
            // OPTIONAL: let the user know an error has occurred then fallback to the cached data
            Toast.makeText(this, exception.getMessage(), Toast.LENGTH_SHORT).show();

            cacheService.load(this);
        }
    }

    @Override
    public void geocodeSuccess(LocationResult location) {
        // completed geocoding successfully
        weatherService.refreshWeather(location.getAddress());

        Pref.setString(context, Config.PREF_CACHED_LOCATION, location.getAddress());
    }

    @Override
    public void geocodeFailure(Exception exception) {
        // GeoCoding failed, try loading weather data from the cache
        cacheService.load(this);
    }

    @Override
    public void onLocationChanged(Location location) {
        geocodingService.refreshLocation(location);
    }

    @Override
    public void onStatusChanged(String s, int i, Bundle bundle) {
        // OPTIONAL: implement your custom logic here
    }

    @Override
    public void onProviderEnabled(String s) {
        // OPTIONAL: implement your custom logic here
    }

    @Override
    public void onProviderDisabled(String s) {
        // OPTIONAL: implement your custom logic here
    }

    public void createCustomFloatingButton() {

        if (Pref.getInt(context, Config.PREF_IS_LOGIN, 0) == 1) {
            final ImageView imageview = new ImageView(context); // Create an icon
            Glide.with(getApplicationContext()).load(R.drawable.upen).asBitmap().into(new BitmapImageViewTarget(imageview) {
                @Override
                protected void setResource(Bitmap resource) {
                    super.setResource(resource);
                    RoundedBitmapDrawable roundedBitmapDrawable = RoundedBitmapDrawableFactory.create(WeatherActivity.this.getResources(), resource);
                    roundedBitmapDrawable.setCircular(true);
                    imageview.setImageDrawable(roundedBitmapDrawable);
                }
            });
            FloatingActionButton actionButton = new FloatingActionButton.Builder(this).setContentView(imageview).build();

        } else {
            ImageView imageview = new ImageView(context); // Create an icon
            TextView text = new TextView(this);
            text.setText("signup_activity");
            imageview.setImageResource(R.drawable.accountbox);

            FloatingActionButton actionButton = new FloatingActionButton.Builder(this).setContentView(imageview).build();

            Button login = new Button(this); // Create an icon
            login.setBackgroundResource(R.drawable.login);

            Button signup = new Button(this); // Create an icon
            signup.setBackgroundResource(R.drawable.signup);

            Button guser = new Button(this); // Create an icon
            guser.setBackgroundResource(R.drawable.guest_user);

            SubActionButton.Builder itemBuilder = new SubActionButton.Builder(this);
            SubActionButton buttonLogin = itemBuilder.setContentView(login).build();
            SubActionButton buttonSignup = itemBuilder.setContentView(signup).build();
            final SubActionButton buttonGuser = itemBuilder.setContentView(guser).build();

            FloatingActionMenu actionMenu = new FloatingActionMenu.Builder(this)
                    .addSubActionView(buttonLogin)
                    .addSubActionView(buttonSignup)
                    .addSubActionView(buttonGuser)
                    .attachTo(actionButton)
                    .build();
            buttonLogin.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent = new Intent(context, LoginActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            buttonSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    intent = new Intent(context, SignupActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            buttonGuser.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    intent = new Intent(context, HomeActivity.class);
                    startActivity(intent);
                    finish();
                }

            });
        }
    }

    public void findViewById() {
        weatherIconImageView = (ImageView) findViewById(R.id.weatherIconImageView);
        temperatureTextView = (TextView) findViewById(R.id.temperatureTextView);
        conditionTextView = (TextView) findViewById(R.id.conditionTextView);
        locationTextView = (TextView) findViewById(R.id.locationTextView);
    }
}
