package gr.kalymnos.sk3m3l10.prognosis.util;

import gr.kalymnos.sk3m3l10.prognosis.R;

/**
 * Provides an image for specific weather
 */

public class WeatherImageProvider {

    private static final int DAY_LOWER_BOUND = 7;
    private static final int DAY_UPPER_BOUND = 19;

    // OpenWeatherMap.org weather values
    private static final String CLEAR_SKY = "clear sky";
    private static final String FEW_CLOUDS = "few clouds";
    private static final String SCATTERED_CLOUDS = "scattered clouds";
    private static final String BROKEN_CLOUDS = "broken clouds";
    private static final String SHOWER_RAIN = "shower rain";
    private static final String RAIN = "rain";
    private static final String THUNDERSTORM = "thunderstorm";
    private static final String SNOW = "snow";
    private static final String MIST = "mist";
    
    private WeatherImageProvider(){}
    
    public static int getImage(String weather, long timeMilli){
        DateUtils dateUtils = new DateUtils(timeMilli);
        boolean isDay = dateUtils.isDay(DAY_LOWER_BOUND,DAY_UPPER_BOUND);

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
