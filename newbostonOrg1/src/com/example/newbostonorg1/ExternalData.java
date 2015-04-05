package com.example.newbostonorg1;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ExternalData extends Activity implements OnItemSelectedListener, OnClickListener {
	private TextView canWrite,canRead;
	private String state;
	boolean canW,canR;
	Spinner spinner;
	String[] paths={"Music","Pictures","Downloads"};
	File path=null;
	File file=null;
	EditText etSaveFile;
	Button bnConfirm,bnSave;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.externaldata);
		canWrite=(TextView) findViewById(R.id.tvCanWrite);
		canRead=(TextView) findViewById(R.id.tvCanRead);
		state=Environment.getExternalStorageState();
		etSaveFile=(EditText) findViewById(R.id.etSaveAs);
		bnConfirm=(Button) findViewById(R.id.bnConfirm);
		bnSave=(Button) findViewById(R.id.bnSave);
		bnConfirm.setOnClickListener(this);
		bnSave.setOnClickListener(this);
		//checkState();
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,paths);
		spinner=(Spinner) findViewById(R.id.spinner1);
		spinner.setAdapter(adapter);
		spinner.setOnItemSelectedListener(this);

	}

	private void checkState() {
		if(state.equals(Environment.MEDIA_MOUNTED))
		{
			//Read and write
			canWrite.setText("true");
			canRead.setText("true");
			canW=true;
			canR=true;
		}
		else if(state.equals(Environment.MEDIA_MOUNTED_READ_ONLY))
		{
			//Read only
			canWrite.setText("false");
			canRead.setText("true");
			canW=false;
			canR=true;
		}
		else{
			canWrite.setText("false");
			canRead.setText("false");
			canW=false;
			canR=false;
		}
	}

	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		int posn=spinner.getSelectedItemPosition();
		switch (posn) {
		case 0:
			path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_MUSIC);
			break;
		case 1: 
			path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
			break;
		case 2:
			path=Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
			break;		}
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.bnConfirm:
			bnSave.setVisibility(View.VISIBLE);
			break;
		case R.id.bnSave:
			String f=etSaveFile.getText().toString();
			file=new File(path, f+".png");
			checkState();
			if(canW && canR)
			{
				path.mkdirs();
				try {
					InputStream is=getResources().openRawResource(R.drawable.hula_penguin);
					OutputStream os=new FileOutputStream(file);
					byte[] bs=new byte[is.available()];
					is.read(bs);
					os.write(bs);
					is.close();
					os.close();
					Toast.makeText(getBaseContext(), "File has been saved", Toast.LENGTH_SHORT).show();
				}
				catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}

			}
			//MediaScannerConnection connection=
					MediaScannerConnection.scanFile(ExternalData.this, new String[]{file.toString()}, null,new MediaScannerConnection.OnScanCompletedListener() {
				
				@Override
				public void onScanCompleted(String path, Uri uri) {
					Toast.makeText(ExternalData.this, "Scan Complete", Toast.LENGTH_SHORT).show();
				}
			} );
			break;

		}

	}
}
