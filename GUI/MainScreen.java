package GUI;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.*;

import SkeletonCode.Tournament;

/**
 * This class describes the Main GUI menu that appears when the program is started
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public class MainScreen extends JTrnFrame{
	private JPanel finalPanel;
	private JButton bracketsBtn;
	private JButton manageBtn;
	private JButton coachBtn;
	private Tournament t;
	
	/**
	 * Constructor for MainScreen Frame. This is the main menu.
	 */
	public MainScreen(){
		super(500,500);
		createItems();
		createPanel();
		setTitle("");
	}


	/**
	 * Creates buttons for this MainScreen
	 */
	private void createItems(){
		ActionListener listener = new choiceListener();
		manageBtn = new JButton("Organizers");
		manageBtn.addActionListener(listener);
		manageBtn.setFont(new Font("Arial", Font.PLAIN, 16));
		
		coachBtn = new JButton("Coaches");
		coachBtn.addActionListener(listener);
		coachBtn.setFont(new Font("Arial", Font.PLAIN, 16));

		bracketsBtn = new JButton("Brackets");
		bracketsBtn.addActionListener(listener);
		bracketsBtn.setFont(new Font("Arial", Font.PLAIN, 16));
	}

	/**
	 * Listens for events with buttons on this MainScreen
	 */
	class choiceListener implements ActionListener{
		public void actionPerformed(ActionEvent event){
			if(event.getSource() == manageBtn){
				JFrame manage = new ManageTournament();
//				frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				manage.setVisible(true);
				dispose();
			}
			
			else if(event.getSource() == bracketsBtn){
				//Checks if a tournament exists and checks if there is a structure
				if (t == null){
					String message = "Please select a tournament";
					JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", 
							JOptionPane.ERROR_MESSAGE); 
				}
				else if (t.getStructure()==null){
					String message = "No tournament structure selected";
					JOptionPane.showMessageDialog(new JFrame(), message, "Dialog", 
							JOptionPane.ERROR_MESSAGE); 
				}
				else{
				JFrame createBracket = new CreateBracket(t);
//				JFrame frame1 = new Division(t1, numOfDivs);
				createBracket.setVisible(true);
				dispose();
				}
			}
			
			else{//event.getSource() == CoachButton
				JFrame register = new Register();
				register.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				register.setVisible(true);
				dispose();
			}
		}
	}

	/**
	 * Creates panels for this MainScreen
	 */
	private void createPanel(){
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		finalPanel = new JPanel();
		
		finalPanel.add(manageBtn);
		finalPanel.add(coachBtn);
		finalPanel.add(bracketsBtn);
		add(finalPanel);
	}

}
