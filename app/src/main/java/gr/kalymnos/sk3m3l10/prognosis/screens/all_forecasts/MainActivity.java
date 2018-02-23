package gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.model_mvc.WeatherService;
import gr.kalymnos.sk3m3l10.prognosis.screens.detail.DetailActivity;
import gr.kalymnos.sk3m3l10.prognosis.view_mvc.WeatherViewMvc;
import static gr.kalymnos.sk3m3l10.prognosis.view_mvc.WeatherViewMvc.WeatherItemListener;

import static android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;

public class MainActivity extends AppCompatActivity implements WeatherItemListener,
LoaderCallbacks<List<Weather>>{

    private static final int ID_WEATHER_LOADER= 1821;

    private WeatherService weatherService;
    List<Weather> weatherList;

    private WeatherViewMvc view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.getSupportLoaderManager().initLoader(ID_WEATHER_LOADER,null,this);
    }

    @Override
    public void onWeatherItemClicked(int itemPosition) {
        Weather w = this.weatherList.get(itemPosition);

        Bundle extras = new Bundle();
        extras.putInt(DetailActivity.IMAGE_KEY,w.getImage());
        extras.putString(DetailActivity.DATE_KEY,w.getDate());
        extras.putString(DetailActivity.DESCRIPTION_KEY,w.getDescription());
        extras.putString(DetailActivity.HIGH_TEMP_KEY,w.getTempHighWithSymbol());
        extras.putString(DetailActivity.LOW_TEMP_KEY,w.getTempLowWithSymbol());
        extras.putString(DetailActivity.HUMIDITY_KEY,w.getHumidity());
        extras.putString(DetailActivity.PRESSURE_KEY,w.getPressure());
        extras.putString(DetailActivity.WIND_KEY,w.getWindWithSymbol());

        Intent intent = new Intent(this,DetailActivity.class);
        intent.putExtras(extras);

        this.startActivity(intent);
    }

    @Override
    public Loader<List<Weather>> onCreateLoader(int id, Bundle args) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<List<Weather>> loader, List<Weather> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Weather>> loader) {

    }
}
