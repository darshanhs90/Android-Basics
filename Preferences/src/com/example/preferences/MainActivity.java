package com.example.preferences;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

public class MainActivity extends Activity {
	private SharedPreferences sharedPreferences;
	TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Log.d("MainActivity", "onCreate");
		sharedPreferences = getPreferences(MODE_PRIVATE);
		int count = sharedPreferences.getInt("count", 0);
		count++;
		Editor editor = sharedPreferences.edit();
		editor.putInt("count", count);
		editor.commit();
		OnClickListener clickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				// SystemClock.sleep(2000);//not to be done
				Runnable runnable = new Runnable() {

					@Override
					public void run() {
						// TODO Auto-generated method stub
						int clickCount = 1 + sharedPreferences.getInt(
								"clickCount", 0);
						sharedPreferences.edit()
								.putInt("clickCount", clickCount).commit();
						textView.setTextColor(0xff00ff00);
						textView.setText("Click count is =" + clickCount);
					}
					
				};
				textView.postDelayed(runnable, 1000);
			}
		};

		Log.d("MainActivity", "count =" + count);
		textView = new TextView(this);
		textView.setText("count is " + count);
		textView.setTextSize(24);
		textView.setOnClickListener(clickListener);
		setContentView(textView);
		// setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	/*
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub
	 * 
	 * //SystemClock.sleep(2000);//not to be done Runnable runnable=new
	 * Runnable() {
	 * 
	 * @Override public void run() { // TODO Auto-generated method stub int
	 * clickCount=1+sharedPreferences.getInt("clickCount",0);
	 * sharedPreferences.edit().putInt("clickCount", clickCount).commit();
	 * textView.setTextColor(0xff00ff00);
	 * textView.setText("Click count is ="+clickCount); } };
	 * textView.postDelayed(runnable, 1000);
	 * 
	 * 
	 * 
	 * 
	 * }
	 * 
	 * @Override public void run() { // TODO Auto-generated method stub int
	 * clickCount=1+sharedPreferences.getInt("clickCount",0);
	 * sharedPreferences.edit().putInt("clickCount", clickCount).commit();
	 * textView.setTextColor(0xff00ff00);
	 * textView.setText("Click count is ="+clickCount); }
	 */
}
