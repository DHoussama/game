package State;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;

// load all the content used in the game
public class Loader {

	public static BufferedImage[][] player = load("/SettlerSprite.gif", 48, 48); // settler tileset
	public static BufferedImage[][] Hiddenplayer = load("/hiddenSettler.gif", 48, 48);
	public static BufferedImage[][] asteroid = load("/asteroid.gif", 96, 96); //
	public static BufferedImage[][] ui = load("/hud .png", 200, 138);
	public static BufferedImage[][] ui2 = load("/playerHUD.png", 96, 119);
	public static BufferedImage[][] Gate = load("/Gate.png", 20, 20);
	public static Image bg = new ImageIcon(Loader.class.getResource("/bgg.gif")).getImage();

	// loads and image and store into matrix of images(useful for loading the player
	// tileset mainly)
	public static BufferedImage[][] load(String s, int w, int h) {
		BufferedImage[][] res;
		try {
			BufferedImage img = ImageIO.read(Loader.class.getResourceAsStream(s)); // read the image
			int width = img.getWidth() / w; // the width of a single tile
			int height = img.getHeight() / h; // the height of a single tile
			res = new BufferedImage[height][width]; // create new matrix of images to store the tiles
			for (int i = 0; i < height; i++) {
				for (int j = 0; j < width; j++) {
					res[i][j] = img.getSubimage(j * w, i * h, w, h); // store the tiles intro the matrix
				}
			}
			return res;
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("loading failed");
		}
		return null;
	}

}