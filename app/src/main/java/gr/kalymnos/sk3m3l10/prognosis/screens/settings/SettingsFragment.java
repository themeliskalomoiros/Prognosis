package gr.kalymnos.sk3m3l10.prognosis.screens.settings;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.preference.Preference;
import android.support.v7.preference.PreferenceFragmentCompat;
import android.support.v7.preference.PreferenceScreen;

import gr.kalymnos.sk3m3l10.prognosis.R;

/**
 * This fragment will host the app settings
 */

public class SettingsFragment extends PreferenceFragmentCompat implements SharedPreferences.OnSharedPreferenceChangeListener{

    private static final int QUERY_LOCATION_INDEX = 0;

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        // Load the preferences from the XML resourse
        this.addPreferencesFromResource(R.xml.preferences);

        setLocationSummary();
    }

    private void setLocationSummary() {
        SharedPreferences sharedPreferences = getPreferenceScreen().getSharedPreferences();
        String storedLocation = sharedPreferences.getString(getActivity().getString(R.string.pref_location_key)
                ,getActivity().getString(R.string.pref_location_default));

        PreferenceScreen settings = getPreferenceScreen();
        Preference locationSetting = settings.getPreference(QUERY_LOCATION_INDEX);
        locationSetting.setSummary(storedLocation);
    }

    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {

    }
}
