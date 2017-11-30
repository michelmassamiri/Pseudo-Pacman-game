package Model.Entity;


import Model.Directions;
import Model.Vertex;

public class BadGuy extends DynamicEntity {


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
            if(labyrinth.graph.isConnected(vertex, next) && ( next.getNbr()== vertex.getNbr() −1 ) )
            {
                move(dir);
            }

        }
    }

   //*/

}
