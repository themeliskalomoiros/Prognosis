package gr.kalymnos.sk3m3l10.prognosis.common;

/**
 * A container which holds weather data fetched from a web service.
 */

public abstract class Weather {

    private String mainWeather,description,timeMilli,tempHigh,tempLow,humidity,pressure,wind;

    public Weather(long timeMilli, String mainWeather, String description, int tempHigh, int tempLow, int humidity, int pressure, double wind) {
        this.timeMilli = String.valueOf(timeMilli);
        this.mainWeather = mainWeather;
        this.description = description;
        this.tempHigh = String.valueOf(tempHigh);
        this.tempLow = String.valueOf(tempLow);
        this.humidity = String.valueOf(humidity);
        this.pressure = String.valueOf(pressure);
        this.wind = String.format("%.1f");
    }

    public String getMainWeather() {
        return mainWeather;
    }

    public String getDescription() {
        return description;
    }

    public String getTimeMilli() {
        return timeMilli;
    }

    public String getTempHigh() {
        return tempHigh;
    }

    public String getTempLow() {
        return tempLow;
    }

    public String getHumidity() {
        return humidity;
    }

    public String getPressure() {
        return pressure;
    }

    public String getWind() {
        return wind;
    }
}
