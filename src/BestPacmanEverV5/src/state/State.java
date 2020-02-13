package state;

import java.io.IOException;

import pacman.GameManager;

public interface State {
	public void doAction(GameManager gm) throws IOException;
}
