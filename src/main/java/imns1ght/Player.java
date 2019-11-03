package imns1ght;

import java.lang.String;

public class Player {
	public Player() {
		this.nickname = "";
	}

	public Player(String nickname, int play_symbol) {
		this.nickname = nickname;
		this.play_symbol = play_symbol;
	}

	public String getNickname() {
		return nickname;
	}

	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	public int getPlay_symbol() {
		return play_symbol;
	}

	public void setPlay_symbol(int play_symbol) {
		this.play_symbol = play_symbol;
	}

	private String nickname;
	private int play_symbol; // 1 == X & 2 == O


}
