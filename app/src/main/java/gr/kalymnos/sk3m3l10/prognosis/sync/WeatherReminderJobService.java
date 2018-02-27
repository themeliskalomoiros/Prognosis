package gr.kalymnos.sk3m3l10.prognosis.sync;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;


import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import gr.kalymnos.sk3m3l10.prognosis.util.NotificationUtils;

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

    private AsyncTask backgroundTask;

    @Override
    public boolean onStartJob(final JobParameters job) {
        this.backgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                // TODO: Here the weather service will get the current weather and return it.
                Log.d(CLASS_TAG,"Job started.");
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                // TODO: Here a notification will be displayed with the current weather.
                NotificationUtils.showWeatherNotification(WeatherReminderJobService.this);
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
