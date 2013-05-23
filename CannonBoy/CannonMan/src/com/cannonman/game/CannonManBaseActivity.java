package com.cannonman.game;

import java.io.IOException;
import java.io.InputStream;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.Sprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.ITexture;
import org.andengine.opengl.texture.bitmap.BitmapTexture;
import org.andengine.opengl.texture.region.ITextureRegion;
import org.andengine.opengl.texture.region.TextureRegionFactory;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.adt.io.in.IInputStreamOpener;
import org.andengine.util.debug.Debug;

import android.view.Display;

public class CannonManBaseActivity extends SimpleBaseGameActivity {

	private static int CAMERA_WIDTH = 720;
	private static int CAMERA_HEIGHT = 480;
	
	private ITexture mTextureBall;
	private ITexture mTextureCannon;
	private ITextureRegion mFaceTextureRegionBall;
	private ITextureRegion mFaceTextureRegionCannon;
	
	@SuppressWarnings("deprecation")
	@Override
	public EngineOptions onCreateEngineOptions() {
		final Display display = getWindowManager().getDefaultDisplay();
		
		CAMERA_WIDTH = display.getWidth();
		CAMERA_HEIGHT = display.getHeight();
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_SENSOR, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}

	@Override
	protected void onCreateResources() {
		
		try {
		this.mTextureBall = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
			@Override
			public InputStream open() throws IOException {
				return getAssets().open("gfx/chromatic_circle.png");
			}});
		this.mTextureCannon = new BitmapTexture(this.getTextureManager(), new IInputStreamOpener() {
			@Override
			public InputStream open() throws IOException {
				return getAssets().open("gfx/crappy_cannon.png");
			}});
		
		this.mTextureBall.load();
		this.mFaceTextureRegionBall = TextureRegionFactory.extractFromTexture(this.mTextureBall);
		this.mTextureCannon.load();
		this.mFaceTextureRegionCannon = TextureRegionFactory.extractFromTexture(this.mTextureCannon);
		} catch (IOException e) {
			Debug.e(e);
		}
	}

	@Override
	protected Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());
		
		final Scene scene = new Scene();
		scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));
		
				
		final Sprite circleSprite = new Sprite(64f, 100f, this.mFaceTextureRegionBall, this.getVertexBufferObjectManager());
		circleSprite.setScale(0.25f);
		final Sprite cannonSprite = new Sprite(64f, 64f, this.mFaceTextureRegionCannon, this.getVertexBufferObjectManager());
		cannonSprite.setScale(0.5f);
		scene.attachChild(circleSprite);
		scene.attachChild(cannonSprite);
		
		return scene;
	}
	
}
