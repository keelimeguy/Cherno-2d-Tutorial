package game2d.entity.mob;

import game2d.entity.Entity;
import game2d.graphics.Screen;
import game2d.graphics.Sprite;

public abstract class Mob extends Entity {

	protected Sprite sprite;
	protected int dir = 0;
	protected boolean moving = false;

	/**
	 * Moves the player and updates its direction
	 * @param dx : The change in x of the mob's location
	 * @param dy : The change in y of the mob's location
	 */
	public void move(int dx, int dy) {

		// Update the direction the mob is moving in
		if (dy < 0) dir = 0;
		if (dx > 0) dir = 1;
		if (dy > 0) dir = 2;
		if (dx < 0) dir = 3;

		// If the mob won't collide, move
		if (!collision(0, dy)) y += dy;
		if (!collision(dx, 0)) x += dx;

	}

	public void update() {
	}

	protected void shoot(int x, int y, double dir) {
	}

	public void render(Screen screen) {
	}

	/**
	 * Determines if the mob will collide should it move in the given direction
	 * @param dx : The change in x of the mob's location
	 * @param dy : The change in y of the mob's location
	 * @return 
	 * True if it will collide, false otherwise
	 */
	protected boolean collision(int dx, int dy) {
		return level.getTile((x + dx) >> 4, (y + dy) >> 4).solid();
	}
}
