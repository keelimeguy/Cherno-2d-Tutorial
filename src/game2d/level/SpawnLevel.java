package game2d.level;

import game2d.level.tile.Board;
import game2d.level.tile.TileColor;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpawnLevel extends Level {

	/**
	 * Creates a level from a path
	 * @param path : The file path to the image that the level will be loaded from
	 */
	public SpawnLevel(String path) {
		super(path);
	}

	/**
	 * Load the level from a resource path
	 * @param path : The file path to the image that the level will be loaded from
	 */
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			width = image.getWidth();
			height = image.getHeight();
			tiles = new int[width * height];
			image.getRGB(0, 0, width, height, tiles, 0, width);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	/**
	 * Generate the level based on the loaded image
	 */
	protected void generateLevel() {
	}

	public Board getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Board.voidTile;
		if (tiles[x + y * width] == TileColor.SPAWN_FLOOR) return Board.floor;
		if (tiles[x + y * width] == TileColor.SPAWN_GRASS) return Board.grass;
		if (tiles[x + y * width] == TileColor.SPAWN_HEDGE) return Board.hedge;
		if (tiles[x + y * width] == TileColor.SPAWN_BRICK) return Board.brick;
		if (tiles[x + y * width] == TileColor.SPAWN_MULTI_BRICK) return Board.multiBrick;
		if (tiles[x + y * width] == TileColor.SPAWN_WATER) return Board.water;
		return Board.voidTile;
	}

}
