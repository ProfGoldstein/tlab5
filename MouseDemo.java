import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Lab 5 demo of mouse events.
 * 
 * @author Jim Teresco
 * @version Spring 2022
 */
public class MouseDemo implements Runnable, MouseListener, MouseMotionListener, MouseWheelListener {

	/**
	 * The run method to set up the graphical user interface
	 */
	@Override
	public void run() {

		JFrame.setDefaultLookAndFeelDecorated(true);
		JFrame frame = new JFrame("MouseDemo");
		frame.setPreferredSize(new Dimension(500, 500));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// construct an anonymous class that extends JPanel,
		// for which we override the paintComponent method
		JPanel panel = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {

				super.paintComponent(g);

				FontMetrics fm = g.getFontMetrics();

				String toDisplay = "Mouse Around and See!";
				int stringWidth = fm.stringWidth(toDisplay);
				int stringAscent = fm.getAscent();

				int xStart = getWidth() / 2 - stringWidth / 2;
				int yStart = getHeight() / 2 + stringAscent / 2;

				g.drawString(toDisplay, xStart, yStart);
			}
		};
		frame.add(panel);
		panel.addMouseListener(this);
		panel.addMouseMotionListener(this);
		panel.addMouseWheelListener(this);

		// display the window we've created
		frame.pack();
		frame.setVisible(true);
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("mouseClicked: " + e);
	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mousePressed: " + e);
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		System.out.println("mouseReleased: " + e);
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		System.out.println("mouseEntered: " + e);
	}

	@Override
	public void mouseExited(MouseEvent e) {
		System.out.println("mouseExited: " + e);
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		System.out.println("mouseDragged: " + e);
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		System.out.println("mouseMoved: " + e);
	}

	@Override
	public void mouseWheelMoved(MouseWheelEvent e) {
		System.out.println("mouseWheelMoved: " + e);
	}

	public static void main(String args[]) {
		javax.swing.SwingUtilities.invokeLater(new MouseDemo());
	}
}
