package State;

import java.awt.Graphics2D;
import java.util.ArrayList;

// this class manages all the game states(menu,play,gameover)
// it adds and switches between different states
public class Manager {

	private ArrayList<State> gameStates;
	private int currentState;
	public static final int MenuState = 0;
	public static final int PlayState = 1; // game states
	public static final int GameOverLosingState = 2;
	public static final int WinGame = 3;

	public Manager() {
		gameStates = new ArrayList<State>(); // arraylist of game states
		currentState = MenuState;

		gameStates.add(new MainMenu(this)); // add menu state
		gameStates.add(new Play(this)); // add play state
		gameStates.add(new GameOver(this)); // add gameover in case of losing
		gameStates.add(new GameWon(this)); // add gameover state in case of winnig

	}

	public void setCurrentState(int state) {
		currentState = state;
	}

	public void update() {
		gameStates.get(currentState).update();
	}

	public void render(Graphics2D g) {
		gameStates.get(currentState).render(g);
	}

	public void keyPressed(int e) {
		gameStates.get(currentState).keyPressed(e);
	}

	public void keyReleased(int e) {
		gameStates.get(currentState).keyReleased(e);
	}

}
