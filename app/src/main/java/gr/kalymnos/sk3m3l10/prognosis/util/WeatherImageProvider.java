package gr.kalymnos.sk3m3l10.prognosis.util;

/**
 * Provides an image for specific weather
 */

public class WeatherImageProvider {

    private enum OpenWeatherMapWeatherValues{

        CLEAR_SKY("clear sky");

        OpenWeatherMapWeatherValues(String mainWeather){
            this.mainWeather=mainWeather;
        }

        private String mainWeather;

        @Override
        public String toString() {
            return this.mainWeather;
        }
    }
}
