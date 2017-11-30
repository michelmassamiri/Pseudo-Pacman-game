package Model ;

import java.util.Random;
import java.util.Vector;

import org.jgrapht.* ;
import org.jgrapht.experimental.dag.DirectedAcyclicGraph;

public class Labyrinth extends DirectedAcyclicGraph<Vertex, Edge> {
	/**
	 * 
	 */
	private static final long serialVersionUID = 789954947381591787L;
	//private Vertex vertex ;
	//private Edge edge ;
	
	private static Labyrinth instance = new Labyrinth();
	 
	private Labyrinth() {
		super(Edge.class) ; //super(Class<Edge extends E> Edge.class)
	}
	
	public static Labyrinth getInstance() {
		return instance ;
	}
	
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
	 * 
	 * @param vertex
	 * @param dir
	 * @return
	 */
	//TODO
	public boolean doesntExist(Vertex vertex, Directions dir) {
		return true ;
	}
	
	/**
	 * 
	 * @param vertex
	 * @param dir
	 * @return
	 */
	//TODO
	@Override
	public Edge getEdge(Vertex vertex, Directions dir) {
		
	}
	
	/**
	 * 
	 * @return A Random Edge
	 */
	//TODO
	public Edge randomEdge() {
		Edge edge = new Edge() ;
		return edge ;
	}
	
	/**
	 * Labyrinth method to close the communication door between two vertexes .
	 * @param edge : the edge that we want to close
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
	 * 
	 * @param vertex
	 * @param dir
	 * @return
	 */
	public boolean isWall(Vertex vertex, Directions dir) {
		Edge edge = instance.getEdge(vertex, dir) ;
		return (edge == null) ;
	}
	
	/**
	 * 
	 * @param vertex
	 * @param dir
	 * @return
	 */
	public boolean isClosed(Vertex vertex, Directions dir) {
		Edge edge = instance.getEdge(vertex, dir) ;
		return (edge == null) || (edge.getType() == Edge.Type.CLOSED_DOOR) ;
	}
	
	/**
	 * 
	 * @param vertex
	 * @param dir
	 * @return
	 */
	public boolean isOpened(Vertex vertex, Directions dir) {
		Edge edge = instance.getEdge(vertex, dir) ;
		return ((edge != null) && ((edge.getType() != Edge.Type.CLOSED_DOOR))) ;
	}
	
	/**
	 * 
	 * @param vertex
	 * @param dir
	 * @return
	 */
	public boolean isClosedDoor(Vertex vertex, Directions dir) {
		Edge edge = instance.getEdge(vertex, dir) ;
		return (edge != null && edge.getType() == Edge.Type.CLOSED_DOOR) ;
	}
	
	/**
	 * 
	 * @param vertex
	 * @param dir
	 * @return
	 */
	public boolean isOpenedDoor(Vertex vertex, Directions dir) {
		Edge edge = instance.getEdge(vertex, dir) ;
		return ((edge != null) && ((edge.getType() != Edge.Type.OPENED_DOOR))) ;
	}
}
