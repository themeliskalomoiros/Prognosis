package gr.kalymnos.sk3m3l10.prognosis.model_mvc;

import android.location.Location;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;

/**
 * The weather service from OpenWeatherMap.com
 */

public class OpenWeatherMapService implements WeatherService {
    @Override
    public Weather getCurrentWeather(String cityName) {
        return null;
    }

    @Override
    public Weather getCurrentWeather(Location location) {
        return null;
    }

    @Override
    public List<Weather> getWeatherForecast(String cityName) {
        return null;
    }

    @Override
    public List<Weather> getWeatherForecast(Location location) {
        return null;
    }

    /*
        This classs will be responsible for retrieving weather values,
        after parsing a json http response.
    */
    private class JsonFinder{

        static final int TYPE_CITY=0;
        static final int TYPE_LOCATION=1;


        private String json;
        private int type;

        JsonFinder(String json, int type){
            this.json=json;
            this.type=type;
        }
    }
}
