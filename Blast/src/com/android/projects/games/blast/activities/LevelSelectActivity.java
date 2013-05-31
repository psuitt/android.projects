package com.android.projects.games.blast.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;

import com.android.projects.games.blast.Blast;
import com.android.projects.games.blast.R;

public class LevelSelectActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_select);
		addListeners();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	private void addListeners() {
		Button play1 = (Button) findViewById(R.id.levelselect_button_level_1);
		play1.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent i = new Intent(LevelSelectActivity.this, MainLevelActivity.class);
				i.putExtra("level", "1");
				startActivity(i);
			}
		});
		Button play2 = (Button) findViewById(R.id.levelselect_button_level_2);
		play2.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent i = new Intent(LevelSelectActivity.this, MainLevelActivity.class);
				i.putExtra("level", "2");
				startActivity(i);
			}
		});
		Button play3 = (Button) findViewById(R.id.levelselect_button_level_3);
		play3.setOnClickListener(new View.OnClickListener() {
			public void onClick(View arg0) {
				Intent i = new Intent(LevelSelectActivity.this, MainLevelActivity.class);
				i.putExtra("level", "3");
				startActivity(i);
			}
		});
	}

}
