package com.android.projects.games.blast.activities;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;

import com.android.projects.games.blast.R;

public class MainLevelActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level);
		TextView dummyTextView = (TextView) findViewById(R.id.section_label);
		Bundle extra = getIntent().getExtras();
		dummyTextView.setText(getIntent().getExtras().getString("level"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
