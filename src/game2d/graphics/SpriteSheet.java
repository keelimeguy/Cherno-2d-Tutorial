package game2d.graphics;

import game2d.Game;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;

import javax.imageio.ImageIO;

public class SpriteSheet {

	private String path;
	public final int SIZE;
	public int[] pixels;

	public static SpriteSheet tiles = new SpriteSheet("/textures/sheets/spritesheet.png", 288);
	public static SpriteSheet spawn_tiles = new SpriteSheet("/textures/sheets/spawn_level.png", 48);
	public static SpriteSheet projectile_wizard = new SpriteSheet("/textures/sheets/projectiles/wizard.png", 48);

	/**
	 * Creates a sprite sheet from a resource path
	 * @param path : File path to image to create sprite sheet from
	 * @param size : Square size of the image to create sprite sheet from
	 */
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	/**
	 * Loads a sprite sheet from the image pointed to by the path
	 */
	private void load() {
		try {
			URL location = Game.class.getProtectionDomain().getCodeSource().getLocation();
			File file = new File(location.getFile());
			BufferedImage image = ImageIO.read(new File(file.getParentFile() + "/res" + path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
