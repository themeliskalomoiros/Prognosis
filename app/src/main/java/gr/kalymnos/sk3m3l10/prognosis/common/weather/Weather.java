package gr.kalymnos.sk3m3l10.prognosis.common.weather;

import gr.kalymnos.sk3m3l10.prognosis.common.weather_units.WeatherUnits;
import gr.kalymnos.sk3m3l10.prognosis.util.DateFormater;

/**
 * A container which holds weather data fetched from a web service.
 */

public abstract class Weather {

    protected WeatherUnits weatherUnit;
    protected String mainWeather,description,tempHigh,tempLow,humidity,pressure,wind;
    protected DateFormater dateFormater;
    private long timeMilli;

    public Weather(long timeMilli, String mainWeather, String description,
                   int tempHigh, int tempLow, int humidity,
                   int pressure, double wind, WeatherUnits weatherUnit) {
        this.timeMilli = timeMilli;
        this.mainWeather = mainWeather;
        this.description = description;
        this.tempHigh = String.valueOf(tempHigh);
        this.tempLow = String.valueOf(tempLow);
        this.humidity = String.valueOf(humidity);
        this.pressure = String.valueOf(pressure);
        this.wind = String.format("%.1f",wind);
        this.weatherUnit=weatherUnit;
    }

    public final String getDate(){
        DateFormater dateFormater = new DateFormater(this.timeMilli);
        return dateFormater.getDate();
    }

    public final long getTimeMilli(){
        return this.timeMilli;
    }

    public final String getMainWeather() {
        return mainWeather;
    }

    public final String getDescription() {
        return description;
    }

    public final String getTempHigh() {
        return tempHigh+" "+this.weatherUnit.getTempUnit();
    }

    public final String getTempLow() {
        return tempLow+" "+this.weatherUnit.getTempUnit();
    }

    public final String getTempHighWithSymbol() {
        return tempHigh+this.weatherUnit.getTempUnitSymbol();
    }

    public final String getTempLowWithSymbol() {
        return tempLow+this.weatherUnit.getTempUnitSymbol();
    }

    public final String getHumidity() {
        return humidity+" "+this.weatherUnit.getHumidityUnit();
    }

    public final String getPressure() {
        return pressure+" "+this.weatherUnit.getPressureUnit();
    }

    public final String getWind() {
        return wind+" "+this.weatherUnit.getWindUnit();
    }

    public final String getWindWithSymbol() {
        return wind+" "+this.weatherUnit.getWindUnitSymbol();
    }

    /* If user searched via city name, then it is the city name,
        if searched with location, then it's Lat/Lon */
    public abstract String getQueryTitle();
}
