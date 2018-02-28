package gr.kalymnos.sk3m3l10.prognosis.model_mvc;

import android.location.Location;

import org.json.JSONException;
import org.json.JSONObject;

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
    private static class JsonAssembler {

        private static final String CLASS_TAG = JsonAssembler.class.getSimpleName();

        private static final int TYPE_CURRENT_WEATHER=0;
        private static final int TYPE_FORECAST=1;

        private String json;
        private int type;

        private JsonAssembler(String json, int responseType){
            this.json=json;
            this.type=responseType;
        }

        String getCityName() throws JSONException {
            String city=null;
            JSONObject jsonFile = new JSONObject(this.json);

            switch (this.type){
                case TYPE_CURRENT_WEATHER:
                    break;
                case TYPE_FORECAST:
                    break;
                default:
                    throw new IllegalArgumentException()
            }

            return city;
        }

    }
}
