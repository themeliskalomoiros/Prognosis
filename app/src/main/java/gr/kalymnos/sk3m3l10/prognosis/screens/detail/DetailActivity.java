package gr.kalymnos.sk3m3l10.prognosis.screens.detail;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.view_mvc.DetailViewMvc;

public class DetailActivity extends AppCompatActivity {

    public static final java.lang.String IMG_KEY = "img key";
    public static final java.lang.String QUERY_TITLE_KEY = "query title key";
    public static final java.lang.String WEATHER_KEY = "weather key";
    public static final java.lang.String DESCRIPTION_KEY = "description key";
    public static final java.lang.String HIGH_TEMP_KEY = "high temp key";
    public static final java.lang.String LOW_TEMP_KEY = "low temp key";
    public static final java.lang.String HUMIDITY_KEY = "humidity key";
    public static final java.lang.String PRESSURE_KEY = "pressure key";
    public static final java.lang.String WIND_KEY = "wind key";
    
    private DetailViewMvc detailViewMvc;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState!=null){
            this.detailViewMvc=new DetailViewMvcImpl(LayoutInflater.from(this),null);
            
            setContentView(R.layout.activity_detail);
        }else{
            // TODO: Do something if the intent does not contain bundled info
        }
    }
    
    private Weather getWeatherFromExtras(Bundle extras){
        int imgRes = extras.getInt(IMG_KEY);
        String queryTitle = extras.getString(QUERY_TITLE_KEY);
        String weather = extras.getString(WEATHER_KEY);
        String description = extras.getString(DESCRIPTION_KEY);
        String highTemp = extras.getString(HIGH_TEMP_KEY);
        String lowTemp = extras.getString(LOW_TEMP_KEY);
        String humidity = extras.getString(HUMIDITY_KEY);
        String pressure = extras.getString(PRESSURE_KEY);
        String wind = extras.getString(WIND_KEY);
    }
}
