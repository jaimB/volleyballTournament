package SkeletonCode;
import java.util.ArrayList;

/**
 * Divisions Tournament. Describes a tournament using Divisions.
 * Implements structure interface.
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public class Divisions implements Structure {
	/**
	 * List of brackets this division holds
	 */
	private ArrayList<Bracket> bList = new ArrayList<Bracket>();
	/**
	 * The number of brackets.
	 */
	private int bNumber;
	/**
	 * If this is true, finals have started
	 */
	private boolean finalStart = false;
	/**
	 * The final stage using SingleElimination
	 */
	private SingleElimination finals;
	
	/**
	 * The list of winners
	 */
	private ArrayList<Team> winners = new ArrayList<Team>();
	
	/**
	 * The Divisions constructor. Uses a list of teams and an arbitrary number.
	 * Creates divisions from these two parameters
	 * 
	 * @param teamList - ArrayList&ltTeam&gt
	 * @param i - integer
	 */
	public Divisions(ArrayList<Team> teamList, int i){
		this.bNumber = i;
		int peoplePerBracket = teamList.size() / i;
		int remainder = teamList.size() % i;
		for (int j = 0; j < i; j++){
			ArrayList<Team> tList = new ArrayList<Team>();
			for (int k = 0 ; k < peoplePerBracket ; k++){
				tList.add(teamList.get(k + (j * peoplePerBracket)));
			}
			if (remainder > 0){
				tList.add(teamList.get(teamList.size() - 1));
				tList.remove(teamList.size() - 1);
				remainder--;
			}
			createBrackets(tList);
		}
		for (int q = 0; q < this.bList.size(); q++){
			this.bList.get(q).makeGames();
		}
	}
	
	@Override
	public int getStructureType(){
		return 1;
	}
	
	@Override
	public void createBrackets(ArrayList<Team> teamList) {
		bList.add(new Bracket(teamList));

	}
	

	@Override
	public ArrayList<Bracket> getBrackets(){
		if (!this.finalStart) {return this.bList;} else {return this.finals.getBrackets();}
	}

	@Override
	public void advanceTournament() {
		if (this.bList.size() > 0){
			ArrayList<Bracket> remove = new ArrayList<Bracket>();
			boolean complete = true;
			for (int i = 0; i < this.bList.size(); i++) {
				if (!this.bList.get(i).checkComplete())
					complete = false;
			}
			if (complete) {
				for (int i = 0; i < this.bList.size(); i++){
					for (int j = 0; j < this.bList.get(i).getGames().size(); j++) {
						this.bList.get(i).getGames().get(j).getWinner().win();
						this.bList.get(i).getGames().get(j).getWinner().addPoints(this.bList.get(i).getGames().get(j).getWinnerScore());
						this.bList.get(i).getGames().get(j).getLoser().addPoints(this.bList.get(i).getGames().get(j).getLoserScore());
					}
					
					//This is where I need to combine brackets if needed
					if (this.bList.get(i).getWinners().size() == 1){
						this.winners.add(this.bList.get(i).getWinners().get(0));
						remove.add(this.bList.get(i));
					} else {
						this.bList.set(i, new Bracket(this.bList.get(i).getWinners() ));
						this.bList.get(i).makeGames();
					}
				}
				for (int z = 0; z < remove.size(); z++){
					//System.out.println(remove.get(z));
					this.bList.remove(remove.get(z));
					this.bList.trimToSize();
					//System.out.println("Size after trim: " + bList.size());					
				}
			}
		} else {
			if (!this.finalStart){
				this.finals = new SingleElimination(this.winners);
				this.finalStart = true;
				//bList = finals.getBrackets(); Don't think I need to do this
			} else {
				this.finals.advanceTournament();
			}
		}
		
	}

}
