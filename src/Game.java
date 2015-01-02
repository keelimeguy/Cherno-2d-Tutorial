package game2d;

import game2d.entity.mob.Player;
import game2d.graphics.Screen;
import game2d.input.Keyboard;
import game2d.input.Mouse;
import game2d.level.Level;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;

public class Game extends Canvas implements Runnable {
	private static final long serialVersionUID = 1L;

	public static int width = 300;
	public static int height = width / 16 * 9;
	public static int scale = 3;
	public static String title = "2DGame";

	private Thread thread;
	private JFrame frame;
	private Keyboard key;
	private Level level;
	private Player player;
	private boolean running = false;

	private Screen screen;

	// The image which will be drawn in the game window
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	/**
	 * Initiates the necessary variables of the game
	 */
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		level = Level.spawn;

		//level = new RandomLevel(64, 64);-
		player = new Player(19 * 16, 62 * 16, key);
		player.init(level);

		addKeyListener(key);

		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
	}

	/**
	 * Starts the game thread
	 */
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	/**
	 * Stops the game thread
	 */
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Runs the game
	 */
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0, updates = 0;
		requestFocus();

		// The game loop
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			// Update 60 times a second
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			// Keep track of and display the game's ups and fps every second
			if (System.currentTimeMillis() - timer >= 1000) {
				timer += 1000;
				frame.setTitle(title + " | ups: " + updates + ", fps: " + frames);
				updates = 0;
				frames = 0;
			}
		}

		// If we get out of the game loop stop the game
		stop();
	}

	/**
	 * Update the game
	 */
	public void update() {
		key.update();
		player.update();
	}

	/**
	 * Render the game
	 */
	public void render() {

		// Create a buffer strategy for the game
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		// Clear the screen to black before rendering
		screen.clear(0);

		int xScroll = player.x - screen.width / 2;
		int yScroll = player.y - screen.height / 2;

		// Render the level with the given screen offset
		level.render(xScroll, yScroll, screen);

		// Render the player
		player.render(screen);

		// Copy the screen pixels to the image to be drawn
		System.arraycopy(screen.pixels, 0, pixels, 0, pixels.length);

		// Draw the image
		Graphics g = bs.getDrawGraphics();
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		g.dispose();
		bs.show();
	}

	/**
	 * Start of the program
	 * @param args : Unused default arguments
	 */
	public static void main(String[] args) {

		// Create the game
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle(Game.title);
		game.frame.add(game);
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		// Start the game
		game.start();
	}
}
