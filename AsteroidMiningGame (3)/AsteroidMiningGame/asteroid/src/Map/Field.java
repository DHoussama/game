
// this class represents 2 types of tile : blocked or normal

package Map;

import java.awt.image.BufferedImage;

public class Field {

	private BufferedImage tile;
	private int type;
	// types
	public static final int normal = 0;
	public static final int blocked = 1;

	// constructor
	public Field(BufferedImage tile, int type) {
		this.tile = tile;
		this.type = type;
	}

	// getters
	public BufferedImage getTile() {
		return tile;
	}

	public int getType() {
		return type;
	}

}
