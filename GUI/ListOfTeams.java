package GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.*;

import SkeletonCode.Tournament;

/**
 * This class is for making the List of teams that appear in the GUI
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public class ListOfTeams extends JTrnFrame{
	private JTextArea listTeamsLabel;
	private JScrollPane centerPanel;
	private JLabel greetingLabel;
	private JLabel teamNameLabel;
	private JLabel trnNameLabel;
	private JPanel finalPanel;
	private JPanel southPanel;
	private JPanel teamPanel;
	private JPanel northPanel;
	private JPanel listTeamsPanel;
	private JButton contButton;
	private JButton manageButton;
	private JButton regButton;
	private Tournament tournament;
	private String trnName;
	private int n = 0;

	/**
	 * Construct for ListOfTeams.
	 */
	public ListOfTeams(){
		super(550,725);
		createItems();
		createText();
		createPanel();
		setSize(550,725);
		setTitle("");
	}
	/**
	 * Constructor for ListOfTeams with Tournament as a parameter
	 * 
 	 * @param t - Tournament
	 */
	public ListOfTeams(Tournament t){
		super(500,500);
		tournament = t;
		trnName = t.getName();
		n++;
		createItems();
		createButton();
		createText();
		createPanel();
		setTitle("");
	}
	
	/**
	 * Creates Items for this ListOfTeams
	 */
	private void createItems(){
		greetingLabel = new JLabel("List Of Teams for Tournament: ", SwingConstants.CENTER);
		greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));

		trnNameLabel = new JLabel(trnName, SwingConstants.CENTER);
		trnNameLabel.setFont(new Font("Arial", Font.BOLD, 24));

		teamNameLabel = new JLabel("Team Name");
		teamNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		
		listTeamsLabel = new JTextArea(19,35);
		listTeamsLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		listTeamsLabel.setEditable(false);
	}

	/**
	 * Creates buttons for this ListOfTeams
	 */
	private void createButton(){
		ActionListener listener = new choiceListener();
		contButton = new JButton("Home");
		contButton.addActionListener(listener);
		contButton.setFont(new Font("Arial", Font.PLAIN, 16));
		
		regButton = new JButton("Register Another Team");
		regButton.addActionListener(listener);
		regButton.setFont(new Font("Arial", Font.PLAIN, 16));

		manageButton = new JButton("Manage Tournaments");
		manageButton.addActionListener(listener);
		manageButton.setFont(new Font("Arial", Font.PLAIN, 16));

	}

	/**
	 * CreateText Populates text in listTeamsLabel
	 */
	private void createText(){
		for(int i = 0; i < tournament.getTeamList().size(); i++){
			listTeamsLabel.append(tournament.getTeamList().get(i).getTeamName() + "\n");
		}
	}
	
	/**
	 * Listens for events with buttons.
	 */
	class choiceListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == regButton){
				JFrame register = new Register();
				register.setVisible(true);
				dispose();
			}
			else if(event.getSource() == manageButton){
				JFrame register = new ManageTournament();
				register.setVisible(true);
				dispose();
			}
			else{	//event.getSource() == contButton
				JFrame mainScrn = new MainScreen();
				mainScrn.setVisible(true);
				dispose();
			}
		}
	}
	
	/**
	 * Creates panels for this ListOfTeams
	 */
	private void createPanel(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finalPanel = new JPanel(new BorderLayout());
		southPanel = new JPanel();
		teamPanel = new JPanel(new GridLayout(1,2));		
		northPanel = new JPanel(new GridLayout(3,1));		
		listTeamsPanel = new JPanel();
		
		listTeamsPanel.add(listTeamsLabel);
		centerPanel = new JScrollPane(listTeamsPanel);
		
		southPanel.add(contButton);
		southPanel.add(manageButton);
		southPanel.add(regButton);
		teamPanel.add(teamNameLabel);
		northPanel.add(greetingLabel);
		northPanel.add(trnNameLabel);
		northPanel.add(teamPanel);
		
		finalPanel.add(northPanel, BorderLayout.NORTH);
		finalPanel.add(centerPanel, BorderLayout.CENTER);
		finalPanel.add(southPanel, BorderLayout.SOUTH);
		add(finalPanel);
	}
}
