package gr.kalymnos.sk3m3l10.prognosis.sync;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;


import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import gr.kalymnos.sk3m3l10.prognosis.common.weather.Weather;
import gr.kalymnos.sk3m3l10.prognosis.model_mvc.FakeWeatherService;
import gr.kalymnos.sk3m3l10.prognosis.model_mvc.WeatherService;
import gr.kalymnos.sk3m3l10.prognosis.util.NotificationUtils;
import gr.kalymnos.sk3m3l10.prognosis.util.ReminderUtils;
import gr.kalymnos.sk3m3l10.prognosis.util.SettingsUtils;

/**
 *  WeatherReminderJobService extends JobService because
 *  this is what Firebases Job-dispatcher requires.
 *
 *  Also JobService runs from the main thread by default
 *  so we will make our calculations in a seperate
 *  thread instead.
 */

public class WeatherReminderJobService extends JobService {

    private static final String CLASS_TAG = WeatherReminderJobService.class.getSimpleName();

    private AsyncTask<Void,Void,Weather> backgroundTask;

    @Override
    public boolean onStartJob(final JobParameters job) {

        this.backgroundTask = new AsyncTask<Void, Void, Weather>() {
            @Override
            protected Weather doInBackground(Void... voids) {
                // TODO: Switch to a real weather service.
                WeatherService weatherService = new FakeWeatherService();
                String cityName = SettingsUtils.getCityName(WeatherReminderJobService.this);
                return weatherService.getCurrentWeather(cityName);
            }

            @Override
            protected void onPostExecute(Weather weather) {
                Context context = WeatherReminderJobService.this;
                NotificationUtils.showWeatherNotification(context,weather);
                jobFinished(job,false);

            }
        };

        backgroundTask.execute();
        return true;    // Indicate that there is job still done (by the thread)
    }


    /*
        Called when our job is stoped, usually because a constrained failed to match.
    *   For example our job starts only when there is internet connection, but if the
    *   latter is lost then onStopJob will be called.
    * */
    @Override
    public boolean onStopJob(JobParameters job) {
        if (this.backgroundTask!=null){
            this.backgroundTask.cancel(true);
        }
        return true;    // Retry the job if it was stoped.
    }
}
