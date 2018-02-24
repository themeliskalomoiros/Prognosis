package gr.kalymnos.sk3m3l10.prognosis.screens.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import gr.kalymnos.sk3m3l10.prognosis.R;

/**
 * This fragment will host the app settings
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final int LOCATION_SETTING_INDEX = 0;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from the XML resourse
        this.addPreferencesFromResource(R.xml.preferences);

        this.setLocationSummary();
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
        String locationKey = this.getActivity().getString(R.string.pref_location_key);
        if (key.equals(sharedPreferences.getString(locationKey,""))){
            PreferenceScreen settings = getPreferenceScreen();
            Preference locationSetting = settings.getPreference(LOCATION_SETTING_INDEX);
            locationSetting.setSummary(sharedPreferences.getString(key,""));
        }
    }

    private void setLocationSummary() {
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        String storedLocation = sharedPreferences.getString(getActivity().getString(R.string.pref_location_key)
                ,getActivity().getString(R.string.pref_location_default));

        PreferenceScreen settings = getPreferenceScreen();
        Preference locationSetting = settings.getPreference(LOCATION_SETTING_INDEX);
        locationSetting.setSummary(storedLocation);
    }
}
