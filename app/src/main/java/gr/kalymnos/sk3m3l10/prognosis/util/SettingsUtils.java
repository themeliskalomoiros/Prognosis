package gr.kalymnos.sk3m3l10.prognosis.util;

import android.bluetooth.le.ScanSettings;
import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.util.Xml;

import gr.kalymnos.sk3m3l10.prognosis.R;

/**
 * Utilities for app settings.
 */

public class SettingsUtils {

    private SettingsUtils(){}
    
    public static String getCityName(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getString(getCityPrefKey(context),getCityDefaultValue(context));
    }

    public static String getCityPrefKey(Context context){
        return context.getString(R.string.pref_location_key);
    }

    public static String getCityDefaultValue(Context context){
        return context.getString(R.string.pref_location_default);
    }

    public static boolean isDeviceLocationEnabled(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context)
                .getBoolean(getDeviceLocationPrefKey(context),getDeviceLocationDefaultValue(context));
    }

    public static String getDeviceLocationPrefKey(Context context){
        return context.getString(R.string.pref_enable_gps_search_key);
    }

    public static boolean getDeviceLocationDefaultValue(Context context){
        return context.getResources().getBoolean(R.bool.gps_search_by_default);
    }

    public static boolean areNotificationsEnabled(Context context){
        return PreferenceManager.getDefaultSharedPreferences(context).getBoolean(getNotificationEnabledPrefKey(context)
                ,getNotificationEnabledDefaultValue(context));
    }

    public static String getNotificationEnabledPrefKey(Context context){
        return context.getString(R.string.pref_notifications_enabled_key);
    }

    public static boolean getNotificationEnabledDefaultValue(Context context){
        return context.getResources().getBoolean(R.bool.weather_notifications_by_default);
    }

    public static String getNotificationTimePrefKey(Context context){
        return context.getString(R.string.pref_notification_time_key);
    }

    // @return: Time in hours.
    public static int getNotificationTimeValue(Context context){
        String entry = PreferenceManager.getDefaultSharedPreferences(context)
                .getString(getNotificationTimePrefKey(context),"");
        // TODO: 'Dirty' solution. Get the entry (ex. "12 hours") and return the numeric value.
        return Integer.parseInt(entry.split(" ")[0]);
    }

}
