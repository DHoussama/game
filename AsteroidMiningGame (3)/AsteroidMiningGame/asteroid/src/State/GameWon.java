package State;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class GameWon extends State {

	private Image image;
	private Color color;
	private Color color2;
	private Font font;
	private Font font2;

	public GameWon(Manager sm) {
		super(sm);
		init();
	}

	@Override
	public void init() {
		image = new ImageIcon(getClass().getResource("/gameoverwin.gif")).getImage(); // load background gif
		color = new Color(130, 0, 0, 255);
		color2 = new Color(255, 255, 255, 255);
		font = new Font("Helvetica", Font.BOLD, 18);
		font2 = new Font("Helvetica", Font.BOLD, 10);
	}

	@Override
	public void render(Graphics2D g) {
		// draw
		g.drawImage(image, 0, 0, 500, 360, null); // draw background
		// draw ending text
		g.drawString("press escape to skip", 65, 270);
	}

	@Override
	public void update() {

	}

	@Override
	public void keyPressed(int e) {
		if (e == KeyEvent.VK_ESCAPE) {
			sm.setCurrentState(Manager.MenuState);
		}
	}

	@Override
	public void keyReleased(int e) {

	}

}
