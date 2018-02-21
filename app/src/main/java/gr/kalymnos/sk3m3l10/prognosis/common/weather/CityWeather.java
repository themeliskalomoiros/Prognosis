package gr.kalymnos.sk3m3l10.prognosis.common;

/*
* A Weather obj acquired by a city name
*
* */

public class CityWeather extends Weather {

    private String cityName,countryCode;

    public CityWeather(String cityName, String countryCode,long timeMilli, String mainWeather,
                       String description, int tempHigh, int tempLow, int humidity,
                       int pressure, double wind, WeatherUnit weatherUnit) {
        super(timeMilli, mainWeather, description, tempHigh, tempLow, humidity, pressure, wind, weatherUnit);
        this.cityName=cityName;
        this.countryCode=countryCode;
    }

    @Override
    public String getQueryTitle() {
        return cityName+", "+countryCode;
    }
}
