package GUI;

import javax.swing.*;

/**
 * This class describes the Player panels that appear when creating a new team.
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public class PlayerPanel extends JPanel{
	private JTextField playerName;
	private JTextField playerAge;
	
	/**
	 * Constructor for a PlayerPanel.
	 */
	public PlayerPanel(){
		playerName = new JTextField(25);
		playerName.setText("");

		playerAge = new JTextField(5);
		playerAge.setText("");
		
		add(playerName);
		add(playerAge);
	}
	
	/**
	 * Returns the player's name as a string represented by this PlayerPanel
	 * @return playerName - String
	 */
	public String getPlayerName(){
		return playerName.getText();
	}
	/**
	 * Returns the player's age as a string represented by this PlayerPanel
	 * @return playerAge - String
	 */
	public String getPlayerAge(){
		return playerAge.getText();
	}	
}
