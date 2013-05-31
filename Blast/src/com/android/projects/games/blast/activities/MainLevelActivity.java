package com.android.projects.games.blast.activities;

import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.content.pm.FeatureInfo;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.android.projects.games.blast.R;

import org.jbox2d.collision.shapes.CircleShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class MainLevelActivity extends Activity {

	SurfaceView view;
	SurfaceHolder holder;
	
	Paint paint;
	
	Vec2 gravity;
	World world;
	Body b2;
	Bitmap image;
	Matrix matrix;
	
	boolean running;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		view = new SurfaceView(this);;
		holder = view.getHolder();
		setFullScreen(this);
		setContentView(view);
		running = true;
		paint = new Paint();
		paint.setColor(0xffffffff);
		
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
		bodyDef.position.y = 50;
		
		Body body = world.createBody(bodyDef);
		FixtureDef f =  new FixtureDef();
		f.shape = circle;
		f.density = 1;
		f.friction = 0.3f;
		f.restitution = 0.8f;
		body.createFixture(f);
		b2 = body;
		
		new Thread() {
			public void run() {
				while(running) {
					
					if (!holder.getSurface().isValid()) {
						continue;
					}
					
					world.step(1f/16f, 15, 2);
					Canvas c = holder.lockCanvas();
					if (c != null) {
						c.drawPaint(paint);
						drawCircle(c, b2);
						holder.unlockCanvasAndPost(c);
						
					}
					try {
						Thread.sleep(8);
					} catch (Exception e) {
						
					}
					
					
					
				}
				
			};
		}.start();
	}
	
	private void drawCircle(Canvas c, Body b) {
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

	private final static void setFullScreen(Activity context) {
		context.requestWindowFeature(Window.FEATURE_NO_TITLE);
		context.getWindow().setBackgroundDrawable(null);
		context.getWindow().setFlags(
				WindowManager.LayoutParams.FLAG_FULLSCREEN, 
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}

}
