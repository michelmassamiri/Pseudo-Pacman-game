package Model;

import Model.Entity.Entity;
import Model.Resources.ResourceManager;
import Model.Resources.Resources;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Vector;

public class Model {

	private Vector<Entity> entities;
	private int playerRank;
	private ResourceManager resourceManager;

	public Model()
	{
		resourceManager = ResourceManager.getInstance();
		entities = new Vector<Entity>();
	    playerRank = 0;
	}

	/**
	 * Method that makes simpler the drawing of each entities. By calling this method and iterate through the vector,
     * you can then draw them with the getImage method from the Entity interface.
	 * The first element should always be the player. If not, it has to be precised.
     * @see Entity
	 * @return a vector with all the entities of the current game.
	 */
    public Vector<Entity> getEntities() {
        return entities;
    }

    /**
     * set the rank of the player in the entities table. Default rank is zero.
     * @param rank the rank in the entities table.
     */
    public void setPlayerRank(int rank) { playerRank = rank; }

    /**
     * Method that will load a bunch of resources at once.
     * @param resources the resources to load stored as pairs : Resources - String (resource name - resource file)
     */
    public void loadResources(HashMap<Resources, String> resources)
    {
        Iterator i = resources.entrySet().iterator();
        while(i.hasNext())
        {
            Map.Entry pair = (Map.Entry) i.next();
            resourceManager.load((Resources) pair.getKey(), (String) pair.getValue());
        }
    }

    /**
     * Method used to unload every resources in the resource manager
     */
    public void unloadAll()
    {
        resourceManager.unloadAll();
    }

    /**
     * add an entity in the entities tables.
     * @param e the entity to add
     */
    public void addEntity(Entity e)
    {
        entities.add(e);
    }

    public void loadAll()
    {
        resourceManager.load(Resources.BAD_GUY, "bad.png");
        resourceManager.load(Resources.BUTTON_CLOSED, "button_close.png");
        resourceManager.load(Resources.BUTTON_OPPEN, "button_open.png");
        resourceManager.load(Resources.CANDY_1, "candy-1.png");
        resourceManager.load(Resources.CANDY_2, "candy-2.png");
        resourceManager.load(Resources.CANDY_3, "candy-3.png");
        resourceManager.load(Resources.CANDY_4, "candy-4.png");
        resourceManager.load(Resources.DOOR_OPEN, "door_open.png");
        resourceManager.load(Resources.PLAYER, "player.png");
    }
}
