package com.example.filltheblanks;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapFactory.Options;
import android.os.Bundle;
import android.os.Environment;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.filltheblanks.R.color;

public class MainActivity extends Activity {
	protected int REQUEST_CODE;
	private String TAG = "MainActivity";
	private EditText name;
	private EditText email;
	private EditText phone;
	private EditText address;
	private EditText opinion;
	private Spinner bgcolor;
	private String back_color;
	private Spinner favstar;
	private RatingBar rating;
	private ProgressBar progress;
	private int curr_progress;
	private boolean null_checker = true;
	private Button button;
	private ScrollView scrollView;
	private String star;
	private ImageView imageView;
	private Bitmap bitmap;
	private File fileLoc;
	private TextView heading;
	private TextView textView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		heading = (TextView) findViewById(R.id.heading);
		name = (EditText) findViewById(R.id.name);
		email = (EditText) findViewById(R.id.email_address);
		phone = (EditText) findViewById(R.id.phone_number);
		address = (EditText) findViewById(R.id.address);
		opinion = (EditText) findViewById(R.id.comments);
		bgcolor = (Spinner) findViewById(R.id.background_color);
		favstar = (Spinner) findViewById(R.id.sports_star);
		rating = (RatingBar) findViewById(R.id.survey_rating);
		progress = (android.widget.ProgressBar) findViewById(R.id.survey_progress);
		button = (Button) findViewById(R.id.submit_button);
		scrollView = (ScrollView) findViewById(R.id.linlayout);
		textView = (TextView) findViewById(R.id.mailid);
		curr_progress = progress.getProgress();
		TextWatcher nullChecker = new TextWatcher() {
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if (s != null) {
					// Log.d(TAG, "before null check");
					String text = s.toString();
					// Log.d(TAG, "after null check");
					if (text == null || text.length() == 0
							|| text.charAt(0) == ' ' || text.contains("Name")) {
						Toast.makeText(getApplicationContext(),
								"All fields are required", Toast.LENGTH_SHORT)
								.show();
						null_checker = true;
						button.setEnabled(false);
					} else {
						null_checker = false;
						button.setEnabled(true);
					}
				}
			}
		};
		name.addTextChangedListener(nullChecker);
		email.addTextChangedListener(nullChecker);
		address.addTextChangedListener(nullChecker);
		phone.addTextChangedListener(nullChecker);
		opinion.addTextChangedListener(nullChecker);
		OnItemSelectedListener colorSelectedListener = new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (bgcolor.getItemAtPosition(arg2).toString().contains("Red")) {
					back_color = "Red";
					Log.d(TAG, "no bg error");
					scrollView.setBackgroundColor(0xffff0000);
				} else if (bgcolor.getItemAtPosition(arg2).toString()
						.contains("Green")) {
					back_color = "Green";
					Log.d(TAG, "no bg error");
					scrollView.setBackgroundColor(0xff00ff00);
				} else if (bgcolor.getItemAtPosition(arg2).toString()
						.contains("Blue")) {
					back_color = "Blue";
					Log.d(TAG, "no bg error");
					scrollView.setBackgroundColor(0xff0000ff);
				} else if (bgcolor.getItemAtPosition(arg2).toString()
						.contains("Pink")) {
					back_color = "Pink";
					Log.d(TAG, "no bg error");
					scrollView.setBackgroundColor(0xffFFC0CB);
				} else if (bgcolor.getItemAtPosition(arg2).toString()
						.contains("Orange")) {
					back_color = "Orange";
					Log.d(TAG, "no bg error");
					scrollView.setBackgroundColor(0xffFF4500);
				} else if (bgcolor.getItemAtPosition(arg2).toString()
						.contains("White")) {
					back_color = "White";
					Log.d(TAG, "no bg error");
					scrollView.setBackgroundColor(0xffF8F8FF);
				} else if (bgcolor.getItemAtPosition(arg2).toString()
						.contains("Violet")) {
					back_color = "Violet";
					Log.d(TAG, "no bg error");
					scrollView.setBackgroundColor(0xffEE82EE);
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		};
		bgcolor.setOnItemSelectedListener(colorSelectedListener);

		OnItemSelectedListener starSelectedListener = new OnItemSelectedListener() {
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				if (favstar.getItemAtPosition(arg2).toString()
						.contains("Roger Federer")) {
					star = "rfed";
				} else if (favstar.getItemAtPosition(arg2).toString()
						.contains("Sachin Tendulkar")) {
					star = "srt";
				} else if (favstar.getItemAtPosition(arg2).toString()
						.contains("Cristiano Ronaldo")) {
					star = "cr7";
				}
			}

			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub

			}
		};
		favstar.setOnItemSelectedListener(starSelectedListener);
		OnClickListener clickListener = new OnClickListener() {

			@Override
			public void onClick(View v) {
				curr_progress = 0;
				boolean val = true;
				if (name.getText().toString().length() == 0) {
					Toast.makeText(getApplicationContext(),
							"Name Field Invalid", Toast.LENGTH_SHORT).show();
					val = false;
				} else {
					curr_progress++;
				}

				if (email.getText().toString().length() == 0) {
					if (val != false)
						Toast.makeText(getApplicationContext(),
								"Email Field Invalid", Toast.LENGTH_SHORT)
								.show();
					val = false;
				} else {
					curr_progress++;
				}
				if (phone.getText().toString().length() == 0) {
					if (val != false)
						Toast.makeText(getApplicationContext(),
								"Phone Field Invalid", Toast.LENGTH_SHORT)
								.show();
					val = false;
				} else {
					curr_progress++;
				}
				if (address.getText().toString().length() == 0) {
					if (val != false)
						Toast.makeText(getApplicationContext(),
								"Address Field Invalid", Toast.LENGTH_SHORT)
								.show();
					val = false;
				} else {
					curr_progress++;
				}
				if (opinion.getText().toString().length() == 0) {
					if (val != false)
						Toast.makeText(getApplicationContext(),
								"Opinion Field Invalid", Toast.LENGTH_SHORT)
								.show();
					val = false;
				} else {
					curr_progress++;
				}
				if (rating.getRating() == 0) {
					if (val != false)
						Toast.makeText(getApplicationContext(),
								"Provide the Rating", Toast.LENGTH_SHORT)
								.show();
					val = false;
				} else {
					curr_progress++;
				}
				if (back_color == null || back_color.length() == 0) {
					if (val != false)
						Toast.makeText(getApplicationContext(),
								"Background Color Not Chosen",
								Toast.LENGTH_SHORT).show();
					val = false;
				} else {
					curr_progress++;
				}
				if (star == null || star.length() == 0) {
					if (val != false)
						Toast.makeText(getApplicationContext(),
								"SportStar Not Selected", Toast.LENGTH_SHORT)
								.show();
					val = false;
				} else {
					curr_progress++;
				}

				Log.d(TAG, "curr progress:" + curr_progress);
				progress.setProgress(curr_progress);
				if (val == false) {
					return;
				}

				Toast.makeText(getApplicationContext(),
						"Thanks for the Review", Toast.LENGTH_LONG).show();
				heading.setText("Thanks for the Review "
						+ name.getText().toString());
				button.setVisibility(View.GONE);
				name.setVisibility(View.GONE);
				email.setVisibility(View.GONE);
				bgcolor.setVisibility(View.GONE);
				phone.setVisibility(View.GONE);
				address.setVisibility(View.GONE);
				opinion.setVisibility(View.GONE);
				bgcolor.setVisibility(View.GONE);
				favstar.setVisibility(View.GONE);
				rating.setVisibility(View.GONE);

				imageView = (ImageView) findViewById(R.id.favStar);
				if (star != null) {
					fileLoc = new Environment()
							.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
					String fileName = star + ".jpg";
					File file = new File(fileLoc, fileName);
					Toast.makeText(getApplicationContext(), file.toString(),
							Toast.LENGTH_SHORT).show();
					InputStream inputStream;
					try {
						inputStream = new BufferedInputStream(
								new FileInputStream(file));
						Log.d(TAG, "inputstream");
						Options options = new Options();
						options.inJustDecodeBounds = false;
						bitmap = BitmapFactory.decodeStream(inputStream, null,
								options);
						inputStream.close();
						imageView.setImageBitmap(bitmap);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				imageView.setVisibility(View.VISIBLE);

				textView.setVisibility(View.VISIBLE);
			}
		};
		button.setOnClickListener(clickListener);

		textView.setVisibility(View.INVISIBLE);
		textView.setText("Kindly Provide your inputs to the following mail id :hsdars@gmail.com");
		textView.setTextColor(color.Aquamarine);
		textView.setTextSize(20);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
