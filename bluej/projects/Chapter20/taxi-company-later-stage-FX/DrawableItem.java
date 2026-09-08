import javafx.scene.image.Image;
    
/**
 * An item that is able to return an image of itself.
 * 
 * @author David J. Barnes and Michael Kölling
 * @version 7.3
 */

public interface DrawableItem extends Item
{
    public Image getImage();
}
