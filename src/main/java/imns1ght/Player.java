package imns1ght;

import java.lang.String;

public class Player {
	public Player() {
		this.nickname = "";
		this.play_symbol = 0;
	}

	public Player(String nickname, int play_symbol) {
		this.nickname = nickname;
		this.play_symbol = play_symbol;
	}

	protected String getNickname() {
		return nickname;
	}

	protected void setNickname(String nickname) {
		this.nickname = nickname;
	}

	protected int getPlay_symbol() {
		return play_symbol;
	}

	protected void setPlay_symbol(int play_symbol) {
		this.play_symbol = play_symbol;
	}

	private String nickname;
	private int play_symbol; // 1 == X & 2 == O


}
