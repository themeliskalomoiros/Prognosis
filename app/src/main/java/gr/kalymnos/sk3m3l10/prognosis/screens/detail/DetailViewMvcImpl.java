package gr.kalymnos.sk3m3l10.prognosis.screens.detail;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.view_mvc.DetailViewMvc;

/**
 * Created by skemelio on 22.02.18.
 */

public class DetailViewMvcImpl implements DetailViewMvc {

    private View rootView;

    private TextView tvDate, tvWeatherDescription,tvHighTemp,tvLowTemp,tvHumidity,tvPressure,tvWind;
    private ImageView imageView;

    public DetailViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        this.rootView=inflater.inflate(R.layout.activity_detail,parent,false);
        this.initializeViews();
    }

    private void initializeViews(){
        this.tvDate = this.rootView.findViewById(R.id.date);
        this.tvWeatherDescription=this.rootView.findViewById(R.id.weather_description);
        this.tvHighTemp= this.rootView.findViewById(R.id.high_temperature);
        this.tvLowTemp=this.rootView.findViewById(R.id.low_temperature);
        this.tvHumidity= this.rootView.findViewById(R.id.humidity);
        this.tvPressure=this.rootView.findViewById(R.id.pressure);
        this.tvWind= this.rootView.findViewById(R.id.wind_measurement);
        this.imageView=this.rootView.findViewById(R.id.weather_icon);
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
    public void bindWeather(Weather weather) {

    }
}
