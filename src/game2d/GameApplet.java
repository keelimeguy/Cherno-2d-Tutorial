package game2d;

import java.awt.BorderLayout;

import javax.swing.JApplet;

public class GameApplet extends JApplet {
	private static final long serialVersionUID = 1L;
	private Game display = new Game();

	public void init() {
		setLayout(new BorderLayout());
		add(display);
	}

	public void start() {
		display.start();
	}

	public void stop() {
		display.stop();
	}
}