package com.example.ebook;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class WofWActivity extends Activity {
WebView webView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_roundball);
		webView=(WebView) findViewById(R.id.webView1);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.loadUrl("file:///android_asset/waroftheworlds.html");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.wof_w, menu);
		return true;
	}

}
