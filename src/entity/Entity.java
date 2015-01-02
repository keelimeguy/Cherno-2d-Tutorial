package game2d.entity;

import game2d.graphics.Screen;
import game2d.level.Level;

import java.util.Random;

public abstract class Entity {

	public int x, y;
	private boolean removed = false;
	protected Level level;
	protected final Random random = new Random();

	public void update() {
	}

	public void render(Screen screen) {
	}

	/**
	 * Removes the entity from the level
	 */
	public void remove() {
		removed = true;
	}

	/**
	 * Determines if the entity is removed
	 * @return
	 * True if the entity is removed, false otherwise
	 */
	public boolean isRemoved() {
		return removed;
	}

	/**
	 * Initiate the current level for the entity
	 * @param level : The level where the entity is present
	 * 
	 */
	public void init(Level level) {
		this.level = level;
	}
}
