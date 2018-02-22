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
 * An implementation of the Detail MVC View
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
        return this.rootView;
    }

    @Override
    public Bundle getViewState() {
        return null;
    }


    @Override
    public void bindImage(int imgResId) {
        this.imageView.setImageResource(imgResId);
    }

    @Override
    public void bindDate(String s) {
        this.tvDate.setText(s);
    }

    @Override
    public void bindDescription(String s) {
        this.tvWeatherDescription.setText(s);
    }

    @Override
    public void bindHighTemp(String s) {
        this.tvHighTemp.setText(s);
    }

    @Override
    public void bindLowTemp(String s) {
        this.tvLowTemp.setText(s);
    }

    @Override
    public void bindHumidity(String s) {
        this.tvHumidity.setText(s);
    }

    @Override
    public void bindPressure(String s) {
        this.tvPressure.setText(s);
    }

    @Override
    public void bindWind(String s) {
        this.tvWind.setText(s);
    }
}
