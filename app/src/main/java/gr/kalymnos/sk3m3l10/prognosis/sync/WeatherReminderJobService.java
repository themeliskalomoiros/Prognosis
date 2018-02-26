package gr.kalymnos.sk3m3l10.prognosis.sync;

import android.app.job.JobParameters;
import android.app.job.JobService;

/**
 * Created by skemelio on 26.02.18.
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
