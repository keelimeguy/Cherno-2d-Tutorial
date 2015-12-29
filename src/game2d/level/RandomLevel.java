package game2d.level;

import game2d.level.tile.Board;

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
	
	public Board getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Board.voidTile;
		if (tiles[x + y * width] == 0) return Board.grass;
		if (tiles[x + y * width] == 1) return Board.hedge;
		if (tiles[x + y * width] == 2) return Board.brick;
		if (tiles[x + y * width] == 3) return Board.floor;
		return Board.voidTile;
	}
}
