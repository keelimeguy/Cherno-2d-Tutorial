package game2d.level.tile;

import game2d.graphics.Screen;
import game2d.graphics.Sprite;

/**
 * My class for tiles with an animated sprite, not yet implemented
 */
public class AnimatedTile extends Tile {
	
	public AnimatedTile(Sprite sprite) {
		super(sprite);
	}
	
	public void render(int x, int y, Screen screen) {
		// Render in pixel precision not tile precision
		screen.renderTile(x << 4, y << 4, this);
	}
	
	public boolean solid() {
		return true;
	}
}
