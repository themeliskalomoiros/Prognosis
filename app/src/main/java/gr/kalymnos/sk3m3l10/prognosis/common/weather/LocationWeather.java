package gr.kalymnos.sk3m3l10.prognosis.common.weather;

/*
* A Weather obj acquired by Location (lat,lon)
*
* */

import gr.kalymnos.sk3m3l10.prognosis.common.weather_units.WeatherUnits;

public class LocationWeather extends Weather {

    private double lat,lon;

    public LocationWeather(double lat,double lon,long timeMilli, String mainWeather, String description,
                           int tempHigh, int tempLow, int humidity, int pressure, double wind,
                           WeatherUnits weatherUnit) {
        super(timeMilli, mainWeather, description, tempHigh, tempLow, humidity, pressure, wind, weatherUnit);
        this.lat=lat;
        this.lon=lon;
    }

    @Override
    public String getQueryTitle() {
        return String.format("Lat:%d\nLon:%d",this.lat,this.lon);
    }
}
