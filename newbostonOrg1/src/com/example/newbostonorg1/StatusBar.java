package com.example.newbostonorg1;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class StatusBar extends Activity implements OnClickListener {
	NotificationManager notificationManager;
	int uniqueID=123456;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statusbar);
		Button bnStatusBar=(Button) findViewById(R.id.bnStatusBar);
		bnStatusBar.setOnClickListener(this);
		notificationManager=(NotificationManager) getSystemService(NOTIFICATION_SERVICE);
		notificationManager.cancel(uniqueID);
	}

	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View v) {
		Intent intent=new Intent(this,StatusBar.class);
		PendingIntent intent1=PendingIntent.getActivity(this,0, intent, 0);
		String body="Hello New Notification";
		String title="This is title";
		Notification  notification=new Notification(R.drawable.images,body,System.currentTimeMillis());
		notification.setLatestEventInfo(this, title, body,intent1);
		notification.defaults=Notification.DEFAULT_ALL;
		notificationManager.notify(uniqueID, notification);
		finish();
	}
}
