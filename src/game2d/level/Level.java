package game2d.level;

import game2d.entity.Entity;
import game2d.entity.projectile.Projectile;
import game2d.graphics.Screen;
import game2d.level.tile.Board;

import java.util.ArrayList;
import java.util.List;

public abstract class Level {

	protected int width, height;
	protected int[] tiles;

	private List<Entity> entities = new ArrayList<Entity>();
	private List<Projectile> projectiles = new ArrayList<Projectile>();

	public static Level spawn = new SpawnLevel("/levels/spawn.png");

	/**
	 * Creates a new level
	 * @param width : The width of the level (in tile units)
	 * @param height : The height of the level (in tile units)
	 */
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tiles = new int[width * height];
		generateLevel();
	}

	/**
	 * Creates a level from a resource path
	 * @param path : File path to the image of the level
	 */
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	protected void generateLevel() {
	}

	protected void loadLevel(String path) {
	}

	/**
	 * Gets the projectile list for the level and returns it.
	 * @return A List<Projectile> object, containing the projectiles that have been added into the level
	 */
	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	/**
	 * Updates the level by updating every entity within the level.
	 */
	public void update() {

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).update();
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).update();
		}
	}

	private void time() {
	}

	/**
	 * Renders all the tiles on the screen
	 * @param xScroll : The scroll offset of the screen in the x direction (in pixels)
	 * @param yScroll : The scroll offset of the screen in the y direction (in pixels)
	 * @param screen : The screen to render to
	 */
	public void render(int xScroll, int yScroll, Screen screen) {

		// Tells the screen how much it is to be offset
		screen.setOffset(xScroll, yScroll);

		// Determine the corner pins, i.e. the (x,y) values of the tiles in the corners of the viewable screen
		// (In tile units not pixels)
		int x0 = xScroll >> 4;
		int x1 = ((xScroll + screen.width) >> 4) + 1;
		int y0 = yScroll >> 4;
		int y1 = ((yScroll + screen.height) >> 4) + 1;

		// Renders all the tiles in the viewable screen at their (x,y) positions
		// (In tile units not pixels)
		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}

		for (int i = 0; i < entities.size(); i++) {
			entities.get(i).render(screen);
		}

		for (int i = 0; i < projectiles.size(); i++) {
			projectiles.get(i).render(screen);
		}
	}

	/**
	 * Adds an entity to the level.
	 * @param e : The entity to be added.
	 */
	public void add(Entity e) {
		entities.add(e);
	}

	/**
	 * Adds a projectile to the level, separate from other entities
	 * @param p : The projectile to be added.
	 */
	public void addProjectile(Projectile p) {
		projectiles.add(p);
	}

	/**
	 * Gets the tile that is at the given position
	 * @param x : The x coordinate of the tile (in tile units)
	 * @param y : The y coordinate of the tile (in tile units)
	 * @return The tile at the given location in the tiles array
	 */
	public Board getTile(int x, int y) {
		return null;
	}
}
