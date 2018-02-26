package gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.AsyncTaskLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

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
        LoaderCallbacks<List<Weather>>, SharedPreferences.OnSharedPreferenceChangeListener,
        LocationListener{

    private static final String CLASS_TAG = MainActivity.class.getSimpleName();

    /* ---------------------------- LOCATION ----------------------------------------------------*/
    private static final long TIME_INTERVAL = 600000;
    private static final float DISTANCE = 10000;
    private static final int PERMISSION_REQUEST_CODE=11;
    private LocationManager locationManager=null;
    /* ------------------------------------------------------------------------------------------*/

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

        startLoaderForCity();
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (this.settingUtils.isDeviceLocationEnabled()){
            initializeLocationListener();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        clearLocationManager();
    }

    private void clearLocationManager() {
        if (this.locationManager!=null){
            this.locationManager.removeUpdates(this);
            this.locationManager=null;
        }
    }

    @Override
    protected void onDestroy() {
        this.defaultPreferences.unregisterOnSharedPreferenceChangeListener(this);
        super.onDestroy();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode){
            case PERMISSION_REQUEST_CODE:
                if (grantResults.length==2 && grantResults[0]==PackageManager.PERMISSION_GRANTED && grantResults[1]==PackageManager.PERMISSION_GRANTED){
                    // permission granted
                    locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,TIME_INTERVAL,DISTANCE,this);
                }else{
                    Toast.makeText(this, this.getString(R.string.permission_location_denied)
                            +" "+this.settingUtils.getCityName(), Toast.LENGTH_SHORT).show();
                    startLoaderForCity();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
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

    private void initializeLocationListener(){
        if (this.locationManager==null){
            this.locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
            boolean providersEnabled = this.locationManager.getAllProviders().size()>0;
            if (providersEnabled){
                requestLocation();
            }
        }
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

    private void requestLocation(){
        boolean grantedGpsPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        boolean grantedWiFiPermission = ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
        if (grantedGpsPermission || grantedWiFiPermission){
            // permision granted
            // TODO: I could check the last known location and if its new use that instead (LocationManager.getLastKnownLocation()).
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,TIME_INTERVAL,DISTANCE,this);
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,TIME_INTERVAL,DISTANCE,this);
        }else{
            // permission denied, ask for permission from the users (works in Marshmellow and later)
            if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.M){

                // if continues to deny, we will explain why we need this permission
                if (ActivityCompat.shouldShowRequestPermissionRationale(this,Manifest.permission.ACCESS_FINE_LOCATION)){
                    this.showAlertDialog(R.string.permission_location_denied_title,R.string.permission_location_explanation_msg);
                }
                // request permissions (calls onRequestPermissionsResult()

                ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.ACCESS_COARSE_LOCATION
                        ,Manifest.permission.ACCESS_FINE_LOCATION},PERMISSION_REQUEST_CODE);
            }
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

    private void startLoaderForCity(){
        Bundle loaderArgs = getLoaderArgs(TYPE_FETCH_FROM_CITY_NAME);
        loaderArgs.putString(CITY_NAME_KEY,this.settingUtils.getCityName());
        this.getSupportLoaderManager().restartLoader(ID_WEATHER_LOADER,loaderArgs,this);
    }

    private void startLoaderForLocation(Location location){
        Bundle loaderArgs = getLoaderArgs(TYPE_FETCH_FROM_DEVICE_LOCATION);
        loaderArgs.putDouble(LON_KEY,location.getLongitude());
        loaderArgs.putDouble(LAT_KEY,location.getLatitude());
        this.getSupportLoaderManager().restartLoader(ID_WEATHER_LOADER,loaderArgs,this);
    }

    private Bundle getLoaderArgs(int fetchType){
        Bundle loaderArgs = new Bundle();
        loaderArgs.putInt(TYPE_FETCH_KEY,fetchType);
        return loaderArgs;
    }

    // Called when user changes something in app Settings.
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(this.settingUtils.getCityPrefKey())){
            // User inputted a new city
            this.forceLoad = true;
            startLoaderForCity();
        }else if(key.equals(this.settingUtils.getDeviceLocationPrefKey())){
            if (!this.settingUtils.isDeviceLocationEnabled()){
                /*
                   We don't have to start a loader to fetch weather data here
                *  because this action will be done anyway in onStart().
                */
                Log.d(CLASS_TAG,"GPS enabled.");
            }else{
                // User disabled location awareness, clear location listener
                clearLocationManager();
                // force a load
                this.forceLoad=true;
                startLoaderForCity();
            }
        }else if(key.equals(this.settingUtils.getNotificationEnabledPrefKey())){
            if (this.settingUtils.areNotificationsEnabled()){
                Log.d(CLASS_TAG,"Notifications enabled.");
            }else{
                Log.d(CLASS_TAG,"Notifications disabled.");
            }
        }
    }

    @Override
    public void onLocationChanged(Location location) {
        if (location!=null){
            // force a load
            this.forceLoad=true;
            startLoaderForLocation(location);
        }else{
            Toast.makeText(this, this.getString(R.string.location_not_found_msg)+" "
                    +this.settingUtils.getCityName(), Toast.LENGTH_SHORT).show();
            startLoaderForCity();
        }
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    private void showAlertDialog(int titleRes, int msgRes){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(titleRes)
                .setMessage(msgRes)
                .create()
                .show();
    }
}
