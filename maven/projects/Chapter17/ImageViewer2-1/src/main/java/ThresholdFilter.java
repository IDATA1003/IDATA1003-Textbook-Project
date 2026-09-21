import javafx.scene.paint.Color;

/**
 * An three-level gray-based threshold filter.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class ThresholdFilter extends SimpleFilter
{
    /**
     * Constructor for objects of class ThresholdFilter.
     * @param name The name of the filter.
     */
    public ThresholdFilter(String name)
    {
        super(name);
    }

    /** 
     * Calculate a new color value from the original color.
     */
    protected Color newPixel(Color oldPixel)
    {
        double brightness = (oldPixel.getRed() + oldPixel.getBlue() + oldPixel.getGreen()) / 3;
        if(brightness <= 0.33) {
            return Color.BLACK;
        }
        else if(brightness <= 0.67) {
            return Color.GRAY;
        }
        else {
            return Color.WHITE;
        }
    }
}
