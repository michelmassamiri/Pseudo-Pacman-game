package Model;

import Model.Entity.Entity;

import java.util.Vector;

public class Model {

	private Vector<Entity> entities;
	private int playerRank;

	public Model()
	{
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
}
