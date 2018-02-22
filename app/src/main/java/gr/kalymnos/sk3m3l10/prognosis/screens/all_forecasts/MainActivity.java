package gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.CityWeather;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.LocationWeather;
import gr.kalymnos.sk3m3l10.prognosis.common.weather_units.OpenWeatherMapUnits;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;

public class MainActivity extends AppCompatActivity implements WeatherListAdapter.OnWeatherItemClickListener{

    List<Weather> weatherList;

    {
        weatherList = new ArrayList<>();
        for (int i=0; i<100; i++){
            weatherList.add(new CityWeather("Kalpaca","GR",1519138800,
                    "rain","Great day, no clouds",
                    15,8,86,55,
                    56.4,new OpenWeatherMapUnits.OpenWeatherMetric()));
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView rc = this.findViewById(R.id.recyclerview_forecast);
        LinearLayoutManager layoutManager =
                new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        rc.setLayoutManager(layoutManager);

        WeatherListAdapter adapter = new WeatherListAdapter(this,weatherList,this);
        rc.setAdapter(adapter);
    }

    @Override
    public void onWeatherItemClick(int itemPosition) {
        Toast.makeText(this, "Position "+itemPosition, Toast.LENGTH_SHORT).show();
    }
}
