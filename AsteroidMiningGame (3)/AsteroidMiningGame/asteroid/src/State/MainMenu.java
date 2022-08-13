package State;

import Main.Panel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

public class MainMenu extends State {

	private boolean help = false;
	private Image bg;
	private int currentChoice = 0;
	private String[] options = { "START", "HELP", "QUIT" };

	private Color titleColor;
	private Font titleFont;

	private Font font;
	private Color menu_color;
	private Color choose_color;

	public MainMenu(Manager sm) {
		super(sm);
		init();
	}

	@Override
	public void init() {
		// draw background
		bg = new ImageIcon(getClass().getResource("/MenuBg.gif")).getImage();
		titleColor = new Color(149, 98, 0); // set color
		titleFont = new Font("Helvetica", Font.BOLD, 32); // set font
		font = new Font("Helvetica", Font.BOLD, 26);
		menu_color = new Color(0xFF1C4792, true);
		choose_color = new Color(0xFFACC5F3, true);

	}

	@Override
	public void render(Graphics2D g) {
		// draw background
		g.drawImage(bg, 0, 0, Panel.width, Panel.heightPlusBar, null);
		// draw title
		g.setColor(titleColor);
		g.setFont(titleFont);
		g.drawString("ASTEROID MINING", 120, 60);
		// draw options
		g.setFont(font);
		if (help) {
			g.setColor(choose_color);
			g.drawString("-Use Arrow keys to move", 20, 100);
			g.drawString("-Use 'I' to check asteroid state", 20, 150);
			g.drawString("-Use 'H' to Hide", 20, 200);
			g.drawString("-Use 'L' to Drill", 20, 250);
			g.drawString("-Use 'Q' to Mine", 20, 300);
		}

		else {
			for (int i = 0; i < options.length; i++) {
				if (i == currentChoice) {
					g.setColor(Color.white);
				} else {
					g.setColor(menu_color);
				}
				g.drawString(options[i], 215, 190 + i * 25);
			}
		}
	}

	@Override
	public void update() {
	}

	private void select() // select an option
	{
		if (currentChoice == 0) {
			sm.setCurrentState(Manager.PlayState);
		}
		if (currentChoice == 1 && !help) {
			help = true;
		}
		if (currentChoice == 2) {
			System.exit(0);
		}
	}

	@Override
	public void keyPressed(int e) {
		if (e == KeyEvent.VK_ENTER && !help) {
			select();
		}

		if (e == KeyEvent.VK_UP && !help) {
			currentChoice--;
			if (currentChoice == -1) {
				currentChoice = options.length - 1;
			}
		}

		if (e == KeyEvent.VK_DOWN && !help) {
			currentChoice++;
			if (currentChoice == options.length) {
				currentChoice = 0;
			}
		}
		if (e == KeyEvent.VK_ESCAPE) {
			help = false;
		}

	}

	@Override
	public void keyReleased(int e) {

	}
}
