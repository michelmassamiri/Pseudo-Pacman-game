package Model;

import java.util.Random;

public class Vertex {
	
	private int x, y ; // The coordinates (position) of the vertex 
	private int nbr ; // The manhattan distance between the source vertex and the target vertex
	
	/*
	 * These variables could be placed in a different file .
	 * We have to reconsider their values too
	 */
	public static final int NORTH_BORDER = 0 ; 
	public static final int SOUTH_BORDER = 15 ;
	public static final int WEST_BORDER = 0 ;
	public static final int EAST_BORDER = 15 ;
	
	/**
	 * A Vertex constructor 
	 * @param x : the required x position
	 * @param y : the required y position
	 * @param nbr : the manhattan distance
	 */
	public Vertex(int x, int y, int nbr) {
		this.x = x ;
		this.y = y ;
		this.nbr = nbr ;
	}
	
	/**
	 * A Vertex construct which generates a vertex with random positions
	 * @param nbr
	 */
	public Vertex(int nbr) {
		Random rand = new Random() ;
		x = rand.nextInt((SOUTH_BORDER - EAST_BORDER) + 1) + EAST_BORDER ;
		y = rand.nextInt((SOUTH_BORDER - EAST_BORDER) + 1) + EAST_BORDER ;
		this.nbr = nbr ;
	}
	
	/**
	 * Returns the x position of the vertex
	 * @return an integer that represents the x position of the vertex
	 */
	public int getX() {
		return x;
	}
	
	/**
	 * Set the x position of the vertex to the required x
	 * @param x the required x position
	 */
	public void setX(int x) {
		this.x = x;
	}
	
	/**
	 * Returns the y position of the vertex
	 * @return an integer that represents the y position of the vertex
	 */
	public int getY() {
		return y;
	}
	
	/**
	 * Set the y position of the vertex to the required y
	 * @param y the required y position
	 */
	public void setY(int y) {
		this.y = y;
	}
	
	/**
	 * Returns the distance between the source and the target vertex.
	 * @return an Integer that represents the manhattan distance.
	 */
	public int getNbr() {
		return nbr;
	}
	
	/**
	 * Set the distance of  the manhattan algorithm 
	 * @param nbr the distance between the source and the target
	 */
	public void setNbr(int nbr) {
		this.nbr = nbr;
	}
	
	/**
	 * Checks if the vertex stays in the BORDERS in case of any move 
	 * @param dir the direction of the move 
	 * @return True of False
	 */
	public Boolean inBorders(Directions dir) {
		Boolean ret = false ;
		
		switch (dir) {
		case NORTH : ret = y - 1 >= NORTH_BORDER ; break ;
		case SOUTH : ret = y + 1 <= SOUTH_BORDER ; break ;
		case WEST  : ret = x - 1 >= WEST_BORDER ; break ;
		case EAST  : ret = x + 1 <= EAST_BORDER ; break ;
		}
		
		return ret ;
	}
}
