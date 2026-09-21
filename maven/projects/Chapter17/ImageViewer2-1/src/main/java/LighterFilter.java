import javafx.scene.paint.Color;

/**
 * An image filter to make the image a bit lighter.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class LighterFilter extends SimpleFilter
{
    /**
     * Constructor for objects of class LighterFilter.
     * @param name The name of the filter.
     */
    public LighterFilter(String name)
    {
        super(name);
    }

    /** 
     * Calculate a new color value from the original color.
     */
    protected Color newPixel(Color oldPixel)
    {
        return oldPixel.brighter();
    }
}
