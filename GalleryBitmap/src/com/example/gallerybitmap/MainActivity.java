package com.example.gallerybitmap;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.text.TextPaint;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;



public class MainActivity extends Activity {
	private int REQUEST_CODE;
	private Bitmap bitmap;
	private ImageView imageView;
	private File filePics;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		OnClickListener clickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
				intent.setType("image/*");
				startActivityForResult(Intent.createChooser(intent, "Select "),
						REQUEST_CODE);
				/*
				 * setContentView(imageView);
				 */
			}
		};
		Button button = (Button) findViewById(R.id.button1);
		button.setOnClickListener(clickListener);

		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if (requestCode == REQUEST_CODE && resultCode == RESULT_OK) {
			Uri uri = data.getData();
			Log.d("MainActivity", uri.toString());
			Toast.makeText(getApplicationContext(), uri.toString(),
					Toast.LENGTH_SHORT).show();
			InputStream inputStream = null;
			try {
				inputStream = getContentResolver().openInputStream(uri);
				Options options = new Options();
				options.inJustDecodeBounds = true;
				BitmapFactory.decodeStream(inputStream, null, options);
				Log.d("MainActivity", "Dimensions are - Height: "
						+ options.outHeight + " Width: " + options.outWidth);
				inputStream.close();
				int height = getResources().getDisplayMetrics().heightPixels;
				int width = getResources().getDisplayMetrics().widthPixels;

				int sampleSize = 1;
				// if(options.outHeight>height || options.outWidth>width){
				while (options.outHeight > height * sampleSize
						|| options.outWidth > width * sampleSize) {
					sampleSize++;

				}
				// }
				Log.d("MainActivity", "Sampling size is " + sampleSize);

				options.inJustDecodeBounds = false;
				options.inSampleSize = sampleSize;

				inputStream = getContentResolver().openInputStream(uri);
				Bitmap bmap = BitmapFactory.decodeStream(inputStream, null,
						options);
				inputStream.close();

				if (bitmap != null) {
					bitmap.recycle();
				}
				// mutable bitmap
				bitmap = Bitmap.createBitmap(bmap.getWidth(),
						bmap.getHeight() + 60, Bitmap.Config.ARGB_8888);
				Canvas canvas = new Canvas(bitmap);
				canvas.drawBitmap(bmap, 0, 0, null);
				TextPaint paint = new TextPaint();
				paint.setTextSize(20);
				paint.setColor(0x80ff00ff);
				canvas.drawText("Image", (bmap.getWidth() / 2) - 10,
						bmap.getHeight() + 30, paint);

				bmap.recycle();
				imageView = (ImageView) findViewById(R.id.imageView1);
				imageView.setImageBitmap(bitmap);

			} catch (Exception e) {
				// TODO Auto-generated catch block
				Log.e("MainActivity", e.getMessage());
			}
		}

	}

	public void saveAndShare(View view) {
		if (bitmap == null) {
			return;
		}
		filePics = new Environment()
				.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
		Log.d("MainActivity", "save and share method" + filePics);
		filePics.mkdirs();
		String fileName = "image123.jpg";
		File file = new File(filePics, fileName);
		try {
			FileOutputStream fileOutputStream = new FileOutputStream(file);
			bitmap.compress(CompressFormat.JPEG, 100, fileOutputStream);
			fileOutputStream.close();
			Uri type = Uri.fromFile(file);
			Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
			intent.setData(type);
			sendBroadcast(intent);

			intent = new Intent(Intent.ACTION_SEND);
			intent.setType("image/jpeg");
			intent.putExtra(Intent.EXTRA_STREAM, type);
			startActivity(Intent.createChooser(intent, "Select   "));

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
