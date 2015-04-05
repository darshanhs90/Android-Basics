package com.example.firstsongapp;

import java.io.IOException;

import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	MediaPlayer mediaPlayer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		Log.e("Error", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// mediaPlayer.start();

	}

	@Override
	protected void onResume() {
		Log.e("Error", "onResume");
		mediaPlayer = MediaPlayer.create(this, R.raw.demons_imagine_dragons);
		mediaPlayer.start();
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.e("Error", "onPause");
		mediaPlayer.stop();
		mediaPlayer.release();
		super.onPause();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void openFB(View v) {
		String url = "https://www.facebook.com/pages/Darshan-Photography/243571499018356";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);

	}

	public void openGoogle(View v) {
		String url = "https://www.google.com";
		Intent i = new Intent(Intent.ACTION_VIEW);
		i.setData(Uri.parse(url));
		startActivity(i);

	}
}
