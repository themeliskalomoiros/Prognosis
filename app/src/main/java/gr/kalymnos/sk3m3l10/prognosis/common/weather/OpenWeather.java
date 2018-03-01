package gr.kalymnos.sk3m3l10.prognosis.common.weather;

/*
* A Weather obj acquired by the OpenWeatherMap.org service.
*
* */

import android.text.TextUtils;

import gr.kalymnos.sk3m3l10.prognosis.common.weather_units.WeatherUnits;

public class OpenWeather extends Weather {

    private String cityName,countryCode;
    private boolean obtainedFromDeviceLocation = false;

    public OpenWeather(String cityName, String countryCode, long timeMilli, String mainWeather,
                       String description, int tempHigh, int tempLow, int humidity,
                       int pressure, double wind, WeatherUnits weatherUnit) {
        super(timeMilli, mainWeather, description, tempHigh, tempLow, humidity, pressure, wind, weatherUnit);
        this.cityName=cityName;
        this.countryCode=countryCode;
    }

    public boolean isObtainedFromDeviceLocation() {
        return obtainedFromDeviceLocation;
    }

    public void setObtainedFromDeviceLocation(boolean obtainedFromDeviceLocation) {
        this.obtainedFromDeviceLocation = obtainedFromDeviceLocation;
    }

    @Override
    public String getQueryTitle() {
        if (TextUtils.isEmpty(this.countryCode)){
            return this.cityName;
        }
        return cityName+", "+countryCode;
    }
}
