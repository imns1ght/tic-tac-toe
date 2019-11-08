package imns1ght;

import java.util.Vector;

public class GameProcess {
	/** Default constructor. */
	public GameProcess() {
		initialize();
	}

	/** Set-up things for the game. */
	protected void initialize() {
		this.board = new Board();
		this.log = new Vector<IntPair>(9);
		this.player_01 = new Player("Player 1", 1);
		this.player_02 = new Player("Player 2", 2);
	}

	/** Check if nobody won. */
	protected boolean is_draw() {
		if (this.board.is_full()) return true;

		return false;
	}

	/** Check position. If found, return position. */
	protected int check_pos(int x, int y) {
		if ((this.board.get(x, y) == this.board.get(x, y - 1))
				&& (this.board.get(x, y) == this.board.get(x, y + 1))) {
			return this.board.get(x, y);
		} else if ((this.board.get(x, y) == this.board.get(x - 1, y))
				&& (this.board.get(x, y) == this.board.get(x + 1, y))) {
			return this.board.get(x, y);
		} else if ((this.board.get(x, y) == this.board.get(x - 1, y - 1))
				&& (this.board.get(x, y) == this.board.get(x + 1, y + 1))) {
			return this.board.get(x, y);
		} else if ((this.board.get(x, y) == this.board.get(x + 1, y - 1))
				&& (this.board.get(x, y) == this.board.get(x - 1, y + 1))) {
			return this.board.get(x, y);
		}

		return 0;
	}

	protected Board board; // Where the game occurs.
	protected Player player_01; // Player one.
	protected Player player_02; // Player two.
	protected Vector<IntPair> log; // Play history.
}
