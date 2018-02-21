package gr.kalymnos.sk3m3l10.prognosis.util;

/**
 * Provides an image for specific weather
 */

public class WeatherImageProvider {

    // OpenWeatherMap.org weather values
    public static final String CLEAR_SKY = "clear sky";
    public static final String FEW_CLOUDS = "few clouds";
    public static final String SCATTERED_CLOUDS = "scattered clouds";
    public static final String BROKEN_CLOUDS = "broken clouds";
    public static final String SHOWER_RAIN = "shower rain";
    public static final String RAIN = "rain";
    public static final String THUNDERSTORM = "thunderstorm";
    public static final String SNOW = "snow";
    public static final String MIST = "mist";
    
    private static WeatherImageProvider instance = null;
    
    private WeatherImageProvider(){ /* Singleton */}

    public static WeatherImageProvider getInstance(){
        if (instance==null){
            instance=new WeatherImageProvider();
        }
        return instance;
    }
    
    public int getImage(String weather){
        switch (weather){
            
        }
    }
}
