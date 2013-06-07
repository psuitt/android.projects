package com.android.projects.games.blast.activities;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.Window;
import android.view.WindowManager;

import com.android.projects.games.blast.R;
import com.android.projects.games.blast.beans.Game;
import com.android.projects.games.blast.beans.Level;
import com.android.projects.games.blast.consts.ColorConstants;
import com.android.projects.games.blast.exceptions.LevelsNotInitializedException;
import com.android.projects.games.blast.factory.LevelFactory;
import com.android.projects.games.blast.util.DisplayUtil;

public class MainLevelActivity extends Activity {

	Display display;

	SurfaceView view;
	SurfaceHolder holder;

	Paint paint;
	Paint scorePaint;

	Vec2 gravity;
	World world;
	Body b2;
	Body b3;
	Bitmap image;
	Matrix matrix;

	Game game;

	boolean running;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		view = new SurfaceView(this);;
		holder = view.getHolder();
		setFullScreen(this);
		setContentView(view);
		running = true;
		display = getWindowManager().getDefaultDisplay();

		paint = new Paint();
		paint.setColor(Color.argb(255, 135, 206, 250));
		scorePaint = new Paint();
		scorePaint.setColor(ColorConstants.WHITE);
		scorePaint.setStyle(Paint.Style.FILL_AND_STROKE);
		scorePaint.setStrokeWidth(1);
		scorePaint.setTextSize(30);

		gravity = new Vec2(0.0f, 50f);
		world = new World(gravity);
		world.setSleepingAllowed(false);
		world.setAutoClearForces(true);
		matrix = new Matrix();

		image = BitmapFactory.decodeResource(getResources(), R.drawable.saw_face);

		CircleShape circle = new CircleShape();

		circle.m_p.set(new Vec2(0, 0));
		circle.setRadius(60);

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.x = 160;
		bodyDef.position.y = 800;

		Body body = world.createBody(bodyDef);
		FixtureDef f =  new FixtureDef();
		f.shape = circle;
		f.density = 2;
		f.friction = 0.3f;
		f.restitution = 0.8f;
		body.createFixture(f);
		b2 = body;

		circle = new CircleShape();

		circle.m_p.set(new Vec2(0, 0));
		circle.setRadius(30);

		bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.x = 170;
		bodyDef.position.y = 900;

		body = world.createBody(bodyDef);
		f =  new FixtureDef();
		f.shape = circle;
		f.density = 2;
		f.friction = 0.3f;
		f.restitution = 0.8f;
		body.createFixture(f);
		b3 = body;

		LevelFactory.createAllLevels(display);

		final String levelString = getIntent().getExtras().getString("level");
		final Integer levelInt = Integer.valueOf(levelString);

		try {
			final Level level = LevelFactory.getLevels()[levelInt];
			createBoundaries(level);
			game = new Game(getResources(), level, world);
		} catch (final LevelsNotInitializedException e) {
			// If this breaks nothing you can do close the app.
			e.printStackTrace();
		}

		new Thread() {
			@Override
			public void run() {
				while(running) {

					if (!holder.getSurface().isValid()) {
						continue;
					}

					world.step(1f/30f, 5, 2);
					final Canvas c = holder.lockCanvas();
					if (c != null) {
						c.drawPaint(paint);

						if (true) { // If game needs a score redraw do it.
							c.drawText("0", 10, 30, scorePaint);
							c.drawText("Blast!", getWidth()/2 - 15, getHeight() - 30, scorePaint);
						}

						drawCircle(c, b2);
						drawCircle(c, b3);
						game.drawBodies(c);
						holder.unlockCanvasAndPost(c);

					}
					try {
						Thread.sleep(8);
					} catch (final Exception e) {

					}



				}

			};
		}.start();
	}

	private void createBoundaries(final Level level) {
		for (int i = 0; i<level.getBoundaries().length; i++) {
			final BodyDef wallBodyDef = new BodyDef();
			wallBodyDef.position.set(level.getBoundaries()[i][0], level.getBoundaries()[i][1]);
			final PolygonShape wallShapeDef = new PolygonShape();
			wallShapeDef.setAsBox(level.getBoundaries()[i][2], level.getBoundaries()[i][3]);
			final Body wall = world.createBody(wallBodyDef);
			wall.createFixture(wallShapeDef, 1f);
		}
	}

	private int getWidth() {
		return DisplayUtil.getWidth(display);
	}

	private int getHeight() {
		return DisplayUtil.getHeight(display);
	}

	private void drawCircle(final Canvas c, final Body b) {
		matrix.reset();
		matrix.postRotate(b.getAngle()/3.14f*180f);
		matrix.preTranslate(-image.getWidth()>>1, -image.getHeight()>>1);
		matrix.postTranslate(b.getPosition().x, b.getPosition().y);
		c.drawBitmap(image, matrix, null);
	}

	@Override
	protected void onDestroy() {
		running = false;
		super.onDestroy();
	}

	private final static void setFullScreen(final Activity context) {
		context.requestWindowFeature(Window.FEATURE_NO_TITLE);
		context.getWindow().setBackgroundDrawable(null);
		context.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

}
