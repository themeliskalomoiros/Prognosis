package gr.kalymnos.sk3m3l10.prognosis.model_mvc;

/**
 * This class provides proper drawables for weather fetched from a weather service.
 */

public interface WeatherServiceImageProvider {
    public int getImage(Object... params);
}
