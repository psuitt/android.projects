package com.android.projects.games.blast.exceptions;

public class LevelsNotInitializedException extends Exception {

	public LevelsNotInitializedException() {
		super("Levels are not initialized call createAllLevels before use.");
	}

}
