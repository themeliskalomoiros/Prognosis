package gr.kalymnos.sk3m3l10.prognosis.util;

import android.content.Context;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;

import java.util.concurrent.TimeUnit;

import gr.kalymnos.sk3m3l10.prognosis.sync.WeatherReminderJobService;

/**
 * This class is responsible for scheduling jobs.
 */

public class ReminderUtils {

    private static final String REMINDER_JOB_TAG = "weather notification reminder tag";

    public static final String CITY_KEY = "city_name_key";

    private static boolean jobInitialized = false;


    synchronized public static void scheduleWeatherReminder(Context context, String cityName){

        if (jobInitialized){
            return;
        }

        // Setting up and starting a job with FirebaseJobDispatcher
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        // TODO: Provide constraints for the job. Internet connection and user defined time interval.
        Job job = dispatcher.newJobBuilder()
                .setService(WeatherReminderJobService.class)
                .setTag(REMINDER_JOB_TAG)
                .setLifetime(Lifetime.FOREVER)
                .setConstraints(Constraint.ON_ANY_NETWORK)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(1,3))
                .setReplaceCurrent(false)
                .build();
        dispatcher.mustSchedule(job);
        jobInitialized=true;
    }

    synchronized public static void stopWeatherReminder(Context context){
        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);
        dispatcher.cancel(REMINDER_JOB_TAG);
        jobInitialized=false;
    }
}
