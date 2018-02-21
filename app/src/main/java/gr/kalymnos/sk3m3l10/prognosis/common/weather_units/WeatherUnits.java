package gr.kalymnos.sk3m3l10.prognosis.common.weather_units;

/**
 * A container wich holds unit data for a Weather obj.
 * Example: For a Weather object that uses units-metric system
 * then the temperature is calculated in Celsius in contrast
 * with a Weather object with units-imperial system where
 * the temperature will be Fahrenheit
 */

public abstract class WeatherUnits {

    public abstract String getWindUnit();
    public abstract String getWindUnitSymbol();
    public abstract String getTempUnit();
    public abstract String getTempUnitSymbol();
    public abstract String getHumidityUnit();
    public abstract String getPressureUnit();


}
