package Object;

import State.Loader;

import java.awt.*;
import java.awt.image.BufferedImage;

import Map.Map;

public class TeleportationGate extends Object {
	private BufferedImage image;

	public TeleportationGate(Map map) {
		super(map);
		width = 20;
		height = 20;
		col_height = 24; // maybe 56
		col_width = 24;
		image = Loader.Gate[0][0];
	}

	public void render(Graphics2D g) {
		set_map_position();
		g.drawImage(image, x + x_map - width / 2, y + y_map - height / 2, null);
	}
	

	
	private boolean activated;
	private TeleportationGate pair;
	static private int count = 0;
	private String ID;
	private Asteroid CurrentAsteroid;

	public void setTeleportationGate() {
		count++;
		ID = String.valueOf(count);
		System.out.println("Gate successfully built.");
	}
	
	public void setActivated(boolean activated) {
		this.activated = activated;
	}

	boolean CheckGatePair() {
		return true;
	}

	public void setPair(TeleportationGate pair) {
		this.pair = pair;
	}

	public TeleportationGate Getpair() {
		return pair;
	}

	public void destroy() {
		System.out.println("gate is destroyed");
	}
	public void setAsteroid(Asteroid as){
		this.CurrentAsteroid = as;
	}
	

}
