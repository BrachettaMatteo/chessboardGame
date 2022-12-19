package it.unicam.cs.pa.chessboardGame.structure;

/**
 * Represents the pawn of the game. He is responsible for the proper functioning of the pawn.
 * 
 * @author Matteo Brachetta
 * @version 0.0
 */
public interface pawn {

	/**
	 * The pawn with a higher hierarchy wins over a pawn with a lower hierarchy.
	 * 
	 * @return the hierarchy of the pawn
	 */
	int getHierarchy();

	/**
	 * setting hierarchy pawn
	 * @param hierarchy new hierarchy of pawn
	 */
	void setHierarchy(int hierarchy);

	/**
	 * update movement for pawn.
	 * 
	 * @throws NullPointerException if movement is null
	 * @param movement new movement for pawn
	 */
	void setMovement(movement movement);

	/**
	 * @return all movement for the pawn
	 */
	movement getMovement();

	/**
	 * @return pawn identifier
	 */
	String getId();

	/**
	 * @return pawn name
	 */
	String getName();

	/**
	 * @return true if pawn is life else the pawn is dead and return false
	 */
	boolean getLife();

	/**
	 * setting life of pawn
	 * @param life true is life false is dead
	 */
	void setLife(boolean life);

	/**
	 * @return owner for pawn if not owner return null
	 */
	player getOwner();

}