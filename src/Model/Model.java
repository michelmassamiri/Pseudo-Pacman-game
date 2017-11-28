package Model;

import Model.Entity.Entity;

import java.util.Vector;

public class Model {

	private Vector<Entity> entities;

	public Model()
	{
		
	}

	/**
	 * Method that makes simpler the drawing of each entities. By calling this method and iterate through the vector,
     * you can then draw them with the getImage method from the Entity interface.
     * @see Entity
	 * @return a vector with all the entities of the current game.
	 */
    public Vector<Entity> getEntities() {
        return entities;
    }
}
