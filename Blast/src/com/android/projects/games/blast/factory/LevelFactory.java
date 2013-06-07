package com.android.projects.games.blast.factory;

import org.jbox2d.dynamics.BodyType;

import android.graphics.Paint;
import android.view.Display;

import com.android.projects.games.blast.beans.Level;
import com.android.projects.games.blast.beans.WorldObject;
import com.android.projects.games.blast.beans.worldobjects.RectangleWorldObject;
import com.android.projects.games.blast.consts.ColorConstants;
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

		LEVELS[0] = null;
		LEVELS[1] = createLevel1(display);

		IS_INIT = true;

	}

	private static Level createLevel1(final Display display) {

		final Level level = new Level();

		level.setBoundaries(DisplayUtil.getWidth(display), DisplayUtil.getHeight(display));
		level.setWorldObjects(getLevel1WorldObjects1());

		return level;

	}

	private static WorldObject[] getLevel1WorldObjects1() {

		final RectangleWorldObject baseFixture = new RectangleWorldObject();
		baseFixture.setBodyType(BodyType.STATIC);
		baseFixture.setDensity(2.0f);
		baseFixture.setFriction(.3f);
		baseFixture.setRestitution(.4f);

		baseFixture.setX(300);
		baseFixture.setY(300);
		baseFixture.setWidth(150);
		baseFixture.setHeight(30);

		Paint scorePaint = new Paint();
		scorePaint.setColor(ColorConstants.WHITE);
		scorePaint.setStyle(Paint.Style.FILL_AND_STROKE);
		scorePaint.setStrokeWidth(4);

		baseFixture.setPaint(scorePaint);

		final RectangleWorldObject movingBlock = new RectangleWorldObject();

		movingBlock.setBodyType(BodyType.DYNAMIC);
		movingBlock.setDensity(2.0f);
		movingBlock.setFriction(.3f);
		movingBlock.setRestitution(.4f);

		movingBlock.setX(300);
		movingBlock.setY(100);
		movingBlock.setWidth(70);
		movingBlock.setHeight(70);

		scorePaint = new Paint();
		scorePaint.setColor(ColorConstants.WHITE);
		scorePaint.setStyle(Paint.Style.FILL_AND_STROKE);
		scorePaint.setStrokeWidth(4);

		movingBlock.setPaint(scorePaint);

		final WorldObject[] worldObjects = new WorldObject[] {
				baseFixture,
				movingBlock
		};

		return worldObjects;
	}

	public static Level[] getLevels() throws LevelsNotInitializedException {
		if (IS_INIT) {
			return LEVELS;
		}
		throw new LevelsNotInitializedException();
	}

}
