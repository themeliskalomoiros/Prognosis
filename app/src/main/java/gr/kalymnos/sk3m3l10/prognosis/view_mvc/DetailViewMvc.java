package gr.kalymnos.sk3m3l10.prognosis.view_mvc;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;

/**
 * A MVC view that will display details of
 * a selected weather forecast to the user
 */

public interface DetailViewMvc extends ViewMvc {
    public void bindWeather(Weather weather);
}
