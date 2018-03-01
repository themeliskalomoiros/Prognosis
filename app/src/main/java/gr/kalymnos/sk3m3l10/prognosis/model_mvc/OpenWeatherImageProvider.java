package gr.kalymnos.sk3m3l10.prognosis.model_mvc;

/**
 * Get proper drawable for OpenWeatherMap service.
 */

public class OpenWeatherImageProvider implements WeatherServiceImageProvider {

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
        return 0;
    }
}
