package com.example.javabook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;

public class Surprise extends Activity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Intent intent=new Intent();
		if(Math.random()>0.5){
		intent.setClass(this,NasaActivity.class);}
		else{
		intent.setClass(this,MainActivity.class);}
		startActivity(intent);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.nasa, menu);
		return true;
	}

}
