package GUI;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import javax.swing.ImageIcon;

import Main.Panel;
import Object.Settler;
import State.Play;
import State.Manager;
import State.Loader;
import Object.Asteroid;

public class GUI {

	private BufferedImage asteroid;
	private BufferedImage ui;
	private BufferedImage Gate;
	private BufferedImage ui2;
	private Settler settler;
	private String time;
	private String depth;
	private String state1;
	private String state2;
	private String radioactive;
	private String resource;
	private String lives;
	private String sinventory;
	private Image storm;
	public static boolean isStorm = false;

	private String[] terma = { "Carbon:", "Iron:", "WaterIce:", "Uranium:", "Gates: " };
	private Manager sm;

	private Font font;
	private Color color;

	public GUI(Settler p, ArrayList<Asteroid> b) {

		settler = p;
		asteroid = Loader.asteroid[0][0];
		ui = Loader.ui[0][0];
		ui2 = Loader.ui2[0][0];
		storm = new ImageIcon(getClass().getResource("/SunStorm.gif")).getImage();

		font = new Font("Century Gothic", Font.BOLD, 10);
		color = new Color(0xFC7A76E8, true);
		// depth = asteroid.g
	}

	public void render(Graphics2D g) {

		// draw ui box
		g.drawImage(ui, Panel.width - 200, Panel.height - 50, null);
		g.drawImage(ui2, Panel.width - 80, Panel.height - 300, null);
		g.setColor(color);
		g.setFont(font);
		lives = String.valueOf(settler.getLives());
		g.drawString("Lives : " + lives, Panel.width - 65, Panel.height - 280);
		g.drawString("resources : ", Panel.width - 65, Panel.height - 260);

		for (int i = 0; i < 5; i++) {
			g.drawString(terma[i], Panel.width - 65, Panel.height - 240 + 10 * i);
		}
		try {
			for (int i = 0; i < 5; i++) {
				sinventory = String.valueOf(settler.getResourceNumber(i));
				g.drawString(sinventory, Panel.width - 15, Panel.height - 240 + 10 * i);

			}
		} catch (Exception e) {
		}

		if (settler.isCollided()) {
			Asteroid a = settler.getAsteroid();
			depth = String.valueOf(a.getDepth());
			state1 = "Perehilion";
			state2 = "Aphelion";
			radioactive = "radioactive";
			resource = a.getCoreResource().getClass().getSimpleName();;
			// draw the statistics of the asteroid on the screen
			if (a.getIsAphelion() == false)
				g.drawString("state : " + state1, Panel.width - 190, Panel.height - 6);
			else
				g.drawString("state : " + state2, Panel.width - 190, Panel.height - 6);
			g.drawString("depth : " + depth + "| ", Panel.width - 190, Panel.height - 25);
			if (a.isRadioactive())
				g.drawString("state : " + radioactive, Panel.width - 140, Panel.height - 25);
			g.drawString("resource : " + resource, Panel.width - 190, Panel.height + 10);
		}
		// draw timer
		int min = (int) (settler.get_time() / 1800); // 30 frames per second so in 1 min the timer increments 1800 times
		int sec = (int) ((settler.get_time() / 30) % 60);
		if (sec % 35 == 0 && sec != 0)
			g.drawString("A sun storm is coming in 10 seconds !!!", Panel.width - 500, Panel.height - 20);
		if (sec % 45 == 0)
			isStorm = true;
		if (sec % 46 == 0) {
			isStorm = false;
			Play.repeat = true;
		}
		if (isStorm)
			g.drawImage(storm, Panel.width - 500, Panel.height - 320, null);

		if (min < 10) { // timer position on the map
			if (sec < 10) {
				time = "0" + min + ":0" + sec;
				g.drawString(time, 250, 22);
			} else {
				time = "0" + min + ":" + sec;
				g.drawString(time, 250, 22);
			}
		} else {
			if (sec < 10) {
				time = min + ":0" + sec;
				g.drawString(time, 250, 22);
			} else {
				time = min + ":" + sec;
				g.drawString(time, 250, 22);
			}
		}

	}

	public String get_time() {
		return time;
	}

}
