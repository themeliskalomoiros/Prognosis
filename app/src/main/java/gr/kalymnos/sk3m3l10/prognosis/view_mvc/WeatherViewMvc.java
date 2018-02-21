package gr.kalymnos.sk3m3l10.prognosis.view_mvc;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;

/**
 * A MVC view that will display weather forecasts to the user
 */

public interface WeatherViewMvc extends ViewMvc {

    interface WeatherItemListener{
        /*
        *   This will be invoked when the user clicks a weather item in the list
        * */
        void onWeatherItemClicked();
    }


    /*
    *   Bind Weather items to the weather list which is displayed by this MVC View
    * */
    void bindWeatherItems(List<Weather> items);

    /*
    *   Set a listener that will be notified by this MVC View
    *   -null to clear
    * */
    void setWeatherItemListener(WeatherItemListener listener);


    void displayProgressIndicator(boolean display);
}
