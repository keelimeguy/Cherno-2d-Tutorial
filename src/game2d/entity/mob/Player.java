package game2d.entity.mob;

import game2d.Game;
import game2d.entity.projectile.Projectile;
import game2d.entity.projectile.WizardProjectile;
import game2d.graphics.Screen;
import game2d.graphics.Sprite;
import game2d.input.Keyboard;
import game2d.input.Mouse;

public class Player extends Mob {

	private Keyboard input;
	private Sprite sprite;
	private int anim = 0;
	private boolean walking = false;

	private int fireRate = 0;
	Projectile p;

	/**
	 * Creates a player at a default location
	 * @param input : The Keyboard controller for this player
	 */
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player00;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	/**
	 * Creates a player at a specific (x,y) location
	 * @param x : The starting x coordinate (in pixel units)
	 * @param y : The starting y coordinate (in pixel units)
	 * @param input : The Keyboard controller for this player
	 */
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player00;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	/**
	 * Updates the player animation and moves the player when necessary
	 */
	public void update() {

		if (fireRate > 0) fireRate--;

		// Increase the animation step, but don't let it increase indefinitely
		if (anim < 7500)
			anim++;
		else
			anim = 0;

		// Update the change in position when a movement key is pressed
		int dx = 0, dy = 0;
		if (input.up) dy--;
		if (input.down) dy++;
		if (input.left) dx--;
		if (input.right) dx++;

		// Move the player if its position will change, set walking flag accordingly
		if (dx != 0 || dy != 0) {
			move(dx, dy);
			walking = true;
		} else {
			walking = false;
		}
		clear();
		updateShooting();
	}

	/**
	 * Clears projectiles which have been removed from the level.
	 */
	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	/**
	 * Creates a new projectile when the player shoots.
	 * @param x : The X-Position of the projectile.
	 * @param y : The Y-Position of the projectile.
	 * @param dir : The direction where the projectile faces.
	 */
	protected void shoot(int x, int y, double dir) {
		Projectile p = new WizardProjectile(x, y, dir);
		level.addProjectile(p);
	}

	/**
	 * Determines if the player is shooting and fires a projectile in the appropriate direction.
	 */
	private void updateShooting() {

		// Shoot when mouse clicks
		if (Mouse.getB() == 1 && fireRate <= 0) {
			double dx = Mouse.getX() - Game.getWindowWidth() / 2;
			double dy = Mouse.getY() - Game.getWindowHeight() / 2;
			double dir = Math.atan2(dy, dx);
			shoot(x, y, dir);
			fireRate = WizardProjectile.FIRE_RATE;
		}
	}

	/**
	 *  Renders the player according to its direction and animation step
	 */
	public void render(Screen screen) {

		// Flip variable (0=none, 1=horizontal, 2=vertical, 3=both)
		int flip = 0;

		// Change the sprite according to the player direction:

		// up
		if (dir == 0) {
			sprite = Sprite.player00;
			if (walking) {
				if (anim % 20 >= 10)
					sprite = Sprite.player01;
				else
					sprite = Sprite.player02;
			}
		}
		// right
		if (dir == 1) {
			sprite = Sprite.player10;
			if (walking) {
				if (anim % 20 >= 10)
					sprite = Sprite.player11;
				else
					sprite = Sprite.player12;
			}
		}
		// down
		if (dir == 2) {
			sprite = Sprite.player20;
			if (walking) {
				if (anim % 20 >= 10)
					sprite = Sprite.player21;
				else
					sprite = Sprite.player22;
			}
		}
		// left
		if (dir == 3) {
			sprite = Sprite.player10;
			if (walking) {
				if (anim % 20 >= 10)
					sprite = Sprite.player11;
				else
					sprite = Sprite.player12;
			}
			flip = 1;
		}

		// Offset the position to center the player
		int xx = x - sprite.SIZE / 2;
		int yy = y - sprite.SIZE / 2;

		// Render the player sprite
		screen.renderPlayer(xx, yy, sprite, flip);
	}

	protected boolean collision(int dx, int dy) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = ((x + dx) + c % 2 * 13 - 7) >> 4;
			int yt = ((y + dy) + c / 2 * 12 + 3) >> 4;
			if (level.getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}
}
