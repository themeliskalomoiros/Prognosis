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
    
    String[] weatherValues = {"clear sky","few clouds","scattered clouds","broken clouds","shower rain","thunderstorm","snow","mist"};
    String[] descriptions = {"Great day, seize it","Some clouds in the sky","Clouds scattered along the sky","Broken clouds","Raining heavilly","Raining cats and dogs","Raining","Snowing","Misty mountains"};

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
