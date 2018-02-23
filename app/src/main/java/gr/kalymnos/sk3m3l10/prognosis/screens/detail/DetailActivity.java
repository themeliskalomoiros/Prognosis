package gr.kalymnos.sk3m3l10.prognosis.screens.detail;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.screens.settings.SettingsActivity;
import gr.kalymnos.sk3m3l10.prognosis.view_mvc.DetailViewMvc;

public class DetailActivity extends AppCompatActivity {

    public static final String DATE_KEY = "date key";
    public static final String DESCRIPTION_KEY = "description key";
    public static final String HIGH_TEMP_KEY = "high temp key";
    public static final String LOW_TEMP_KEY = "low temp key";
    public static final String HUMIDITY_KEY = "humidity key";
    public static final String PRESSURE_KEY = "pressure key";
    public static final String WIND_KEY = "wind key";
    public static final String IMAGE_KEY= "image key";
    
    private DetailViewMvc detailViewMvc;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = this.getIntent().getExtras();
        if (extras!=null){
            this.detailViewMvc=new DetailViewMvcImpl(LayoutInflater.from(this),null);

            this.detailViewMvc.bindDate(extras.getString(DATE_KEY));
            this.detailViewMvc.bindImage(extras.getInt(IMAGE_KEY));
            this.detailViewMvc.bindDescription(extras.getString(DESCRIPTION_KEY));
            this.detailViewMvc.bindHighTemp(extras.getString(HIGH_TEMP_KEY));
            this.detailViewMvc.bindLowTemp(extras.getString(LOW_TEMP_KEY));
            this.detailViewMvc.bindHumidity(extras.getString(HUMIDITY_KEY));
            this.detailViewMvc.bindPressure(extras.getString(PRESSURE_KEY));
            this.detailViewMvc.bindWind(extras.getString(WIND_KEY));
            setContentView(this.detailViewMvc.getRootView());
        }else{
            // TODO: Do something (better) if the intent does not contain bundled info
            this.finish();
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
}
