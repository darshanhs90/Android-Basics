package com.example.newbostonorg1;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/*public class MyBringBackSurface extends SurfaceView implements Runnable{

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
			holder.unlockCanvasAndPost(canvas);

		}

	}


}
*/