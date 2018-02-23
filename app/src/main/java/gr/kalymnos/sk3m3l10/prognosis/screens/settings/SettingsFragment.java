package gr.kalymnos.sk3m3l10.prognosis.screens.settings;

import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.support.annotation.Nullable;

import gr.kalymnos.sk3m3l10.prognosis.R;

/**
 * This fragment will host the app settings
 */

public class SettingsFragment extends PreferenceFragment {
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from the XML resourse
        this.addPreferencesFromResource(R.xml.preferences);
    }
}
