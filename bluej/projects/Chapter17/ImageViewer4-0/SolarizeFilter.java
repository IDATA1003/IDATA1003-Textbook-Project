import javafx.scene.paint.Color;

/**
 * An image filter to create a solarization effect.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 1.0
 */
public class SolarizeFilter extends Filter
{
    /**
     * Constructor for objects of class Solarize.
     * @param name The name of the filter.
     */
    public SolarizeFilter(String name)
    {
        super(name);
    }

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
                Color pix = image.getPixel(x, y);
                double red = pix.getRed();
                if(red <= 0.5) {
                    red = 1 - red;
                }
                double green = pix.getGreen();
                if(green <= 0.5) {
                    green = 1 - green;
                }
                double blue = pix.getBlue();
                if(blue <= 0.5) {
                    blue = 1 - blue;
                }
                image.setPixel(x, y, new Color(red, green, blue, 1));
            }
        }
    }
}
