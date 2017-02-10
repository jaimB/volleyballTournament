package GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.*;

import SkeletonCode.*;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * This class describes the register team panel that 
 * is used to register within a tournament for coaches
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public class Register extends JTrnFrame{
	private JPanel mainInformationPanel;
	private JPanel selectTournamentPanel;
	private JPanel teamPanel;
	private JPanel organizerPanel;
	private JPanel coachPanel;
	private JPanel northBtns;
	private JPanel centerBtns;
	private JPanel playerListPanel;
	private JPanel southPanel;
	private JPanel playerPanel;
	private JPanel completePanel;
	private JPanel finalPanel;
	private JLabel greetingLabel;
	private JComboBox tournamentBox;
	private JButton registerButton;
	private JButton addButton;
	private JButton cancelButton;
	private JButton clearButton;
	private JLabel teamNameLabel;
	private JLabel selectLabel;
	private JLabel organizerNameLabel;
	private JLabel playerNameLabel;
	private JLabel coachNameLabel;
	private JLabel regDateLabel;
	private JTextField teamNameField;
	private JTextField organizerNameField;
	private JTextField playerNameField;
	private JTextField playerAgeField;
	private JTextField coachNameField;
	private JScrollPane centerPanel;
	private ArrayList<PlayerPanel> players; 
	private ArrayList<String> tNames = new ArrayList<String>();
	private int nTournaments;
	private String time;
	private String date;
	private int n = 1;
	


	/**
	 * Constructor for Register. Registers Teams with a tournament.
	 */
	public Register(){
		super(550,725);
		players = new ArrayList<PlayerPanel>();
		getInfo();
		createLabels();
		createFields();
		createButton();
		createPanel();
		setTitle("Register for Tournament");
	}

	/**
	 * Returns nothing, but, populates the names of all the tournaments in an arraylist.
	 */
	private void getInfo(){
		nTournaments = Viewer.Tournaments.size();
		for(int i = 0; i < nTournaments; i++){
			tNames.add(Viewer.Tournaments.get(i).getName());
		}
		time = "1:00AM";
		date = "January 1, 2016";
		if (Viewer.Tournaments.size() != 0){
			date = Viewer.Tournaments.get(0).getEndDate();
//			time = listOfTourns.get(0).getStartDate();
		}
	}

	/**
	 * Creates labels for this Register
	 */
	private void createLabels(){
		greetingLabel = new JLabel("Register For Tournament", SwingConstants.CENTER);
		greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));

		selectLabel = new JLabel("Select a Tournament: ");
		selectLabel.setFont(new Font("Arial", Font.PLAIN, 16));		

		ActionListener listener = new choiceListener();
		tournamentBox = new JComboBox(tNames.toArray());
		tournamentBox.addActionListener(listener);
		tournamentBox.setFont(new Font("Arial", Font.PLAIN, 16));

		regDateLabel = new JLabel("Registration closes on " + date + " at " + time + ".", SwingConstants.CENTER);
		regDateLabel.setFont(new Font("Arial", Font.PLAIN, 16));
		
		teamNameLabel = new JLabel("Team Name:");
		teamNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));

		coachNameLabel = new JLabel("Coach Name:");
		coachNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));

		organizerNameLabel = new JLabel("Organization:");
		organizerNameLabel.setFont(new Font("Arial", Font.PLAIN, 16));

		playerNameLabel = new JLabel("              Player's Name                    Age", SwingConstants.CENTER);
		playerNameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
	}

	/**
	 * Creates textfields for this Register
	 */
	private void createFields(){
		teamNameField = new JTextField(30);
		teamNameField.setText("");

		organizerNameField = new JTextField(30);
		organizerNameField.setText("");
		
		coachNameField = new JTextField(30);
		coachNameField.setText("");
	}
	
	/**
	 * Creates buttons for this Register
	 */
	private void createButton(){
		ActionListener listener = new choiceListener();
		registerButton = new JButton("Register");
		registerButton.addActionListener(listener);
		registerButton.setFont(new Font("Arial", Font.PLAIN, 14));
		
		addButton = new JButton("Add Player");
		addButton.addActionListener(listener);
		addButton.setFont(new Font("Arial", Font.PLAIN, 14));
		
		cancelButton = new JButton("Cancel");
		cancelButton.addActionListener(listener);
		cancelButton.setFont(new Font("Arial", Font.PLAIN, 14));
		
		clearButton = new JButton("Clear");
		clearButton.addActionListener(listener);
		clearButton.setFont(new Font("Arial", Font.PLAIN, 14));
	}

	/**
	 * Listens for events with buttons on this Register.
	 */
	class choiceListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == tournamentBox){
				String selected = tournamentBox.getSelectedItem().toString();
				for(int i = 0; i < nTournaments; i++){
					if(selected.equals(Viewer.Tournaments.get(i).getName())){
						regDateLabel.setText("Registration closes on " + Viewer.Tournaments.get(i).getEndDate());
						revalidate();
						break;
					}
                }
			}
			else if(event.getSource() == addButton){
				if(n >= 40){
					JFrame frame1 = new popUp("You Have Reached the Max Number of Players.");
					frame1.setVisible(true);
					dispose();
				}
				else{
					PlayerPanel p = new PlayerPanel();
					players.add(p);
					playerPanel.add(p);
					playerListPanel.add(playerPanel);
					revalidate();
				}
			}
			else if(event.getSource() == cancelButton){
				JFrame frame1 = new MainScreen();
				frame1.setVisible(true);
				dispose();
			}
			else if(event.getSource() == clearButton){
				JFrame frame1 = new Register();
				frame1.setVisible(true);
				dispose();
			}
			else{
				//Checks for empty fields
				if (teamNameField.getText().isEmpty() || coachNameField.getText().isEmpty()) {
					String message = "Please fill all fields";
					JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", 
							JOptionPane.ERROR_MESSAGE); 
				}
				else{
					int index = 0;
					for(int i=0; i < tNames.size(); i++){
						if(tNames.get(i) == (String)tournamentBox.getSelectedItem()){
							Viewer.Tournaments.get(i).addTeam(new Team(teamNameField.getText(), coachNameField.getText()));
							index = i;
							break;
						}
					}
					JFrame frame1 = new ListOfTeams(Viewer.Tournaments.get(index));
					frame1.setVisible(true);
					dispose();
					for(int i=0; i < players.size(); i++){
						if(players.get(i).getPlayerName() != null && players.get(i).getPlayerAge() != null){
							System.out.println(players.get(i).getPlayerName());
						}
					}
				}
			}
		}
	}
	
	/**
	 * Creates panels for this Register
	 */
	private void createPanel(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		mainInformationPanel = new JPanel(new GridLayout(6,1));
		selectTournamentPanel = new JPanel();
		teamPanel = new JPanel();
		organizerPanel = new JPanel();
		coachPanel = new JPanel();
		northBtns = new JPanel();
		centerBtns = new JPanel();
		playerListPanel = new JPanel();
		southPanel = new JPanel(new BorderLayout());
		completePanel = new JPanel(new BorderLayout());
		finalPanel = new JPanel(new BorderLayout());
		
		selectTournamentPanel.add(selectLabel);
		selectTournamentPanel.add(tournamentBox);
		teamPanel.add(teamNameLabel);
		teamPanel.add(teamNameField);
		organizerPanel.add(organizerNameLabel);
		organizerPanel.add(organizerNameField);
		coachPanel.add(coachNameLabel);
		coachPanel.add(coachNameField);

		northBtns.add(addButton);
		centerBtns.add(cancelButton);
		centerBtns.add(clearButton);
		centerBtns.add(registerButton);

		PlayerPanel p = new PlayerPanel();
		players.add(new PlayerPanel());
		playerPanel = new JPanel(new GridLayout(n-1,1));
		playerPanel.add(p);
		playerListPanel.add(playerPanel);

		mainInformationPanel.add(selectTournamentPanel);
		mainInformationPanel.add(regDateLabel);
		mainInformationPanel.add(teamPanel);
		mainInformationPanel.add(organizerPanel);
		mainInformationPanel.add(coachPanel);
		mainInformationPanel.add(playerNameLabel);

		southPanel.add(northBtns, BorderLayout.NORTH);
		southPanel.add(centerBtns, BorderLayout.CENTER);
		
		completePanel.add(mainInformationPanel, BorderLayout.NORTH);
		completePanel.add(playerListPanel, BorderLayout.CENTER);

		centerPanel = new JScrollPane(completePanel);

		finalPanel.add(greetingLabel, BorderLayout.NORTH);
		finalPanel.add(centerPanel, BorderLayout.CENTER);
		finalPanel.add(southPanel, BorderLayout.SOUTH);
		add(finalPanel);
	}
}