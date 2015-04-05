package com.example.newbostonorg1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {
	private TextView tvDisplay;
	private Button add,sub;
	private int count=0;
	private String TAG="MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		add=(Button)findViewById(R.id.button1);
		sub=(Button)findViewById(R.id.button2);
		tvDisplay=(TextView) findViewById(R.id.tvDisplay);
		add.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				count++;

				tvDisplay.setText("Your total is "+count);
			}
		});
		sub.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				count--;
				tvDisplay.setText("Your total is "+count);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
