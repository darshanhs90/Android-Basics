package com.example.newbostonorg1;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

public class SeekBarVolume extends Activity implements OnSeekBarChangeListener {
	SeekBar sbVolume;
	MediaPlayer mediaPlayer;
	AudioManager audioManager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.seekbarvolume);
		sbVolume=(SeekBar) findViewById(R.id.sbVolume);
		sbVolume.setOnSeekBarChangeListener(this);	
		mediaPlayer=MediaPlayer.create(this,R.raw.testmp3);
		mediaPlayer.start();
		audioManager=(AudioManager) getSystemService(AUDIO_SERVICE);
		int maxV=audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC); 
		int curV=audioManager.getStreamVolume(AudioManager.STREAM_MUSIC); 
		sbVolume.setMax(maxV);
		sbVolume.setProgress(curV);
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		mediaPlayer.release();
	}

	@Override
	public void onProgressChanged(SeekBar seekBar, int progress,
			boolean fromUser) {
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, progress, 0);
	}
	@Override
	public void onStartTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}
	@Override
	public void onStopTrackingTouch(SeekBar seekBar) {
		// TODO Auto-generated method stub

	}

}
