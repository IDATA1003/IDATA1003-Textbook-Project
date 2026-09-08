import javafx.scene.canvas.*;
import javafx.scene.paint.*;

import java.util.Iterator;

/**
 * CitiView provides a JavaFX component to draw the city with its actors in it.
 * It is implemented as a subclass of the JavaFX Canvas.
 *
 * @author Michael Kölling and David J Barnes
 * @version 7.0
 */
public class CityView extends Canvas
{
    // The dimensions of the GUI.
    public static final int VIEW_WIDTH = 700;
    public static final int VIEW_HEIGHT = 700;

    private City city;

    private final double cellWidth;
    private final double cellHeight;
    
    /**
     * Initialise the view.
     */
    public CityView(City city)
    {
        super(VIEW_WIDTH, VIEW_HEIGHT);
        this.city = city;
        cellWidth = VIEW_WIDTH / city.getWidth();
        cellHeight = VIEW_HEIGHT / city.getHeight();
    }

    public void update()
    {
        paintBackgound();

        GraphicsContext g = getGraphicsContext2D();
        Iterator<Item> items = city.getItems();
        while(items.hasNext()) {
            Item item = items.next();
            if(item instanceof DrawableItem drawable){
                Location location = item.getLocation();
                g.drawImage(drawable.getImage(), location.x()*cellWidth, location.y()*cellHeight);
            }
        }
    }

    /**
     * Prepare for a new round of painting. Since the component
     * may be resized, compute the scaling factor again.
     */
    public void paintBackgound()
    {
        GraphicsContext g = getGraphicsContext2D();
        
        g.setFill(Color.WHITE);
        g.fillRect(0, 0, VIEW_WIDTH, VIEW_HEIGHT);
        
        g.setStroke(Color.GREY);
        for(int i = 0; i < city.getWidth(); i++) {
            g.strokeLine(i * cellWidth, 0, i * cellWidth, VIEW_HEIGHT - 1);
        }
        for(int i = 0; i < city.getHeight(); i++) {
            g.strokeLine(0, i * cellHeight, VIEW_WIDTH - 1, i * cellHeight);
        }
    }
}
