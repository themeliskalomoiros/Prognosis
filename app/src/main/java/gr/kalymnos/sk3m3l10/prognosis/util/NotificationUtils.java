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
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.content.ContextCompat;

import gr.kalymnos.sk3m3l10.prognosis.R;
import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.screens.all_forecasts.MainActivity;
import gr.kalymnos.sk3m3l10.prognosis.screens.detail.DetailActivity;

public class NotificationUtils {

    private static final int WEATHER_NOTIFICATION_INTENT_ID=1990;
    private static final String NOTIFICATION_CHANNEL_ID = "weather notification channel id";

    // TODO: This notification must display an accurate title,text and image relative to the weatherForecast.
    public static void showWeatherNotification(Context context, Weather weather){
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
                .setLargeIcon(largeIcon(context,weather.getImage()))
                .setContentTitle(weather.getQueryTitle())
                .setContentText(weather.getMainWeather())
                .setStyle(new NotificationCompat.BigTextStyle().bigText(weather.getMainWeather()))
                .setDefaults(Notification.DEFAULT_VIBRATE)
                .setContentIntent(contentIntent(context,weather))
                .setAutoCancel(true); /* The notification will go away if we click on it*/

        // Set the priority for backward compatibility
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN
                &&  Build.VERSION.SDK_INT < Build.VERSION_CODES.O){
            notificationBuilder.setPriority(NotificationCompat.PRIORITY_HIGH);
        }

        notificationManager.notify(WEATHER_NOTIFICATION_INTENT_ID,notificationBuilder.build());
    }

    /*
       This method will create the pending intent which will trigger
       when the notification is pressed. This pending intent will open up
       the DetailActivity.

       The flag is set to FLAG_UPDATE_CURRENT, so that if the intent is created
       again, keep the intent but update the data.
    */
    private static PendingIntent contentIntent(Context context,Weather w){
        // Bundle the Weather obj
        Bundle extras = new Bundle();
        extras.putInt(DetailActivity.IMAGE_KEY,w.getImage());
        extras.putString(DetailActivity.DATE_KEY,w.getDate());
        extras.putString(DetailActivity.DESCRIPTION_KEY,w.getDescription());
        extras.putString(DetailActivity.HIGH_TEMP_KEY,w.getTempHighWithSymbol());
        extras.putString(DetailActivity.LOW_TEMP_KEY,w.getTempLowWithSymbol());
        extras.putString(DetailActivity.HUMIDITY_KEY,w.getHumidity());
        extras.putString(DetailActivity.PRESSURE_KEY,w.getPressure());
        extras.putString(DetailActivity.WIND_KEY,w.getWindWithSymbol());

        Intent startDetailActivity = new Intent(context, DetailActivity.class);
        startDetailActivity.putExtras(extras);

        return PendingIntent.getActivity(context,WEATHER_NOTIFICATION_INTENT_ID,startDetailActivity
                ,PendingIntent.FLAG_UPDATE_CURRENT);
    }

    // This method will decode a bitmap needed for the notification.
    private static Bitmap largeIcon(Context context, int imgRes){
        return BitmapFactory.decodeResource(context.getResources(), imgRes);
    }
}
