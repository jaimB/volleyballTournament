package GUI;

import java.awt.*;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.text.JTextComponent;

import SkeletonCode.Bracket;
import SkeletonCode.Team;
import SkeletonCode.Tournament;

//import SkeletonCode.Team;

/**
 * SingleElimination represented as a JPanel.
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public class SingleElim extends JPanel{
	private JPanel treePanel;
	private int nSpaces;
	private int columns;
	private int colNum;
	private int spacers;
	private int side = 5;
	private int top = 1;
	private int bottom = 1;
	private String teamName;
	private int teamSpaces;
	private Tournament tournament;
	private ArrayList<JComponent> panList = new ArrayList<JComponent>();
	ArrayList<JTextField> scoreArray = new ArrayList<JTextField>();

	/**
	 * Constructor for a SingleElim
	 * 
	 * @param t - Tournament
	 * @param coln - Integer
	 * @param nColumns - Integer
	 */
	public SingleElim(Tournament t, int coln, int nColumns){
		super();
		this.setBackground(Color.WHITE);
		tournament = t;
		for(int i = 0; tournament.getTeamList().size() > Math.pow(2,i); i++){
			teamSpaces = i+1;
		}
		colNum = coln;
		columns = nColumns;
		nSpaces = (int) Math.pow(2,teamSpaces);
		spacers = (int) Math.pow(2, colNum);
		createBorders();
		createTextBoxes();
		createPanel();
	}
	
	/**
	 * Creates panels for this SingleElimination object.
	 */
	private void createBorders(){
		MatteBorder line;
		Border empty = BorderFactory.createEmptyBorder();

		treePanel = new JPanel(new GridLayout(2*nSpaces, 1,0,0));
		//creates each column
		int height = (int)Math.pow(2, colNum+1);
		for(int j = 0; j<2*nSpaces; j++){
			panList.add(new JTextArea(2,8));
			//Removes line in between a match 
			if((j-spacers)%height == 0){
				top = 1;
				bottom = 0;
				side = 5;
			}
			else if((j-spacers)%height == height-2){
				top = 0;
				bottom = 1;
				side = 5;	
			}
			else{
				top = 0;
				bottom = 0;
				side = 5;
			}
			line = BorderFactory.createMatteBorder(top, 0, bottom, side, Color.black);
			panList.get(j).setBorder(line);
			//removes side bar from blocks in spacing area
			if(j < spacers || j > 2*nSpaces-spacers){
				line = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black);
				panList.get(j).setBorder(line);
			}
			((JTextComponent) panList.get(j)).setEditable(false);
		}
		//Removes blocks that are not suppose to connect teams
		for(int i = 0; i < (Math.pow(2,columns-colNum)-1); i++){
			for(int j = 0; j < height; j++){
				if(i%2 == 1){
					line = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black);
					panList.get(spacers + i*height + j).setBorder(line);
				}
			}
		}
/*		if(numOfColns == colnNum){
			line = BorderFactory.createMatteBorder(1, 0, 0, 0, Color.black);
			panList.get(2*numOfTeams-spacers).setBorder(line);
		}
		else{
*/			line = BorderFactory.createMatteBorder(0, 0, 0, 0, Color.black);
			panList.get(2*nSpaces-spacers).setBorder(line);
//		}
	}
	
	/**
	 * Creates Boxes for this SingleElim. Returns a JPanel with the boxes.
	 * 
	 * @param i - Integer
	 * @return menuPanel - JPanel
	 */
	
	private JLabel createByeLabel(){
		JLabel label = new JLabel("bye");
		return label;
	}
	
	private JPanel createBoxes(int i){
		JPanel menuPanel = new JPanel();
		Bracket b = tournament.getStructure().getBrackets().get(0);
		ArrayList<JLabel> labelArray = new ArrayList<JLabel>();
		if(colNum != columns){
			for(int j = 0; j < tournament.getStructure().getBrackets().get(0).getGames().size(); j++){
				labelArray.add(new JLabel(tournament.getStructure().getBrackets().get(0).getGames().get(j).getTeamOne().getTeamName()));
				if(!tournament.getStructure().getBrackets().get(0).getGames().get(j).bye()){
					labelArray.add(new JLabel(tournament.getStructure().getBrackets().get(0).getGames().get(j).getTeamTwo().getTeamName()));
				}
				else{
					labelArray.add(createByeLabel());
				}
			}
			for(int j = 0; j < 2*tournament.getStructure().getBrackets().get(0).getGames().size(); j++){
				scoreArray.add(new JTextField(3));
				scoreArray.get(j).setText("" + 0);
			}
			menuPanel.add(labelArray.get(i));
			menuPanel.add(scoreArray.get(i));
		}
		else{
			labelArray.add(new JLabel(tournament.getStructure().getBrackets().get(0).getWinners().get(0).getTeamName()));
			menuPanel.add(labelArray.get(2*tournament.getStructure().getBrackets().get(0).getGames().size()));
		}

		menuPanel.setBackground(Color.WHITE);
		return menuPanel;
	}
	
	/**
	 * Creates textfields for the boxes created.
	 */
	private void createTextBoxes(){
		for(int i = 0; i <= nSpaces/Math.pow(2,colNum)-1; i++){
			int index = (int) (Math.pow(2, colNum+1)*i + Math.pow(2, colNum) - 1);
			panList.set(index, createBoxes(i));
		}
	}
/*	private JPanel createBoxes(int i){
		JPanel menuPanel = new JPanel();
		ArrayList<String> array = new ArrayList<String>();
		for(int j = 0; j < tournament.getTeamList().size(); j++){
			array.add(tournament.getTeamList().get(j).getTeamName());
		}
		Bracket b = tournament.getStructure().getBrackets().get(0);
		JComboBox teamMenu = new JComboBox(array.toArray());
		JTextField scoreField = new JTextField(1);
		scoreField.setText("" + 0);
		
		menuPanel.add(teamMenu);
		menuPanel.add(scoreField);
		menuPanel.setBackground(Color.WHITE);
		return menuPanel;
	}
	private void createComboBoxes(){
		for(int i = 0; i <= nTeams/Math.pow(2,colNum)-1; i++){
			int index = (int) (Math.pow(2, colNum+1)*i + Math.pow(2, colNum) - 1);
			panList.set(index, createBoxes(i));
		}
	}
	
	/**
	 * Creates panels for this Register
	 */
	private void createPanel(){
		for(int j = 0; j < 2*nSpaces; j++){
			treePanel.add(panList.get(j));
		}
		add(treePanel);
	}

	/**
	 * Returns an ArrayList of JTextFields representing the scores.
	 * @return scoreArray - ArrayList&ltJTextField&gt
	 */
	public ArrayList<JTextField> getScoreArray(){
		return scoreArray;
	}
}