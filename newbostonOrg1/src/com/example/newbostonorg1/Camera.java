package com.example.newbostonorg1;

import java.io.IOException;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

public class Camera extends Activity implements OnClickListener{
	private Button button;
	private ImageButton imageButton;
	private ImageView imageViewCamera;
	private Intent i;
	private int requestCode=0;
	private Bitmap bitmap;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.photo);
		init();
	}

	private void init() {
		imageViewCamera=(ImageView) findViewById(R.id.ivCamera);
		imageButton=(ImageButton) findViewById(R.id.ibCamera);
		button=(Button) findViewById(R.id.bSetWallpaper);
		imageButton.setOnClickListener(this);
		button.setOnClickListener(this);
	}	
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibCamera:
			i=new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);
			startActivityForResult(i, requestCode);

			break;
		case R.id.bSetWallpaper:
			try {
				if(bitmap==null){
					Toast.makeText(getApplicationContext(), "Please take a picture", Toast.LENGTH_SHORT).show();
				}
				else{
				getApplicationContext().setWallpaper(bitmap);
				Toast.makeText(getApplicationContext(), "Image Set successfully!!", Toast.LENGTH_SHORT).show();
				
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			break;

		}
	} 
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode == RESULT_OK){
			Bundle bundle=data.getExtras();
		bitmap=(Bitmap) bundle.get("data");
		imageButton.setImageBitmap(bitmap);
		//imageViewCamera.setImageBitmap(bitmap);
		imageViewCamera.setImageResource(R.drawable.jabberwocky_2);
		}
	}
}
