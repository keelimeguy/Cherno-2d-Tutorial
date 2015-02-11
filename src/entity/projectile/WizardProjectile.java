package game2d.entity.projectile;

import game2d.graphics.Screen;
import game2d.graphics.Sprite;

public class WizardProjectile extends Projectile {

	public static final int FIRE_RATE = 8; //higher number = slower rate
	
	/**
	 * Creates a new WizardProjectile
	 * @param x : The X-position of the projectile.
	 * @param y : The Y-position of the projectile.
	 * @param dir : The direction the projectile is facing.
	 */
	public WizardProjectile(int x, int y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		sprite = Sprite.wizardProjectile;
		dx = speed * Math.cos(angle);
		dy = speed * Math.sin(angle);
	}

	/**
	 * Updates the projectile.
	 */
	public void update() {
		move();
	}

	/**
	 * Moves the projectile along its trajectory
	 */
	protected void move() {
		x += dx;
		y += dy;
		if (distance() > range) remove();
	}

	/**
	 * Determines the distance the projectile has traveled.
	 * @return The distance traveled as a Double value.
	 */
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	/**
	 * Renders the projectile.
	 * @param screen : The Screen to which the projectile will be rendered.
	 */
	public void render(Screen screen) {
		screen.renderProjectile((int) x - 12, (int) y - 4, this);
	}
}
