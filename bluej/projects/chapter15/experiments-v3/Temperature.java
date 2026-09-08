/**
 * A record of a temperature reading.
 * The default unit for readings is celsius
 * but alternatives may be used.
 *
 * @author David J. Barnes and Michael Kölling
 * @version 7.0
 * @param temperature The temperature reading.
 * @param unit E.g., "Celsius" (the default).
 */
public record Temperature(double temperature, String unit)
{
    public static final double CELSIUS_FP = 0;
    public static final double FAHRENHEIT_FP = 32;
    public static final double ZERO_CELSIUS_IN_KELVIN = 273.15;
    
    public Temperature(double value)
    {
        this(value, "Celsius");
    }
}
