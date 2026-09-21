import javafx.scene.image.*;
import javafx.scene.paint.Color;

/**
 * OFImage is a class that defines an image in OF (Objects First) format.
 * 
 * @author Michael Kölling
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
}
