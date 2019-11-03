package imns1ght;

public class Board {
	/** Default constructor. */
	public Board() {
		this.board = new int[5][5];
	}

	///////////////////////////////////////
	// Gets and Sets
	///////////////////////////////////////

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	/** Returns the value at position (n, m) */
	public int get(int n, int m) {
		return this.board[n][m];
	}

	/** Set value at position (n, m) */
	public void set(int n, int m, int play_symbol) {
		this.board[n][m] = play_symbol;
		setSize(getSize() + 1); // Update the number of elements.
	}

	///////////////////////////////////////
	// Extra 
	///////////////////////////////////////
	
	/** Clear the board. */
	public void clear() {
		for (int i = 0; i < max_size; i++) {
			for (int j = 0; j < max_size; j++) {
				this.board[i][j] = 0;
			}
		}

		setSize(0); // Update the number of elements.
	}

	/** Verify if the board is full. */
	public boolean is_full() {
		return getSize() == max_size;
	}

	/** Print the board (with extra positions). */
	public void print_matrix() {
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				System.out.print(this.board[i][j] + " ");
			}

			System.out.println();
		}
	}

	/** Print the board. */
	public void print_board() {
		for (int i = 1; i < 4; i++) {
			System.out.print("[ ");
			for (int j = 1; j < 4; j++) {
				if (this.board[i][j] == 1) {
					System.out.print("X ");
				} else if (this.board[i][j] == 2) {
					System.out.print("O ");
				} else {
					System.out.print("- ");
				}
			}

			System.out.println("]");
		}
	}

	private int[][] board;
	private int size;
	static int max_size = 9;
}