package GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.*;

import SkeletonCode.Tournament;

/**
 * This class describes a Tournament panel that is shown in the ManageTournament frame
 * Tournament panel has teams, edit, delete and generate buttons.
 * <br><br>
 * Generate opens the {@link TournamentType} Frame
 * <br>
 * Edit brings up the {@link CreateTournament} frame
 * <br>
 * Delete causes the panel to dispose and die
 * <br>
 * Teams brings up {@link ListOfTeams} frame.
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public class TournamentPanel extends JPanel{
	private static final long serialVersionUID = 2250135867691325206L;
	private JLabel trnLabel;
	private JLabel venueLabel;
	private JLabel startDateLabel;
	private JLabel regDateLabel;
	private JLabel structureLabel;
	private JLabel numTeamsLabel;
	private JButton teamsButton;
	private JButton editButton;
	private JButton deleteButton;
	private JButton generateButton;
	private JPanel finalPanel;
	private JPanel centerPanel;
	private JPanel southPanel;

	private String trnName;
	private String venueName;
	private String startDate;
	private String regDate;
	private String orgName;
	private String orgInfo;
	private int numOfTeams;
	private int index;
	private String tType;
	private Tournament tournament;
	
	/**
	 * Constructor for a TournamentPanel
	 */
	public TournamentPanel(){
		getInfo();
		createItems();
		createButtons();
		createPanels();
	}

	/**
	 * Constructor for a TournamentPanel with two parameters
	 * @param tourn - Tournament
	 * @param i - Integer
	 */
	public TournamentPanel(Tournament tourn, int i){
		tournament = tourn;
		index = i;
		getInfo();
		createItems();
		createButtons();
		createPanels();
	}

	/**
	 * Returns nothing, populates instance strings to be displayed in the panel.
	 */
	private void getInfo(){
		numOfTeams = tournament.getTeamList().size();
 		trnName = tournament.getName();
 		venueName = tournament.getVenue();
 		startDate = tournament.getStartDate();
 		regDate = tournament.getEndDate();
 		//TODO: FIX THIS
 		tType = String.valueOf("0");
	}

	/**
	 * Creates Labels for this TournamentPanel
	 */
	private void createItems(){
		trnLabel = new JLabel("Name: " + trnName);
		trnLabel.setFont(new Font("Arial", Font.PLAIN, 16));

		venueLabel = new JLabel("Location: " + venueName);
		venueLabel.setFont(new Font("Arial", Font.PLAIN, 16));

		numTeamsLabel = new JLabel("Number of Teams: " + Integer.valueOf(numOfTeams));
		numTeamsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		
		structureLabel = new JLabel("Structure: " + tType);
		structureLabel.setFont(new Font("Arial", Font.PLAIN, 16));

		startDateLabel = new JLabel("Tournament Start Date: " + startDate);
		startDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
			
		regDateLabel = new JLabel("Registration Closing Date: " + regDate);
		regDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		if(tType.equals("0")){		structureLabel.setText("Structure: Unspecified");	}
		else if(tType.equals("1")){		structureLabel.setText("Structure: Single Elimination");	}
		else if(tType.equals("2")){		structureLabel.setText("Structure: Double Elimination");	}
		else 					{		structureLabel.setText("Structure: Divisions");	}
	}
	
	/**
	 * Creates buttons for this TournamentPanel
	 */
	private void createButtons(){
		ActionListener listener = new choiceListener();
		editButton = new JButton("Edit");
		editButton.addActionListener(listener);
		editButton.setFont(new Font("Arial", Font.PLAIN, 16));	

		generateButton = new JButton("Generate");
		generateButton.addActionListener(listener);
		generateButton.setFont(new Font("Arial", Font.PLAIN, 16));	

		teamsButton = new JButton("Teams List");
		teamsButton.addActionListener(listener);
		teamsButton.setFont(new Font("Arial", Font.PLAIN, 16));	

		deleteButton = new JButton("Delete");
		deleteButton.addActionListener(listener);
		deleteButton.setFont(new Font("Arial", Font.PLAIN, 16));	
	}

	/**
	 * Listens for events with buttons on this TournamentPanel
	 */
	class choiceListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == generateButton){
				if(tType.equals("unspecified")){
					JFrame frame1 = new TournamentType(index);
					frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame1.setVisible(true);
					((ManageTournament)finalPanel.getRootPane().getParent()).dispose();
				}
				else{
					JFrame frame1 = new TournamentType(index, tType);
					frame1.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
					frame1.setVisible(true);
					((ManageTournament)finalPanel.getRootPane().getParent()).dispose();
				}
			}
			else if(event.getSource() == teamsButton){
				JFrame frame1 = new ListOfTeams(tournament);
				frame1.setVisible(true);
				((ManageTournament)finalPanel.getRootPane().getParent()).dispose();
			}
			else if(event.getSource() == deleteButton){
				Viewer.Tournaments.remove(index);
				JFrame frame1 = new ManageTournament();
				frame1.setVisible(true);
				((ManageTournament)finalPanel.getRootPane().getParent()).dispose();
			}
			else{	//event.getSource() == editButton
				Viewer.Tournaments.remove(index);
				JFrame frame1 = new CreateTournament(trnName, venueName, orgName, orgInfo);
				frame1.setVisible(true);
				((ManageTournament)finalPanel.getRootPane().getParent()).dispose();
			}
		}
	}
		
	/**
	 * Creates panels on this TournamentPanel
	 */
	private void createPanels(){
		finalPanel = new JPanel(new BorderLayout());
		centerPanel = new JPanel(new GridLayout(4,2));
		southPanel = new JPanel();

		centerPanel.add(trnLabel);
		centerPanel.add(structureLabel);
		centerPanel.add(venueLabel);
		centerPanel.add(regDateLabel);
		centerPanel.add(numTeamsLabel);
		centerPanel.add(startDateLabel);

		southPanel.add(editButton);
		southPanel.add(deleteButton);
		southPanel.add(generateButton);
		southPanel.add(teamsButton);

		finalPanel.add(centerPanel, BorderLayout.CENTER);
		finalPanel.add(southPanel, BorderLayout.SOUTH);
		finalPanel.setBorder(BorderFactory.createRaisedBevelBorder());

		add(finalPanel);
	}
}