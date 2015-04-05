package com.example.newbostonorg1;

import android.app.Activity;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.SlidingDrawer;
import android.widget.SlidingDrawer.OnDrawerOpenListener;

public class Slider extends Activity implements OnCheckedChangeListener, OnClickListener, OnDrawerOpenListener {
	SlidingDrawer slidingDrawer;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sliding);
		Button button1=(Button) findViewById(R.id.slidingHandle1) ;
		Button button2=(Button) findViewById(R.id.slidingHandle2) ;
		Button button3=(Button) findViewById(R.id.slidingHandle3) ;

		Button button4=(Button) findViewById(R.id.slidingHandle4) ;
		Button handle=(Button) findViewById(R.id.handle) ;
		CheckBox checkBox= (CheckBox) findViewById(R.id.cbSlideable) ;
		checkBox.setOnCheckedChangeListener(this);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		handle.setOnClickListener(this);
		slidingDrawer=(SlidingDrawer) findViewById(R.id.slidingDrawer);
		slidingDrawer.setOnDrawerOpenListener(this);
	}

	@Override
	public void onClick(View view) {

		switch (view.getId()) {
		case R.id.slidingHandle1:
			slidingDrawer.open();
			break;
		case R.id.slidingHandle2:

			slidingDrawer.open();
			break;
		case R.id.slidingHandle3:

			slidingDrawer.toggle();
			break;
		case R.id.slidingHandle4:

			slidingDrawer.close();
			break;
		case R.id.handle:
			break;
		}
	}  
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		if(buttonView.isChecked())
		{
			slidingDrawer.lock();
		}
		else
		{
			slidingDrawer.unlock();
		}
	}

	@Override
	public void onDrawerOpened() {
		// TODO Auto-generated method stub
		MediaPlayer mediaPlayer=MediaPlayer.create(this,R.raw.explosion);
		mediaPlayer.start();
	}
}
