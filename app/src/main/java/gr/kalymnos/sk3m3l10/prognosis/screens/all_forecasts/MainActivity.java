package gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v7.app.AppCompatActivity;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.model_mvc.FakeWeatherService;
import gr.kalymnos.sk3m3l10.prognosis.model_mvc.WeatherService;
import gr.kalymnos.sk3m3l10.prognosis.screens.detail.DetailActivity;
import gr.kalymnos.sk3m3l10.prognosis.view_mvc.WeatherViewMvc;
import static gr.kalymnos.sk3m3l10.prognosis.view_mvc.WeatherViewMvc.WeatherItemListener;

import static android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.Loader;
import android.view.LayoutInflater;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements WeatherItemListener,
LoaderCallbacks<List<Weather>>{

    private static final int ID_WEATHER_LOADER= 1821;

    private WeatherService weatherService;
    List<Weather> weatherList;

    private WeatherViewMvc view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create the mvc view to display the weather forecast
        this.view = new WeatherViewMvcImpl(LayoutInflater.from(this),null);
        this.view.setWeatherItemListener(this);
        setContentView(view.getRootView());

        // define the weather service
        this.weatherService = new FakeWeatherService();

        // initialize the loader to start fetching weather from a service
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
    public Loader<List<Weather>> onCreateLoader(int loaderId, Bundle args) {
        switch (loaderId){
            case ID_WEATHER_LOADER:
                return new AsyncTaskLoader<List<Weather>>(this) {

                    @Override
                    protected void onStartLoading() {
                        // we start fetching, display the progress
                        view.displayProgressIndicator(true);
                        this.forceLoad();
                    }

                    @Nullable
                    @Override
                    public List<Weather> loadInBackground() {
                        // fetch the weather
                        return weatherList = weatherService.getWeatherForecast("Athens");
                    }
                };
            default:
                return null;
        }
    }

    @Override
    public void onLoadFinished(Loader<List<Weather>> loader, List<Weather> data) {
        // load is finished, hide the progress bar and
        // command the MVC view to bind the weather data
        view.displayProgressIndicator(false);
        view.bindWeatherItems(data);
    }

    @Override
    public void onLoaderReset(Loader<List<Weather>> loader) {

    }
}
