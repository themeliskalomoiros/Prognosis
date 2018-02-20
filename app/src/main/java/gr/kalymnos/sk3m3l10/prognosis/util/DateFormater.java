package gr.kalymnos.sk3m3l10.prognosis.util;

import java.util.Calendar;

/*
* Takes milliseconds and returns them in a pretty format
*
* Example:  Tuesday, February 23
*
* */

public class DateFormater {
    private Calendar calendar;

    public DateFormater(long timeMilli){
        this.calendar = Calendar.getInstance();
        this.calendar.setTimeInMillis(timeMilli);
    }

    @Override
    public String toString() {
        return this.getDayName()+", "+this.getMonth()+" "+this.getDayOfMonth();
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
}
