package gr.kalymnos.sk3m3l10.prognosis.util;

import android.content.Context;
import android.content.SharedPreferences;

import gr.kalymnos.sk3m3l10.prognosis.R;

/**
 * Utilities for app settings.
 */

public class SettingsUtils {
    private SharedPreferences settingPreferences = null;
    private Context context;

    public SettingsUtils(Context context, SharedPreferences settingPreferences){
        this.context=context;
        this.settingPreferences=settingPreferences;
    }

    public String getCityNameFromSettings(){
        String locationKey = this.context.getString(R.string.pref_location_key);
        String defaultValue = this.context.getString(R.string.pref_location_default);
        return this.settingPreferences.getString(locationKey,defaultValue);
    }

    public boolean isSettingsLocationEnabled(){
        String deviceLocationSettingKey = this.context.getString(R.string.pref_enable_gps_search_key);
        boolean defaultLocationSettingValue = this.context.getResources().getBoolean(R.bool.gps_search_by_default);
        return this.settingPreferences.getBoolean(deviceLocationSettingKey,defaultLocationSettingValue);
    }

}
