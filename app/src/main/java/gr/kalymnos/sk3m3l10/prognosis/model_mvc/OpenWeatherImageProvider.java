package gr.kalymnos.sk3m3l10.prognosis.model_mvc;

import gr.kalymnos.sk3m3l10.prognosis.R;

/**
 * Get proper drawable for OpenWeatherMap service.
 */

public class OpenWeatherImageProvider implements WeatherServiceImageProvider {

    private static final String CLASS_TAG = OpenWeatherImageProvider.class.getSimpleName();
    
    public static OpenWeatherImageProvider instance = null;

    private OpenWeatherImageProvider(){}

    public static OpenWeatherImageProvider getInstance(){
        if (instance==null){
            instance=new OpenWeatherImageProvider();
        }
        return instance;
    }

    /*
    *   OpenWeatherMap.org provides an image file name for every weather object.
    *   The getImage() will map that filename to a native drawable resource,
    *   relative to the specific weather condition of course.
    *
    * */
    @Override
    public int getImage(Object... params) {
        String iconFileName = (String) params[0];
        
        if (iconFileName!=null){
            switch (iconFileName){
                case "01d":
                    return R.drawable.sun;
                case "02d":
                    return R.drawable.sun_few_clouds;
                case "03d":
                    return R.drawable.clouds;
                case "04d":
                    return R.drawable.clouds;
                case "09d":
                    return R.drawable.heavy_rain;
                case "10d":
                    return R.drawable.scattered_rain;
                case "11d":
                    return R.drawable.storm;
                case "13d":
                    return R.drawable.snow;
                case "50d":
                    return R.drawable.clouds;
                case "01n":
                    return R.drawable.moon;
                case "02n":
                    return R.drawable.moon_few_clouds;
                case "03n":
                    return R.drawable.clouds;
                case "04n":
                    return R.drawable.clouds;
                case "09n":
                    return R.drawable.heavy_rain;
                case "10n":
                    return R.drawable.scattered_rain;
                case "11n":
                    return R.drawable.storm;
                case "13n":
                    return R.drawable.snow;
                case "50n":
                    return R.drawable.clouds;
                default:
                    throw new IllegalArgumentException(String.format("%s: Incorrect icon file name '%s'. ",CLASS_TAG,iconFileName));
            }
        }
        throw new IllegalArgumentException(CLASS_TAG+": Expected icon file name from OpenWeatherMap.org.");
    }
}
