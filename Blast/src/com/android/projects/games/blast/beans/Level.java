package com.android.projects.games.blast.beans;

import com.android.projects.games.blast.consts.ColorConstants;
import com.android.projects.games.blast.enums.LevelType;


public class Level {

	private int backgroundColorInt = ColorConstants.SKY_BLUE;
	private LevelType levelType = LevelType.LEVEL_1;
	private float[][] boundaries;
	private WorldObject[] worldObjects;

	public Level() {
	}

	public LevelType getLevelType() {
		return levelType;
	}

	public void setLevelType(final LevelType levelType) {
		this.levelType = levelType;
	}

	public int getBackgroundColorInt() {
		return backgroundColorInt;
	}

	public void setBackgroundColorInt(final int backgroundColorInt) {
		this.backgroundColorInt = backgroundColorInt;
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

	public WorldObject[] getWorldObjects() {
		return worldObjects;
	}

	public void setWorldObjects(final WorldObject[] worldObjects) {
		this.worldObjects = worldObjects;
	}

}
