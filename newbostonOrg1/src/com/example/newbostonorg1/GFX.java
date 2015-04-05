package com.example.newbostonorg1;

import android.app.Activity;
import android.os.Bundle;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;

public class GFX extends Activity {

	MyBringBack ourView;
	WakeLock lock;
	PowerManager manager;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		manager=(PowerManager) getSystemService(POWER_SERVICE);
		lock=manager.newWakeLock(PowerManager.FULL_WAKE_LOCK,"wateva");
		super.onCreate(savedInstanceState);
		lock.acquire();
		ourView =new MyBringBack(this);
		setContentView(ourView);

	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		lock.release();
	}
}
