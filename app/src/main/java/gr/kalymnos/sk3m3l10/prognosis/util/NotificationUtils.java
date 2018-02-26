package gr.kalymnos.sk3m3l10.prognosis.util;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts.MainActivity;

public class NotificationUtils {

    private static final int WEATHER_NOTIFICATION_INTENT_ID=1990;
    private static final String NOTIFICATION_CHANNEL_ID = "weather notification channel id";

    public static void showWeatherNotification(Context context){
        NotificationManager notificationManager = (NotificationManager) context
                .getSystemService(Context.NOTIFICATION_SERVICE);

        // Since Android O we have to create a notification channel to display notifications
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            NotificationChannel channel = new NotificationChannel(NOTIFICATION_CHANNEL_ID,
                    context.getString(R.string.weather_notification_channel_name)
                    ,NotificationManager.IMPORTANCE_HIGH); /* IMPORTANCE_HIGH so notification must pop up*/
            notificationManager.createNotificationChannel(channel);
        }

        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(context,NOTIFICATION_CHANNEL_ID)
                .setColor(ContextCompat.getColor(context,R.color.primary))
                .setSmallIcon(R.drawable.ic_sun)
                .setLargeIcon(largeIcon(context,R.drawable.sun_few_clouds))
                .setContentTitle("Weather notification")
                .setContentText("The weather is splendit today!!!")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("The weather is splendit today!!!"))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context))
                .setAutoCancel(true); /* The notification will go away if we click on it*/
    }

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
