/**
 * A record of a time.
 * Used here to record the time a reading was taken.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 */
public class Time
{
    private final int hour;
    private final int minute;
    
    /**
     * Create a Time object.
     * @param hour The hour of the reading.
     * @param minute The minute of the reading.
     */
    public Time(int hour, int minute)
    {
        this.hour = hour;
        this.minute = minute;
    }
    
    /**
     * Get the hour.
     * @return The hour.
     */
    public int getHour()
    {
        return hour;
    }
    
    /**
     * Get the minute.
     * @return The minute.
     */
    public int getMinute()
    {
        return minute;
    }

    /**
     * Return the time in the form: hh:mm.
     * @return The time.
     */
    public String toString()
    {
        return twoDigitString(hour) + ":" + twoDigitString(minute);
    }
    
    /**
     * Return val as a two-digit String.
     * @param val The value to be formatted.
     */
    private String twoDigitString(int val)
    {
        if(val >= 10) {
            return "" + val;
        }
        else {
            return "0" + val;
        }
    }
}
