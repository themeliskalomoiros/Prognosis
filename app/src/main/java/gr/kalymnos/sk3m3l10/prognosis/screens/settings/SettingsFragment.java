package gr.kalymnos.sk3m3l10.prognosis.screens.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.CheckBoxPreference;
import android.support.v7.preference.ListPreference;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.widget.Toast;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.util.SettingsUtils;

/**
 * This fragment will host the app settings
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{
    
    private Preference cityPref;
    private ListPreference notificationTimePref;
    private CheckBoxPreference notificationsEnabledPref,geoLocationPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from the XML resourse
        this.addPreferencesFromResource(R.xml.preferences);

        this.initialize();

        this.setLocationSummary();

        // set notification time preference summary
        this.notificationTimePref.setSummary(notificationTimePref.getEntry());

        this.setNotificationsTimeEnabled();
        this.setCityNameEnabled();
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
        if (key.equals(SettingsUtils.getCityPrefKey(this.getActivity()))){
            this.setLocationSummary();
        }else if(key.equals(SettingsUtils.getNotificationTimePrefKey(this.getActivity()))){
            notificationTimePref.setSummary(notificationTimePref.getEntry());
        }else if (key.equals(SettingsUtils.getNotificationEnabledPrefKey(this.getActivity()))){
            this.setNotificationsTimeEnabled();
        }else if (key.equals(SettingsUtils.getDeviceLocationPrefKey(this.getActivity()))){
            this.setCityNameEnabled();
        }
    }

    private void setCityNameEnabled() {
        if (this.geoLocationPref.isChecked()){
            this.cityPref.setEnabled(false);
        }else{
            this.cityPref.setEnabled(true);
        }
    }

    private void setNotificationsTimeEnabled(){
        if (this.notificationsEnabledPref.isChecked()){
            this.notificationTimePref.setEnabled(true);
        }else{
            this.notificationTimePref.setEnabled(false);
        }
    }

    private void setLocationSummary(){
        String cityName = this.getPreferenceScreen().getSharedPreferences()
                .getString(SettingsUtils.getCityPrefKey(this.getActivity()),SettingsUtils.getCityDefaultValue(this.getActivity()));
        cityPref.setSummary(cityName);
    }

    private void initialize(){
        this.cityPref = this.findPreference(SettingsUtils.getCityPrefKey(this.getActivity()));
        this.notificationTimePref = (ListPreference) this.findPreference(SettingsUtils.getNotificationTimePrefKey(this.getActivity()));
        this.notificationsEnabledPref = (CheckBoxPreference) this.findPreference(SettingsUtils.getNotificationEnabledPrefKey(this.getActivity()));
        this.geoLocationPref= (CheckBoxPreference) this.findPreference(SettingsUtils.getDeviceLocationPrefKey(this.getActivity()));
    }
}