package com.example.newbostonorg1;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;

public class Tabs extends Activity implements OnClickListener {
	TabHost host;
	TabSpec spec;
	TextView tvTimer;
	long start,stop=0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.tabs);
		Button newTab=(Button) findViewById(R.id.bnAddTab);
		Button startTimer=(Button) findViewById(R.id.bnStartWatch);
		Button stopTimer=(Button) findViewById(R.id.bnStopWatch);
		tvTimer=(TextView) findViewById(R.id.tvTimer);
		newTab.setOnClickListener(this);
		startTimer.setOnClickListener(this);
		stopTimer.setOnClickListener(this);
		host=(TabHost) findViewById(R.id.tabhost); 
		host.setup();
		spec=host.newTabSpec("tabspec");
		spec.setContent(R.id.tab1);
		spec.setIndicator("Stop Watch");
		host.addTab(spec);
		spec=host.newTabSpec("tabspec");
		spec.setContent(R.id.tab2);
		spec.setIndicator("Tab2");
		host.addTab(spec);
		spec=host.newTabSpec("tabspec");
		spec.setContent(R.id.tab3);
		spec.setIndicator("Add a tab");
		host.addTab(spec);

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.bnAddTab:
			spec=host.newTabSpec("tabspec");
			spec.setContent(new TabHost.TabContentFactory() {

				@Override
				public View createTabContent(String tag) {
					// TODO Auto-generated method stub
					TextView textView=new TextView(Tabs.this);
					textView.setText("This is a new Tab");
					return textView;
				}
			}); 

			spec.setIndicator("New Tab");
			host.addTab(spec);  
			break;
		case R.id.bnStartWatch:
			start=System.currentTimeMillis(); 
			/*stop=0;
			float result=-start+stop;
			result=result/100;
			tvTimer.setText(Float.toString(result));*/
			break;
		case R.id.bnStopWatch:
			stop=System.currentTimeMillis();
			if(start!=0){
				long result1=stop-start;
				int millis=(int)result1;
				int seconds=(int)result1/1000;
				int minutes=(int)seconds/60;
				int hours=(int)minutes/60;
				millis=millis%100;
				seconds=seconds%60;
				//tvTimer.setText(hours+':'+minutes+':'+seconds+':'+millis);
				tvTimer.setText(String.format("%d:%02d:%02d", minutes,seconds,millis));

			}
			break;
		}

	}
}
