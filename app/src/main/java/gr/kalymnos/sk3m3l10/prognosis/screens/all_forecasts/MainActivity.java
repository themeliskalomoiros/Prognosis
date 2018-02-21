package gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.CityWeather;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.LocationWeather;
import gr.kalymnos.sk3m3l10.prognosis.common.weather_units.OpenWeatherMapUnits;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;

public class MainActivity extends AppCompatActivity {

    List<Weather> weatherList;

    {
        weatherList = new ArrayList<>();
        Weather w1 = new CityWeather("Athens","GR",1519138800,"Clear","Great day, no clouds",15,8,86,55,56.4,new OpenWeatherMapUnits.OpenWeatherMetric());
        Weather w2 = new CityWeather("Rio De Janeiro","GR",1519138800,"Clear","Great day, no clouds",15,8,86,55,56.4,new OpenWeatherMapUnits.OpenWeatherMetric());
        Weather w3 = new CityWeather("Kalpaca","GR",1519138800,"Clear","Great day, no clouds",15,8,86,55,56.4,new OpenWeatherMapUnits.OpenWeatherMetric());
        Weather w4 = new LocationWeather(55555,666666,1519138800,"Clear","Great day, no clouds",15,8,86,55,56.4,new OpenWeatherMapUnits.OpenWeatherMetric());
        Weather w5 = new CityWeather("Kalymnos","GR",1519138800,"Clear","Great day, no clouds",15,8,86,55,56.4,new OpenWeatherMapUnits.OpenWeatherMetric());
        weatherList.add(w1);
        weatherList.add(w2);
        weatherList.add(w3);
        weatherList.add(w4);
        weatherList.add(w5);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rc = this.findViewById(R.id.recyclerview_forecast);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(layoutManager);

        WeatherListAdapter adapter = new WeatherListAdapter(this,weatherList);
        rc.setAdapter(adapter);
    }
}
