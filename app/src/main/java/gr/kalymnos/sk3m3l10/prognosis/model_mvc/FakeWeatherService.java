package gr.kalymnos.sk3m3l10.prognosis.model_mvc;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;

/**
 * Created by skemelio on 23.02.18.
 */

public class FakeWeatherService implements WeatherService {


    @Override
    public List<Weather> getWeatherForecast(String cityName) {
        return null;
    }

    @Override
    public List<Weather> getWeatherForecast(double lat, double lon) {
        return null;
    }
}
