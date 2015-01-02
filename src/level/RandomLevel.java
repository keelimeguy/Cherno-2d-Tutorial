package game2d.level;

import game2d.level.tile.Tile;

import java.util.Random;

public class RandomLevel extends Level {

	private static final Random random = new Random();

	/**
	 * Creates a random level
	 * @param width : The width of the level
	 * @param height : The height of the level
	 */
	public RandomLevel(int width, int height) {
		super(width, height);
	}

	/**
	 * Generates a random level
	 */
	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tiles[x + y * width] = random.nextInt(4);
			}
		}
	}
	
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == 0) return Tile.grass;
		if (tiles[x + y * width] == 1) return Tile.hedge;
		if (tiles[x + y * width] == 2) return Tile.brick;
		if (tiles[x + y * width] == 3) return Tile.floor;
		return Tile.voidTile;
	}
}
