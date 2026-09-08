import javafx.scene.image.*;
import javafx.scene.paint.Color;

/**
 * OFImage is a class that defines an image in OF (Objects First) format.
 * It is a writable image that adds getPixel and setPixel methods to the 
 * standard JavaFX images classes.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 2.1
 */
public class OFImage extends WritableImage
{
    /**
     * Create an OFImage copied from a BufferedImage.
     * @param image The image to copy.
     */
    public OFImage(Image image)
    {
         super(image.getPixelReader(), (int)image.getWidth(), (int)image.getHeight());
    }

    /**
     * Create an OFImage with specified size and unspecified content.
     * @param width The width of the image.
     * @param height The height of the image.
     */
    public OFImage(int width, int height)
    {
        super(width, height);
    }

    /**
     * Set a given pixel of this image to a specified color. The
     * color is represented as an (r,g,b) value.
     * @param x The x position of the pixel.
     * @param y The y position of the pixel.
     * @param col The color of the pixel.
     */
    public void setPixel(int x, int y, Color col)
    {
        getPixelWriter().setColor(x, y, col);
    }
    
    /**
     * Get the color value at a specified pixel position.
     * @param x The x position of the pixel.
     * @param y The y position of the pixel.
     * @return The color of the pixel at the given position.
     */
    public Color getPixel(int x, int y)
    {
        return getPixelReader().getColor(x, y);
    }
    
    /**
     * Get the height of this image (as an int).
     */
    public int getHeightAsInt()
    {
        return (int) super.getHeight();   // the super getHeight method returns a double
    }

    /**
     * Get the width of this image (as an int).
     */
    public int getWidthAsInt()
    {
        return (int) super.getWidth();   // the super getWidth method returns a double
    }

    /**
     * Make this image a bit darker.
     */
    public void darker()
    {
        int height = getHeightAsInt();
        int width = getWidthAsInt();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                setPixel(x, y, getPixel(x, y).darker());
            }
        }
    }

    /**
     * Make this image a bit lighter.
     */
    public void lighter()
    {
        int height = getHeightAsInt();
        int width = getWidthAsInt();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                setPixel(x, y, getPixel(x, y).brighter());
            }
        }
    }

    /**
     * Perform a three level threshold operation.
     * That is: repaint the image with only three color values:
     *          white, gray, and black.
     */
    public void threshold()
    {
        int height = getHeightAsInt();
        int width = getWidthAsInt();
        for(int y = 0; y < height; y++) {
            for(int x = 0; x < width; x++) {
                Color pixel = getPixel(x, y);
                double brightness = (pixel.getRed() + pixel.getBlue() + pixel.getGreen()) / 3;
                if(brightness <= 0.33) {
                    setPixel(x, y, Color.BLACK);
                }
                else if(brightness <= 0.67) {
                    setPixel(x, y, Color.GRAY);
                }
                else {
                    setPixel(x, y, Color.WHITE);
                }
            }
        }
    }
}
