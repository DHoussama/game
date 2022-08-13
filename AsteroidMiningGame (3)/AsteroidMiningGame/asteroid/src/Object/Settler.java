package Object;

import State.Play;
import State.Loader;

import static State.Play.gates;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

import Map.Map;

public class Settler extends Object {

	// tile frames
	private BufferedImage[] right_tile;
	private BufferedImage[] left_tile;
	private BufferedImage[] up_tile;
	private BufferedImage[] down_tile;
	private BufferedImage[] up_tileHidden;

	// for animating the player tiles
	private final int down = 0;
	private final int left = 1;
	private final int right = 2;
	private final int up = 3;

	private long timer; // game timer
	private boolean hidden;
	private int lives = 3;
	private boolean collided = false;
	private Asteroid asteroid;
	ArrayList<Resource> Resources;
	ArrayList<TeleportationGate> Gates;
	int[] resourceNumber = { 0, 0, 0, 0, 0 };
	int num_gates = 0;

	// methods

	// constructor
	public Settler(Map map) {

		super(map);
		width = 32;
		height = 32;
		col_height = 24;
		col_width = 24;
		speed = 4;
		down_tile = Loader.player[0];
		left_tile = Loader.player[1];
		right_tile = Loader.player[2];
		up_tile = Loader.player[3];

		up_tileHidden = Loader.Hiddenplayer[0];

		anim.setImages(down_tile);
		anim.set_wait_time(7);
		asteroid = new Asteroid(map);
		hidden = false;

		gates = new ArrayList<TeleportationGate>();
		
		Resources = new ArrayList<>();
		Resources.add(new Hollow());
		Gates = new ArrayList<>();
	}

	public boolean isHidden() {
		return hidden;
	}

	public int getLives() {
		return lives;
	}

	public void setLives(int lives) {
		this.lives = lives;
	}

// mine method
	public void Mine(ArrayList<Asteroid> Asteroids) {
		for (int i = 0; i < Asteroids.size(); i++) {
			Asteroid a = Asteroids.get(i);
			if (this.collide_with(a)) {
				if (a.getDepth() == 0) {
					if (a.getResource() == null)
						System.out.println("can't");
					else {
						setResources(a.getResource());
						increment_resource_num(a);
						a.removeResource();
					}

				}

				else {
					System.out.println("can't");
				}
			}
		}

	}

	public Asteroid getAsteroid() {
		return asteroid;
	}

	public boolean isCollided() {
		return collided;
	}

	public void check(ArrayList<Asteroid> Asteroids) {
		for (int i = 0; i < Asteroids.size(); i++) {
			Asteroid a = Asteroids.get(i);
			if (this.collide_with(a)) {
				asteroid = a;
				collided = true;

			}
		}
	}

	// Drill method
	public void Drill(ArrayList<Asteroid> Asteroids) {

		for (int i = 0; i < Asteroids.size(); i++) {
			Asteroid a = Asteroids.get(i);
			if (this.collide_with(a) && a.getDepth() > 0) {
				a.setDepth(a.getDepth() - 1);
				asteroid = a;
				collided = true;

			}
		}
	}

// Hide method
	public void Hide(ArrayList<Asteroid> asteroids) {
		if (hidden == false) {
			System.out.println("can't 1");

			for (int i = 0; i < asteroids.size(); i++) {
				Asteroid a = asteroids.get(i);
				if (this.collide_with(a)) {
					if (a.getDepth() == 0) {
						hidden = true;
						System.out.println("can't 2");
						return;
					} else {
						System.out.println("can't ");
					}
				}
			}
		}
		if (hidden) {

			hidden = false;
		}
	}

	public void build_Gate() {

		if ((getResourceNumber(1) >= 2) && getResourceNumber(3) >= 1
				&& getResourceNumber(2) >= 1) {

			setResourceUnitsByIndex(1, -2);
			setResourceUnitsByIndex(2, -1);
			setResourceUnitsByIndex(3, -1);
			setResourceNumber(4, 1);
			setNum_gates(1);
			// return true;
		} else {
			System.out.println("You do not have enough resources to build the gates");
			// return false;
		}

		// return false;
	}

	public void deploygate(ArrayList<Asteroid> Asteroids) {
		for (int i = 0; i < Asteroids.size(); i++) {
			Asteroid a = Asteroids.get(i);
			if (this.collide_with(a) && getNum_gates() > 0) {
				TeleportationGate gate = new TeleportationGate(map);
				gate.set_position(a.getA(), a.getB());
				gates.add(gate);

				setResourceNumber(4, -1);
				setNum_gates(-1);
			}
		}

	}

	public void teleport(ArrayList<Asteroid> Asteroids, TeleportationGate g) {
		for (int i = 0; i < Asteroids.size(); i++) {
			Asteroid a = Asteroids.get(i);
			if (this.collide_with(a)) {
			}
		}
	}

	// setter for animation
	private void set_animation(int act, BufferedImage[] images, int wait_t) {
		curr_anim = act;
		anim.setImages(images);
		anim.set_wait_time(wait_t);
	}

	// timer
	public long get_time() {
		return timer;
	}

	// teleportaion gate getter

	public void update() {
		timer++;
		if (hidden) {
			set_animation(up, up_tileHidden, 6);
			return;
		}
		if (r) {
			if (curr_anim != right)
				set_animation(right, right_tile, 7);
		}
		if (l) {
			if (curr_anim != left)
				set_animation(left, left_tile, 7);
		}
		if (u) {
			if (curr_anim != up)
				set_animation(up, up_tile, 7);
		}
		if (d) {
			if (curr_anim != down)
				set_animation(down, down_tile, 7);
		}

		super.update();
	}

	public void increment_resource_num(Asteroid asteroid) {
		Uranium n = new Uranium();
		Carbon c = new Carbon();
		Iron i = new Iron();
		WaterIce w = new WaterIce();
		try {
			if (asteroid.getResource().getClass().equals(c.getClass()))
				setResourceNumber(0, 1);
			if (asteroid.getResource().getClass().equals(i.getClass()))
				setResourceNumber(1, 1);
			if (asteroid.getResource().getClass().equals(w.getClass()))
				setResourceNumber(2, 1);
			if (asteroid.getResource().getClass().equals(n.getClass()))
				setResourceNumber(3, 1);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void render(Graphics2D g) {
		set_map_position();
		super.render(g);
	}
	
	public void setResourceUnitsByIndex(int index, int units) {
		this.resourceNumber[index] += units;
	}

	public void setNum_gates(int i) {
		num_gates += i;

	}

	public int getNum_gates() {
		return num_gates;
	}

	public int getResourceNumber(int i) {
		return resourceNumber[i];
	}

	public void setResourceNumber(int i, int j) {
		this.resourceNumber[i] += j;
	}

	public void setResources(Resource mb) {
		Resources.add(mb);
		System.out.println("resource added to the inventory");
	}

	public Resource getResource(int index) {
		return Resources.get(index);
	}

	// check if the player has the needed resource to win the game

	public void setGates(TeleportationGate g) {
		Gates.add(g);
		System.out.println("gate added to the inventory");
	}

	public void RemoveResources(int index) {
		Resources.remove(index);
	}

}
