package Model;

import org.jgrapht.graph.DefaultEdge;

public class Edge extends DefaultEdge implements Comparable<Edge> {
	
	public enum Type {
		OPENED_DOOR,
		CLOSED_DOOR,
		CORRIDOR;
	};
	
	private Type type ; // The new attribut to the Edge which represents the possibility to passe through an Edge. 
	
	/**
	 * The constructor of the Edge class.
	 * @param type : OPENED_DOOR, CLOSED_DOOR or CORRIDOR.
	 */
	public Edge(Type type) {
		super();
		this.type = type ;
	}
	
	/**
	 * Default Constructor, the Type of the Edge is CORRIDOR.
	 */
	public Edge() {
		super();
		this.type = Type.CORRIDOR ;
	}
	
	@Override
	public Vertex getSource() {
		return (Vertex)super.getSource();
	}
	
	@Override
	public Vertex getTarget() {
		return (Vertex)super.getTarget() ;
	}
	
	public Type getType() {
		return type ;
	}
	
	public void setType(Type type) {
		this.type = type ;
	}
	
	@Override
	//TODO
	public int compareTo(Edge o) {
		return 1 ;
	}
	
}
