package Object;

import State.Loader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Scanner;

import Map.Map;

import java.util.ArrayList;
import java.util.Random;

//asteroid object
public class Asteroid extends Object {
	private Resource CoreResource;
	private int Depth;
	private boolean IsAphelion;
	private boolean isRadioactive;
	private boolean isHollow;
	private BufferedImage image;
	private int ResourceAmount;
	private Settler Settlers;
	private ArrayList<Asteroid> Neighbors;
	private ArrayList<Resource> Resources;
	private String ID;
	static  int id = 0;



	public Resource getCoreResource() {
		return CoreResource;
	}


	public Asteroid(Map map) {
		super(map);
		width = 96;
		height = 96;
		col_height = 24;
		col_width = 24;
		image = Loader.asteroid[0][0];
	}
	public void setAsteroid (){
		Depth = 0;
		ResourceAmount = 0;
		IsAphelion = false;
		id = id + 1;
		ID = Integer.toString(id);
		CoreResource = new Iron();
		Resources = new ArrayList<Resource>();
		Neighbors = new ArrayList<Asteroid>();
		System.out.println("Asteroid "+id+ " has been created.");
	}
	public void setAsteroid(int depth, int resourceamount, boolean isaphelion) {
		Depth = depth;
		ResourceAmount = resourceamount;
		IsAphelion = isaphelion;
		id = id +1;
		ID = Integer.toString(id);
		Random rand = new Random();
		switch (rand.nextInt(4)) {
		case 0:
			this.CoreResource = new Iron();
			break;
		case 1:
			this.CoreResource = new Carbon();
			break;
		case 2:
			this.CoreResource = new Uranium();
			break;
		case 3:
			this.CoreResource = new WaterIce();
			break;
		}

		Resources = new ArrayList<Resource>();
		Neighbors = new ArrayList<Asteroid>();
		System.out.println("Asteroid "+this.ID+ " has been created.");
		}





	public void setRadioactive(boolean radioactive) {
		isRadioactive = radioactive;
	}

	public boolean isRadioactive() {
		return isRadioactive;
	}

//getters and setters
	public void setAphelion(boolean Aphelion) {
		this.IsAphelion = Aphelion;
	}

	public boolean getIsAphelion() {
		return IsAphelion;
	}


	public int getDepth() {
		return Depth;
	}

	public void setDepth(int depth) {
		this.Depth = depth;
	}

	public Resource getResource() {
		return CoreResource;
	}

	public void setResource(Resource resource) {
		this.CoreResource = resource;
	}

	public void removeResource() {
		this.CoreResource = new Hollow();
	}

	public void render(Graphics2D g) {
		set_map_position();
		g.drawImage(image, x + x_map - width / 2, y + y_map - height / 2, null);
	}
}
