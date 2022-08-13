package State;

import java.awt.*;

// super abstract class for games states
public abstract class State {

	protected Manager sm;

	public State(Manager sm) {
		this.sm = sm;
	}

	public abstract void init();

	public abstract void render(Graphics2D g);

	public abstract void update();

	public abstract void keyPressed(int e);

	public abstract void keyReleased(int e);

}
