package Map;

import Main.Panel;
import State.Loader;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Map {

	// position
	private int x;
	private int y;

	private double smooth;

	// bounds
	private int xmin;
	private int ymin;
	private int xmax;
	private int ymax;

	// map
	private int[][] map;
	private int tile_size;
	private int num_rows;
	private int num_columns;
	private int width;
	private int height;

	// tileset
	private BufferedImage tileset;
	private int num_tilesPerLine;
	private Field[][] tiles;

	// drawing
	private int row_start;
	private int col_start;
	private int num_rows_draw;
	private int num_cols_draw;

	// special touch
	private BufferedImage credits;
	private Image MapImage;

	// constructor ;
	public Map(int tile_size) {
		this.tile_size = tile_size;
		num_rows_draw = Panel.heightPlusBar / tile_size + 4; // only draw the part of the map that fits into
		num_cols_draw = Panel.width / tile_size + 4; // the panel

		MapImage = Loader.bg;
	}

	public void load_tiles(String s) {
		try {
			tileset = ImageIO.read((getClass().getResourceAsStream(s))); // read the tile set
			num_tilesPerLine = tileset.getWidth() / tile_size; // the number of tiles per line
			tiles = new Field[3][num_tilesPerLine]; // matrix of different tiles
			BufferedImage img;
			for (int col = 0; col < num_tilesPerLine; col++) {
				img = tileset.getSubimage(col * tile_size, 0, tile_size, tile_size);
				tiles[0][col] = new Field(img, Field.normal);
				img = tileset.getSubimage(col * tile_size, tile_size, tile_size, tile_size);
				tiles[1][col] = new Field(img, Field.normal);
				img = tileset.getSubimage(col * tile_size, tile_size, tile_size, tile_size);
				tiles[2][col] = new Field(img, Field.normal);

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// loading the map
	public void load_map(String s) {
		try {
			InputStream input = getClass().getResourceAsStream(s);
			BufferedReader br = new BufferedReader(new InputStreamReader(input));

			num_columns = Integer.parseInt(br.readLine()); // read first line which contains the number of columns
			num_rows = Integer.parseInt((br.readLine())); // read the second line which contains the number of rows
			map = new int[num_rows][num_columns];
			width = num_columns * tile_size;
			height = num_rows * tile_size;
			xmin = Panel.width - width;
			xmax = 0; // bounds
			ymin = Panel.height - height - 128;
			ymax = 0;

			for (int row = 0; row < num_rows; row++) {
				String l = br.readLine();
				String[] parts = l.split("\\s+"); // split line by whitespaces
				for (int col = 0; col < num_columns; col++) {
					map[row][col] = Integer.parseInt(parts[col]);

				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// getters
	public int getTile_size() {
		return tile_size;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getNum_rows() {
		return num_rows;
	}

	public int getNum_columns() {
		return num_columns;
	}

	public int getmap_index(int r, int c) {
		return map[r][c];
	}

	public int getType(int row, int column) {
		int pos_rc = map[row][column];
		int r = pos_rc / num_tilesPerLine;
		int c = pos_rc % num_tilesPerLine;

		return tiles[r][c].getType();
	}

	// setters
	public void set_tile(int r, int c, int i) {
		map[r][c] = i;
	}

	// makes the camera movement smoother
	public void set_smooth(double s) {
		smooth = s;
	}

	public void setPosition(double x, double y) {
		this.x += (x - this.x) * smooth;
		this.y += (y - this.y) * smooth;
		fixBounds();
		row_start = -this.y / tile_size;
		col_start = -this.x / tile_size;
	}

	// make sure that the bounds aren't passed
	private void fixBounds() {
		if (x < xmin)
			x = xmin;
		if (x < ymin)
			y = ymin;
		if (x > xmax)
			x = xmax;
		if (y > ymax)
			y = ymax;
	}

	public void render(Graphics2D g) {
		g.drawImage(credits, 0, 195, 220, 114, null);

		g.drawImage(MapImage, x, y, null);

	}

}
