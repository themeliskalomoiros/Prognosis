package gr.kalymnos.sk3m3l10.prognosis.util;

import java.util.Calendar;

/*
* Takes milliseconds and returns them in a pretty format
*
* Example:  Tuesday, February 23
*
* */

public class DateUtils {
    private Calendar calendar;

    public DateUtils(long timeSeconds){
        // OpenWeatherMapApi returns unix time in seconds that have past since 'the Epoch'.
        long timeMilli = timeSeconds * 1000;
        this.calendar = Calendar.getInstance();
        this.calendar.setTimeInMillis(timeMilli);
    }

    public String getDate() {
        return this.getDayName()+", "+this.getMonth()+" "+this.getDayOfMonth();
    }

    /*
    * @param lowerBound: From this hour is a day
    * @param lowerBound: Until this hour is a day
    * */
    public boolean isDayLight(int lowerBound, int upperBound){
        int hour = this.calendar.get(Calendar.HOUR_OF_DAY);
        if (hour>=lowerBound && hour<=upperBound){
            return true;
        }
        return false;
    }

    private String getMonth(){
        switch (this.calendar.get(Calendar.MONTH)){
            case Calendar.JANUARY:
                return "January";
            case Calendar.FEBRUARY:
                return "February";
            case Calendar.MARCH:
                return "March";
            case Calendar.APRIL:
                return "April";
            case Calendar.MAY:
                return "May";
            case Calendar.JUNE:
                return "June";
            case Calendar.JULY:
                return "July";
            case Calendar.AUGUST:
                return "August";
            case Calendar.SEPTEMBER:
                return "September";
            case Calendar.OCTOBER:
                return "October";
            case Calendar.NOVEMBER:
                return "November";
            case Calendar.DECEMBER:
                return "December";
            default:
                return null;
        }
    }

    private String getDayOfMonth(){
        return String.valueOf(this.calendar.get(Calendar.DAY_OF_MONTH));
    }

    private String getDayName(){

        if (isToday()){
            return "Today";
        }

        if (isTomorrow()){
            return "Tomorrow";
        }

        switch (this.calendar.get(Calendar.DAY_OF_WEEK)) {
            case Calendar.SUNDAY:
                return "Sunday";
            case Calendar.MONDAY:
                return "Monday";
            case Calendar.TUESDAY:
                return "Tuesday";
            case Calendar.WEDNESDAY:
                return "Wed";
            case Calendar.THURSDAY:
                return "Thur";
            case Calendar.FRIDAY:
                return "Fr";
            case Calendar.SATURDAY:
                return "Sat";
            default:
                return null;
        }
    }
    
    private boolean isToday(){
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(System.currentTimeMillis());

        // Todays date
        int todayYear = today.get(Calendar.YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int todayDay = today.get(Calendar.DAY_OF_MONTH);

        // Date to compare with today
        int otherYear = this.calendar.get(Calendar.YEAR);
        int otherMonth = this.calendar.get(Calendar.MONTH);
        int otherDay = this.calendar.get(Calendar.DAY_OF_MONTH);

        if (todayYear==otherYear && todayMonth==otherMonth && todayDay==otherDay){
            return true;
        }
        return false;
    }

    private boolean isTomorrow(){
        Calendar today = Calendar.getInstance();
        today.setTimeInMillis(System.currentTimeMillis());

        // Todays date
        int todayYear = today.get(Calendar.YEAR);
        int todayMonth = today.get(Calendar.MONTH);
        int todayDay = today.get(Calendar.DAY_OF_MONTH);

        // Date to compare with tomorrow
        int otherYear = this.calendar.get(Calendar.YEAR);
        int otherMonth = this.calendar.get(Calendar.MONTH);
        int otherDay = this.calendar.get(Calendar.DAY_OF_MONTH);

        if (todayYear==otherYear && todayMonth==otherMonth && todayDay==otherDay+1){
            return true;
        }
        return false;
    }
}
