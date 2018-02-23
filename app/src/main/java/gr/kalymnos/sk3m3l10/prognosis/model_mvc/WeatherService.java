package gr.kalymnos.sk3m3l10.prognosis.model_mvc;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;

/**
 * A weather service which supply us with live Weather data.
 */

public interface WeatherService {
    public List<Weather> getWeatherForecast(String cityName);
    public List<Weather> getWeatherForecast(String cityName,String countryCode);
    public List<Weather> getWeatherForecast(double lat, double lon);
}
