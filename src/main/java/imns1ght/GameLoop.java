package imns1ght;

import java.util.Scanner; // Import the Scanner class
import java.util.Vector;

public class GameLoop {

	// @
	class IntPair {
		IntPair() {

		}

		IntPair(int x, int y) {
			this.x = x;
			this.y = y;
		}

		private int getX() {
			return x;
		}

		private void setX(int x) {
			this.x = x;
		}

		private int getY() {
			return y;
		}

		private void setY(int y) {
			this.y = y;
		}

		private int x;
		private int y;
	}

	/** Default constructor. */
	public GameLoop() {
		UI userInterface = new UI();
		initialize();
	}

	/** Set-up things for the game. */
	private void initialize() {
		System.out.println("========== WELCOME TO TIC TAC TOE! ==========");
		this.board = new Board();
		this.log = new Vector<IntPair>(9);
		this.input = new Scanner(System.in); // Create a Scanner object

		System.out.println("\n[How to Play]\n");
		this.board.print_board();
		System.out.println("\n>>> Rules: the player who succeeds in placing three "
				+ "of their marks in a horizontal, vertical, or "
				+ "diagonal row wins the game.");
		System.out.println("\n[Players]\n");
		System.out.print(">>> Player 1 (X): ");
		String nickname = input.nextLine(); // Read user input
		this.player_01 = new Player(nickname, 1);
		System.out.print(">>> Player 2 (O): ");
		nickname = input.nextLine(); // Read user input
		this.player_02 = new Player(nickname, 2);

		System.out.println("\n============= Running =============\n");
		this.board.print_board();
	}

	/** Receive coordinates to place in the board. */
	private void receive_coords(IntPair coords) {
		do {
			System.out.print("Row: ");
			coords.setX(Integer.parseInt(input.nextLine()));
			System.out.print("Column: ");
			coords.setY(Integer.parseInt(input.nextLine()));

		} while (!is_valid(coords.getX(), coords.getY()));
		System.out.println();
	}

	/** Check if nobody won. */
	private boolean is_draw() {
		if (this.board.is_full()) {
			System.out.println("\nDraw between " + this.player_01.getNickname()
					+ " and " + this.player_02.getNickname() + "!");
			return true;
		}

		return false;
	}

	public boolean process_events() {
		IntPair coords = new IntPair(); // Save the coordinates.

		if (is_draw())
			return true;

		///////////////////////////////////////////
		// Player 01
		///////////////////////////////////////////

		System.out.println("\n[X] " + this.player_01.getNickname() + "\'s turn");
		receive_coords(coords);
		this.board.set(coords.getX(), coords.getY(), this.player_01.getPlay_symbol());
		this.log.add(coords);
		this.board.print_board();

		// Check for victory of player 01.
		for (int i = 0; i < this.log.size(); i++) {
			if (check_pos(this.log.get(i).getX(), this.log.get(i)
					.getY()) == this.player_01.getPlay_symbol()) {
				System.out.println("\n" + this.player_01.getNickname() + " WON!");
				return true;
			}
		}

		if (is_draw())
			return true;

		///////////////////////////////////////////
		// Player 02
		///////////////////////////////////////////

		System.out.println("\n[O] " + this.player_02.getNickname() + "\'s turn");
		coords = new IntPair();
		receive_coords(coords);
		this.board.set(coords.getX(), coords.getY(), this.player_02.getPlay_symbol());
		this.log.add(coords);
		this.board.print_board();

		// Check for victory of player 02.
		for (int i = 0; i < this.log.size(); i++) {
			if (check_pos(this.log.get(i).getX(), this.log.get(i)
					.getY()) == this.player_02.getPlay_symbol()) {
				System.out.println("\n" + this.player_02.getNickname() + " WON!");
				return true;
			}
		}

		return false;
	}

	/** Check position. If found, return position. */
	private int check_pos(int x, int y) {
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

	/**
	 * Verify if position is valid
	 * 
	 * @return true if valid.
	 * @return false if invalid.
	 */
	private boolean is_valid(int row, int column) {
		if ((row > 3 || row < 1) || (column > 3 || column < 1)) {
			System.out.println("Out of range! Trying again...");
			return false;
		} else if (find_log(row, column)) {
			System.out.println("Ocuppied position! Trying again...");
			return false;
		}

		return true;
	}

	/**
	 * Search for position in the log.
	 * 
	 * @return false if not found.
	 */
	private boolean find_log(int x, int y) {
		for (int i = 0; i < this.log.size(); i++) {
			if ((this.log.get(i).getX() == x) && (this.log.get(i).getY() == y)) {
				return true;
			}
		}

		return false;
	}

	private Board board; // Where the game occurs.
	private Player player_01; // Player one.
	private Player player_02; // Player two.
	private Scanner input; // Used to read input from the players.
	private Vector<IntPair> log; // Play history.
}
