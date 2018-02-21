package gr.kalymnos.sk3m3l10.prognosis.common;

/*
* A Weather obj acquired by Location (lat,lon)
*
* */

public class LocationWeather extends Weather {

    private long lat,lon;

    public LocationWeather(long lat,long lon,long timeMilli, String mainWeather, String description,
                           int tempHigh, int tempLow, int humidity, int pressure, double wind,
                           WeatherUnit weatherUnit) {
        super(timeMilli, mainWeather, description, tempHigh, tempLow, humidity, pressure, wind, weatherUnit);
        this.lat=lat;
        this.lon=lon;
    }

    @Override
    public String getQueryTitle() {
        return String.format("Lat:%d\nLon:%d",this.lat,this.lon);
    }
}
