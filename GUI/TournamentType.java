package GUI;

import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;
import javax.swing.*;

import SkeletonCode.Tournament;

/**
 * This class is for creating the type for a tournament
 * <br><br>
 * Can open to {@link Division} frame
 * <br>
 * Can open to {@link CreateBracket} frame
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public class TournamentType extends JTrnFrame {
	private static final long serialVersionUID = 647812786814549835L;
	private JLabel greetingLabel;
	private JLabel divLabel;
	private JLabel teamLabel;
	private JLabel typeLabel;
	private JTextField divField;
	private JComboBox bracketBox;
	private JButton submitButton;
	private JButton backBtn;
	private JPanel finalPanel;
	private JPanel teamLabelPanel;
	private JPanel northPanel;
	private JPanel southPanel;
	private JPanel divPanel;
	private String trnName;
	private String nTeams;
	private String trnType;
	private int size;
	private int trnIndex;
	private int nType;
	private int nDivisions;
	private Tournament tournament;
	/**
	 * Constructor for a TournamentType
	 * 
	 * @param i - Integr
	 */
	public TournamentType(int i) {
		super(550, 725);
		tournament = Viewer.Tournaments.get(i);
		trnIndex = i;
		getInfo();
		createItems();
		createButton();
		createPanel();
		setTitle("Create Tournament");
	}

	/**
	 * Constructor for a TournamentType, Takes two extra parameters for index and type.
	 * @param i - Integer
	 * @param type - String
	 */
	public TournamentType(int i, String type) {
		super(500, 500);
		tournament = Viewer.Tournaments.get(i);
		trnIndex = i;
		trnType = type;
		getInfo();
		createItems();
		createButton();
		createPanel();
		setTitle("Create Tournament");
	}

	/**
	 * Returns nothing, populates the name and size
	 */
	public void getInfo() {
		trnName = "TOURNAMENT NAME";
		trnName = tournament.getName();
		size = tournament.getTeamList().size();
		// intTeams = 0;
	}

	/**
	 * Creates labels and a box for TournamentType
	 */
	private void createItems() {
		nTeams = Integer.toString(size);

		String[] bracketTypes = { "Single Elimination", "Divisions" };
		bracketBox = new JComboBox(bracketTypes);

		greetingLabel = new JLabel("Create Tournament", SwingConstants.CENTER);
		greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));

		divLabel = new JLabel("How many divisions are needed? ", SwingConstants.CENTER);
		divLabel.setFont(new Font("Arial", Font.PLAIN, 16));

		teamLabel = new JLabel("There are " + nTeams + " teams registered in " + trnName + ".", SwingConstants.CENTER);
		teamLabel.setFont(new Font("Arial", Font.PLAIN, 16));

		typeLabel = new JLabel("Select a tournament type:");
		typeLabel.setFont(new Font("Arial", Font.PLAIN, 16));

		divField = new JTextField(2);
		divField.setFont(new Font("Arial", Font.PLAIN, 16));
		divField.setText("0");
	}

	/**
	 * Creates buttons for this TournamentType
	 */
	private void createButton() {
		ActionListener listener = new choiceListener();
		submitButton = new JButton("Submit");
		submitButton.addActionListener(listener);
		submitButton.setFont(new Font("Arial", Font.PLAIN, 16));
		
		backBtn = new JButton("Back");
		backBtn.addActionListener(listener);
		backBtn.setFont(new Font("Arial", Font.PLAIN, 16));
	}

	/**
	 * Listens for events with buttons with this TournamentType
	 */
	class choiceListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if(event.getSource() == backBtn){
				JFrame frame1 = new ManageTournament();
				frame1.setVisible(true);
				dispose();
			}
			else{ 	//event.getSource() == submitBtn
				// set type of tournament
				JFrame tournamentTypeFrame;
				int numOfDivs = Integer.valueOf(divField.getText());
				if (bracketBox.getSelectedItem().equals("Single Elimination")) {
					tournament.createSingleElim();
				} else {
					tournament.createDivisions(numOfDivs);
				}
				// TODO: FIX THIS
				// tournament.setType(numType);
				Viewer.Tournaments.set(trnIndex, tournament);
				if (tournament.getStructure().getStructureType() == 1) {
					tournamentTypeFrame = new Division(tournament, numOfDivs);
				} else {
					tournamentTypeFrame = new CreateBracket(tournament);
				}
				tournamentTypeFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				tournamentTypeFrame.setVisible(true);
			}
		}
	}

	/**
	 * Creates panels for this TournamentType
	 */
	private void createPanel() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		finalPanel = new JPanel(new BorderLayout());
		teamLabelPanel = new JPanel();
		northPanel = new JPanel(new GridLayout(3, 1));
		southPanel = new JPanel();
		divPanel = new JPanel();

		divPanel.add(divLabel);
		divPanel.add(divField);

		teamLabelPanel.add(typeLabel);
		teamLabelPanel.add(bracketBox);

		northPanel.add(greetingLabel);
		northPanel.add(teamLabel);
		northPanel.add(teamLabelPanel);

		southPanel.add(backBtn);
		southPanel.add(submitButton);

		finalPanel.add(northPanel, BorderLayout.NORTH);
		finalPanel.add(divPanel);
		finalPanel.add(southPanel, BorderLayout.SOUTH);

		add(finalPanel);
	}
}
