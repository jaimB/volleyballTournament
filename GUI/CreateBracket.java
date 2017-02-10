package GUI;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import SkeletonCode.Tournament;

/**
 * This class is for creating Tournament brackets. <br>
 * Uses {@link SingleElim} JPanels in it.
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public class CreateBracket extends JTrnFrame {
	private JPanel bracketPanel;
	private JPanel southPanel;
	private JPanel centerPanel;
	private JPanel finalPanel;
	private SingleElim singEl;
	private JButton submitBtn;
	private JLabel greetingLabel;
	private JButton homeButton;
	private JScrollPane area;
	private int nColumns;
	private int nTeams;
	private int nSpaces;
	private Tournament tournament;
	private int columnNum = 0;

	/**
	 * Constructor for a CreateBracket. It creates brackets
	 * 
	 * @param tourn - Tournament
	 */
	public CreateBracket(Tournament tourn) {
		super(550, 725);
		for (int i = 0; tourn.getTeamList().size() > Math.pow(2, i); i++) {
			nSpaces = i + 1;
		}
		tournament = tourn;
		nTeams = (int) Math.pow(2, nSpaces);
		nColumns = (int) (Math.log(nTeams) / Math.log(2));
		createButton();
		createPanels();
		setTitle("");
	}
	/**
	 * Creats columns for the CreateBracket
	 * 
	 * @param col - Integer
	 * @return cPanel - JPanel
	 */
	private JPanel createColumn(int col){
		JPanel cPanel = new JPanel();
		columnNum = col;
		singEl = new SingleElim(tournament, columnNum, nColumns);
		cPanel.add(singEl);
		columnNum++;
		return cPanel;
	}

	/**
	 * Creates buttons for the CreateBracket
	 */
	private void createButton() {

		greetingLabel = new JLabel("Bracket", SwingConstants.CENTER);
		greetingLabel.setFont(new Font("Arial", Font.BOLD, 24));

		ActionListener listener = new choiceListener();
		submitBtn = new JButton("Submit");
		submitBtn.addActionListener(listener);
		submitBtn.setFont(new Font("Arial", Font.PLAIN, 16));

		homeButton = new JButton("Home");
		homeButton.addActionListener(listener);
		homeButton.setFont(new Font("Arial", Font.PLAIN, 16));	}

	/**
	 * Listens to actions on this CreateBracket
	 *
	 */
	class choiceListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == homeButton){
				JFrame frame1 = new MainScreen();
				frame1.setVisible(true);
			}
			else{	//event.getSource() == submitBtn
				//Checks for empty fields
				if(tournament.getStructure().getBrackets().get(0).getGames().size() >= 1){
					for(int i = 0; i < 2*tournament.getStructure().getBrackets().get(0).getGames().size(); i++){
						tournament.getStructure().getBrackets().get(0).getGames().get(i/2).setScoreOne(Integer.valueOf(singEl.getScoreArray().get(i).getText()));
						i++;
						tournament.getStructure().getBrackets().get(0).getGames().get(i/2).setScoreTwo(Integer.valueOf(singEl.getScoreArray().get(i).getText()));
						tournament.getStructure().getBrackets().get(0).getGames().get(i/2).completeGame();
					}
					boolean complete = true;
					for (int p = 0; p < tournament.getStructure().getBrackets().get(0).getGames().size(); p++){if (!tournament.getStructure().getBrackets().get(0).getGames().get(p).isComplete()){complete = false;}}
					if(complete){
						tournament.getStructure().advanceTournament();
						bracketPanel.add(createColumn(columnNum));
						revalidate();
					}
					else{
						String message = "Please input valid scores";
						JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", 
						JOptionPane.ERROR_MESSAGE);
					}
				}
				else{
					tournament.getStructure().getBrackets().get(0).getGames().get(0).setScoreOne(Integer.valueOf(singEl.getScoreArray().get(0).getText()));
					tournament.getStructure().getBrackets().get(0).getGames().get(0).setScoreTwo(Integer.valueOf(singEl.getScoreArray().get(1).getText()));
					tournament.getStructure().getBrackets().get(0).getGames().get(0).completeGame();
					bracketPanel.add(createColumn(columnNum));
					revalidate();
				}
			}
		}
	}

	/**
	 * Creates all the panels for this CreateBracket
	 */
	private void createPanels(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		bracketPanel = new JPanel(new GridLayout(1,nColumns,0,0));
		centerPanel = new JPanel();
		southPanel = new JPanel();
		finalPanel = new JPanel(new BorderLayout());

		bracketPanel = createColumn(columnNum);

		centerPanel.add(bracketPanel);
		southPanel.add(homeButton);
		southPanel.add(submitBtn);

		finalPanel.add(greetingLabel, BorderLayout.NORTH);
		finalPanel.add(centerPanel, BorderLayout.CENTER);
		finalPanel.add(southPanel, BorderLayout.SOUTH);

		area = new JScrollPane(finalPanel);
		add(area);
	}
}
