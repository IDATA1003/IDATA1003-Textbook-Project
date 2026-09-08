import javafx.scene.paint.Color;

/**
 * The SimpleFilter implements the main body of the filter's apply
 * method, leaving only the newPixel method abstract. This class can be used
 * as a superclass for all filters where the color of each pixel depends only
 * on the single original pixel at the same position.
 *
 * @author Michael Kölling and David J. Barnes
 * @version 1.0
 */
public abstract class SimpleFilter extends Filter
{
    /**
     * Constructor for objects of class SimpleFilter.
     * @param name The name of the filter.
     */
    public SimpleFilter(String name)
    {
        super(name);
    }

    /** 
     * Calculate a new color value from the original color.
     */
    protected abstract Color newPixel(Color oldPixel);
    
    /**
     * Apply this filter to an image.
     * 
     * @param  image  The image to be changed by this filter.
     */
    public void apply(OFImage image)
    {
        int height = image.getHeightAsInt();
        int width = image.getWidthAsInt();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                image.setPixel(x, y, newPixel(image.getPixel(x, y)));
            }
        }
    }
}
