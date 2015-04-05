package com.example.newbostonorg1;

import java.util.Locale;
import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class TextVoice extends Activity implements OnClickListener {
	String[] texts={"Whats up","hello"};
	TextToSpeech textToSpeech;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.textvoice);
		Button bnTextVoice=(Button) findViewById(R.id.bnTextVoice);
		bnTextVoice.setOnClickListener(this);
		textToSpeech=new TextToSpeech(this, new TextToSpeech.OnInitListener() {
			
			@Override
			public void onInit(int status) {
				if(status!=TextToSpeech.ERROR){
					textToSpeech.setLanguage(Locale.ENGLISH);
				}
			}
		});
	}

	@Override
	public void onClick(View v) {
		Random random=new Random();
		String r=texts[random.nextInt(texts.length)];
		textToSpeech.speak(r,TextToSpeech.QUEUE_FLUSH, null);
	}
	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		if(textToSpeech!=null){
			textToSpeech.stop();
			textToSpeech.shutdown();
		}
		super.onPause();
	}
}
