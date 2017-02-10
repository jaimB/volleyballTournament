package GUI;

import java.awt.Dimension;
import java.awt.Toolkit;

import javax.swing.JFrame;

/**
 * This just overrides frames so they always center
 *
 */
public class JTrnFrame extends JFrame {
	/**
	 * Acts the same as new JFrame();
	 */
	public JTrnFrame() {
		super();
	}
	/**
	 * Centers the JFrame with width and height
	 * 
	 * @param width - Integer
	 * @param height - Integer
	 */
	public JTrnFrame(int width, int height) {
		this();
		this.setSize(width,height);
		this.Center();
	}
	/**
	 * Centers the frame with it's current dimensions.
	 */
	public void Center() {
	    Dimension dimension = Toolkit.getDefaultToolkit().getScreenSize();
	    int x = (int) ((dimension.getWidth() - this.getWidth()) / 2);
	    int y = (int) ((dimension.getHeight() - this.getHeight()) / 2);
	    this.setLocation(x, y);
	}
}
