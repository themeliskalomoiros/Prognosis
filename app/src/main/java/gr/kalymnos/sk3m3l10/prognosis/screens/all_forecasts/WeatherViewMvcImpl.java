package gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.view_mvc.WeatherViewMvc;


public class WeatherViewMvcImpl implements WeatherViewMvc {

    private View rootView;

    private RecyclerView recyclerView;
    private WeatherListAdapter adapter;

    private ProgressBar progressBar;

    public WeatherViewMvcImpl(LayoutInflater inflater, ViewGroup parent) {
        this.rootView = inflater.inflate(R.layout.activity_main,parent,false);
        this.progressBar = this.rootView.findViewById(R.id.pb_loading_indicator);
        this.adapter=new WeatherListAdapter(inflater.getContext());
        this.initRecyclerView();
    }

    private void initRecyclerView(){
        this.recyclerView=this.rootView.findViewById(R.id.recyclerview_forecast);
        /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter we pass in to the LinearLayoutManager
         * constructor. (Comment from Udacity)
         */
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(rootView.getContext(), LinearLayoutManager.VERTICAL, false);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(this.adapter);
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
    public void bindWeatherItems(List<Weather> items) {
        this.adapter.addItems(items);
        this.adapter.notifyDataSetChanged();
    }

    @Override
    public void setWeatherItemListener(WeatherItemListener listener) {
        this.adapter.setListener(listener);
    }

    @Override
    public void displayProgressIndicator(boolean display) {
        if (display){
            this.progressBar.setVisibility(View.VISIBLE);
        }else{
            this.progressBar.setVisibility(View.INVISIBLE);
        }
    }
}
