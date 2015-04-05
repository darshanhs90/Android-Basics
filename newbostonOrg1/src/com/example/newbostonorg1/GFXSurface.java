package com.example.newbostonorg1;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.View.OnTouchListener;

public class GFXSurface extends Activity implements OnTouchListener{

	MyBringBackSurface ourSurfaceView;
	float x,y,sX,sY,fX,fY,dX,dY,scaledX,scaledY,aniX,aniY;
	Bitmap bitmap,plus;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		ourSurfaceView=new MyBringBackSurface(this);
		ourSurfaceView.setOnTouchListener(this);
		x=0;
		y=0;
		sX=0;
		sY=0;
		fX=0;
		fY=0;
		dX=0;dY=0;scaledX=0;scaledY=0;aniX=0;
		aniY=0;
		bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.plus);

		plus=BitmapFactory.decodeResource(getResources(), R.drawable.plusselected);
		setContentView(ourSurfaceView);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		ourSurfaceView.pause();
	}
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		ourSurfaceView.resume();
	}
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		x=event.getX();
		y=event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			sX=event.getX();
			sY=event.getY();
			aniX=aniY=dX=dY=scaledX=scaledY=fX=fY=0;
			break;
		case MotionEvent.ACTION_UP:
			fX=event.getX();
			fY=event.getY();
			dX=fX-sX;
			dY=fY-sY;
			scaledX=dX/20;
			scaledY=dY/20;
			x=y=0;
			break;
		}
		return true;
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
				if(x!=0 && y!=0){
					canvas.drawBitmap(bitmap,x-bitmap.getWidth()/2,y-bitmap.getHeight()/2, null);
				} 
				if(sX!=0 && sY!=0){
					canvas.drawBitmap(plus,sX-plus.getWidth()/2,sY-plus.getHeight()/2, null);
				} 
				if(fX!=0 && fY!=0){
					canvas.drawBitmap(bitmap,fX-bitmap.getWidth()/2-aniX,fY-bitmap.getHeight()/2-aniY, null);
					canvas.drawBitmap(plus,fX-plus.getWidth()/2,fY-plus.getHeight()/2, null);
				} 
				aniX+=scaledX;
				aniY+=scaledY;
				
				holder.unlockCanvasAndPost(canvas);

			}

		}


	}

}
