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

    private SettingsUtils settingsUtils;
    private Preference cityPref;
    private ListPreference notificationTimePref;
    private CheckBoxPreference notificationsEnabledPref;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from the XML resourse
        this.addPreferencesFromResource(R.xml.preferences);

        this.initialize();

        this.setLocationSummary();

        // set notification time preference summary
        this.notificationTimePref.setSummary(notificationTimePref.getEntry());

        this.setNotificationsTimeEnabled();
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
        if (key.equals(this.settingsUtils.getCityPrefKey())){
            this.setLocationSummary();
        }else if(key.equals(this.settingsUtils.getNotificationTimePrefKey())){
            notificationTimePref.setSummary(notificationTimePref.getEntry());
        }else if (key.equals(this.settingsUtils.getNotificationEnabledPrefKey())){
            this.setNotificationsTimeEnabled();
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
                .getString(this.settingsUtils.getCityPrefKey(),this.settingsUtils.getCityDefaultValue());
        cityPref.setSummary(cityName);
    }

    private void initialize(){
        this.settingsUtils = new SettingsUtils(this.getActivity(),this.getPreferenceScreen().getSharedPreferences());
        this.cityPref = this.findPreference(this.settingsUtils.getCityPrefKey());
        this.notificationTimePref = (ListPreference) this.findPreference(this.settingsUtils.getNotificationTimePrefKey());
        this.notificationsEnabledPref = (CheckBoxPreference) this.findPreference(this.settingsUtils.getNotificationEnabledPrefKey());
    }
}