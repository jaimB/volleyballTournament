package SkeletonCode;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * This is the major class this project uses.
 * Holds teams, start dates, end dates, and the type of this tournament as an integer
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public class Tournament implements Serializable {


	/**
	 * 
	 */
	private static final long serialVersionUID = -1201513273644700077L;
	/**
	 * The place where the tournament is taking place.
	 */
	private String venue;
	/**
	 * The date this tournament will start
	 */
	private String startDate;			//Start and end dates are when one is allowed to register for the tournament
	/**
	 * The date this tournament will end
	 */
	private String endDate;
	/**
	 * The tournament type as an integer
	 */
//	private int tType;
	/**
	 * The tournament name as a string
	 */
	private Structure structure;
	
	private String tournamentName;
	/**
	 * The list of teams of this tournament
	 */
	private ArrayList<Team> teamList = new ArrayList<Team>();
	/**
	 * The Tournament constructor. Takes Name, start and end dates, and the structure as an integer
	 * @param name - String
	 * @param sDate - String
	 * @param eDate - String
	 * @param structure - Integer
	 */
	public Tournament(String name, String sDate, String eDate, String venue){
		this.startDate = sDate;
		this.endDate = eDate;
		this.tournamentName = name;
//		this.tType = structure;
		this.setVenue(venue);
	}
	/**
	 * Returns the name of this tournament
	 * @return tournamentName - String
	 */
	public String getName(){
		return this.tournamentName;
	}
	/**
	 * Sets the name of this tournament
	 * @param name - String
	 */
	public void setName(String name){
		this.tournamentName = name;
	}
	/**
	 * Returns the start date of this Tournament
	 * return startDate - String
	 */
	public String getStartDate(){
		return this.startDate;
	}
	/**
	 * Set the start date of this tournament
	 * @param date - String
	 */
	public void setStartDate(String date){
		this.startDate = date;
	}
	/**
	 * Returns the end date of this Tournament
	 * @return endDate - String
	 */
	public String getEndDate(){
		return this.endDate;
	}
	/**
	 * Sets the end date of this Tournament
	 * @param date - String
	 */
	public void setEndDate(String date){
		this.endDate = date;
	}
	/**
	 * Add a team to the tournament
	 * @param team - Team
	 */
	public void addTeam(Team team){
		this.teamList.add(team);
	}
	/**
	 * Returns the list of teams of this Tournament
	 * @return teamList - ArrayList&ltTeam&gt
	 */
	public ArrayList<Team> getTeamList(){
		return this.teamList;
	}
	/**
	 * Returns the type of this tournament
	 * @return tType - Integer
	 */
	//public int getType(){
	//	return this.tType;
	//}
	/**
	 * Set the Tournament type
	 * @param t - Integer
	 */
	//public void setType(int t){
	//	this.tType = t;
	//}
	/**
	 * Returns the venue of this tournament
	 * @return venue - String
	 */
	public String getVenue() {
		return this.venue;
	}
	/**
	 * Sets the venue of this tournament
	 * @param venue - String
	 */
	public void setVenue(String venue) {
		this.venue = venue;
	}
	
	public void createSingleElim(){
		structure = new SingleElimination(teamList);
	}
	
	public void createDivisions(int i){
		structure = new Divisions(teamList, i);
	}
	
	public Structure getStructure(){
		return structure;
	}
}
