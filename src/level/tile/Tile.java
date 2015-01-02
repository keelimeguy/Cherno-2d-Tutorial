package game2d.level.tile;

import game2d.graphics.Screen;
import game2d.graphics.Sprite;

public abstract class Tile {

	public static Tile voidTile = new BasicTile(Sprite.voidSprite, false);

	// Spawn level tiles
	public static Tile grass = new AnimatedTile(Sprite.spawnGrass);
	public static Tile hedge = new AnimatedTile(Sprite.spawnHedge);
	public static Tile water = new AnimatedTile(Sprite.spawnWater);
	public static Tile brick = new BasicTile(Sprite.spawnBrick, true);
	public static Tile floor = new BasicTile(Sprite.spawnFloor, false);
	public static Tile multiBrick = new BasicTile(Sprite.spawnMultiBrick, true);
	
	public int x, y;
	public Sprite sprite;

	/**
	 * Creates a tile from a sprite
	 * @param sprite : The sprite used by the tile
	 */
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	/**
	 * Renders the tile to the screen
	 * @param x : The x coordinate of the tile (in tile units)
	 * @param y : The y coordinate of the tile (in tile units)
	 * @param screen : The screen to draw the tile to
	 */
	public void render(int x, int y, Screen screen) {
	}

	/**
	 * Determines if the tile is solid or not
	 * @return True if the tile is solid, false otherwise
	 */
	public boolean solid() {
		return false;
	}
}
