import javafx.scene.paint.Color;

import java.util.List;
import java.util.ArrayList;

/**
 * An image filter to detect edges and highlight them, a bit like 
 * a colored pencil drawing.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 1.0
 */
public class EdgeFilter extends Filter
{
    private static final double TOLERANCE = 0.1;
    
    private OFImage original;
    private int width;
    private int height;

    /**
     * Constructor for objects of class EdgeFilter.
     * @param name The name of the filter.
     */
    public EdgeFilter(String name)
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
                image.setPixel(x, y, edge(x, y));
            }
        }
    }

    /**
     * Return a new color that is the smoothed color of a given
     * position. The "smoothed color" is the color value that is the
     * average of this pixel and all the adjacent pixels.
     * @param xpos The x position of the pixel.
     * @param ypos The y position of the pixel.
     * @return The smoothed color.
     */
    private Color edge(int xpos, int ypos)
    {
        List<Color> pixels = new ArrayList<>(9);
        
        for(int y = ypos-1; y <= ypos+1; y++) {
            for(int x = xpos-1; x <= xpos+1; x++) {
                if( x >= 0 && x < width && y >= 0 && y < height ) {
                    pixels.add(original.getPixel(x, y));
                }
            }
        }

        return new Color(1 - diffRed(pixels), 1 - diffGreen(pixels), 1 - diffBlue(pixels), 1);
    }

    /**
     * @param pixels The list of pixels to be averaged.
     * @return The average of all the red values in the given list of pixels.
     */
    private double diffRed(List<Color> pixels)
    {
        double max = 0;
        double min = 1;
        for(Color color : pixels) {
            double val = color.getRed();
            if(val > max) {
                max = val;
            }
            if(val < min) {
                min = val;
            }
        }
        double difference = max - min - TOLERANCE;
        if(difference < 0) {
            difference = 0;
        }
        return difference;
    }

    /**
     * @param pixels The list of pixels to be averaged.
     * @return The average of all the green values in the given list of pixels.
     */
    private double diffGreen(List<Color> pixels)
    {
        double max = 0;
        double min = 1;
        for(Color color : pixels) {
            double val = color.getGreen();
            if(val > max) {
                max = val;
            }
            if(val < min) {
                min = val;
            }
        }
        double difference = max - min - TOLERANCE;
        if(difference < 0) {
            difference = 0;
        }
        return difference;
    }

    /**
     * @param pixels The list of pixels to be averaged.
     * @return The average of all the blue values in the given list of pixels.
     */
    private double diffBlue(List<Color> pixels)
    {
        double max = 0;
        double min = 1;
        for(Color color : pixels) {
            double val = color.getBlue();
            if(val > max) {
                max = val;
            }
            if(val < min) {
                min = val;
            }
        }
        double difference = max - min - TOLERANCE;
        if(difference < 0) {
            difference = 0;
        }
        return difference;
    }

}
