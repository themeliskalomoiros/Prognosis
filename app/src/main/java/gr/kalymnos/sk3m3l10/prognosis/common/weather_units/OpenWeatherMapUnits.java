package gr.kalymnos.sk3m3l10.prognosis.common.weather_units;

/**
 * WeatherUnits for OpenWeatherMap.org
 */

public abstract class OpenWeatherMapUnits extends WeatherUnits {

    public static class OpenWeatherDefault extends OpenWeatherMapUnits {

        @Override
        public String getWindUnit() {
            return "meter/sec";
        }

        @Override
        public String getWindUnitSymbol() {
            return "m/s";
        }

        @Override
        public String getTempUnit() {
            return "Kelvin";
        }

        @Override
        public String getTempUnitSymbol() {
            return "K";
        }

        @Override
        public String getHumidityUnit() {
            return "%";
        }

        @Override
        public String getPressureUnit() {
            return "hPa";
        }
    }

    public static class OpenWeatherMetric extends OpenWeatherMapUnits {

        @Override
        public String getWindUnit() {
            return "meter/sec";
        }

        @Override
        public String getWindUnitSymbol() {
            return "m/s";
        }

        @Override
        public String getTempUnit() {
            return "Celsius";
        }

        @Override
        public String getTempUnitSymbol() {
            return "\u2103";
        }

        @Override
        public String getHumidityUnit() {
            return "%";
        }

        @Override
        public String getPressureUnit() {
            return "hPa";
        }
    }

    public static class OpenWeatherImperial extends OpenWeatherMapUnits {

        @Override
        public String getWindUnit() {
            return "miles/hour";
        }

        @Override
        public String getWindUnitSymbol() {
            return "m/h";
        }

        @Override
        public String getTempUnit() {
            return "Fahrenheit";
        }

        @Override
        public String getTempUnitSymbol() {
            return "\u2109";
        }

        @Override
        public String getHumidityUnit() {
            return "%";
        }

        @Override
        public String getPressureUnit() {
            return "hPa";
        }
    }
}
