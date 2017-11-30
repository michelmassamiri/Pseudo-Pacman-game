package Model.Entity;


import java.util.Vector;

import Model.Directions;
import Model.Vertex;
import Model.Resources.ResourceManager;
import Model.Resources.Resources;
import javafx.scene.image.ImageView;

public class BadGuy extends DynamicEntity {
	
	
	public BadGuy() {
		pos = new Vector<>();
        imageView = new ImageView(ResourceManager.getInstance().get(Resources.BAD_GUY));

        pos.add(new Float(0.0f));
        pos.add(new Float(0.0f));
	}
	
	public BadGuy(float x, float y) {
		pos = new Vector<>();
        imageView = new ImageView(ResourceManager.getInstance().get(Resources.BAD_GUY));

        pos.add(x);
        pos.add(y);
	}

    /*
     * This is the algorithm used to make the non player characters move.
     * @param labyrinth the labyrinth in which the enemy is supposed to move.
     **/
	/*
    public void Manhatan(Labyrinth labyrinth)
    {
        Vertex vertex = this.getVertex(labyrinth.graph);
        for ( Directions dir : Directions.values() ) {
            Vertex next =  labyrinth.graph.getVertexByDir(vertex, dir);
            if(labyrinth.graph.isConnected(vertex, next) && ( next.getNbr()== vertex.getNbr() -1 ) )
            {
                move(dir);
            }

        }
    }*/

}
