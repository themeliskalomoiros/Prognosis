package gr.kalymnos.sk3m3l10.prognosis.model_mvc;

import android.location.Location;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;

/**
 * A weather service which supply us with live Weather data.
 */

public interface WeatherService {

    public Weather getCurrentWeather(String cityName);
    public Weather getCurrentWeather(Location location);

    public List<Weather> getWeatherForecast(String cityName);
    public List<Weather> getWeatherForecast(Location location);
}
