package com.android.projects.games.blast.beans;

/**
 * Game bean for storing game stats.
 * 
 * @author Sora
 *
 */
public class Game {

	private int score;
	private Level level;
	
	public Game(Level level) {
		this.setLevel(level);
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(Level level) {
		this.level = level;
	}
	
}
