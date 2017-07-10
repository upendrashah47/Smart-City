package com.us.smartcity.Weather;

/**
 * Created by Bhaskar on 01-03-2016.
 */
public interface YahooWeatherExceptionListener {
    public void onFailConnection(final Exception e);
    public void onFailParsing(final Exception e);
    public void onFailFindLocation(final Exception e);
}