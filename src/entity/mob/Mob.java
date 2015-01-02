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
	 * @param dx : The change in x of the player's location
	 * @param dy : The change in y of the player's location
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

	public void render(Screen screen) {
	}

	/**
	 * Determines if the mob will collide should it move in the given direction
	 * @return 
	 * True if it will collide, false otherwise
	 */
	private boolean collision(int dx, int dy) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + dx) + c % 2 * 13 - 7) >> 4;
			int yt = ((y + dy) + c / 2 * 12 + 3) >> 4;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
		//return level.getTile((x + dx) >> 4, (y + dy) >> 4).solid();
	}
}
