package Model.Resources;

import javafx.scene.image.Image;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

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
     * Method that unload every image that is currently loaded in the manager
     */
    public void unloadAll()
    {
        Iterator i = imagesStored.entrySet().iterator();
        while(i.hasNext())
        {
            Map.Entry pair = (Map.Entry) i.next();
            unload((Resources) pair.getKey());
        }
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
