package com.android.projects.games.blast.factory;

import android.view.Display;

import com.android.projects.games.blast.beans.Level;
import com.android.projects.games.blast.exceptions.LevelsNotInitializedException;
import com.android.projects.games.blast.util.DisplayUtil;

public class LevelFactory {

	private static final int MAX_LEVEL = 3;
	private static final Level[] LEVELS = new Level[MAX_LEVEL];

	private static boolean IS_INIT = false;

	/** Hidden constructor. */
	private LevelFactory() {
	}

	public static final void createAllLevels(final Display display) {

		LEVELS[0] = createLevel1(display);

		IS_INIT = true;

	}

	private static Level createLevel1(final Display display) {

		final Level level = new Level();

		level.setBoundaries(DisplayUtil.getWidth(display), DisplayUtil.getHeight(display));

		return level;

	}

	public static Level[] getLevels() throws LevelsNotInitializedException {
		if (IS_INIT) {
			return LEVELS;
		}
		throw new LevelsNotInitializedException();
	}

}
