package gr.kalymnos.sk3m3l10.prognosis.model_mvc;

import android.location.Location;

import org.json.JSONArray;
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

        /*--------------------------JSON KEYS--------------------*/
        private static final String CITY_NAME = "name";
        private static final String DATE_TIME = "dt";
        private static final String CITY = "city";
        private static final String SYS = "sys";
        private static final String COUNTRY = "country";
        private static final String LIST = "list";
        private static final String MAIN = "main";
        private static final String TEMP_MIN = "temp_min";
        private static final String TEMP_MAX = "temp_max";
        private static final String TEMP_MAX = "temp_max";
        /*-------------------------------------------------------*/

        // Json response types (from .../weather? or .../forecast?)
        private static final int TYPE_CURRENT_WEATHER=0;
        private static final int TYPE_FORECAST=1;

        private int type;
        private JSONObject rootObj;

        private JsonAssembler(String json, int responseType) throws JSONException {
            this.rootObj = new JSONObject(json);
            this.type=responseType;
        }

        private String getCityName(){
            switch (this.type){
                case TYPE_CURRENT_WEATHER:
                    return rootObj.optString(CITY_NAME);
                case TYPE_FORECAST:
                    JSONObject city = rootObj.optJSONObject(CITY);
                    return city.optString(CITY_NAME);
                default:
                    throw new IllegalArgumentException(CLASS_TAG+": Unknown responseType.");
            }
        }

        private String getCountryCode(){
            switch (this.type){
                case TYPE_CURRENT_WEATHER:
                    JSONObject sys = this.rootObj.optJSONObject(SYS);
                    return sys.optString(COUNTRY);
                case TYPE_FORECAST:
                    JSONObject city = rootObj.optJSONObject(CITY);
                    return city.optString(COUNTRY);
                default:
                    throw new IllegalArgumentException(CLASS_TAG+": Unknown responseType.");
            }
        }

        private long getTimeMilli(){
            switch (this.type){
                case TYPE_CURRENT_WEATHER:
                    return this.rootObj.optLong(DATE_TIME);
                default:
                    throw new IllegalArgumentException(CLASS_TAG+": responseType " +
                            "must be TYPE_CURRENT_WEATHER for this call.");
            }
        }

        // This is used for forecasts where every Weather object has its own time-milli.
        private long getTimeMilli(int index){
            switch (this.type){
                case TYPE_FORECAST:
                    JSONArray list = this.rootObj.optJSONArray(LIST);
                    return list.optJSONObject(index).optLong(DATE_TIME);
                default:
                    throw new IllegalArgumentException(CLASS_TAG+": responseType " +
                            "type must be TYPE_FORECAST for this call.");
            }
        }


        /*  
            The temperature in the api is a double, 
            though the optInt() is able to cast it to an integer.
        */
        private int getMaxTemp(){
            switch (this.type){
                case TYPE_CURRENT_WEATHER:
                    return this.rootObj.optJSONObject(MAIN).optInt(TEMP_MAX);
                default:
                    throw new IllegalArgumentException(CLASS_TAG+": responseType " +
                            "must be TYPE_CURRENT_WEATHER for this call.");
            }
        }

        private int getMaxTemp(int index){
            switch (this.type){
                case TYPE_FORECAST:
                    JSONArray list = this.rootObj.optJSONArray(LIST);
                    return list.optJSONObject(index).optJSONObject(MAIN)
                            .optInt(TEMP_MAX);
                default:
                    throw new IllegalArgumentException(CLASS_TAG+": responseType " +
                            "must be TYPE_FORECAST for this call.");
            }
        }

        private int getMinTemp(){
            switch (this.type){
                case TYPE_CURRENT_WEATHER:
                    return this.rootObj.optJSONObject(MAIN).optInt(TEMP_MIN);
                default:
                    throw new IllegalArgumentException(CLASS_TAG+": responseType " +
                            "must be TYPE_CURRENT_WEATHER for this call.");
            }
        }

        private int getMinTemp(int index){
            switch (this.type){
                case TYPE_FORECAST:
                    JSONArray list = this.rootObj.optJSONArray(LIST);
                    return list.optJSONObject(index).optJSONObject(MAIN)
                            .optInt(TEMP_MIN);
                default:
                    throw new IllegalArgumentException(CLASS_TAG+": responseType " +
                            "must be TYPE_FORECAST for this call.");
            }
        }

    }
}
