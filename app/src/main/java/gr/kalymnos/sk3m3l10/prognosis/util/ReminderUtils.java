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
    private static boolean jobInitialized = false;

    /*
        The job ideally will start at the time that the user specified.
        In practise it will not happen this way. This constant states the
        maximum tollerance in seconds where the system can delay to start our
        job. Yet again is not guaranteed.
    */
    private static final int TIME_TOLERANCE = (int) TimeUnit.MINUTES.toSeconds(15);


    synchronized public static void scheduleWeatherReminder(Context context){

        int userInputInHours = SettingsUtils.getNotificationTimeValue(context);
        int userInputInSeconds = (int) TimeUnit.HOURS.toSeconds(userInputInHours);

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
                .setTrigger(Trigger.executionWindow(userInputInSeconds
                        ,userInputInSeconds+TIME_TOLERANCE))
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
