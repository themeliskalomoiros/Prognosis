package gr.kalymnos.sk3m3l10.prognosis.model_mvc;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;

/**
 * A mock weather service
 */

/*
* new CityWeather("Kalpaca","GR",1519138800,
                    "rain","Great day, no clouds",
                    15,8,86,55,
                    56.4,new OpenWeatherMapUnits.OpenWeatherMetric())
*
* */

public class FakeWeatherService implements WeatherService {

    // OpenWeatherMap.org weather values
    private static final String CLEAR_SKY = "clear sky";
    private static final String FEW_CLOUDS = "few clouds";
    private static final String SCATTERED_CLOUDS = "scattered clouds";
    private static final String BROKEN_CLOUDS = "broken clouds";
    private static final String SHOWER_RAIN = "shower rain";
    private static final String RAIN = "rain";
    private static final String THUNDERSTORM = "thunderstorm";
    private static final String SNOW = "snow";
    private static final String MIST = "mist";

    // Possible descriptions
    private static final String CLEAR_SKY = "Great day, seize it";
    private static final String FEW_CLOUDS = "Some clouds in the sky";
    private static final String SCATTERED_CLOUDS = "Clouds scattered along the sky";
    private static final String BROKEN_CLOUDS = "Broken clouds";
    private static final String SHOWER_RAIN = "Raining heavilly";
    private static final String RAIN = "Raining";
    private static final String THUNDERSTORM = "Raining cats and dogs";
    private static final String SNOW = "Snowing";
    private static final String MIST = "Misty mountains";

    @Override
    public List<Weather> getWeatherForecast(String cityName) {
        return null;
    }

    @Override
    public List<Weather> getWeatherForecast(String cityName, String countryCode) {
        return null;
    }

    @Override
    public List<Weather> getWeatherForecast(double lat, double lon) {
        return null;
    }
}
