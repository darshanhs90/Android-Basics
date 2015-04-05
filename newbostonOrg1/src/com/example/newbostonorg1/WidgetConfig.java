package com.example.newbostonorg1;

import android.app.Activity;
import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RemoteViews;


public class WidgetConfig extends Activity implements OnClickListener{
	EditText etWidgetConfig;
	AppWidgetManager aWM;
	Context c;
	int aWID;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widgetconfig);
		etWidgetConfig=(EditText) findViewById(R.id.etWidgetConfig);
		Button bnWidgetConfig=(Button) findViewById(R.id.bnWidgetConfig);
		bnWidgetConfig.setOnClickListener(this);
		c=this;
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		if(bundle!=null){
			aWID=bundle.getInt(aWM.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		else{
			finish();	
		}
		aWM=AppWidgetManager.getInstance(this);
	}

	@Override
	public void onClick(View v) {
		String e=etWidgetConfig.getText().toString();
		RemoteViews views=new RemoteViews(c.getPackageName(),R.layout.widget);
		views.setTextViewText(R.id.tvConfigInput,e);
		Intent intent=new Intent(this, Splash.class);
		PendingIntent pendingIntent=PendingIntent.getActivity(c, 0, intent, 0);
		views.setOnClickPendingIntent(R.id.bnWidgetOpen, pendingIntent);
		
		Intent reIntent=new Intent();
		reIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, aWID);
		setResult(RESULT_OK,reIntent);
		
		aWM.updateAppWidget(aWID, views);
		finish();
	}
}
