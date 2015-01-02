package game2d.graphics;

public class Sprite {

	public final int SIZE;
	private int x, y;
	public int[] pixels;
	private SpriteSheet sheet;

	public static Sprite voidSprite = new Sprite(16, 0x1b87e0);
	
	// Spawn level sprites
	public static Sprite spawnGrass = new Sprite(16, 0, 0, SpriteSheet.spawn_tiles);
	public static Sprite spawnHedge = new Sprite(16, 1, 0, SpriteSheet.spawn_tiles);
	public static Sprite spawnWater = new Sprite(16, 2, 0, SpriteSheet.spawn_tiles);
	public static Sprite spawnBrick = new Sprite(16, 0, 1, SpriteSheet.spawn_tiles);
	public static Sprite spawnFloor = new Sprite(16, 1, 1, SpriteSheet.spawn_tiles);
	public static Sprite spawnMultiBrick = new Sprite(16, 0, 2, SpriteSheet.spawn_tiles);

	// Player sprites:
	
	//up
	public static Sprite player00 = new Sprite(32, 3, 0, SpriteSheet.tiles);
	public static Sprite player01 = new Sprite(32, 3, 1, SpriteSheet.tiles);
	public static Sprite player02 = new Sprite(32, 3, 2, SpriteSheet.tiles);
	//side
	public static Sprite player10 = new Sprite(32, 4, 0, SpriteSheet.tiles);
	public static Sprite player11 = new Sprite(32, 4, 1, SpriteSheet.tiles);
	public static Sprite player12 = new Sprite(32, 4, 2, SpriteSheet.tiles);
	//down
	public static Sprite player20 = new Sprite(32, 5, 0, SpriteSheet.tiles);
	public static Sprite player21 = new Sprite(32, 5, 1, SpriteSheet.tiles);
	public static Sprite player22 = new Sprite(32, 5, 2, SpriteSheet.tiles);

	/**
	 * Creates a sprite from a sprite sheet
	 * @param size : Square size of the sprite (in pixels)
	 * @param x : The x coordinate of the sprite in the sprite sheet (in size units)
	 * @param y : The y coordinate of the sprite in the sprite sheet (in size units)
	 * @param sheet : The sprite sheet to take the sprite image from
	 */
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		pixels = new int[SIZE * SIZE];
		load();
	}

	/**
	 * Creates a sprite of a given color
	 * @param size : Square size of the sprite (in pixels)
	 * @param color : The color to fill the sprite
	 */
	public Sprite(int size, int color) {
		SIZE = size;
		pixels = new int[SIZE * SIZE];
		setColor(color);
	}

	/**
	 * Fills the sprite with a color
	 * @param color : The color to fill the sprite
	 */
	private void setColor(int color) {
		for (int i = 0; i < SIZE * SIZE; i++) {
			pixels[i] = color;
		}
	}

	/**
	 * Loads the sprite from its sprite sheet
	 */
	private void load() {
		for (int y = 0; y < SIZE; y++) {
			for (int x = 0; x < SIZE; x++) {
				pixels[x + y * SIZE] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.SIZE];
			}
		}
	}
}
