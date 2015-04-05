package com.example.newbostonorg1;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPrefs extends Activity implements OnClickListener {
	EditText etShare;
	TextView tvShare;
	final String fileName="MySharedString";
	Editor editor;
	SharedPreferences sharedPreferences;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedprefs); 
		setupVars();
		sharedPreferences=getSharedPreferences(fileName,0);

	}

	private void setupVars() {
		Button bnSave=(Button) findViewById(R.id.bnSave);
		Button bnLoad=(Button) findViewById(R.id.bnLoad);
		bnSave.setOnClickListener(this);
		bnLoad.setOnClickListener(this);
		etShare=(EditText) findViewById(R.id.etShare);
		tvShare=(TextView) findViewById(R.id.tvShare);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bnSave:
			String data=etShare.getText().toString();
			editor=sharedPreferences.edit();
			editor.putString("sharedString", data);
			editor.commit();
			break;
		case R.id.bnLoad:
			sharedPreferences=getSharedPreferences(fileName,0);
			String text=sharedPreferences.getString("sharedString","Could not Load Data");
			tvShare.setText(text);
			break;
		}
	}
}
