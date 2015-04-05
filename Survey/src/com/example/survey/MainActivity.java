package com.example.survey;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements TextWatcher{
	private EditText mName;
	private EditText mPhone;
	private EditText mEmail;
	private EditText mComments;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mName=(EditText) findViewById(R.id.name);
		mPhone=(EditText) findViewById(R.id.phone);
		mEmail=(EditText) findViewById(R.id.email);
		mComments=(EditText) findViewById(R.id.comments);

		///one way for actionlistener
		/*	TextWatcher textWatcher=new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
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
				String str=s.toString();
				boolean val=str.length()>0 && !(str.toLowerCase().contains("duck"));
				if(val==true){
				findViewById(R.id.imageButton1).setVisibility(View.INVISIBLE);}
				else{
					findViewById(R.id.imageButton1).setVisibility(View.GONE);

				}
			}
		};*/
		mEmail.addTextChangedListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void submitForm(View view){
		Log.d("MainActivity","submitform");
		String name=mName.getText().toString();
		String phone=mPhone.getText().toString();
		String email=mEmail.getText().toString();
		String comments=mComments.getText().toString();
		//validation code begins here

		/*	if(!(email.contains("@"))){
			mEmail.requestFocus();
			Toast.makeText(getApplicationContext(), "Invalid email", Toast.LENGTH_SHORT).show();		

			return;
		}
		//Toast.makeText(getApplicationContext(), mName.getText().toString(), Toast.LENGTH_SHORT).show();		

		Toast.makeText(this.getApplicationContext(),"Thank You",Toast.LENGTH_LONG).show();
		Animation anim=AnimationUtils.makeOutAnimation(this,true);
		view.startAnimation(anim);
		view.setVisibility(View.INVISIBLE);*/

		//Simple way to send some text
		/*Intent intent=new Intent(Intent.ACTION_SEND);
		intent.setType("text/plain");
		intent.putExtra(Intent.EXTRA_TEXT,"what an app");
		startActivity(intent);*/

		//Send sms message
		/*Intent intent=new Intent(Intent.ACTION_VIEW);
		intent.setData((Uri.parse("sms:"+phone))); 
		intent.putExtra("sms_body","what an app");
		startActivity(intent);*/

		Intent intent=new Intent(Intent.ACTION_SENDTO,Uri.fromParts("mailto","someone@somewhere",null));
		intent.putExtra(Intent.EXTRA_SUBJECT,"important news");
		intent.putExtra(Intent.EXTRA_TEXT,name+" says "+comments);
		if(intent.resolveActivity(getPackageManager())==null){
			Toast.makeText(getApplicationContext(), "no apps", Toast.LENGTH_SHORT).show();
			return;	
		}

		startActivity(intent.createChooser(intent, "Choose your email app"));

	}
	public void onTextChanged(CharSequence s, int start, int before, int count) {
		// TODO Auto-generated method stub

	}

	public void beforeTextChanged(CharSequence s, int start, int count,
			int after) {
		// TODO Auto-generated method stub

	}

	public void afterTextChanged(Editable s) {
		// TODO Auto-generated method stub
		String str=s.toString();
		boolean val=str.length()>0 && !(str.toLowerCase().contains("duck"));
		if(val==true){
		findViewById(R.id.imageButton1).setVisibility(View.INVISIBLE);
		Toast.makeText(getApplicationContext(), "message", Toast.LENGTH_SHORT).show();
		}
		else{
			findViewById(R.id.imageButton1).setVisibility(View.GONE);

		}
	}
}
