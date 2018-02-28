package gr.kalymnos.sk3m3l10.prognosis.util;

import android.location.Location;
import android.net.Uri;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

/**
 * Network utilities for OpenWeatherMap.com
 */

public class OpenWeatherMapUtils{

    // OpenWeatherMap.org parameters
    private static final String QUERY_PARAM = "q";
    private static final String FORMAT_PARAM = "mode";
    private static final String UNITS_PARAM = "units";
    private static final String API_KEY_PARAM = "appid";
    private static final String LAT_PARAM = "lat";
    private static final String LON_PARAM = "lon";

    // OpenWeatherMap.org parameter values
    private static final String FORMAT_JSON = "json";
    private static final String UNITS_METRIC = "metric";
    private static final String API_KEY = "b2b88ba02f81b823def0b5ca5dd0471f";


    // Urls
    private static final String BASE_URL = "http://api.openweathermap.org/data/2.5/";
    public static final String CURRENT_WEATHER_URL = BASE_URL+"weather";
    public static final String FORECAST_URL = BASE_URL+"forecast";

    private static final String CLASS_TAG = OpenWeatherMapUtils.class.getSimpleName();

    private OpenWeatherMapUtils(){}

    public static URL buildUrlWithCityQuery(String cityQuery, String apiUrl) {
        Uri weatherQueryUri = Uri.parse(apiUrl).buildUpon()
                .appendQueryParameter(QUERY_PARAM, cityQuery)
                .appendQueryParameter(FORMAT_PARAM, FORMAT_JSON)
                .appendQueryParameter(UNITS_PARAM, UNITS_METRIC)
                .appendQueryParameter(API_KEY_PARAM,API_KEY)
                .build();
        try {
            URL weatherQueryUrl = new URL(weatherQueryUri.toString());
            Log.d(CLASS_TAG, "Current weather URL: " + weatherQueryUrl);
            return weatherQueryUrl;
        } catch (MalformedURLException e) {
            Log.e(CLASS_TAG, e.getMessage());
            return null;
        }
    }

    public static URL buildUrlWithLocationQuery(Location location, String apiUrl) {
        Uri weatherQueryUri = Uri.parse(apiUrl).buildUpon()
                .appendQueryParameter(LAT_PARAM, String.valueOf(location.getLatitude()))
                .appendQueryParameter(LON_PARAM, String.valueOf(location.getLongitude()))
                .appendQueryParameter(FORMAT_PARAM, FORMAT_JSON)
                .appendQueryParameter(UNITS_PARAM, UNITS_METRIC)
                .appendQueryParameter(API_KEY_PARAM,API_KEY)
                .build();
        try {
            URL weatherQueryUrl = new URL(weatherQueryUri.toString());
            Log.d(CLASS_TAG, "Location URL: " + weatherQueryUrl);
            return weatherQueryUrl;
        } catch (MalformedURLException e) {
            Log.e(CLASS_TAG, e.getMessage());
            return null;
        }
    }
}
