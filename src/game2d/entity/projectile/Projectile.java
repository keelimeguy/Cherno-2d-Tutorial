package game2d.entity.projectile;

import game2d.entity.Entity;
import game2d.graphics.Sprite;

import java.util.Random;

public abstract class Projectile extends Entity {

	protected final int xOrigin, yOrigin;
	protected double dx, dy;
	protected double angle;
	protected Sprite sprite;
	protected double x, y;
	protected double speed, range, damage;

	protected final Random random = new Random();
	
	/**
	 * Create a new Projectile at a given position with a given facing.
	 * @param x : The X-position where the projectile originated.
	 * @param y : The Y-position where the projectile originated.
	 * @param dir : The direction the projectile will head.
	 */
	public Projectile(int x, int y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getSpriteSize() {
		return sprite.SIZE;
	}

	protected void move() {
	}

}
