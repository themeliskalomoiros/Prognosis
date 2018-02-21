package gr.kalymnos.sk3m3l10.prognosis.common;

/**
 * WeatherUnits for OpenWeatherMap.org
 */

public abstract class OpenWeatherUnit extends WeatherUnit {

    public static class OpenWeatherDefault extends OpenWeatherUnit{

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
    }

    public static class OpenWeatherMetric extends OpenWeatherUnit{

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
    }

    public static class OpenWeatherImperial extends OpenWeatherUnit{

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
    }
}
