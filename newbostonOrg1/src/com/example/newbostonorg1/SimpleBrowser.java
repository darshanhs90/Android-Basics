package com.example.newbostonorg1;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
public class SimpleBrowser extends Activity implements OnClickListener {
	WebView webView;
	EditText webAdd;

	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simplebrowser);
		webView=(WebView) findViewById(R.id.wvBrowser);
		Button go=(Button) findViewById(R.id.bnGo);
		Button goBack=(Button) findViewById(R.id.bnGoBackPage);
		Button goForward=(Button) findViewById(R.id.bnGoForward);
		Button refreshPage=(Button) findViewById(R.id.bnRefreshPage);
		Button clearHist=(Button) findViewById(R.id.bnClearHistory);
		go.setOnClickListener(this);
		goBack.setOnClickListener(this);
		goForward.setOnClickListener(this);
		refreshPage.setOnClickListener(this);
		clearHist.setOnClickListener(this);
		webAdd=(EditText) findViewById(R.id.etWebAdd);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.getSettings().setLoadWithOverviewMode(true);
		webView.getSettings().setUseWideViewPort(true);
		webView.setWebViewClient(new OurViewClient());

		try {
			webView.loadUrl("http://www.mybringback.com");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bnGo:
			webView.loadUrl(webAdd.getText().toString());
			InputMethodManager inputMethodManager=(InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
			inputMethodManager.hideSoftInputFromWindow(v.getWindowToken(), 0);
			break;
		case R.id.bnGoForward:
			if(webView.canGoForward())
				webView.goForward();
			break;
		case R.id.bnGoBackPage:
			if(webView.canGoBack())
				webView.goBack();
			break;
		case R.id.bnRefreshPage:
			webView.reload();
			break;
		case R.id.bnClearHistory:
			webView.clearHistory();
			break;
		}

	}

}
