package game2d.level.tile;

import game2d.graphics.Screen;
import game2d.graphics.Sprite;

public class BasicTile extends Tile {

	private boolean isSolid;

	public BasicTile(Sprite sprite, boolean isSolid) {
		super(sprite);
		this.isSolid = isSolid;
	}

	public void render(int x, int y, Screen screen) {
		// Render in pixel precision not tile precision
		screen.renderTile(x << 4, y << 4, this);
	}

	public boolean solid() {
		return isSolid;
	}
}
