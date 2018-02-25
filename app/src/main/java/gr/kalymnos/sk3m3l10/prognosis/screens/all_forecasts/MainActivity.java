package gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.tasks.OnSuccessListener;

import java.util.List;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.model_mvc.FakeWeatherService;
import gr.kalymnos.sk3m3l10.prognosis.model_mvc.WeatherService;
import gr.kalymnos.sk3m3l10.prognosis.screens.detail.DetailActivity;
import gr.kalymnos.sk3m3l10.prognosis.screens.settings.SettingsActivity;
import gr.kalymnos.sk3m3l10.prognosis.util.SettingsUtils;
import gr.kalymnos.sk3m3l10.prognosis.view_mvc.WeatherViewMvc;

import static android.support.v4.app.LoaderManager.LoaderCallbacks;
import static gr.kalymnos.sk3m3l10.prognosis.view_mvc.WeatherViewMvc.WeatherItemListener;

public class MainActivity extends AppCompatActivity implements WeatherItemListener,
        LoaderCallbacks<List<Weather>>, SharedPreferences.OnSharedPreferenceChangeListener {

    private static final String CLASS_TAG = MainActivity.class.getSimpleName();

    /* ---------------------------- LOADER ------------------------------------------------------*/
    private static final int ID_WEATHER_LOADER = 1821;
    private static final int TYPE_FETCH_FROM_DEVICE_LOCATION = 1010;
    private static final int TYPE_FETCH_FROM_CITY_NAME = 1011;
    // when access Loader<List<Weather>> args, this key will return the fetch type of the Loader.
    private static final String TYPE_FETCH_KEY = "loader type fetch key";
    private static final String CITY_NAME_KEY = "city name key";
    private static final String LAT_KEY = "latitude key";
    private static final String LON_KEY = "longitude key";
    /* ------------------------------------------------------------------------------------------*/

    private WeatherService weatherService;
    private List<Weather> weatherList = null;

    private WeatherViewMvc view;

    private SharedPreferences defaultPreferences;
    private SettingsUtils settingUtils = null;

    private static final int PERMISSION_LOCATION_REQUEST_CODE = 13;
    private FusedLocationProviderClient locationClient = null;

    // When true Loader is forced to load new data
    private boolean forceLoad = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // create the mvc view to display the weather forecast
        this.view = new WeatherViewMvcImpl(LayoutInflater.from(this), null);
        this.view.setWeatherItemListener(this);
        setContentView(view.getRootView());

        // define the weather service
        this.weatherService = new FakeWeatherService();

        // initialize default shared preferences (settings) and setting utils.
        this.defaultPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        this.defaultPreferences.registerOnSharedPreferenceChangeListener(this);
        this.settingUtils = new SettingsUtils(this,this.defaultPreferences);

        startFetchingWeatherForTheFirstTime();
    }

    private void startFetchingWeatherForTheFirstTime(){
        if (this.settingUtils.isSettingsLocationEnabled()) {
            // Aqcuire location and fetch weather
        }else{
            // Fetch weather usin city-name defined in settigns
            startLoaderForCity(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        this.getMenuInflater().inflate(R.menu.main_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.menu_item_settings:
                this.startActivity(new Intent(this, SettingsActivity.class));
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        this.defaultPreferences.unregisterOnSharedPreferenceChangeListener(this);
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

    

    private void startLoaderForCity(boolean init){
        Bundle loaderArgs = getLoaderArgs(TYPE_FETCH_FROM_CITY_NAME);
        loaderArgs.putString(CITY_NAME_KEY,this.settingUtils.getCityNameFromSettings());
        if (init){
            this.getSupportLoaderManager().initLoader(ID_WEATHER_LOADER,loaderArgs,this);
        }else{
            this.getSupportLoaderManager().restartLoader(ID_WEATHER_LOADER,loaderArgs,this);
        }
    }

    private void startLoaderForLocation(boolean init, Location location){
        Bundle loaderArgs = getLoaderArgs(TYPE_FETCH_FROM_DEVICE_LOCATION);
        loaderArgs.putDouble(LON_KEY,location.getLongitude());
        loaderArgs.putDouble(LAT_KEY,location.getLatitude());
        if (init){
            this.getSupportLoaderManager().initLoader(ID_WEATHER_LOADER,loaderArgs,this);
        }else{
            this.getSupportLoaderManager().restartLoader(ID_WEATHER_LOADER,loaderArgs,this);
        }
    }

    @Override
    public Loader<List<Weather>> onCreateLoader(int loaderId, Bundle args) {
        switch (loaderId){
            case ID_WEATHER_LOADER:
                return new AsyncTaskLoader<List<Weather>>(this) {

                    @Override
                    protected void onStartLoading() {
                        if (weatherList!=null && !forceLoad){
                            // Delivers any previously loaded data immediately
                            deliverResult(weatherList);
                        }else{
                            // reset the flag
                            forceLoad=false;

                            // Start fetching, display the progress
                            view.displayProgressIndicator(true);

                            // Force a new load
                            this.forceLoad();

                        }
                    }

                    @Nullable
                    @Override
                    public List<Weather> loadInBackground() {
                        if (args!=null){
                            int fetchType = args.getInt(TYPE_FETCH_KEY);
                            switch (fetchType){
                                case TYPE_FETCH_FROM_CITY_NAME:
                                    // fetch the weather
                                    String cityName = args.getString(CITY_NAME_KEY);
                                    return weatherList = weatherService.getWeatherForecast(cityName);
                                case TYPE_FETCH_FROM_DEVICE_LOCATION:
                                    double lat = args.getDouble(LAT_KEY);
                                    double lon = args.getDouble(LON_KEY);
                                    return weatherList = weatherService.getWeatherForecast(lat,lon);
                                default:
                                    throw new IllegalArgumentException(CLASS_TAG+": Unknown loader fetch type!");
                            }
                        }else{
                            throw new NullPointerException(CLASS_TAG+": Loader can't load in background, args are null!");
                        }
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

    private Bundle getLoaderArgs(int fetchType){
        Bundle loaderArgs = new Bundle();
        loaderArgs.putInt(TYPE_FETCH_KEY,fetchType);
        return loaderArgs;
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(this.getString(R.string.pref_location_key))){
            /*
                  Location in settings changed.
               1) State that the loader is forced to load new data.
               2) Fetch the new weather data
            */
            this.forceLoad = true;
            startLoaderForCity(false);
        }else if(key.equals(this.getString(R.string.pref_enable_gps_search_key))){
            // TODO: gps setting changed
            boolean gpsEnabled = this.defaultPreferences.getBoolean(key,this.getResources().getBoolean(R.bool.gps_search_by_default));
            if (gpsEnabled){
                Log.d(CLASS_TAG,"GPS enabled.");
            }else{
                Log.d(CLASS_TAG,"GPS disabled.");
            }
        }else if(key.equals(this.getString(R.string.pref_weather_notifications_search_key))){
            // TODO: weather notification setting changed
            boolean notificationsEnabled = this.defaultPreferences.getBoolean(key,this.getResources().getBoolean(R.bool.weather_notifications_by_default));
            if (notificationsEnabled){
                Log.d(CLASS_TAG,"Notifications enabled.");
            }else{
                Log.d(CLASS_TAG,"Notifications disabled.");
            }
        }
    }
}
