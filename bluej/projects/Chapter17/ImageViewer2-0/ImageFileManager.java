import javafx.stage.Stage;
import javafx.stage.FileChooser;
import javafx.scene.image.*;
import java.io.*;

// old Swing/AWT APIs and conversion APIs for saving the image to disk
import javafx.embed.swing.SwingFXUtils;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/**
 * ImageFileManager is a small utility class with static methods to load
 * and save images.
 * 
 * The files on disk can be in JPG or PNG image format. For files written
 * by this class, the format is determined by the constant IMAGE_FORMAT.
 * 
 * @author Michael Kölling and David J. Barnes
 * @version 1.0
 */
public class ImageFileManager
{
    // A constant for the image format that this writer uses for writing.
    // Available formats are "jpg" and "png".
    private static final String IMAGE_FORMAT = "jpg";
    
    /**
     * Read an image file from disk and return it as an OFImage. This method
     * can read JPG and PNG file formats. In case of any problem (e.g the file does
     * not exist, is in an undecodable format, or any other read error) this method
     * returns null.
     * 
     * @param imageFile  The image file to be loaded.
     * @return           The image object or null if it was not a valid image file.
     */
    public static OFImage loadImage(File imageFile)
    {
        return loadImage(imageFile.toURI().toString());
    }

    /**
     * Read an image file from disk and return it as a OFImage. This method
     * can read JPG and PNG file formats. In case of any problem (e.g the file does
     * not exist, is in an undecodable format, or any other read error) this method
     * returns null.
     * 
     * @param imageName  The URI of the image path to be loaded.
     * @return           The image object or null if it was not a valid image file.
     */
    public static OFImage loadImage(String imageName)
    {
        Image image = new Image(imageName);      // Image is a read-only image

        if (image == null || image.isError()) {
            return null;
        }
        else {
            return new OFImage(image);           // OFImage is a writable image
        }
    }

    /**
     * Write an image file to disk. The file format is JPG. In case of any problem
     * the method just silently returns.
     * 
     * @param image  The image to be saved.
     * @param file   The file to save to.
     */
    public static void saveImage(OFImage image, File file)
    {
        try {
            BufferedImage bImage = SwingFXUtils.fromFXImage(image, null);
            ImageIO.write(bImage, IMAGE_FORMAT, file);
        }
        catch(IOException exc) {
            return;
        }
    }
}
