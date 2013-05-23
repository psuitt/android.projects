package com.android.games.cannonboy;

import org.andengine.engine.camera.Camera;
import org.andengine.engine.options.EngineOptions;
import org.andengine.engine.options.ScreenOrientation;
import org.andengine.engine.options.resolutionpolicy.RatioResolutionPolicy;
import org.andengine.entity.scene.Scene;
import org.andengine.entity.scene.background.Background;
import org.andengine.entity.sprite.AnimatedSprite;
import org.andengine.entity.sprite.TiledSprite;
import org.andengine.entity.util.FPSLogger;
import org.andengine.opengl.texture.TextureOptions;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.BitmapTextureAtlasTextureRegionFactory;
import org.andengine.opengl.texture.atlas.bitmap.BuildableBitmapTextureAtlas;
import org.andengine.opengl.texture.atlas.bitmap.source.IBitmapTextureAtlasSource;
import org.andengine.opengl.texture.atlas.buildable.builder.BlackPawnTextureAtlasBuilder;
import org.andengine.opengl.texture.atlas.buildable.builder.ITextureAtlasBuilder.TextureAtlasBuilderException;
import org.andengine.opengl.texture.region.TiledTextureRegion;
import org.andengine.ui.activity.SimpleBaseGameActivity;
import org.andengine.util.debug.Debug;

import android.view.Display;

public class CannonBoyGameActivity extends SimpleBaseGameActivity {

	private static int CAMERA_WIDTH = 0;
	private static int CAMERA_HEIGHT = 0;
	
	
	private BuildableBitmapTextureAtlas mBitmapTextureAtlas;
	private TiledTextureRegion mCannonBoyTextureRegion;
	
	@Override
	public EngineOptions onCreateEngineOptions() {
		
		Display currDisplay = getWindowManager().getDefaultDisplay();
		CAMERA_WIDTH = currDisplay.getWidth();
		CAMERA_HEIGHT = currDisplay.getHeight();
		
		final Camera camera = new Camera(0, 0, CAMERA_WIDTH, CAMERA_HEIGHT);

		return new EngineOptions(true, ScreenOrientation.LANDSCAPE_FIXED, new RatioResolutionPolicy(CAMERA_WIDTH, CAMERA_HEIGHT), camera);
	}

	@Override
	protected void onCreateResources() {
		
		BitmapTextureAtlasTextureRegionFactory.setAssetBasePath("gfx/");
		
		this.mBitmapTextureAtlas = new BuildableBitmapTextureAtlas(this.getTextureManager(), 512, 256, TextureOptions.NEAREST);
		
		this.mCannonBoyTextureRegion = BitmapTextureAtlasTextureRegionFactory.createTiledFromAsset(this.mBitmapTextureAtlas, this, "face_circle_tiled.png", 2, 1);
		
		try {
			this.mBitmapTextureAtlas.build(new BlackPawnTextureAtlasBuilder<IBitmapTextureAtlasSource, BitmapTextureAtlas>(0, 0, 1));
			this.mBitmapTextureAtlas.load();
		} catch (TextureAtlasBuilderException e) {
			Debug.e(e);
		}
	}

	@Override
	protected Scene onCreateScene() {
		this.mEngine.registerUpdateHandler(new FPSLogger());

		final Scene scene = new Scene();
		scene.setBackground(new Background(0.09804f, 0.6274f, 0.8784f));

		/* Quickly twinkling face. */
		final TiledSprite cannonBoy = new TiledSprite(100, 50, this.mCannonBoyTextureRegion, this.getVertexBufferObjectManager());
		cannonBoy.setCurrentTileIndex(0);
		scene.attachChild(cannonBoy);
		return scene;
	}
}
