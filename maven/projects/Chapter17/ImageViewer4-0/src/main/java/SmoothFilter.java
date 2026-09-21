import javafx.scene.paint.Color;
import java.util.List;
import java.util.ArrayList;

/**
 * An image filter to reduce sharp edges and pixelization. A bit like
 * a soft lens.
 * 
 * @author Michael Kölling and David J. Barnes.
 * @version 1.0
 */
public class SmoothFilter extends Filter
{
    private OFImage original;
    private int width;
    private int height;
    
    /**
     * Constructor for objects of class SmoothFilter.
     * @param name The name of the filter.
     */
    public SmoothFilter(String name)
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
        original = new OFImage(image);
        width = original.getWidthAsInt();
        height = original.getHeightAsInt();
        
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                image.setPixel(x, y, smooth(x, y));
            }
        }
    }
    
    /**
     * Return a new color that is the smoothed color of a given
     * position. The "smoothed color" is the color value that is the
     * average of this pixel and all the adjacent pixels.
     * @param xpos The xposition of the pixel.
     * @param ypos The yposition of the pixel.
     * @return The smoothed color.
     */
    private Color smooth(int xpos, int ypos)
    {
        List<Color> pixels = new ArrayList<>(9);
        
        for(int y = ypos - 1; y <= ypos + 1; y++) {
            for(int x = xpos - 1; x <= xpos + 1; x++) {
                if( x >= 0 && x < width && y >= 0 && y < height ) {
                    pixels.add(original.getPixel(x, y));
                }
            }
        }

        return new Color(avgRed(pixels), avgGreen(pixels), avgBlue(pixels), 1);
    }

    /**
     * @param pixels The list of pixels.
     * @return The average of all the red values in the given list of pixels.
     */
    private double avgRed(List<Color> pixels)
    {
        double total = 0;
        for(Color color : pixels) {
            total += color.getRed();
        }
        return total / pixels.size();
    }

    /**
     * @param pixels The list of pixels.
     * @return The average of all the green values in the given list of pixels.
     */
    private double avgGreen(List<Color> pixels)
    {
        double total = 0;
        for(Color color : pixels) {
            total += color.getGreen();
        }
        return total / pixels.size();
    }

    /**
     * @param pixels The list of pixels.
     * @return The average of all the blue values in the given list of pixels.
     */
    private double avgBlue(List<Color> pixels)
    {
        double total = 0;
        for(Color color : pixels) {
            total += color.getBlue();
        }
        return total / pixels.size();
    }
}
