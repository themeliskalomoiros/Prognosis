package gr.kalymnos.sk3m3l10.prognosis.model_mvc;

import android.location.Location;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.OpenWeather;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.common.weather_units.OpenWeatherMapUnits;

/**
 * A mock weather service
 */

/*
* new OpenWeather("Kalpaca","GR",1519138800,
                    "rain","Great day, no clouds",
                    15,8,86,55,
                    56.4,new OpenWeatherMapUnits.OpenWeatherMetric())
*
* */

public class FakeWeatherService implements WeatherService {

    private static final int WEATHER_ITEMS_MAX_SIZE= 100;
    private static final int TIME_MILLI = 1519387200;

    private String[] weatherValues = {"clear sky","few clouds","scattered clouds","broken clouds","shower rain","thunderstorm","snow","mist"};
    private String[] descriptions = {"Great day, seize it","Some clouds in the sky","Clouds scattered along the sky","Broken clouds","Raining heavilly","Raining cats and dogs","Snowing","Misty mountains"};

    @Override
    public Weather getCurrentWeather(String cityName) {
        int index = new Random().nextInt(weatherValues.length);

        Weather weather = new OpenWeather(cityName,"",TIME_MILLI,weatherValues[index],
                descriptions[index],getRandomTemp(),getRandomTemp(),getRandomHumidity(),
                getRandomPressure(),getRandomWind(),new OpenWeatherMapUnits.OpenWeatherMetric());

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return weather;
    }

    @Override
    public Weather getCurrentWeather(Location location) {
        int index = new Random().nextInt(weatherValues.length);

        Weather weather = new OpenWeather("Duisburg","DE",TIME_MILLI,weatherValues[index],
                descriptions[index],getRandomTemp(),getRandomTemp(),getRandomHumidity(),
                getRandomPressure(),getRandomWind(),new OpenWeatherMapUnits.OpenWeatherMetric());

        // We query weather from device location, so set the flag before returning the object.
        ((OpenWeather)weather).setObtainedFromDeviceLocation(true);

        try {
            Thread.sleep(1200);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return weather;
    }

    @Override
    public List<Weather> getWeatherForecast(String cityName) {
        Bundle workerArgs = new Bundle();
        workerArgs.putInt(ForecastWorker.TYPE_KEY,ForecastWorker.TYPE_CITY);
        workerArgs.putString(ForecastWorker.CITY_KEY,cityName);

        ForecastWorker worker = new ForecastWorker(workerArgs);

        worker.start();

        try {
            worker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return worker.getForecast();
    }
    
    @Override
    public List<Weather> getWeatherForecast(Location location) {
        Bundle workerArgs = new Bundle();
        workerArgs.putInt(ForecastWorker.TYPE_KEY,ForecastWorker.TYPE_LOCATION);
        workerArgs.putDouble(ForecastWorker.LAT_KEY,location.getLatitude());
        workerArgs.putDouble(ForecastWorker.LON_KEY,location.getLongitude());

        ForecastWorker worker = new ForecastWorker(workerArgs);

        worker.start();

        try {
            worker.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // We query forecast from device location, so set the flag before returning the objects.
        List<Weather> forecast = worker.getForecast();
        for (Weather w : forecast){
            OpenWeather openWeather = (OpenWeather) w;
            openWeather.setObtainedFromDeviceLocation(true);
        }
        return forecast;
    }

    private int getRandomTemp(){
        // Returns a temperature from -50 to 50
        Random r = new Random();
        return r.nextInt(101)-50;
    }

    private int getRandomHumidity(){
        // Returns random humidity 0-100 (%)
        Random r = new Random();
        return r.nextInt(101);
    }

    private int getRandomPressure(){
        Random r = new Random();
        return r.nextInt(600)+500;
    }

    private double getRandomWind(){
        Random r = new Random();
        int integerPart = r.nextInt(10);
        int fractionalPart = r.nextInt(10);
        return new Double(String.format("%d.%d",integerPart,fractionalPart));
    }

    private int returnRandomNumber(int limit){
        return new Random().nextInt(limit)+1;
    }

    private class ForecastWorker extends Thread{
        private static final int TYPE_CITY=0;
        private static final int TYPE_LOCATION=1;

        private static final String TYPE_KEY="worker type key";
        private static final String CITY_KEY="city key";
        private static final String LAT_KEY="lat key";
        private static final String LON_KEY="lon key";

        private List<Weather> forecast = new ArrayList<>();


        private Bundle args;

        ForecastWorker(Bundle args){
            this.args=args;
        }

        @Override
        public void run() {
            int itemSize = returnRandomNumber(WEATHER_ITEMS_MAX_SIZE);
            Random r = new Random();

            switch (getType()){

                case TYPE_CITY:

                    String cityName = this.args.getString(CITY_KEY);
                    for (int i=0; i<itemSize; i++){
                        // A random index that will feed with random mock values Weather objects
                        int index = r.nextInt(weatherValues.length);

                        Weather weather = new OpenWeather(cityName,"",TIME_MILLI,weatherValues[index],
                                descriptions[index],getRandomTemp(),getRandomTemp(),getRandomHumidity(),
                                getRandomPressure(),getRandomWind(),new OpenWeatherMapUnits.OpenWeatherMetric());

                        forecast.add(weather);
                    }
                    break;

                case TYPE_LOCATION:

                    double lat = this.args.getDouble(LAT_KEY);
                    double lon = this.args.getDouble(LAT_KEY);
                    for (int i=0; i<itemSize; i++){
                        int index = r.nextInt(weatherValues.length);

                        Weather weather = new OpenWeather("Duisburg","DE",TIME_MILLI,weatherValues[index],
                                descriptions[index],getRandomTemp(),getRandomTemp(),getRandomHumidity(),
                                getRandomPressure(),getRandomWind(),new OpenWeatherMapUnits.OpenWeatherMetric());

                        forecast.add(weather);
                    }
                    break;
                default:
                    throw new IllegalArgumentException(ForecastWorker.class.getSimpleName()
                            +": Unknown type.");
            }

            // sleep to mimic network delays
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        private int getType(){
            return this.args.getInt(TYPE_KEY);
        }

        List<Weather> getForecast(){
            return this.forecast;
        }
    }

}
