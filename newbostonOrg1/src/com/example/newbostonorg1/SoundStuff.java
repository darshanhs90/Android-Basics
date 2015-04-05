package com.example.newbostonorg1;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;

public class SoundStuff extends Activity implements OnClickListener,OnLongClickListener {
	SoundPool pool;  
	int explosion=0;
	MediaPlayer mediaPlayer;
	Boolean explosionPlaying,mediaPlaying=false;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View view=new View(this);
		view.setOnClickListener(this);
		view.setOnLongClickListener(this);

		setContentView(view);
		pool=new SoundPool(5, AudioManager.STREAM_MUSIC, 0);
		explosion=pool.load(this,R.raw.explosion,1);
		mediaPlayer=MediaPlayer.create(this,R.raw.testmp3);

	}

	@Override
	public void onClick(View view) {
		if(explosion!=0)
			/*explosionPlaying=true;
		if(mediaPlaying)*/
			//mediaPlayer.stop();
		pool.play(explosion, 1, 1, 1, 0, 1);

	}

	@Override
	public boolean onLongClick(View v) {
		// TODO Auto-generated method stub
		/*mediaPlaying=true;
		if(explosionPlaying)*/
		//pool.stop(explosion);
		mediaPlayer.start();
		return false;
	}
}
