package Model;

import javafx.scene.image.Image;
import java.util.HashMap;

public class ResourceManager {

    private static ResourceManager INSTANCE = null;
    private HashMap<Resources, Image> imagesStored;

    private ResourceManager()
    {
        imagesStored = new HashMap<Resources, Image>();
    }

    /**
     * Static method that returns the instance of the singleton.
     * @return the instance of the ResourceManager
     */
    public static ResourceManager getInstance()
    {
        if(INSTANCE == null)
            INSTANCE = new ResourceManager();
        return INSTANCE;
    }

    /**
     * Method that load and store a picture in the resources table.
     * @param key the name associated with the picture
     * @param fileName the file where the picture is stored
     */
    public void load(Resources key, String fileName)
    {
        Image image = new Image( getClass().getResource(fileName).toExternalForm());
        imagesStored.put(key, image);
    }

    /**
     * Method that unload a picture from the memory.
     * @param key the name of the picture to unload
     */
    public void unload(Resources key)
    {
        imagesStored.remove(key);
    }

    /**
     * Method that will return the Image associated with the key
     * @param key the name of the Image you want to get
     * @return the Image stored, null if there is none associated with the key
     */
    public Image get(Resources key)
    {
        return imagesStored.get(key);
    }


}
