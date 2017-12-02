package Model ;

import java.util.*;

import org.jgrapht.graph.SimpleGraph;
import Model.Edge.Type;


public class Labyrinth extends SimpleGraph<Vertex, Edge> {


	private static final long serialVersionUID = 789954947381591787L;
	
	/* Singelton's Design Pattern instance */
	private static Labyrinth instance = new Labyrinth();
	 
	/**
	 * The labyrinth private constructor. Nothing changed, the upon class will instantiate.
	 */
	private Labyrinth() {
		super(Edge.class) ; //super(Class<Edge extends E> Edge.class)
	}
	
	/**
	 * The public Singleton method to get the instance .
	 * @return the labyrinth's instance .
	 */
	public static Labyrinth getInstance() {
		return instance ;
	}

    /**
     * Method that builds a random graph (can be a non fully connected graph), representing the labyrinth
     * @param vertex the vertex on which the graph will be built
     */
	public void buildRandomPath(Vertex vertex) {
		Vector<Directions> v = new Vector<Directions>() ;
		
		for(int i = 0 ; i <4 ; ++i) {
			v.add(Directions.values()[i]) ;
		}
		
		Directions directions [] = new Directions[4] ;
		int index ;
		Random random = new Random() ;
		for(int i = 0 ; i < directions.length; ++i) {
			index = random.nextInt(v.size()) ;
			directions[i] = v.get(index) ;
			v.remove(index) ;
		}
		
		/* For each direction, we will advance in profound to build the graph */
		for(int i = 0 ; i < 4 ; ++i) {
			Directions dir = directions[i] ;
			int x, y, xt, yt ;
			if(vertex.inBorders(dir) && instance.doesntExist(vertex, dir)) {
				x = vertex.getX() ;
				y = vertex.getY() ;
				switch(dir) {
				case NORTH: xt = x ; yt = y-1 ; break ;
				case SOUTH: xt = x; yt = y+1 ; break ;
				case EAST: xt = x+1; yt = y; break ;
				case WEST: xt = x-1; yt = y; break ;
				default: xt = 0; yt = 0; break ;
				}
				
				Vertex next = new Vertex(xt, yt, vertex.getNbr()+1) ;
				instance.addVertex(next) ;
				instance.addEdge(vertex, next) ;
				buildRandomPath(next) ;
			}
		}
	}
	
	/**
	 * Method that tells if the designated vertex is already created or not
	 * @param vertex the vertex from which we look if another exists
	 * @param dir the direction in which the supposed vertex would be
	 * @return true if there is no vertex yet, false otherwise
	 */
	public boolean doesntExist(Vertex vertex, Directions dir) {
	    Edge e = getEdge(vertex, dir);
		return e == null;
	}
	
	/**
	 * Get the edge between two vertices, if they both exists
	 * @param vertex the from vertex
	 * @param dir the direction in which the to vertex should be
	 * @return An edge if there is one, null otherwise
	 */
	public Edge getEdge(Vertex vertex, Directions dir) {
	    Vertex v = getVertexByDir(vertex, dir);
	    Edge e = getEdge(vertex, v);
		return e;
	}
	
	/**
	 * Method that gives a random edge from the graph
	 * @return A Random Edge
	 */
	public Edge randomEdge() {
        Random r = new Random();
        Edge e = null;
        while(e == null)
        {
            Vertex v = randomVertex();
            Directions dir = Directions.values()[r.nextInt(4)];
            e = getEdge(v, dir);
        }
		return e ;
	}
	
	/**
	 * Generate a random vertex. The position of the vertex does not cross the borders limit. 
	 * @return The generated vertex .
	 */
	public Vertex randomVertex() {
		Random random = new Random() ;
		int Low = Vertex.WEST_BORDER;
		int High = Vertex.EAST_BORDER;
		int x = random.nextInt(High-Low) + Low;
		int y = random.nextInt(High-Low) + Low;
		Vertex vertex = new Vertex(x, y, 0) ;
		
		return vertex ;
	}
	
	/**
	 * Generate a random vertex that does exist in the graph by a random direction.
	 * @return the generated vertex .
	 */
	public Vertex randomVertexByDir() {
		 Random r = new Random();
		 Vertex v = null;
		 
		 int Low = Vertex.WEST_BORDER;
		 int High = Vertex.EAST_BORDER;
		 int x = r.nextInt(High-Low) + Low;
		 int y = r.nextInt(High-Low) + Low;
		 Vertex vertex = new Vertex(x, y, 0) ;

		 while(v == null)
		 {
			 Directions dir = Directions.values()[r.nextInt(4)];
			 v = getVertexByDir(vertex, dir) ;
		 }
		 return v ;
	}
	
	/**
	 * Get the vertex to which we want to reach, given the source vertex and the direction to the target .
	 * @param vertex : the source vertex .
	 * @param dir : the direction to which we want to get .
	 * @return the target vertex .
	 */
	public Vertex getVertexByDir(Vertex vertex, Directions dir) {
	    int x = vertex.getX(), y = vertex.getY();
	    if(dir == Directions.NORTH)
	        --y;
	    else if(dir == Directions.SOUTH)
	        ++y;
	    else if(dir == Directions.EAST)
	        ++x;
	    else if(dir == Directions.WEST)
	        --x;
        Vertex v = new Vertex(x, y, 0), ret = null;
        Set<Vertex> s =  vertexSet();
        Iterator<Vertex> it = s.iterator();
        while(it.hasNext())
        {
            Vertex current = (Vertex) it.next();
            if(current.compareTo(v) == 0)
            {
                ret = current;
                break;
            }
        }
		return ret;
	}
	
