package com.example.newbostonorg1;

import java.util.Random;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class TextPlay extends Activity {
	private ToggleButton toggler;
	private EditText command;
	private Button validator;
	private TextView invalid;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.text);
		validator=(Button) findViewById(R.id.validate_command);
		toggler=(ToggleButton) findViewById(R.id.toggleButton1);
		command=(EditText) findViewById(R.id.textCommand);
		invalid=(TextView) findViewById(R.id.textInvalid);
		toggler.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(toggler.isChecked()){
					command.setInputType(InputType.TYPE_CLASS_TEXT|InputType.TYPE_TEXT_VARIATION_PASSWORD);
				}else{
					command.setInputType(InputType.TYPE_CLASS_TEXT);
				} 
			}
		});
		validator.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String input=command.getText().toString();
				if(input.equalsIgnoreCase("Left")){
					invalid.setGravity(Gravity.LEFT);
					invalid.setVisibility(View.VISIBLE);
				}else if(input.equalsIgnoreCase("Center")){
					invalid.setGravity(Gravity.CENTER);
					invalid.setVisibility(View.VISIBLE);
				}else if(input.equalsIgnoreCase("Blue")){
					invalid.setTextColor(Color.BLUE);
					invalid.setVisibility(View.VISIBLE);
				}else if(input.equalsIgnoreCase("WTF")){
					Random random=new Random();
					invalid.setText(input+"!!!!!!!");
					invalid.setTextSize(random.nextInt(30)); 
					invalid.setTextColor(Color.rgb(random.nextInt(255), random.nextInt(255), random.nextInt(255))); 
					invalid.setVisibility(View.VISIBLE);
				}else{
					invalid.setVisibility(View.GONE);
				}
			}
		});
	}
}
