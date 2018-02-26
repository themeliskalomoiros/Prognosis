package gr.kalymnos.sk3m3l10.prognosis.sync;

import android.app.job.JobParameters;
import android.app.job.JobService;

/**
 *  WeatherReminderJobService extends JobService because
 *  this is what Firebases Job-dispatcher requires.
 *
 *  Also JobService runs from the main thread by default
 *  so we will implement make our calculations in a seperate
 *  thread instead.
 */

public class WeatherReminderJobService extends JobService {
    @Override
    public boolean onStartJob(JobParameters params) {
        return false;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        return false;
    }
}
