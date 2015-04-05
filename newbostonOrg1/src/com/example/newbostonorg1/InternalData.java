package com.example.newbostonorg1;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.SharedPreferences.Editor;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class InternalData extends Activity implements OnClickListener{
	EditText etShare;
	TextView tvShare;
	Editor editor;
	FileOutputStream fos;
	FileInputStream fis;
	String fileName="InternalString";
	ProgressDialog dialog;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sharedprefs);
		setupVars();
	}

	private void setupVars() {
		Button bnSave=(Button) findViewById(R.id.bnSave);
		Button bnLoad=(Button) findViewById(R.id.bnLoad);
		bnSave.setOnClickListener(this);
		bnLoad.setOnClickListener(this);
		etShare=(EditText) findViewById(R.id.etShare);
		tvShare=(TextView) findViewById(R.id.tvShare);
		try {
			fos=openFileOutput(fileName, Context.MODE_PRIVATE);
			fos.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bnSave:
			String data=etShare.getText().toString();

			try {
				fos=openFileOutput(fileName, 0);
				fos.write(data.getBytes());
				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			/*Saving Data Via File
			File f=new File(fileName);
			try {
				fos=new FileOutputStream(f);

				fos.close();
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} 
			 */			
			break;
		case R.id.bnLoad:
			new loadSomeStuff().execute(fileName);
			break;
		}
	}
	public class loadSomeStuff extends AsyncTask<String,Integer,String>{

		@Override
		protected String doInBackground(String... arg0) {
			String str=null;
			System.out.println("Here");

			for(int i=0;i<20;i++){
				publishProgress(5);
				System.out.println("Here 2");
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			dialog.dismiss();

			try {
				fis=openFileInput(fileName);
				byte[] dataarray=new byte[fis.available()];

				while(fis.read(dataarray)!=-1){
					str=new String(dataarray);			
				}
				fis.close();
				//tvShare.setText(str);
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return str;
		}
		protected void onPreExecute() {
			dialog=new ProgressDialog(InternalData.this);
			dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
			dialog.setMax(100);
			dialog.show();

		}

		protected void onProgressUpdate(Integer... values) {
			// TODO Auto-generated method stub
			dialog.incrementProgressBy(values[0]);

		}


		protected void onPostExecute(String result) {
			tvShare.setText(result);

		}
	}
}
