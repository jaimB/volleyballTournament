package SkeletonCode;
import java.util.ArrayList;

/**
 * Structure interface describes a class that has a createBrackets, advanceTournament and getBrackets methods.
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public interface Structure {
	/**
	 * Create the brackets from a teamList.
	 * Creates brackets this method is defined to create.
	 * 
	 * @param teamList - ArrayList&lt<Team&gt
	 */
	public void createBrackets(ArrayList<Team> teamList);
	
	/**
	 * Advance the tournament this method is defined to advance.
	 */
	public void advanceTournament();
	
	/**
	 * Get the brackets. 
	 * Returns the brackets this method defines.
	 * 
	 * @return brackets - ArrayList&ltBracket&gt
	 */
	public ArrayList<Bracket> getBrackets();
	
	public int getStructureType();
}
