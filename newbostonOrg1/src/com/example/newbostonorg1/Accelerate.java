package com.example.newbostonorg1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Accelerate extends Activity implements SensorEventListener{

	float x,y,sensorX,sensorY;
	Bitmap ball;
	SensorManager manager;
	Sensor sensor;
	MyBringBackSurface backSurface;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		manager=(SensorManager) getSystemService(SENSOR_SERVICE);
		if(manager.getSensorList(Sensor.TYPE_ACCELEROMETER).size()!=0){
			sensor=manager.getSensorList(Sensor.TYPE_ACCELEROMETER).get(0);
			manager.registerListener(this, sensor,manager.SENSOR_DELAY_NORMAL);
			ball=BitmapFactory.decodeResource(getResources(), R.drawable.plus);
			x=y=sensorX=sensorY=0;
		}
		backSurface=new MyBringBackSurface(this);
		backSurface.resume();
		setContentView(backSurface);
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onSensorChanged(SensorEvent event) {
		try {
			Thread.sleep(16);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sensorX=event.values[0];
		sensorY=event.values[1];
	}

	public class MyBringBackSurface extends SurfaceView implements Runnable{

		SurfaceHolder holder;
		Thread thread=null;;
		Boolean isRunning=false;
		public MyBringBackSurface(Context context) {
			super(context);
			holder=getHolder();
			thread=new Thread(this);
			thread.start();
		}

		public void pause() {
			isRunning=false;
			while(true){
				try {
					thread.join();
					break;
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			thread=null;
		}

		public void resume() {
			isRunning=true;
			thread=new Thread(this);
			thread.start();
		}

		@Override
		public void run() {
			while(isRunning){
				if(!holder.getSurface().isValid()){
					continue;
				}
				Canvas canvas=holder.lockCanvas();
				canvas.drawRGB(20,20,200);
				canvas.drawBitmap(ball, canvas.getWidth()/2+sensorX*20,canvas.getHeight()/2+sensorY*20, null);
				holder.unlockCanvasAndPost(canvas);

			}

		}


	}
	@Override
	protected void onPause() {
		manager.unregisterListener(this);
		super.onPause();
	}
}
