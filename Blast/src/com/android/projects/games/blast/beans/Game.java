package com.android.projects.games.blast.beans;

/**
 * Game bean for storing game stats.
 * 
 * @author Sora
 *
 */
public class Game {

	public int score;
	public Level level;
	
	public Game(Level level) {
		this.level = level;
	}
	
}
