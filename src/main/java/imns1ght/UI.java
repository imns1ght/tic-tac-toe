package imns1ght;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UI implements ActionListener {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel ctrlAndStatus;
	private JButton[][] positions;
	private JButton reset;
	private JLabel statusLabel;
	private int currPlayer = 1;
	private GameProcess game;

	UI() {
		this.game = new GameProcess();
		this.mainFrame = new JFrame("Tic Tac Toe");
		this.mainFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		this.mainFrame.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 0));
		this.mainFrame.setSize(250, 350);
		this.mainFrame.setResizable(false);
		this.mainFrame.setVisible(true);
		this.mainFrame.setBackground(Color.decode("#121111"));

		this.mainPanel = new JPanel();
		this.mainPanel.setPreferredSize(new Dimension(250, 225));
		this.mainPanel.setBackground(Color.decode("#121111"));
		this.mainFrame.add(mainPanel);

		this.ctrlAndStatus = new JPanel();
		this.ctrlAndStatus.setPreferredSize(new Dimension(250, 100));
		this.ctrlAndStatus.setLayout(new FlowLayout(FlowLayout.CENTER, 0, 10));
		this.ctrlAndStatus.setBackground(Color.decode("#121111"));
		this.mainFrame.add(ctrlAndStatus);

		this.statusLabel = new JLabel("Welcome to Tic Tac Toe!");

		this.positions = new JButton[3][3];
		this.reset = new JButton("Reset");
		this.reset.addActionListener(this);
		this.reset.setFont(new Font("Arial", Font.BOLD, 16));
		this.reset.setForeground(Color.decode("#d2cfcf"));
		this.reset.setBackground(Color.black);
		this.reset.setBorderPainted(false);

		// create number buttons
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.positions[i][j] = new JButton("");
				this.positions[i][j].addActionListener(this);
				this.positions[i][j].setForeground(Color.decode("#d2cfcf"));
				this.positions[i][j].setBackground(Color.black);
				this.positions[i][j].setBorderPainted(false);
				this.positions[i][j]
						.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
				this.positions[i][j].setPreferredSize(new Dimension(70, 70));
				this.mainPanel.add(this.positions[i][j]);
			}
		}

		this.statusLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		this.statusLabel.setForeground(Color.decode("#d2cfcf"));
		this.ctrlAndStatus.add(this.statusLabel);
		this.ctrlAndStatus.add(this.reset);
	}

	private void choose_position(ActionEvent arg0) {
		if (game.is_draw()) {
			this.statusLabel.setText("DRAW GAME!");
			return;
		}

		JButton tmp = (JButton) arg0.getSource();
		IntPair index = findButton(tmp);
		index.setX(index.getX() + 1);
		index.setY(index.getY() + 1);

		game.board.set(index.getX(), index.getY(), currPlayer);
		IntPair coords = new IntPair(index.getX(), index.getY());
		game.log.add(coords);

		if (currPlayer == 1) {
			tmp.setText("X");
			currPlayer = 2;
		} else {
			tmp.setText("O");
			currPlayer = 1;
		}

		tmp.setEnabled(false);

		if (check_winner() == 1) {
			this.statusLabel.setText("\n" + game.player_01.getNickname() + " WON!");
			this.mainPanel.setEnabled(false);
			disableAllButtons();
			return;
		} else if (check_winner() == 2) {
			this.statusLabel.setText("\n" + game.player_02.getNickname() + " WON!");
			this.mainPanel.setEnabled(false);
			disableAllButtons();
			return;
		}

		if (game.is_draw()) {
			this.statusLabel.setText("DRAW GAME!");
			this.mainPanel.setEnabled(false);
			return;
		}
	}

	private void disableAllButtons() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.positions[i][j].setEnabled(false);
			}
		}
	}

	private void activateAllButtons() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.positions[i][j].setEnabled(true);
				this.positions[i][j].setText("");
			}
		}
	}

	private IntPair findButton(JButton button) {
		for (int x = 0; x < 3; x++) {
			for (int y = 0; y < 3; y++) {
				if (button.equals(this.positions[x][y])) {
					this.statusLabel.setText(x + "," + y + " clicked");
					IntPair pos = new IntPair(x, y);

					return pos;
				}
			}
		}

		return null;
	}

	private int check_winner() {
		// Check for victory of player 01.
		for (int i = 0; i < game.log.size(); i++) {
			if (game.check_pos(game.log.get(i).getX(), game.log.get(i)
					.getY()) == game.player_01.getPlay_symbol()) {
				return 1;
			}
		}

		// Check for victory of player 01.
		for (int i = 0; i < game.log.size(); i++) {
			if (game.check_pos(game.log.get(i).getX(), game.log.get(i)
					.getY()) == game.player_02.getPlay_symbol()) {
				this.statusLabel.setText(
						"\n" + game.player_02.getNickname() + " WON!");
				return 2;
			}
		}

		return -1;
	}

	private void reset() {
		this.statusLabel.setText("Lets play again!");
		this.game = new GameProcess();
		activateAllButtons();
		this.currPlayer = 1;
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		JButton tmp = (JButton) arg0.getSource();

		if (tmp.getText() == "Reset") {
			reset();
		} else {
			choose_position(arg0);
		}
	}
}
