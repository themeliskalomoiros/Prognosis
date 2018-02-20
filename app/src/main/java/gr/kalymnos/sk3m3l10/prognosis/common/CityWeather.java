package gr.kalymnos.sk3m3l10.prognosis.common;

/**
 * Created by skemelio on 20.02.18.
 */

public class CityWeather extends Weather {

    private String cityName,countryCode;

    public CityWeather(String cityName,long timeMilli, String mainWeather,
                       String description, int tempHigh, int tempLow, int humidity, int pressure,
                       double wind) {
        super(timeMilli, mainWeather, description, tempHigh, tempLow, humidity, pressure, wind);
        this.cityName = cityName;
    }

    public String getCityName() {
        return cityName;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    @Override
    public String toString() {
        return cityName+", "+countryCode;
    }
}
