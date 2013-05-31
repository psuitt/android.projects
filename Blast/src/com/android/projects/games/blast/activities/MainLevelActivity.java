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
import com.android.projects.games.blast.beans.Level;

public class MainLevelActivity extends Activity {

	SurfaceView view;
	SurfaceHolder holder;

	Paint paint;

	Vec2 gravity;
	World world;
	Body b2;
	Body b3;
	Bitmap image;
	Matrix matrix;

	boolean running;

	@Override
	protected void onCreate(final Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		view = new SurfaceView(this);;
		holder = view.getHolder();
		setFullScreen(this);
		setContentView(view);
		running = true;
		paint = new Paint();
		paint.setColor(Color.argb(255, 135, 206, 250));

		gravity = new Vec2(0.0f, 20f);
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

		final Level level = new Level();

		final Display display = getWindowManager().getDefaultDisplay();
		final int width = display.getWidth();
		final int height = display.getHeight();

		level.setBoundaries(width, height);

		for (int i = 0; i<level.getBoundaries().length; i++) {
			final BodyDef wallBodyDef = new BodyDef();
			wallBodyDef.position.set(level.getBoundaries()[i][0], level.getBoundaries()[i][1]);
			final PolygonShape wallShapeDef = new PolygonShape();
			wallShapeDef.setAsBox(level.getBoundaries()[i][2], level.getBoundaries()[i][3]);
			final Body wall = world.createBody(wallBodyDef);
			wall.createFixture(wallShapeDef, 1f);
		}

		new Thread() {
			@Override
			public void run() {
				while(running) {

					if (!holder.getSurface().isValid()) {
						continue;
					}

					world.step(1f/16f, 15, 5);
					final Canvas c = holder.lockCanvas();
					if (c != null) {
						c.drawPaint(paint);
						drawCircle(c, b2);
						drawCircle(c, b3);
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
