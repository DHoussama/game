package State;

import Main.Panel;
import Map.Map;
import Object.Settler;
import Object.Asteroid;
import Object.Carbon;
import Object.Iron;
import Object.TeleportationGate;
import Object.Uranium;
import Object.WaterIce;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;

import GUI.GUI;

public class Play extends State {
	// map
	private Map map;
	// player
	private Settler settler;
	// asteroids
	private ArrayList<Asteroid> Asteroids;
	public static ArrayList<TeleportationGate> gates;
	// user interface
	private GUI ui;

	public static boolean repeat = true;

	public Play(Manager sm) {
		super(sm);
		init();
	}

	@Override
	public void init() {
		// load the map
		map = new Map(32);
		map.load_tiles("/map.png");
		map.load_map("/map.txt");
		map.setPosition(0, 0);
		map.set_smooth(0.1);
		// asteroids
		Asteroids = new ArrayList<Asteroid>();
		add_asteroids();
		// player
		settler = new Settler(map);
		settler.set_position(2, 3);
		// Base
		// add_items();
		// user interface
		ui = new GUI(settler, Asteroids);

	}

	private void add_asteroids() {
		Asteroid g;
		g = new Asteroid(map);
		g.set_position(5, 9);
		g.setResource(new Carbon());
		g.setDepth(7);
		g.setRadioactive(false);
		g.setAphelion(true);
		Asteroids.add(g);

		g = new Asteroid(map);
		g.set_position(2, 16);
		g.setDepth(5);
		g.setResource(new Uranium());
		g.setRadioactive(true);
		Asteroids.add(g);

		g = new Asteroid(map);
		g.set_position(5, 30);
		g.setDepth(6);
		g.setResource(new WaterIce());
		g.setRadioactive(false);

		Asteroids.add(g);

		g = new Asteroid(map);
		g.set_position(8, 22);
		g.setDepth(7);
		g.setResource(new Iron());
		g.setRadioactive(false);
		Asteroids.add(g);

		g = new Asteroid(map);
		g.set_position(12, 7);
		g.setDepth(8);
		g.setResource(new Carbon());
		g.setRadioactive(false);

		Asteroids.add(g);

		g = new Asteroid(map);
		g.set_position(13, 38);
		g.setDepth(10);
		g.setResource(new Uranium());
		g.setAphelion(true);
		Asteroids.add(g);

		g = new Asteroid(map);
		g.set_position(18, 31);
		g.setDepth(3);
		g.setResource(new WaterIce());
		Asteroids.add(g);

		g = new Asteroid(map);
		g.set_position(22, 29);
		g.setDepth(5);
		g.setResource(new Iron());
		Asteroids.add(g);

		g = new Asteroid(map);
		g.set_position(30, 7);
		g.setDepth(11);
		g.setResource(new Carbon());
		g.setRadioactive(true);
		g.setAphelion(true);
		Asteroids.add(g);

		g = new Asteroid(map);
		g.set_position(38, 1);
		g.setDepth(9);
		g.setResource(new Uranium());
		Asteroids.add(g);

		g = new Asteroid(map);
		g.set_position(34, 21);
		g.setDepth(7);
		g.setResource(new WaterIce());
		Asteroids.add(g);

		g = new Asteroid(map);
		g.set_position(37, 36);
		g.setDepth(6);
		g.setResource(new Iron());
		Asteroids.add(g);

	}

	@Override
	public void render(Graphics2D g) {
		// draw map
		map.render(g);

		// draw items
		/*
		 * for (SettlerBase t : SettlerBases) { t.render(g); }
		 */
		// draw asteroids
		for (Asteroid b : Asteroids) {
			b.render(g);
		}

		// draw gates
		if (gates != null) {
			for (TeleportationGate gate : gates) {
				gate.render(g);
			}
		}
		// draw player
		settler.render(g);
		// draw UI
		ui.render(g);

	}

	@Override
	public void update() {
		for (int i = 0; i < Asteroids.size(); i++) {
			Asteroid a = Asteroids.get(i);
			if (a.getDepth() == 0 && a.getIsAphelion() == false && a.isRadioactive()) {
				Asteroids.remove(i); // asteroid exploded and removed from map
				if (settler.collide_with(a)) {
					if (settler.getLives() != 0) { // check if settler run out of lives
						settler.set_position(2, 3); // reposition the settler in the base
						settler.setLives(settler.getLives() - 1);
					} // decrease its lives
					else {
						init();

						sm.setCurrentState(Manager.GameOverLosingState);
					}
				}
			}

		}
		{
			if (settler.isHidden() == false && GUI.isStorm) {
				if (settler.getLives() != 0) { // check if settler run out of lives
					settler.set_position(2, 3);
					if (repeat)// reposition the settler in the base
						settler.setLives(settler.getLives() - 1);
					repeat = false;
				} // decrease its lives
				else {
					init();
					sm.setCurrentState(Manager.GameOverLosingState);
				}
			}
		}

		if (check_win()) {
			init();
			sm.setCurrentState(Manager.WinGame);
		}
		// update player
		settler.update();
		// update map
		map.setPosition(Panel.width / 2 - settler.getX(), Panel.height / 2 - settler.getY());

	}

	public boolean check_win() {
		for (int i = 0; i < 4; i++) {
			if (settler.getResourceNumber(i) != 3)
				return false;

		}
		return true;
	}

	@Override
	public void keyPressed(int e) {
		if (e == KeyEvent.VK_LEFT && !settler.isHidden())
			settler.set_left(true);
		if (e == KeyEvent.VK_RIGHT && !settler.isHidden())
			settler.set_right(true);
		if (e == KeyEvent.VK_DOWN && !settler.isHidden())
			settler.set_down(true);
		if (e == KeyEvent.VK_UP && !settler.isHidden())
			settler.set_up(true);
		if (e == KeyEvent.VK_M)
			settler.Mine(Asteroids); // press M to mine
		if (e == KeyEvent.VK_Q)
			settler.Drill(Asteroids);// press D to drill
		if (e == KeyEvent.VK_H)
			settler.Hide(Asteroids);// press H to hide
		if (e == KeyEvent.VK_I)
			settler.check(Asteroids);// press I to check asteroid state
		if (e == KeyEvent.VK_B)
			settler.build_Gate();// press B to build gate
		if (e == KeyEvent.VK_D)
			settler.deploygate(Asteroids);// press D to deploy the gate
		if (e == KeyEvent.VK_ESCAPE)
			sm.setCurrentState(Manager.MenuState);

	}

	@Override
	public void keyReleased(int e) {
		if (e == KeyEvent.VK_LEFT)
			settler.set_left(false);
		if (e == KeyEvent.VK_RIGHT)
			settler.set_right(false);
		if (e == KeyEvent.VK_DOWN)
			settler.set_down(false);
		if (e == KeyEvent.VK_UP)
			settler.set_up(false);

	}
}
