package com.android.games.cannonboy;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class CannonBoyGameActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_cannon_boy_game);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.cannon_boy_game, menu);
		return true;
	}

}
