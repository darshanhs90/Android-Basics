package com.example.newbostonorg1;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class SQLView extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlview);
		TextView tvSQLInfo=(TextView) findViewById(R.id.tvSQLInfo);
		HotorNot hotorNot=new HotorNot(this);
		hotorNot.open();
		String data=hotorNot.getData();
		hotorNot.close();
		tvSQLInfo.setText(data);
	}
}
