package imns1ght;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class UI {
	private JFrame mainFrame;
	private JPanel mainPanel;
	private JPanel ctrlAndStatus;
	private JButton[][] positions;
	private JButton reset;
	private JLabel statusLabel;

	UI() {
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
		this.reset.setFont(new Font("Arial", Font.BOLD, 16));
		this.reset.setForeground(Color.decode("#d2cfcf"));
		this.reset.setBackground(Color.black);
		this.reset.setBorderPainted(false);

		// create number buttons
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				this.positions[i][j] = new JButton("X");
				this.positions[i][j].setForeground(Color.decode("#d2cfcf"));
				this.positions[i][j].setBackground(Color.black);
				this.positions[i][j].setBorderPainted(false);
				this.positions[i][j]
						.setFont(new Font("Comic Sans MS", Font.PLAIN, 40));
				this.positions[i][j].setPreferredSize(new Dimension(70, 70));
				this.mainPanel.add(this.positions[i][j]);
			}
		}
		this.positions[0][2].setText("O");
		this.positions[0][1].setText("O");
		this.positions[2][0].setText("O");
		this.positions[2][2].setText("O");

		this.statusLabel.setFont(new Font("Comic Sans MS", Font.PLAIN, 16));
		this.statusLabel.setForeground(Color.decode("#d2cfcf"));
		this.ctrlAndStatus.add(this.statusLabel);
		this.ctrlAndStatus.add(this.reset);
	}
}
