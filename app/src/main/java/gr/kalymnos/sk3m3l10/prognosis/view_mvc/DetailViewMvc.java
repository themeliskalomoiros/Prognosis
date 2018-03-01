package gr.kalymnos.sk3m3l10.prognosis.view_mvc;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;

/**
 * A MVC view that will display details of
 * a selected weather forecast to the user
 */

public interface DetailViewMvc extends ViewMvc {
    public void bindImage(int imgResId);
    public void bindDate(String s);
    public void bindDescription(String s);
    public void bindHighTemp(String s);
    public void bindLowTemp(String s);
    public void bindHumidity(String s);
    public void bindPressure(String s);
    public void bindWind(String s);
    public void bindApproxHour(String s);
}
