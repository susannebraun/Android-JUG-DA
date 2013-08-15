package com.example.jug_demo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	
	private static final String EDIT_TEXT_VISIBLE_KEY = "EDIT_TEXT";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final EditText input = (EditText) findViewById(R.id.editText1);
		input.setVisibility(View.GONE);
		
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				if(input.getVisibility() == View.GONE) {
					input.setVisibility(View.VISIBLE);
				} else {
					input.setVisibility(View.GONE);
				}
				
			}
		});
		
		if(savedInstanceState != null) {
			if(savedInstanceState.getBoolean(EDIT_TEXT_VISIBLE_KEY, false)) {
				input.setVisibility(View.VISIBLE);
			}
		}
	}

	
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		
		boolean isVisible = false;
		EditText input = (EditText) findViewById(R.id.editText1);
		if(input.getVisibility() == View.VISIBLE) {
			isVisible = true;
		}
		
		outState.putBoolean(EDIT_TEXT_VISIBLE_KEY, isVisible);
	}



	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
