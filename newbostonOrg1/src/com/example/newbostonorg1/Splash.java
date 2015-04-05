package com.example.newbostonorg1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.preference.PreferenceManager;

public class Splash extends Activity{
	private MediaPlayer splashSong;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);   

		/*if(mediaPlayer.getCurrentPosition()==mediaPlayer.getDuration()){
		Intent intent=new Intent(this,MainActivity.class);
		startActivity(intent);
		mediaPlayer.stop();
	}*/
		setContentView(R.layout.splash);
		splashSong=MediaPlayer.create(Splash.this,R.raw.testmp3);

		SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(getBaseContext());

		if(preferences.getBoolean("checkbox", false)){
			splashSong.start();
			}
		Thread timer=new Thread(){
			@Override
			public void run() {
				try{
					//sleep(3000);
					sleep(300);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					//Intent openMainActivity=new Intent("com.example.newbostonorg1.MAINACTIVITY");
					Intent openMainActivity=new Intent(getApplicationContext(),MenuAct.class);
					startActivity(openMainActivity);  
				}

			}
		};
		timer.start();
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		splashSong.release();
		finish();
		super.onPause();
	}
}
