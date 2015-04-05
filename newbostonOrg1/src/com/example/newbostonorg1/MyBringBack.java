package com.example.newbostonorg1;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Align;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.view.View;

public class MyBringBack extends View{

	Bitmap bitmap;
	float changingY;
	Typeface typeface;
	public MyBringBack(Context context) {
		super(context); 
		bitmap=BitmapFactory.decodeResource(getResources(), R.drawable.plus);
		changingY=0;
		typeface=Typeface.SANS_SERIF;
	}
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		super.onDraw(canvas);
		canvas.drawColor(Color.WHITE);
		
		TextPaint textPaint=new TextPaint();
		textPaint.setARGB(50,150, 150,150);
		textPaint.setTextAlign(Align.CENTER);
		textPaint.setTextSize(50);
		textPaint.setTypeface(typeface);
		canvas.drawText("MyBringBack Class", canvas.getWidth()/2, 200, textPaint);
		
		
		
		canvas.drawBitmap(bitmap, canvas.getWidth()/2, changingY,null);
		if(changingY<canvas.getHeight()){
			changingY+=10;
		}
		else{
			changingY=0;
		}
		Rect mRect=new Rect(0,400,canvas.getWidth(),550);
		Paint paint=new Paint();
		paint.setColor(Color.BLUE);
		canvas.drawRect(mRect, paint);
		
		invalidate();
	}
}