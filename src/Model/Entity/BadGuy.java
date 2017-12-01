package Model.Entity;


import java.util.Vector;

import Model.Directions;
import Model.Entity.Actions.BadGuyAction;
import Model.Labyrinth;
import Model.Vertex;
import Model.Resources.ResourceManager;
import Model.Resources.Resources;
import javafx.scene.image.ImageView;

public class BadGuy extends DynamicEntity {

    private BadGuyAction action;

    /**
     * Default constructors for the BadGuy
     */
	public BadGuy() {
		this(0.f, 0.f);
	}

    /**
     * Constructor that assign a predefined position to the BadGuy.
     * @param x the position on the x axis
     * @param y the position on the y axis
     */
	public BadGuy(float x, float y) {
		pos = new Vector<>();
        imageView = new ImageView(ResourceManager.getInstance().get(Resources.BAD_GUY));

        action = new BadGuyAction(this);

        pos.add(x);
        pos.add(y);
	}

    /**
     * Look for the vertex on which is the BadGuy in the graph
     * @param g the graph in which is the badGuy
     * @return the vertex on which it is
     */
	private Vertex getVertex(Labyrinth g)
    {
        Vertex v = new Vertex((int)(getPosX().floatValue()), (int)(getPosY().floatValue()), 0);
        for(Vertex tmp : g.vertexSet())
            if(v.compareTo(tmp) == 0)
                return tmp;

        return null;
    }



    /**
     * This is the algorithm used to make the non player characters move.
     * @param labyrinth the labyrinth in which the enemy is supposed to move.
     **/

    public void Manhatan(Labyrinth labyrinth)
    {
        Vertex vertex = this.getVertex(labyrinth);
        for ( Directions dir : Directions.values() ) {
            Vertex next =  labyrinth.getVertexByDir(vertex, dir);
            if(labyrinth.isConnected(vertex, next) && ( next.getNbr()== vertex.getNbr() -1 ) )
            {
                move(dir);
            }

        }
    }

}