	/**
	 * Labyrinth method to close the communication door between two vertexes .
	 * @param edge the edge that we want to close
	 */
	public void closeDoor(Edge edge) {
		edge.setType(Edge.Type.CLOSED_DOOR );
	}
	
	/**
	 * Close a random door
	 */
	public void closeDoorRandom() {
		Edge edge = instance.randomEdge() ;
		closeDoor(edge) ;
	}
	
	/**
	 * Method that tells if there is a wall from a specific vertex and a specific direction
	 * @param vertex the vertex from which there might be a wall
	 * @param dir the direction in which we want to check if there is a wall
	 * @return true if there is a wall, false otherwise
	 */
	public boolean isWall(Vertex vertex, Directions dir) {
		Edge edge = instance.getEdge(vertex, dir) ;
		return (edge == null) ;
	}
	
	/**
	 *  Method that tells if there is a closed way from a specific vertex and a specific direction
	 * @param vertex the vertex from which there might be a closed way
	 * @param dir the direction in which we want to check if there is a closed way
	 * @return true if there is a closed way, false otherwise
	 */
	public boolean isClosed(Vertex vertex, Directions dir) {
	    Edge edge = null;
	    if(vertex != null)
		    edge = instance.getEdge(vertex, dir) ;
		return (edge == null) || (edge.getType() == Edge.Type.CLOSED_DOOR) ;
	}
	
	/**
	 * Method that tells if there is way way from a specific vertex and a specific direction
     * @param vertex the vertex from which there might be a way
     * @param dir the direction in which we want to check if there is a way
     * @return true if there is a way, false otherwise
	 */
	public boolean isOpened(Vertex vertex, Directions dir) {
		Edge edge = instance.getEdge(vertex, dir) ;
		return ((edge != null) && ((edge.getType() != Edge.Type.CLOSED_DOOR))) ;
	}
	
	/**
     *  Method that tells if there is a closed door from a specific vertex and a specific direction
     * @param vertex the vertex from which there might be a closed door
     * @param dir the direction in which we want to check if there is a closed door
     * @return true if there is a closed door, false otherwise
	 */
	public boolean isClosedDoor(Vertex vertex, Directions dir) {
		Edge edge = instance.getEdge(vertex, dir) ;
		return (edge != null && edge.getType() == Edge.Type.CLOSED_DOOR) ;
	}

    /**
     *  Method that tells if there is a open door from a specific vertex and a specific direction
     * @param vertex the vertex from which there might be a open door
     * @param dir the direction in which we want to check if there is a open door
     * @return true if there is a open door, false otherwise
     */
	public boolean isOpenedDoor(Vertex vertex, Directions dir) {
		Edge edge = instance.getEdge(vertex, dir) ;
		return ((edge != null) && ((edge.getType() != Edge.Type.OPENED_DOOR))) ;
	}

    /**
     * Method that tells if two vertices are connected on the graph
     * @param v1 the first vertex
     * @param v2 the second vertex
     * @return true if there is an edge between the two vertices, false otherwise.
     */
	public boolean isConnected(Vertex v1, Vertex v2)
    {
        return getEdge(v1, v2) != null;
    }
	
	/**
	 * Opens a door randomly .
	 */
	public void openDoorRandom() {
		Random random = new Random() ;
		
		for(int i = 1 ; i <= 1000 ; ++i) {
			Vertex vertex = instance.randomVertex() ;
			if(vertex != null) {
				Directions dir = Directions.values()[random.nextInt(Directions.values().length)] ;
				if(isWall(vertex, dir)) {
					Vertex vertex2 = instance.getVertexByDir(vertex, dir) ;
					if(vertex2 != null) {
						Edge edge = instance.getEdge(vertex, vertex2) ;
						if(edge == null) {
							instance.addEdge(vertex, vertex2, new Edge(Type.OPENED_DOOR)) ;
							return ;
						}
					}
				}
			}
		}
	}
	
	/**
	 * The Manhattan's algorithm to calculate the distance between the source vertex and the target vertex .
	 * @param src : The source vertex .
	 * @param trg : The target vertex .
	 */
	private void calculateManhattanDistance(Vertex src, Vertex trg)
	{
		Queue<Vertex> fifo = new ArrayDeque<Vertex>();
		trg.setNbr(1);
		fifo.add(trg);
		while(!fifo.isEmpty())
        {
            Vertex current = fifo.remove();
            for(Directions dir : Directions.values())
            {
                if(isOpened(current, dir))
                {
                    Vertex next = getVertexByDir(current, dir);
                    if(next != null)
                    {
                        if(next.getNbr() == 0)
                        {
                            next.setNbr(current.getNbr()+1);
                            if(!next.equals(src))
                                fifo.add(next);
                        }
                    }

                }
            }
        }
	}
	
	/**
	 * The public Manhattan's method. It initializes the vertexes distance to 0 and then call the Manhattan's algorithm .
	 * @param src : The source vertex .
	 * @param trg : The target vertex .
	 */
	public void launchManhattan(Vertex src, Vertex trg) {
		for(Vertex vertex : instance.vertexSet()) {
			vertex.setNbr(0);
		}
		
		calculateManhattanDistance(src, trg);
	}
	
	/**
	 * Get a vertex in the graph, given its position .
	 * @param x : The x coordinates of the vertex .
	 * @param y : The y coordinates of the vertex .
	 * @return A vertex in the position(x,y), null in the case of no such vertex .
	 */
	public Vertex getVertexByXY(int x, int y) {
		for(Vertex vertex : instance.vertexSet()) {
			if(vertex.getX() == x && vertex.getY() == y)
				return vertex ;
		}
		
		return null ;
	}
}
