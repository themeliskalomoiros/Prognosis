package gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.view_mvc.WeatherViewMvc;


public class WeatherViewMvcImpl implements WeatherViewMvc {

    private View rootView;
    private WeatherItemListener weatherItemListener;

    private RecyclerView recyclerView;
    private WeatherListAdapter adapter;

    public WeatherViewMvcImpl(LayoutInflater inflater, ViewGroup parent, WeatherListAdapter adapter) {
        this.rootView = inflater.inflate(R.layout.activity_main,parent,false);
        this.adapter = adapter;

        // recycler view initialization
        this.recyclerView=this.rootView.findViewById(R.id.recyclerview_forecast);
        /*
         * A LinearLayoutManager is responsible for measuring and positioning item views within a
         * RecyclerView into a linear list. This means that it can produce either a horizontal or
         * vertical list depending on which parameter you pass in to the LinearLayoutManager
         * constructor. In our case, we want a vertical list, so we pass in the constant from the
         * LinearLayoutManager class for vertical lists, LinearLayoutManager.VERTICAL.
         *
         * There are other LayoutManagers available to display your data in uniform grids,
         * staggered grids, and more! See the developer documentation for more details.
         *
         * The third parameter (shouldReverseLayout) should be true if you want to reverse your
         * layout. Generally, this is only true with horizontal lists that need to support a
         * right-to-left layout.
         */
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(inflater.getContext(), LinearLayoutManager.VERTICAL, false);
        this.recyclerView.setLayoutManager(layoutManager);
        this.recyclerView.setAdapter(adapter);
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
