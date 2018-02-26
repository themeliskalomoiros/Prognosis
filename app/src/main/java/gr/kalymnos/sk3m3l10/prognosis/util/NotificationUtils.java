package gr.kalymnos.sk3m3l10.prognosis.util;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts.MainActivity;

public class NotificationUtils {

    private static final int WEATHER_NOTIFICATION_INTENT_ID=1990;

    /*
       This method will create the pending intent which will trigger
       when the notification is pressed. This pending intent will open up
       the MainActivity.

       The flag is set to FLAG_UPDATE_CURRENT, so that if the intent is created
       again, keep the intent but update the data.
    */
    private static PendingIntent contentIntent(Context context){
        Intent startMainActivity = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context,WEATHER_NOTIFICATION_INTENT_ID,startMainActivity
                ,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    // This method will decode a bitmap needed for the notification.
    private static Bitmap largeIcon(Context context, int imgRes){
        return BitmapFactory.decodeResource(context.getResources(), imgRes);
    }
}
