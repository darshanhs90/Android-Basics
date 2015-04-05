package com.example.ebook;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.webkit.WebView;

public class MainActivity extends Activity {
	WebView webView;
	MediaPlayer mediaPlayer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		webView=(WebView) findViewById(R.id.webView1);
		webView.getSettings().setBuiltInZoomControls(true);
		mediaPlayer = MediaPlayer.create(this, R.raw.halloween);

		mediaPlayer.setLooping(true);
		mediaPlayer.start();
		mediaPlayer.setLooping(true);
		webView.loadUrl("file:///android_asset/jabberwocky.html");
		
	}
	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
	mediaPlayer.stop();
		super.onDestroy();
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	public void showimage(View view){
		
		webView=null;
		Intent intent=new Intent();
		intent.setClass(view.getContext(), JabimgActivity.class);
		mediaPlayer.stop();
		startActivity(intent);
		
	}
	public void gotowiki(View view){
		webView=null;
		mediaPlayer.stop();
		webView=(WebView) findViewById(R.id.webView1);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.loadUrl("http://en.wikipedia.org/wiki/Jabberwocky");
		
	}
	
}
