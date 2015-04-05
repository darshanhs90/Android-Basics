package com.example.gremnemonics;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.Toast;

public class Survey extends Activity implements OnClickListener {
	Intent intent;
	ImageButton ibnSubmitSurvey;
	RatingBar rbReview;
	String emailBody;
	EditText etName,etComments;
	Spinner spEducationalStatus;
	ArrayAdapter<String> dropDownAdapter;
	MnemonicDB db;
	String status[]={"Select the Educational Status","Student-High School","Student-College","Student-Engineering","Working Professional","Student-Other"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.survey);
		Intent i=getIntent();
		Bundle bundle=i.getExtras();
		db=bundle.getParcelable("database");
		db.setcontext(getApplicationContext());
		init();
	}

	private void init() {
		ibnSubmitSurvey=(ImageButton) findViewById(R.id.ibnSubmitSurvey);
		ibnSubmitSurvey.setOnClickListener(this);
		intent=getIntent();      
		dropDownAdapter=new ArrayAdapter<String>(getApplicationContext(), R.layout.dropdownlayout,status);
		spEducationalStatus=(Spinner) findViewById(R.id.spEducationalStatus);
		spEducationalStatus.setAdapter(dropDownAdapter);
		rbReview=(RatingBar) findViewById(R.id.rbReview);
		etName=(EditText) findViewById(R.id.etName);
		etComments=(EditText) findViewById(R.id.etComments);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.ibnSubmitSurvey:
			if(!etName.getText().toString().contentEquals("")&&
					!etComments.getText().toString().contentEquals("")
					&&rbReview.getRating()!=0
					&&!spEducationalStatus.getSelectedItem().toString().contentEquals("Select the Educational Status")){
				AlertDialog.Builder builder=new AlertDialog.Builder(this);
				builder.setPositiveButton("Submit", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Intent intent = new Intent(Intent.ACTION_SEND);
						db.open();
						TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
						String telId=telephonyManager.getDeviceId();
						String newTelId="7652"+telId.substring(0,4)+"6342"+telId.substring(4,8)+"9231"+telId.substring(8,12)+"7462"
								+telId.substring(12);
						emailBody="ID is :"+newTelId+"\n"+
								" Name : "+etName.getText()+"\n"+
								" Educational Status : "+status[spEducationalStatus.getSelectedItemPosition()]+"\n"+
								" Rating is : "+rbReview.getProgress()+"\n"+
								" Comments : "+etComments.getText()+"\n"+
								" Unique Id is :" +db.getUID();
						db.close();
						intent.setType("text/html");
						intent.putExtra(Intent.EXTRA_EMAIL, new String[]{"hsdars@gmail.com"});
						intent.putExtra(Intent.EXTRA_SUBJECT, "Application Rating/Opinion ");
						intent.putExtra(Intent.EXTRA_TEXT, emailBody);
						db.open(); 
						db.resetSurvey("TRUE");
						db.close();
						startActivity(Intent.createChooser(intent, "Send Email"));
					}
				});
				builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {

					@Override
					public void onClick(DialogInterface dialog, int which) {
						Toast.makeText(getApplicationContext(), "Kindly Complete the Survey", Toast.LENGTH_SHORT).show();
					}
				});
				AlertDialog dialog=builder.create();
				dialog.setTitle("Submit Survey");
				dialog.setMessage("Continue Submitting the survey?");
				dialog.show();
			}
			else{
				Toast.makeText(getApplicationContext(), "Required Field Missing", Toast.LENGTH_SHORT).show();
			}
			break;
		}
	}
}
