package gr.kalymnos.sk3m3l10.prognosis.common;

/**
 * A container which holds weather data fetched from a web service.
 */

public abstract class Weather {
    private long timeMilli;
    private String mainWeather,description;
    private int tempHigh,tempLow,humidity,pressure;
    private double wind;

    public Weather(long timeMilli, String mainWeather, String description, int tempHigh, int tempLow, int humidity, int pressure, double wind) {
        this.timeMilli = timeMilli;
        this.mainWeather = mainWeather;
        this.description = description;
        this.tempHigh = tempHigh;
        this.tempLow = tempLow;
        this.humidity = humidity;
        this.pressure = pressure;
        this.wind = wind;
    }

    public long getTimeMilli() {
        return timeMilli;
    }

    public String getMainWeather() {
        return mainWeather;
    }

    public String getDescription() {
        return description;
    }

    public int getTempHigh() {
        return tempHigh;
    }

    public int getTempLow() {
        return tempLow;
    }

    public int getHumidity() {
        return humidity;
    }

    public int getPressure() {
        return pressure;
    }

    public double getWind() {
        return wind;
    }
}
