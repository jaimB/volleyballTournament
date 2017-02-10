package GUI;

import javax.swing.JFrame;

import SkeletonCode.Tournament;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

/**
 * This is the entry point for the Tournament GUI <br>
 * Opens {@link MainScreen} frame.
 * 
 * @author John Hollett
 * @author Keir Strickland Murphy
 * @author Rory Campbell
 * @author Jaimee Bessey
 * @author Kristan James Hart
 * @author Karl Chiasson
 */
public class Viewer {

	public static ArrayList<Tournament> Tournaments;

	/**
	 * The entry point for the tournament.
	 * @param args - String[]
	 */
	public static void main(String[] args) {
		JFrame menu = new MainScreen();
		menu.setVisible(true);
		menu.addWindowListener(new WindowAdapter() {
			@SuppressWarnings("unchecked")
			public void windowOpened(WindowEvent e) {
				try {
					FileInputStream fis = new FileInputStream("Tournaments.txt");
					ObjectInputStream ois = new ObjectInputStream(fis);
					try {
						Object o = ois.readObject();
						if (o == null) {
							Tournaments = new ArrayList<Tournament>();
						} else
							Tournaments = (ArrayList<Tournament>) o;

					} catch (IOException | ClassNotFoundException err) {
						Tournaments = new ArrayList<Tournament>();
					} finally {
						ois.close();
						fis.close();
					}
				} catch (FileNotFoundException err) {
					Tournaments = new ArrayList<Tournament>();
				} catch (IOException err) {
					// Could not close
				}
			}
		});
		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				try {
					FileOutputStream fos = new FileOutputStream("Tournaments.txt");
					ObjectOutputStream oos = new ObjectOutputStream(fos);
					try {
						oos.writeObject(Tournaments);

					} catch (IOException err) {

					} finally {
						oos.flush();
						oos.close();
						fos.flush();
						fos.close();
					}

				} catch (IOException err) {
					// Could not find file or open file.
				}
			}
		}));
	}
}