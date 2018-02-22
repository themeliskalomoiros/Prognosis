package gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts;

import android.os.Bundle;
import android.view.View;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.view_mvc.WeatherViewMvc;


public class WeatherViewMvcImpl implements WeatherViewMvc {

    private View rootView;
    private WeatherItemListener weatherItemListener;



    public WeatherViewMvcImpl() {
    }

    @Override
    public View getRootView() {
        return null;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }

    @Override
    public void bindWeatherItems(List<Weather> items) {

    }

    @Override
    public void setWeatherItemListener(WeatherItemListener listener) {

    }

    @Override
    public void displayProgressIndicator(boolean display) {

    }
}
