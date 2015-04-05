package com.example.ebook;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.webkit.WebView;

public class JabimgActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		WebView webView=(WebView) findViewById(R.id.webView1);
		webView.getSettings().setBuiltInZoomControls(true);
		webView.loadUrl("file:///android_asset/jabberwockyimg.html");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.jabimg, menu);
		return true;
	}

}
