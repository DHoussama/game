package State;

import java.awt.*;
import java.awt.event.KeyEvent;
import javax.swing.*;

public class GameOver extends State {

	private Image image;
	private Color color;
	private Color color2;
	private Font font;
	private Font font2;

	public GameOver(Manager sm) {
		super(sm);
		init();
	}

	@Override
	public void init() {
		image = new ImageIcon(getClass().getResource("/gameover.gif")).getImage(); // load background gif
		color = new Color(130, 0, 0, 255);
		color2 = new Color(255, 255, 255, 255);
		font = new Font("Helvetica", Font.BOLD, 18);
		font2 = new Font("Helvetica", Font.BOLD, 10);
	}

	@Override
	public void render(Graphics2D g) {
		// draw
		g.drawImage(image, 0, 0, 500, 340, null); // draw background
		// draw ending text
		g.drawString("press escape to skip", 50, 290);
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
