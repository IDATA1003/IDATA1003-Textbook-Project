
/**
 * A record of a time.
 * Used here to record the time a reading was taken.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.3
 * @param hour The hour of the reading.
 * @param minute The minute of the reading.
 */
public record Time(int hour, int minute)
{
    /**
     * Prevent an invalid time from being created.
     * @param hour The hour of the reading.
     * @param minute The minute of the reading.
     */
    public Time(int hour, int minute)
    {   
        if(hour < 0 || hour >= 24) {
            throw new IllegalStateException("Invalid hour: " + hour);
        }   
        else if(minute < 0 || minute >= 60) {
            throw new IllegalStateException("Invalid minute: " + minute);
        }   
        else {
            this.hour = hour;
            this.minute = minute;
        }   
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
