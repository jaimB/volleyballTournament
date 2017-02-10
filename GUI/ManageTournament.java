package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.*;

import SkeletonCode.Tournament;

/**
 * This class describes the Management panel for tournaments that exist currently
 * <br>
 * Houses the TournamentPanels in it's central area
 * <br>
 * Refer to {@link TournamentPanel} for the same information
 * <br><br>
 * For a TournamentPanel, contains these buttons and where they lead as follows
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
public class ManageTournament extends JTrnFrame {

	private static final long serialVersionUID = 1153755212127268824L;
	private JLabel greetingLabel;
	private JButton newBtn;
	private JButton backBtn;
	private JButton homeBtn;
	private JPanel finalPanel;
	private JPanel northPanel;
	private JPanel tListPanel;
	private JPanel btnPanel;
	private JScrollPane scrollPane;
	private ArrayList<TournamentPanel> tPanels = new ArrayList<TournamentPanel>();
	private int size;

	/**
	 * Constructor for a ManageTournament
	 */
	public ManageTournament() {
		super(710, 730);
		size = Viewer.Tournaments.size();
		createItems();
		createButtons();
		createPanels();
		setTitle("Manage Tournaments");
	}

	/**
	 * Creates a single label for ManageTournament
	 */
	private void createItems() {
		greetingLabel = new JLabel("Manage Tournaments", SwingConstants.CENTER);
		greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));
	}

	/**
	 * Creates buttons for this ManageTournament.
	 */
	private void createButtons() {
		ActionListener listener = new choiceListener();
		newBtn = new JButton("Create New Tournament");
		newBtn.addActionListener(listener);
		newBtn.setFont(new Font("Arial", Font.PLAIN, 16));

		homeBtn = new JButton("Home");
		homeBtn.addActionListener(listener);
		homeBtn.setFont(new Font("Arial", Font.PLAIN, 16));
	}

	/**
	 * Listens for events with buttons on this ManageTournament
	 */
	class choiceListener implements ActionListener {
		public void actionPerformed(ActionEvent event) {
			if (event.getSource() == homeBtn) {
				JFrame frame1 = new MainScreen();
				frame1.setVisible(true);
				dispose();
			}
			else {// event.getSource() == newButton
				JFrame frame1 = new CreateTournament();
				frame1.setVisible(true);
				dispose();
			}
		}
	}

	/**
	 * Create panels for ManageTournament
	 */
	private void createPanels() {
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		finalPanel = new JPanel(new BorderLayout());
		northPanel = new JPanel(new GridLayout(2, 1));
		tListPanel = new JPanel(new GridLayout(size, 1));
		btnPanel = new JPanel();

		northPanel.add(greetingLabel);
		btnPanel.add(homeBtn);
		btnPanel.add(newBtn);
		northPanel.add(btnPanel);

		for (int i = 0; i < Viewer.Tournaments.size(); i++) {
			tPanels.add(new TournamentPanel(Viewer.Tournaments.get(i), i));
			tListPanel.add(tPanels.get(i));
		}
		scrollPane = new JScrollPane(tListPanel);

		finalPanel.add(northPanel, BorderLayout.NORTH);
		finalPanel.add(scrollPane, BorderLayout.CENTER);

		add(finalPanel);
	}
}
