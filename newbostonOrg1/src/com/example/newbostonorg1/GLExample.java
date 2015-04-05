package com.example.newbostonorg1;

import android.app.Activity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;

public class GLExample extends Activity {
	GLSurfaceView ourSurfaceView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ourSurfaceView=new GLSurfaceView(this);
		ourSurfaceView.setRenderer(new GLRendererEx());
		setContentView(ourSurfaceView);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.onPause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.onResume();
	}
}
