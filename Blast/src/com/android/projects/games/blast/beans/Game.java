package com.android.projects.games.blast.beans;

import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;

import com.android.projects.games.blast.R;
import com.android.projects.games.blast.beans.worldobjects.RectangleWorldObject;
import com.android.projects.games.blast.factory.ShapeFactory;

/**
 * Game bean for storing game stats.
 *
 * @author Sora
 *
 */
public class Game {

	private int score;
	private Level level;
	private Matrix matrix;
	private final Bitmap image;

	public Game(final Resources r, final Level level) {
		this(r, level, null);
	}

	public Game(final Resources r, final Level level, final World world) {
		this.level = level;
		image = BitmapFactory.decodeResource(r, R.drawable.saw_face);
		if (world != null) {
			createBodies(world);
		}
	}

	public void createBodies(final World world) {

		for (final WorldObject worldObject : level.getWorldObjects()) {

			ShapeFactory.createWorldBody(world, worldObject);

		}

	}

	public void drawBodies(final Canvas c) {

		for (final WorldObject worldObject : level.getWorldObjects()) {

			switch (worldObject.getWorldObjectType()) {

				case RECTANGLE:
					drawRectangle(c, worldObject);
					break;
				default:
					break;

			}

		}

	}

	private void drawRectangle(final Canvas c, final WorldObject worldObject) {
		final Body b = worldObject.getBody();
		final RectangleWorldObject r = (RectangleWorldObject) worldObject;
		c.drawRect(b.getPosition().x,
				b.getPosition().y,
				b.getPosition().x + r.getWidth(),
				b.getPosition().y + r.getHeight(),
				worldObject.getPaint());
	}

	private void drawCircle(final Canvas c, final Body b) {
		matrix.reset();
		matrix.postRotate(b.getAngle()/3.14f*180f);
		matrix.preTranslate(-image.getWidth()>>1, -image.getHeight()>>1);
		matrix.postTranslate(b.getPosition().x, b.getPosition().y);
		c.drawBitmap(image, matrix, null);
	}

	public int getScore() {
		return score;
	}

	public void setScore(final int score) {
		this.score = score;
	}

	public Level getLevel() {
		return level;
	}

	public void setLevel(final Level level) {
		this.level = level;
	}

}
