package com.android.projects.games.blast.beans;

import com.android.projects.games.blast.consts.ColorConstants;
import com.android.projects.games.blast.enums.LevelType;


public class Level {

	public int backgroundColorInt = ColorConstants.SKY_BLUE;
	public LevelType levelType = LevelType.LEVEL_1;
	private float[][] boundaries;

	public Level() {
	}

	public float[][] getBoundaries() {
		return boundaries;
	}

	public void setBoundaries(final float x, final float y) {
		boundaries = new float[][]
			{{	0f, 0f,  x, 5f},
			 {	0f,	0f,	5f,	 y},
			 {	0f,	 y,  x, 5f},
			 {	x,	0f, 5f,	 y}};
	}

}
