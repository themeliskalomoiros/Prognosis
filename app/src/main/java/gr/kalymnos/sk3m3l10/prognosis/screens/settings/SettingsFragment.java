package gr.kalymnos.sk3m3l10.prognosis.screens.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceCategory;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;
import android.widget.Toast;

import gr.kalymnos.sk3m3l10.prognosis.R;

/**
 * This fragment will host the app settings
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    private String cityNameKey, cityNameDefaultValue, notificationTimeKey;
    private Preference cityPref;
    private ListPreference notificationTimePref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from the XML resourse
        this.addPreferencesFromResource(R.xml.preferences);

        this.initialize();

        // set city preference summary
        String cityName = this.getPreferenceScreen().getSharedPreferences()
                .getString(cityNameKey,cityNameDefaultValue);
        cityPref.setSummary(cityName);

        // set notification time preference summary
        notificationTimePref.setSummary(notificationTimePref.getEntry());
    }

    private void initialize(){
        this.cityNameKey = this.getString(R.string.pref_location_key);
        this.cityNameDefaultValue = this.getString(R.string.pref_location_default);
        this.notificationTimeKey = this.getString(R.string.pref_notification_time_key);

        this.cityPref = this.findPreference(cityNameKey);
        this.notificationTimePref = (ListPreference) this.findPreference(this.getString(R.string.pref_notification_time_key));
    }

    @Override
    public void onResume() {
        super.onResume();
        this.getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onPause() {
        super.onPause();
        this.getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (key.equals(this.cityNameKey)){
            String cityName = this.getPreferenceScreen().getSharedPreferences()
                    .getString(cityNameKey,cityNameDefaultValue);
            cityPref.setSummary(cityName);
        }else if(key.equals(this.notificationTimeKey)){
            notificationTimePref.setSummary(notificationTimePref.getEntry());
        }
    }
}