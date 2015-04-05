package com.example.newbostonorg1;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class SQLLiteExample extends Activity implements OnClickListener {
	EditText etName,etHotness,etRowId;
	TextView tvName,tvHotness;
	Button bnUpdateSQLDB,bnViewData,bnGetInformation,bnEditEntry,bnDeleteEntry;
	HotorNot hotorNot;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sqlliteexample);
		setupvars();
	}

	private void setupvars() {
		etName=(EditText) findViewById(R.id.etName);
		etHotness=(EditText) findViewById(R.id.etHotness);
		tvName=(TextView) findViewById(R.id.tvName);
		tvHotness=(TextView) findViewById(R.id.tvHotness);
		bnUpdateSQLDB=(Button) findViewById(R.id.bnUpdateSQLDB);
		bnViewData=(Button) findViewById(R.id.bnViewdata);
		bnUpdateSQLDB.setOnClickListener(this);
		bnViewData.setOnClickListener(this);
		hotorNot=new HotorNot(SQLLiteExample.this);
		etRowId=(EditText) findViewById(R.id.etRowId);
		bnGetInformation=(Button) findViewById(R.id.bnGetInformation);
		bnEditEntry=(Button) findViewById(R.id.bnEditEntry);
		bnDeleteEntry=(Button) findViewById(R.id.bnDeleteEntry);
		bnGetInformation.setOnClickListener(this);
		bnEditEntry.setOnClickListener(this);
		bnDeleteEntry.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bnUpdateSQLDB:
			Boolean didItWork=true;
			String name=etName.getText().toString();
			String hotness=etHotness.getText().toString();
			try{
				hotorNot.open();
				hotorNot.createEntry(name,hotness);
				hotorNot.close();
			}catch(Exception e){
				dialog(e);
			}finally{
				Dialog dialog=new Dialog(SQLLiteExample.this);
				if(didItWork){
					dialog.setTitle("DB Worked");
					TextView textView=new TextView(SQLLiteExample.this);
					textView.setText("Success");
					dialog.setContentView(textView);
					dialog.show();  

				}
			}
			break;
		case R.id.bnViewdata:
			Intent intent=new Intent(this,SQLView.class);
			startActivity(intent);
			break;
		case R.id.bnGetInformation:
			try{
				String rowId=etRowId.getText().toString();
				long l=Long.parseLong(rowId);
				hotorNot.open();
				String returnedName=hotorNot.getName(l);
				String returnedHotness=hotorNot.getHotness(l);
				etName.setText(returnedName);
				etHotness.setText(returnedHotness);
				hotorNot.close();
			}catch(Exception e){
				dialog(e);
			}
			break;
		case R.id.bnEditEntry:
			try{
				hotorNot.open();
				String MRowId=etRowId.getText().toString();
				long mL=Long.parseLong(MRowId);
				String mName=etName.getText().toString();
				String mHotness=etHotness.getText().toString();
				hotorNot.updateEntry(mL,mName, mHotness);
				hotorNot.close();
			}catch(Exception e){
				dialog(e);
			}
			break;
		case R.id.bnDeleteEntry:
			try{
				hotorNot.open();
				String dRowId=etRowId.getText().toString();
				long dL=Long.parseLong(dRowId);
				hotorNot.deleteEntry(dL);
				hotorNot.close();
			}catch(Exception e){
				dialog(e);
			}
			break;
		}

	}

	private void dialog(Exception e) {
		Boolean didItWork;
		didItWork=false;
		Dialog dialog=new Dialog(SQLLiteExample.this);
		dialog.setTitle("DB Didn't Work");
		TextView textView=new TextView(SQLLiteExample.this);
		textView.setText(e.toString());
		dialog.setContentView(textView);
		dialog.show();
	}
}
