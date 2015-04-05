package com.example.gremnemonics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;
import android.widget.TextView;
import android.widget.Toast;

public class Stats extends Activity implements OnClickListener{
	TabHost thStats;
	TabSpec spec;
	ImageButton ibnReset;
	View view;
	ProgressBar pbAllWords,pb1to100Words,pb100to200Words,pb200to300Words,pb300to400Words,pb400to500Words,pb500to600Words,
	pb600to700Words,pb700to800Words,pb800to900Words,pb900to1000Words,pb1000to1100Words;
	TextView tvAllWordsCount,tv1to100WordsCount,tv100to200WordsCount,tv200to300WordsCount,tv300to400WordsCount,tv400to500WordsCount,tv500to600WordsCount,
	tv600to700WordsCount,tv700to800WordsCount,tv800to900WordsCount,tv900to1000WordsCount,tv1000to1100WordsCount;
	ProgressBar pbNonViewedWords,pbRedWords,pbOrangeRedWords,pbYellowWords,pbLightGreenWords,pbGreenWords;
	TextView tvNonViewedWordsCount,tvRedWordsCount,tvOrangeRedWordsCount,tvYellowWordsCount,tvLightGreenWordsCount,tvGreenWordsCount;
	String ListValue;
	ImageButton ibnRandomize,ibnListIcon,ibnStatsIcon,ibnSettings,ibnHome;
	MnemonicDB db;
	ArrayList<String> nameValues,mainRowNames,mainRowColors;
	int countAllWordsCountr,countNonViewedWordsCountr,count1to400WordsCount,count400to800WordsCount,count800to1200WordsCount,
	count1200to1600WordsCount,count1600to2000WordsCount,count2000to2400WordsCount,count2400to2800WordsCount,count2800to3200WordsCount,
	count3200to3600WordsCount,count3600to4000WordsCount,count4000to4400WordsCount,countRedWordsCount,countOrangeRedWordsCount,countYellowWordsCount,
	countLightGreenWordsCount,countGreenWordsCount;
	SharedPreferences spStats;
	Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.stats);
		Intent intent=new Intent(this, StatLoader.class);
		Bundle bundle=new Bundle();
		bundle.putParcelable("database", db);
		bundle.putStringArrayList("NameValues",nameValues);
		bundle.putStringArrayList("MainRowNames",mainRowNames);
		bundle.putStringArrayList("MainRowColors",mainRowColors);
		intent.putExtras(bundle); 
		startActivityForResult(intent,0);
	}
	private void init() {
		view=new View(this);
		thStats=(TabHost) findViewById(R.id.thStats); 
		thStats.setup();
		spec=thStats.newTabSpec("OrderByList");
		spec.setContent(R.id.tbOrderByList);
		spec.setIndicator("Stats By List");
		thStats.addTab(spec);
		spec=thStats.newTabSpec("OrderByStatus");
		spec.setContent(R.id.tbOrderByStatus);
		spec.setIndicator("Stats By Status");
		thStats.addTab(spec);
		thStats.setOnClickListener(this);	
		ibnReset=(ImageButton) findViewById(R.id.ibnReset);
		ibnHome=(ImageButton) findViewById(R.id.ibnHome);
		ibnHome.setOnClickListener(this);
		ibnReset.setOnClickListener(this);
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		db=new MnemonicDB(getApplicationContext());
		db=(MnemonicDB) bundle.getParcelable("database");
		nameValues=bundle.getStringArrayList("NameValues");
		mainRowNames=bundle.getStringArrayList("MainRowNames");
		mainRowColors=bundle.getStringArrayList("MainRowColors");
		db.setcontext(getApplicationContext());
		tvInit();
		pbInit();
		getDbValues();
	}
	private void getDbValues() {
		tvAllWordsCount.setText(countAllWordsCountr+"/4362");
		System.out.println(countAllWordsCountr+"counteresdf");
		pbAllWords.setProgress(countAllWordsCountr);
		tvNonViewedWordsCount.setText(countNonViewedWordsCountr+"/4362");
		pbNonViewedWords.setProgress(countNonViewedWordsCountr);
		tv1to100WordsCount.setText(count1to400WordsCount+"/400");
		pb1to100Words.setProgress(count1to400WordsCount);
		tv100to200WordsCount.setText(count400to800WordsCount+"/400");
		pb100to200Words.setProgress(count400to800WordsCount);
		tv200to300WordsCount.setText(count800to1200WordsCount+"/400");
		pb200to300Words.setProgress(count800to1200WordsCount);
		tv300to400WordsCount.setText(count1200to1600WordsCount+"/400");
		pb300to400Words.setProgress(count1200to1600WordsCount);
		tv400to500WordsCount.setText(count1600to2000WordsCount+"/400");
		pb400to500Words.setProgress(count1600to2000WordsCount);
		tv500to600WordsCount.setText(count2000to2400WordsCount+"/400");
		pb500to600Words.setProgress(count2000to2400WordsCount);
		tv600to700WordsCount.setText(count2400to2800WordsCount+"/400");
		pb600to700Words.setProgress(count2400to2800WordsCount);
		tv700to800WordsCount.setText(count2800to3200WordsCount+"/400");
		pb700to800Words.setProgress(count2800to3200WordsCount);
		tv800to900WordsCount.setText(count3200to3600WordsCount+"/400");
		pb800to900Words.setProgress(count3200to3600WordsCount);
		tv900to1000WordsCount.setText(count3600to4000WordsCount+"/400");
		pb900to1000Words.setProgress(count3600to4000WordsCount);
		tv1000to1100WordsCount.setText(count4000to4400WordsCount+"/400");
		pb1000to1100Words.setProgress(count4000to4400WordsCount);
		tvRedWordsCount.setText(countRedWordsCount+"/4362");
		pbRedWords.setProgress(countRedWordsCount);
		tvOrangeRedWordsCount.setText(countOrangeRedWordsCount+"/4362");
		pbOrangeRedWords.setProgress(countOrangeRedWordsCount);
		tvYellowWordsCount.setText(countYellowWordsCount+"/4362");
		pbYellowWords.setProgress(countYellowWordsCount);
		tvLightGreenWordsCount.setText(countLightGreenWordsCount+"/4362");
		pbLightGreenWords.setProgress(countLightGreenWordsCount);
		tvGreenWordsCount.setText(countGreenWordsCount+"/4362");
		pbGreenWords.setProgress(countGreenWordsCount);
	}
	private void tvInit() {
		tvAllWordsCount=(TextView) findViewById(R.id.tvAllWordsCount);
		tv1to100WordsCount=(TextView) findViewById(R.id.tv1to100WordsCount);
		tv100to200WordsCount=(TextView) findViewById(R.id.tv100to200WordsCount);
		tv200to300WordsCount=(TextView) findViewById(R.id.tv200to300WordsCount);
		tv300to400WordsCount=(TextView) findViewById(R.id.tv300to400WordsCount);
		tv400to500WordsCount=(TextView) findViewById(R.id.tv400to500WordsCount);
		tv500to600WordsCount=(TextView) findViewById(R.id.tv500to600WordsCount);
		tv600to700WordsCount=(TextView) findViewById(R.id.tv600to700WordsCount);
		tv700to800WordsCount=(TextView) findViewById(R.id.tv700to800WordsCount);
		tv800to900WordsCount=(TextView) findViewById(R.id.tv800to900WordsCount);
		tv900to1000WordsCount=(TextView) findViewById(R.id.tv900to1000WordsCount);
		tv1000to1100WordsCount=(TextView) findViewById(R.id.tv1000to1100WordsCount);

		tvNonViewedWordsCount=(TextView) findViewById(R.id.tvNonviewedWordsCount);
		tvRedWordsCount=(TextView) findViewById(R.id.tvRedviewedWordsCount);
		tvOrangeRedWordsCount=(TextView) findViewById(R.id.tvOrangeRedViewedWordsCount);
		tvYellowWordsCount=(TextView) findViewById(R.id.tvYellowViewedWordsCount);
		tvLightGreenWordsCount=(TextView) findViewById(R.id.tvLightGreenViewedWordsCount);
		tvGreenWordsCount=(TextView) findViewById(R.id.tvGreenViewedWordsCount);
		ibnRandomize=(ImageButton) findViewById(R.id.ibnRandomise);
		ibnListIcon=(ImageButton) findViewById(R.id.ibnListIcon);
		ibnStatsIcon=(ImageButton) findViewById(R.id.ibnStatsIcon);
		ibnSettings=(ImageButton) findViewById(R.id.ibnSettings);
		ibnRandomize.setOnClickListener(this);
		ibnListIcon.setOnClickListener(this);
		ibnStatsIcon.setOnClickListener(this);
		ibnSettings.setOnClickListener(this);
	}
	private void pbInit() {
		pbAllWords=(ProgressBar) findViewById(R.id.pbAllWords);
		pb1to100Words=(ProgressBar) findViewById(R.id.pb1to100Words);
		pb100to200Words=(ProgressBar) findViewById(R.id.pb100to200Words);
		pb200to300Words=(ProgressBar) findViewById(R.id.pb200to300Words);
		pb300to400Words=(ProgressBar) findViewById(R.id.pb300to400Words);
		pb400to500Words=(ProgressBar) findViewById(R.id.pb400to500Words);
		pb500to600Words=(ProgressBar) findViewById(R.id.pb500to600Words);
		pb600to700Words=(ProgressBar) findViewById(R.id.pb600to700Words);
		pb700to800Words=(ProgressBar) findViewById(R.id.pb700to800Words);
		pb800to900Words=(ProgressBar) findViewById(R.id.pb800to900Words);
		pb900to1000Words=(ProgressBar) findViewById(R.id.pb900to1000Words);
		pb1000to1100Words=(ProgressBar) findViewById(R.id.pb1000to1100Words);

		pbNonViewedWords=(ProgressBar) findViewById(R.id.pbNonViewedWords);
		pbRedWords=(ProgressBar) findViewById(R.id.pbRedWords);
		pbOrangeRedWords=(ProgressBar) findViewById(R.id.pbOrangeRedWords);
		pbYellowWords=(ProgressBar) findViewById(R.id.pbYellowWords);
		pbLightGreenWords=(ProgressBar) findViewById(R.id.pbLightGreenWords);
		pbGreenWords=(ProgressBar) findViewById(R.id.pbGreenWords);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.ibnReset:
			AlertDialog.Builder builder=new AlertDialog.Builder(this);
			builder.setPositiveButton("Reset", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "Reset Complete", Toast.LENGTH_SHORT).show();
					tvAllWordsCount.setText("0/4362");
					pbAllWords.setProgress(0);
					tv1to100WordsCount.setText("0/400");
					pb1to100Words.setProgress(0);
					tv100to200WordsCount.setText("0/400");
					pb100to200Words.setProgress(0);
					tv200to300WordsCount.setText("0/400");
					pb200to300Words.setProgress(0);
					tv300to400WordsCount.setText("0/400");
					pb300to400Words.setProgress(0);
					tv400to500WordsCount.setText("0/400");
					pb400to500Words.setProgress(0);
					tv500to600WordsCount.setText("0/400");
					pb500to600Words.setProgress(0);
					tv600to700WordsCount.setText("0/400");
					pb600to700Words.setProgress(0);
					tv700to800WordsCount.setText("0/400");  
					pb700to800Words.setProgress(0);
					tv800to900WordsCount.setText("0/400");
					pb800to900Words.setProgress(0);
					tv900to1000WordsCount.setText("0/400");
					pb900to1000Words.setProgress(0);
					tv1000to1100WordsCount.setText("0/362");
					pb1000to1100Words.setProgress(0);

					tvNonViewedWordsCount.setText("4362/4362");
					pbNonViewedWords.setProgress(4362);
					tvRedWordsCount.setText("0/4362");
					pbRedWords.setProgress(0);
					tvOrangeRedWordsCount.setText("0/4362");
					pbOrangeRedWords.setProgress(0);
					tvYellowWordsCount.setText("0/4362");
					pbYellowWords.setProgress(0);
					tvLightGreenWordsCount.setText("0/4362");
					pbLightGreenWords.setProgress(0);
					tvGreenWordsCount.setText("0/4362");
					pbGreenWords.setProgress(0);
					db.open();
					db.resetColor();
					db.close();  
					view.postInvalidate();
				}
			});
			builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dialog, int which) {
					Toast.makeText(getApplicationContext(), "Reset Cancelled", Toast.LENGTH_SHORT).show();
				}
			});
			AlertDialog dialog=builder.create();
			dialog.setTitle("Reset");
			dialog.setMessage("Do you want to Reset?");
			dialog.show();
			break;
		case R.id.ibnRandomise:
			Random random=new Random();
			int n=random.nextInt(11);
			ArrayList<String> str=new ArrayList<String>();
			db.open();
			str=db.getRowNames((n*400)+1);
			db.close();
			Intent intent=new Intent(this, Display.class);
			Bundle bundle=new Bundle();
			bundle.putParcelable("database", db);
			bundle.putString("Source", "HomeScreen");
			bundle.putStringArrayList("NameValues", str);	
			bundle.putStringArrayList("MainRowNames", mainRowNames);	
			bundle.putStringArrayList("MainRowColors", mainRowColors);	
			intent.putExtras(bundle); 
			startActivity(intent);
			break;
		case R.id.ibnStatsIcon:
			break;
		case R.id.ibnListIcon:
			Intent listIcon=new Intent(this,DisplayItems.class);
			Bundle bundle1=new Bundle();
			bundle1.putParcelable("database", db); 
			bundle1.putStringArrayList("NameValues",this.nameValues);
			bundle1.putStringArrayList("MainRowNames", mainRowNames);	
			bundle1.putStringArrayList("MainRowColors", mainRowColors);	
			listIcon.putExtras(bundle1); 
			startActivity(listIcon);
			break;
		case R.id.ibnSettings:
			Intent settings=new Intent(getApplicationContext(), Settings.class);
			Bundle bundle2=new Bundle();
			bundle2.putParcelable("database", db); 
			bundle2.putStringArrayList("NameValues",this.nameValues);
			bundle2.putStringArrayList("MainRowNames", mainRowNames);	
			bundle2.putStringArrayList("MainRowColors", mainRowColors);	
			settings.putExtras(bundle2); 
			startActivity(settings);
			break;
		case R.id.ibnHome:
			Intent i=new Intent(getApplicationContext(), HomeScreen.class);
			startActivity(i);
			break; 
		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		Bundle bundle=data.getExtras();
		db=bundle.getParcelable("database");
		nameValues=bundle.getStringArrayList("NameValues");
		init();
		countAllWordsCountr=bundle.getInt("countAllWordsCountr");
		countNonViewedWordsCountr=bundle.getInt("countNonViewedWordsCountr");
		count1to400WordsCount=bundle.getInt("count1to100WordsCount");
		count400to800WordsCount=bundle.getInt("count100to200WordsCount");
		count800to1200WordsCount=bundle.getInt("count200to300WordsCount");
		count1200to1600WordsCount=bundle.getInt("count300to400WordsCount");
		count1600to2000WordsCount=bundle.getInt("count400to500WordsCount");
		count2000to2400WordsCount=bundle.getInt("count500to600WordsCount");
		count2400to2800WordsCount=bundle.getInt("count600to700WordsCount");
		count2800to3200WordsCount=bundle.getInt("count700to800WordsCount");
		count3200to3600WordsCount=bundle.getInt("count800to900WordsCount");
		count3600to4000WordsCount=bundle.getInt("count900to1000WordsCount");
		count4000to4400WordsCount=bundle.getInt("count1000to1100WordsCount");

		countRedWordsCount=bundle.getInt("countRedWordsCount");
		countOrangeRedWordsCount=bundle.getInt("countOrangeRedWordsCount");
		countYellowWordsCount=bundle.getInt("countYellowWordsCount");
		countLightGreenWordsCount=bundle.getInt("countLightGreenWordsCount");
		countGreenWordsCount=bundle.getInt("countGreenWordsCount");
		getDbValues();
		view.postInvalidate();
	}

	@Override
	protected void onResume() {
		super.onResume();
		getprefs();
	}
	private void getprefs() {
		spStats=getSharedPreferences("spStatScreen", 0);
		countAllWordsCountr=spStats.getInt("countAllWordsCountr",0 );
		countNonViewedWordsCountr=spStats.getInt("countNonViewedWordsCountr",0);
		count1to400WordsCount=spStats.getInt("count1to100WordsCount",0);
		count400to800WordsCount=spStats.getInt("count100to200WordsCount",0);
		count800to1200WordsCount=spStats.getInt("count200to300WordsCount",0);
		count1200to1600WordsCount=spStats.getInt("count300to400WordsCount",0);
		count1600to2000WordsCount=spStats.getInt("count400to500WordsCount",0);
		count2000to2400WordsCount=spStats.getInt("count500to600WordsCount",0);
		count2400to2800WordsCount=spStats.getInt("count600to700WordsCount",0);
		count2800to3200WordsCount=spStats.getInt("count700to800WordsCount",0);
		count3200to3600WordsCount=spStats.getInt("count800to900WordsCount",0);
		count3600to4000WordsCount=spStats.getInt("count900to1000WordsCount",0);
		count4000to4400WordsCount=spStats.getInt("count1000to1100WordsCount",0);
		countRedWordsCount=spStats.getInt("countRedWordsCount",0);
		countOrangeRedWordsCount=spStats.getInt("countOrangeRedWordsCount",0);
		countYellowWordsCount=	spStats.getInt("countYellowWordsCount",0);
		countLightGreenWordsCount=spStats.getInt("countLightGreenWordsCount",0);
		countGreenWordsCount=spStats.getInt("countGreenWordsCount", 0);
		editor=spStats.edit();

		/*Set<String> set1=new TreeSet<String>();
		set1=spStats.getStringSet("MainRowNames", null);
		if(set1!=null){
			mainRowNames=new ArrayList<String>();
			for (Iterator<String> iterator = set1.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				mainRowNames.add(string);	
			}
		}
		Set<String> set2=new TreeSet<String>();
		set2=spStats.getStringSet("MainRowColors", null);
		if(set2!=null){
			mainRowColors=new ArrayList<String>();
			for (Iterator<String> iterator = set2.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				mainRowColors.add(string);	
			}
		}*/
		Set<String> set1=new TreeSet<String>();
		set1=spStats.getStringSet("MainRowNames", null);
		if(set1!=null){
			mainRowNames=new ArrayList<String>();
			for (Iterator<String> iterator = set1.iterator(); iterator.hasNext();) { 
				String string = (String) iterator.next();
				mainRowNames.add(string);	
			}
		}
		Set<String> set2=new TreeSet<String>();
		set2=spStats.getStringSet("MainRowColors", null);
		if(set2!=null){
			mainRowColors=new ArrayList<String>();
			for (Iterator<String> iterator = set2.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				string=string.substring(0,string.indexOf('z'));
				mainRowColors.add(string);
			}
			if(mainRowColors.size()!=4362){
				mainRowColors=null;
			}
			if(mainRowNames.size()!=4362){
				mainRowColors=null;
			}
		}
	}
	@Override
	protected void onPause() {
		super.onPause();
		saveprefs();
	}
	private void saveprefs() {
		spStats=getSharedPreferences("spStatScreen", 0);
		editor=spStats.edit();
		editor.putInt("countAllWordsCountr", countAllWordsCountr);
		editor.putInt("countNonViewedWordsCountr",countNonViewedWordsCountr);
		editor.putInt("count1to100WordsCount",count1to400WordsCount);
		editor.putInt("count100to200WordsCount",count400to800WordsCount);
		editor.putInt("count200to300WordsCount",count800to1200WordsCount);
		editor.putInt("count300to400WordsCount",count1200to1600WordsCount);
		editor.putInt("count400to500WordsCount",count1600to2000WordsCount);
		editor.putInt("count500to600WordsCount",count2000to2400WordsCount);
		editor.putInt("count600to700WordsCount",count2400to2800WordsCount);
		editor.putInt("count700to800WordsCount",count2800to3200WordsCount);
		editor.putInt("count800to900WordsCount",count3200to3600WordsCount);
		editor.putInt("count900to1000WordsCount",count3600to4000WordsCount);
		editor.putInt("count1000to1100WordsCount",count4000to4400WordsCount);
		editor.putInt("countRedWordsCount",countRedWordsCount);
		editor.putInt("countOrangeRedWordsCount",countOrangeRedWordsCount);
		editor.putInt("countYellowWordsCount",countYellowWordsCount);
		editor.putInt("countLightGreenWordsCount",countLightGreenWordsCount);
		editor.putInt("countGreenWordsCount", countGreenWordsCount);
		/*Set<String> set1=new TreeSet<String>();
		if(mainRowNames!=null){
			for (int i = 0; i < mainRowNames.size(); i++) {
				set1.add(mainRowNames.get(i));
			}
			editor.putStringSet("MainRowNames",set1);}
		else
			editor.putStringSet("MainRowNames",null);
		Set<String> set2=new TreeSet<String>();
		if(mainRowColors!=null){
			for (int i = 0; i < mainRowColors.size(); i++) {
				set2.add(mainRowColors.get(i));
			}
			editor.putStringSet("MainRowColors",set2);}
		else
			editor.putStringSet("MainRowColors",null);
		 */
		Set<String> set1=new TreeSet<String>();
		if(mainRowNames!=null && mainRowNames.size()==4362){
			for (int i = 0; i < mainRowNames.size(); i++) {
				set1.add(mainRowNames.get(i));
			}
			editor.putStringSet("MainRowNames",set1);
		}
		else{
			editor.putStringSet("MainRowNames",null);}
		Set<String> set0=new TreeSet<String>();
		if(mainRowColors!=null && mainRowColors.size()==4362){
			for (int i = 0; i < mainRowColors.size(); i++) {
				set0.add(mainRowColors.get(i)+'z'+i);
			}
			editor.putStringSet("MainRowColors",set0);
		}
		else
		{
			editor.putStringSet("MainRowColors",null);
		}		
		editor.commit();
	}
}
