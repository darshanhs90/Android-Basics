package com.example.newbostonorg1;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Data extends Activity  implements OnClickListener{
	private Button start,startFor;
	private EditText sendET;
	private TextView gotAnswer;
	private int requestCode;
	private Bundle bundle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.get);
		init();
	}
	private void init() {
		start=(Button) findViewById(R.id.bSA);
		startFor=(Button) findViewById(R.id.bSAFR);
		sendET=(EditText) findViewById(R.id.eTgetData);
		gotAnswer=(TextView) findViewById(R.id.tvgetEnd);
		start.setOnClickListener(this);
		startFor.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {

		switch (v.getId()) {
		case R.id.bSA:
			String etText=sendET.getText().toString();
			 bundle=new Bundle();
			bundle.putString("etText", etText);
			Intent intent=new Intent(this,OpenedClass.class);
			// intent.putExtra(etText+"1", etText);
			intent.putExtras(bundle);
			startActivity(intent);
			break;
		case R.id.bSAFR:
			String etText1=sendET.getText().toString();
			Intent intent2=new Intent(this,OpenedClass.class);
			bundle=new Bundle();
			bundle.putString("etText", etText1);
			intent2.putExtras(bundle);
			startActivityForResult(intent2, requestCode);
			break;

		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(resultCode==RESULT_OK){
			Bundle bundle=new Bundle();
			bundle=data.getExtras();
			gotAnswer.setText(bundle.getString("key"));
		}
	}
}