package com.example.newbostonorg1;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Voice extends Activity implements OnClickListener{
	ListView lvVoiceReturn;
	int check=1111;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.voice);
		lvVoiceReturn=(ListView) findViewById(R.id.lvVoiceReturn);
		Button bnVoice=(Button) findViewById(R.id.bnVoice);
		bnVoice.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		startActivityForResult(intent, check);
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		if(requestCode==check && resultCode==RESULT_OK){
			ArrayList<String> results=data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
			lvVoiceReturn.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,results));

		}
		super.onActivityResult(requestCode, resultCode, data);

	}
}
