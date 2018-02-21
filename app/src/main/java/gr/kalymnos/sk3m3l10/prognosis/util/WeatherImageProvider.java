package gr.kalymnos.sk3m3l10.prognosis.util;

import gr.kalymnos.sk3m3l10.prognosis.R;

/**
 * Provides an image for specific weather
 */

public class WeatherImageProvider {

    public static final int DAY_LOWER_BOUND = 7;
    public static final int DAY_UPPER_BOUND = 19;

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
    
    public int getImage(String weather, long timeMilli){
        DateFormater dateFormater = new DateFormater(timeMilli);
        boolean isDay = dateFormater.isDay(DAY_LOWER_BOUND,DAY_UPPER_BOUND);

        switch (weather){
            case CLEAR_SKY:
                if (isDay){
                    return R.drawable.sun;
                }else{
                    return R.drawable.moon;
                }
                
            case FEW_CLOUDS:
                if (isDay){
                    return R.drawable.sun_few_clouds;
                }else{
                    return R.drawable.moon_few_clouds;
                }

            case SCATTERED_CLOUDS:
                return R.drawable.clouds;
                
            case BROKEN_CLOUDS:
                return R.drawable.clouds;
                
            case RAIN:
                return R.drawable.scattered_rain;
                
            case SHOWER_RAIN:
                return R.drawable.heavy_rain;
                
            case THUNDERSTORM:
                return R.drawable.storm;
                
            case SNOW:
                return R.drawable.snow;
                
            case MIST:
                return R.drawable.clouds;
                
            default:
                throw new IllegalArgumentException("Unknown weather value from OpenWeatherMap.com");
        }
    }
}
