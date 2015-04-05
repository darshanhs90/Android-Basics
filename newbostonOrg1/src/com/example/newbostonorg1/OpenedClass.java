package com.example.newbostonorg1;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class OpenedClass extends Activity  implements OnClickListener, OnCheckedChangeListener{
	private TextView tvQuestion,tvText;
	private Button breturn;
	private RadioGroup rgAnswers;
	private String gotBread;
	private String returnAnswer="null for now";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.send);
		init();
		SharedPreferences preferences=PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		String edittext=preferences.getString("name","No Name");
		String value=preferences.getString("list","1");
		if(value.contentEquals("1")){
			tvQuestion.setText(edittext);
		}

		/*Bundle bundle=getIntent().getExtras();
		gotBread=bundle.getString("etText");
		if(gotBread!=null){
			tvQuestion.setText(gotBread);}
		else{
			tvQuestion.setText("");
		}*/
	}

	private void init() {
		tvQuestion=(TextView) findViewById(R.id.tvQuestion);
		tvText=(TextView) findViewById(R.id.tvRGroup);
		breturn=(Button) findViewById(R.id.bReturn);
		rgAnswers=(RadioGroup) findViewById(R.id.rgAnswers);
		breturn.setOnClickListener(this); 
		rgAnswers.setOnCheckedChangeListener(this);
	}

	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
		switch (arg1) {
		case R.id.rbAnswer1:
			returnAnswer=((RadioButton)findViewById(R.id.rbAnswer1)).getText().toString();
			break;
		case R.id.rbAnswer2:
			returnAnswer=((RadioButton)findViewById(R.id.rbAnswer2)).getText().toString();

			break;
		case R.id.rbAnswer3:
			returnAnswer=((RadioButton)findViewById(R.id.rbAnswer3)).getText().toString();

			break;
		}
		tvText.setText(returnAnswer);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		Intent intent=new Intent();
		Bundle bundle=new Bundle();
		bundle.putString("key", returnAnswer);
		intent.putExtras(bundle);
		setResult(RESULT_OK, intent);
		finish();

	}
}