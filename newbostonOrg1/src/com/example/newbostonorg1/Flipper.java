package com.example.newbostonorg1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ViewFlipper;
 
public class Flipper extends Activity implements OnClickListener {
	ViewFlipper flipper;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.flipper);
		flipper=(ViewFlipper) findViewById(R.id.viewFlipper1);
		flipper.setOnClickListener(this);
		flipper.setFlipInterval(500);
		flipper.startFlipping();
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		flipper.showNext();
	}
}
