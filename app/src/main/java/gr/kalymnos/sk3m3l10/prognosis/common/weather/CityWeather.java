package gr.kalymnos.sk3m3l10.prognosis.common.weather;

/*
* A Weather obj acquired by a city name
*
* */

import android.text.TextUtils;

import gr.kalymnos.sk3m3l10.prognosis.common.weather_units.WeatherUnits;

public class CityWeather extends Weather {

    private String cityName,countryCode;

    public CityWeather(String cityName, String countryCode,long timeMilli, String mainWeather,
                       String description, int tempHigh, int tempLow, int humidity,
                       int pressure, double wind, WeatherUnits weatherUnit) {
        super(timeMilli, mainWeather, description, tempHigh, tempLow, humidity, pressure, wind, weatherUnit);
        this.cityName=cityName;
        this.countryCode=countryCode;
    }

    @Override
    public String getQueryTitle() {
        if (TextUtils.isEmpty(this.countryCode)){
            return this.cityName;
        }
        return cityName+", "+countryCode;
    }
}
