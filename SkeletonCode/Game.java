package SkeletonCode;
	
/**
 * Describes a Game used in a tournament.
 * Each game has two teams and scores for both.
 * Keeps track of the completion and venue as well.
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public class Game {		//coming back later to add time and venue if we want them
	/**
	 * The venue this game is located
	 */
	private String venue; //If we need a venue object we can make that later
	/**
	 * The time of this game
	 */
	private String time;
	/**
	 * The first team
	 */
	private Team teamOne;
	/**
	 * The second team
	 */
	private Team teamTwo;
	/**
	 * The first team's score
	 */
	private int teamOneScore;
	/**
	 * The second team's score.
	 */
	private int teamTwoScore;
	/**
	 * If this is true, the game has concluded.
	 */
	private boolean complete;
	/**
	 * The Game constructor. Creates a game from two teams.
	 * 
	 * @param one - Team
	 * @param two - Team
	 */
	private boolean bye;
	
	public Game(Team one, Team two){
		this.teamOne = one;
		this.teamTwo = two;
//		this.venue = location;
		this.complete = false;
		bye = false;
	}
	
	public Game(Team one){
		this.teamOne = one;
		teamTwo = null;
		bye = true;
		teamOneScore = 0;
		complete = true;
	}
	
	/**
	 * Set the score for the first team to i
	 * @param i - score
	 */
	public void setScoreOne(int i){
		if (!bye){
		this.teamOneScore = i;
		this.complete = false;						//To prevent completing the game and then going back and entering a new result that doesn't work.
		}
		}
	/**
	 * Set the score for the second team to i
	 * @param i - score
	 */
	public void setScoreTwo(int i){
		if (!bye){
		this.teamTwoScore = i;
		this.complete = false;	
		}
	}
	/**
	 * Get the score of the first team
	 * @return teamOne - score
	 */
	public int getScoreOne(){
		return this.teamOneScore;
	}
	/**
	 * Get the score of the second team
	 * @return teamTwo - score
	 */
	public int getScoreTwo(){
		return this.teamTwoScore;
	}
	/**
	 * Set the location of this game.
	 * In other words, Set the venue of this game.
	 * @param location - Venue
	 */
	public void setLocation(String location){
		this.venue = location;
	}
	/**
	 * Set the time of this game
	 * @param t - Time
	 */
	public void setTime(String t){
		this.time = t;
	}
	/**
	 * Get the venue of this game
	 * @return venue - String
	 */
	public String getVenue(){
		return this.venue;
	}
	/**
	 * Returns the time of this game as a String
	 * 
	 * @return time - String
	 */
	public String getTime(){
		return this.time;
	}
	/**
	 * Completes the game described by this class
	 */
	public void completeGame(){
		if (!bye){
			if (this.teamOneScore + this.teamTwoScore > 27){
				if ((this.teamOneScore > this.teamTwoScore) || (this.teamTwoScore > this.teamOneScore)){		//Normally you have to be ahead by 2 points but in the following case a valid score could be the final score of a game: the receiving team either scored the last point or earned a side-out in the last point and the receiving team is ahead by 1 point. In this case the receiving team wins.
					this.complete = true;
				}
			}
		}else {this.complete = true;}
	}
	/**
	 * Returns a boolean representing the completion of a game
	 * @return complete - boolean
	 */
	public boolean isComplete(){
		return this.complete;
	}
	/**
	 * Returns the winner of this game; This should not be called unless the game is complete
	 * @return winner - Team
	 */
	public Team getWinner(){		//Don't call this unless you've already checked if the game is complete
		if (!bye){
		if (this.teamOneScore > this.teamTwoScore){return this.teamOne;} else {return this.teamTwo;}
		}else{return this.teamOne;}
	}
	/**
	 * Returns the loser of this game; This should not be called unless the game is complete
	 * @return loser - Team
	 */
	public Team getLoser(){		//Don't call this unless you've already checked if the game is complete
		if (this.teamOneScore < this.teamTwoScore){return this.teamOne;} else {return this.teamTwo;}
	}
	/**
	 * Returns the score of the winner
	 * @return score - integer
	 */
	public int getWinnerScore(){
		if (this.teamOneScore > this.teamTwoScore){return this.teamOneScore;} else {return this.teamTwoScore;}
	}
	/**
	 * Returns the score of the loser
	 * @return score - integer
	 */
	public int getLoserScore(){
		if (this.teamOneScore < this.teamTwoScore){return this.teamOneScore;} else {return this.teamTwoScore;}
	}
	public Team getTeamOne(){
		return teamOne;
	}
	public Team getTeamTwo(){
		return teamTwo;
	}
	public void setTeamOne(Team t){
		teamOne = t;
		complete = false;
	}
	public void setTeamTwo(Team t){
		teamTwo = t;
		complete = false;
		bye = false;
	}
	public boolean bye(){
		return bye;
	}
}
