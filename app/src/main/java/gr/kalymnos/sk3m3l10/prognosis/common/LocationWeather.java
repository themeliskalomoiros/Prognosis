package gr.kalymnos.sk3m3l10.prognosis.common;

/**
 * Created by skemelio on 20.02.18.
 */

public class LocationWeather extends Weather {

    private long lat,lon;

    public LocationWeather(long lat,long lon,long timeMilli, String mainWeather, String description, int tempHigh, int tempLow, int humidity, int pressure, double wind) {
        super(timeMilli, mainWeather, description, tempHigh, tempLow, humidity, pressure, wind);
        this.lat=lat;
        this.lon=lon;
    }

    public long getLat() {
        return lat;
    }

    public long getLon() {
        return lon;
    }

    @Override
    public String toString() {
        return String.format("Lat:%d\nLon:%d",this.lat,this.lon);
    }
}
