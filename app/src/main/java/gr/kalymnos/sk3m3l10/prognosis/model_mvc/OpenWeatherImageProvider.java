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

    @Override
    public int getImage(Object... params) {
        return 0;
    }
}
