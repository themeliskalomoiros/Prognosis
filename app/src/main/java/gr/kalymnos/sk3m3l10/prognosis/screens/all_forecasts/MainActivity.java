package gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.CityWeather;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.LocationWeather;
import gr.kalymnos.sk3m3l10.prognosis.common.weather_units.OpenWeatherMapUnits;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.view_mvc.WeatherViewMvc;

import static gr.kalymnos.sk3m3l10.prognosis.view_mvc.WeatherViewMvc.WeatherItemListener;

public class MainActivity extends AppCompatActivity implements WeatherItemListener{

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

    private WeatherViewMvc view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = new WeatherViewMvcImpl(LayoutInflater.from(this),null);
        view.setWeatherItemListener(this);
        view.bindWeatherItems(this.weatherList);
        setContentView(view.getRootView());
    }

    @Override
    public void onWeatherItemClicked(int itemPosition) {
//        Toast.makeText(this, "Position "+itemPosition, Toast.LENGTH_SHORT).show();
        List<Weather> list = new ArrayList<>();
        for (int i=0; i<100; i++){
            list.add(new CityWeather("Rio de Janeiro","Bra",1519138800,
                    "clear sky","Great day, no clouds",
                    15,8,86,55,
                    56.4,new OpenWeatherMapUnits.OpenWeatherMetric()));
        }
        view.bindWeatherItems(list);
    }
}
