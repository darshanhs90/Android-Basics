package com.example.gremnemonics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.telephony.TelephonyManager;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

public class HomeScreen extends Activity implements OnClickListener,OnItemClickListener {
	ImageButton ibnRandomize,ibnListIcon,ibnStatsIcon,ibnSettings,ibnLogo;
	LinearLayout llhomeScreen;
	String ListValue;
	MnemonicDB mnemonicDB;
	ProgressBar pbStatusIndicator;
	ArrayAdapter<String> adapter;
	ListView lvCategories;
	ArrayList<String> str1,strnew;
	ArrayList<String> mainRowNames,mainRowColors;
	SharedPreferences spHomeScreen;
	Editor editor;
	String classes[]={"1-400","401-800","801-1200","1201-1600",
			"1601-2000","2001-2400","2401-2800","2801-3200","3201-3600","3601-4000","4001-4400"};
	@Override 
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homescreen);
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		//System.out.println("ID is      " +telephonyManager.getDeviceId());
		llhomeScreen=(LinearLayout) findViewById(R.id.llhomeScreen);
		getIntentDetails();
		mnemonicDB=new MnemonicDB(getApplicationContext());
		mnemonicDB.open();

		//mnemonicDB.emptyDB(); 
		if(mnemonicDB.getDBStat()==null){
			mnemonicDB.deletedb();
			startActivityForResult((new Intent(this, DBCreator.class)),0);
			mnemonicDB.close();
		}
		else{
			str1=new ArrayList<String>();
			init();  
			mnemonicDB.close();
		}
	}
	private void getIntentDetails() {
		mnemonicDB=new MnemonicDB(getApplicationContext());
		mnemonicDB.setcontext(getApplicationContext());
	}
	private void init() {
		mnemonicDB.open();
		TelephonyManager telephonyManager = (TelephonyManager)getSystemService(Context.TELEPHONY_SERVICE);
		//System.out.println("ID is      " +telephonyManager.getDeviceId());

		Boolean bl=mnemonicDB.setIMEI(telephonyManager.getDeviceId());
		//bl=mnemonicDB.setIMEI("123455");

		mnemonicDB.close();
		if(bl==true){
			ibnRandomize=(ImageButton) findViewById(R.id.ibnRandomise);
			ibnListIcon=(ImageButton) findViewById(R.id.ibnListIcon);
			ibnStatsIcon=(ImageButton) findViewById(R.id.ibnStatsIcon);
			ibnSettings=(ImageButton) findViewById(R.id.ibnSettings);
			ibnLogo=(ImageButton) findViewById(R.id.ibnLogo);
			ibnLogo.setOnClickListener(this);
			ibnRandomize.setOnClickListener(this);
			ibnListIcon.setOnClickListener(this);
			ibnStatsIcon.setOnClickListener(this);	
			ibnSettings.setOnClickListener(this);
			adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,classes);
			lvCategories=(ListView) findViewById(R.id.lvCategories);
			lvCategories.setAdapter(adapter);	
			lvCategories.setOnItemClickListener(this);
			pbStatusIndicator=(ProgressBar) findViewById(R.id.pbStatusIndicator);
			mnemonicDB.open();
			//System.out.println("working?");
			Intent intent=getIntent();
			this.getprefs();
			Bundle bundle=intent.getExtras();
			if(bundle!=null){
				mainRowNames=bundle.getStringArrayList("MainRowNames");
				mainRowColors=bundle.getStringArrayList("MainRowColors");
			}
			else{
				//System.out.println("Bundle Null");
			}
			if(mainRowNames==null||mainRowColors==null || mainRowNames.size()!=4362||mainRowColors.size()!=4362)
			{
				//System.out.println("Mainrownames null");
				if(str1.size()!=4362||strnew.size()!=4362){
					System.out.println("from DB taxing");
					str1=mnemonicDB.getRowNames(4362);
					strnew=mnemonicDB.getColors(4362);
					mainRowColors=strnew;
					mainRowNames=str1;
				}
			}
			else{
				str1=mainRowNames;
				strnew=mainRowColors;
			}
			System.out.println("NAmes"+mainRowNames.size()+"Colors"+mainRowColors.size());
			int a[]=mnemonicDB.getCount();
			//System.out.println(str1.get(977));
			//System.out.println("YES");
			int j=a[6]+a[7]+a[8]+a[9]+a[10]+a[11]+a[12]+a[13]+a[14]+a[15]+a[16];
			pbStatusIndicator.setProgress((int)((j)/11));
			if(mnemonicDB.getProgressStat().contentEquals("FALSE"))
				pbStatusIndicator.setVisibility(View.INVISIBLE); 
			mnemonicDB.close();
			saveprefs();
		}
		else{
			llhomeScreen.setOnClickListener(this);
			llhomeScreen.removeAllViewsInLayout();
		}
	}

	@Override  
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.llhomeScreen:
			Toast.makeText(getApplicationContext(),"Avoid Piracy,Support Developers", Toast.LENGTH_LONG).show();
			break;
		case R.id.ibnRandomise:
			Random random=new Random();
			int n=random.nextInt(11);
			ArrayList<String> str=new ArrayList<String>();
			ArrayList<String> str6=new ArrayList<String>();

			if(n!=10){
				mnemonicDB.open(); 
				//System.out.println(n);
				if(mainRowNames!=null && mainRowNames.size()!=0){
					if(mainRowColors!=null && mainRowColors.size()!=0){
						str=new ArrayList<String>();
						str6=new ArrayList<String>();
						for (int i = n*400; i < (n*400)+401; i++) {
							//System.out.println(i+"  "+mainRowNames.size()+"     "+mainRowColors.size());
							str.add(mainRowNames.get(i));
							str6.add(mainRowColors.get(i));
						}					}
					else{
						str=mnemonicDB.getRowNames((n*400)+1);	
						str6=mnemonicDB.getColors((n*400)+1);	
					}
				}
				mnemonicDB.close();}
			else{
				str=str1;
				str6=strnew;
			}
			Intent intent=new Intent(this, Display.class);
			Bundle bundle=new Bundle();
			bundle.putParcelable("database", mnemonicDB);
			bundle.putString("Source", "HomeScreen");
			bundle.putStringArrayList("NameValues", str);	
			bundle.putStringArrayList("Colors", str6);	
			bundle.putStringArrayList("MainRowNames", mainRowNames);	
			bundle.putStringArrayList("MainRowColors", mainRowColors);	
			intent.putExtras(bundle); 
			startActivity(intent);
			break;
		case R.id.ibnListIcon:
			Intent listIcon=new Intent(this,DisplayItems.class);
			Bundle bundle1=new Bundle();
			bundle1.putParcelable("database", mnemonicDB);
			bundle1.putStringArrayList("NameValues",this.mainRowNames);
			bundle1.putStringArrayList("Colors",this.mainRowColors);
			bundle1.putStringArrayList("MainRowNames", mainRowNames);	
			bundle1.putStringArrayList("MainRowColors", mainRowColors);	
			listIcon.putExtras(bundle1); 
			startActivity(listIcon);
			break;
		case R.id.ibnStatsIcon:
			Intent stats=new Intent(getApplicationContext(), Stats.class);
			Bundle bundle2=new Bundle();
			bundle2.putParcelable("database", mnemonicDB);
			bundle2.putStringArrayList("NameValues", str1);
			bundle2.putStringArrayList("MainRowNames", mainRowNames);	
			bundle2.putStringArrayList("MainRowColors", mainRowColors);	
			stats.putExtras(bundle2);
			startActivity(stats);
			break;
		case R.id.ibnSettings:
			Intent settings=new Intent(getApplicationContext(), Settings.class);
			Bundle bundle3=new Bundle();
			bundle3.putParcelable("database", mnemonicDB);
			bundle3.putStringArrayList("NameValues", str1);
			bundle3.putStringArrayList("MainRowNames", mainRowNames);	
			bundle3.putStringArrayList("MainRowColors", mainRowColors);	
			settings.putExtras(bundle3);
			startActivity(settings);
			break;
		case R.id.ibnLogo:
			Dialog dialog=new Dialog(this);
			dialog.setTitle("Developer Info");
			dialog.setCancelable(true);
			dialog.setContentView(R.layout.developerinfo);
			dialog.show(); 
			dialog.setCanceledOnTouchOutside(true);
			break;
		}
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
		ArrayList<String> str2=new ArrayList<String>();	
		ArrayList<String> str3=new ArrayList<String>();	

		mnemonicDB.open();
		if(position!=0)
		{	
			System.out.println("Postition is"+position+"**400="+position*400);
			if(mainRowNames!=null && mainRowNames.size()!=0){
				if(mainRowColors!=null && mainRowColors.size()!=0){
					if(position!=10){
						/*List<String> lst1=mainRowNames.subList((position*400)+1,position+800);
						str2=(ArrayList<String>) lst1;
						//str2=(ArrayList<String>) str2;
						System.out.println("two");
						List<String> lst2=mainRowColors.subList((position*400)+1,position+800);
						str3=(ArrayList<String>) lst2;		*/	
						//System.out.println(position*400);
						//System.out.println("  "+mainRowNames.size()+"     "+mainRowColors.size());
						//System.out.println(mainRowNames.get(400));
						//System.out.println(mainRowColors.get(400));
						//System.out.println(mainRowNames.get(mainRowNames.size()-3));
						str2=new ArrayList<String>();
						str3=new ArrayList<String>();
						for (int i = position*400; i < (position*400)+401; i++) {
							//System.out.println(i+"  "+mainRowNames.size()+"     "+mainRowColors.size());
							str2.add(mainRowNames.get(i));
							str3.add(mainRowColors.get(i));
						}
					}
					else{
						str2=new ArrayList<String>();
						str3=new ArrayList<String>();
						for (int i = mainRowNames.size()-400; i < mainRowNames.size(); i++) {
							//System.out.println(i+"  "+mainRowNames.size()+"     "+mainRowColors.size());
							str2.add(mainRowNames.get(i));
							str3.add(mainRowColors.get(i));
						}
					}	
				}
			}
			else{
				str2=mnemonicDB.getRowNames((position*400)+1);
				str3=mnemonicDB.getColors((position*400)+1);
			}
		}
		else
		{
			if(mainRowNames!=null && mainRowNames.size()!=0){
				if(mainRowColors!=null && mainRowColors.size()!=0){
					//System.out.println(mainRowNames.size()+"      "+mainRowColors);
					str2=new ArrayList<String>();
					str3=new ArrayList<String>();
					for (int i = 0; i < 401; i++) {
						//System.out.println(i+"  "+mainRowNames.size()+"     "+mainRowColors.size());
						str2.add(mainRowNames.get(i));
						str3.add(mainRowColors.get(i));
					}		
				}
			}
			else{
				str2=mnemonicDB.getRowNames(1);
				str3=mnemonicDB.getColors(1);
			}
		}
		mnemonicDB.close();
		Intent intent=new Intent(this, DisplayItems.class);
		Bundle bundle2=new Bundle();
		bundle2.putParcelable("database", mnemonicDB);
		bundle2.putString("Source", "HomeScreen");
		bundle2.putStringArrayList("NameValues", str2);
		bundle2.putStringArrayList("Colors", str3);
		bundle2.putStringArrayList("MainRowNames", mainRowNames);	
		bundle2.putStringArrayList("MainRowColors", mainRowColors);	
		intent.putExtras(bundle2);
		startActivity(intent);
	}
	@Override
	protected void onPause() {
		super.onPause();
		saveprefs();
	}
	private void saveprefs() {
		System.out.println("In save prefs");
		spHomeScreen=getSharedPreferences("spHomeScreen", 0);
		editor=spHomeScreen.edit(); 
		Set<String> set=new TreeSet<String>();
		Set<String> set2=new TreeSet<String>();

		if(str1!=null){
			for (int i = 0; i < str1.size(); i++) {
				set.add(str1.get(i));
			}
			for (int i = 0; i < strnew.size(); i++) {
				set2.add(strnew.get(i));
			}
			editor.putStringSet("StringSet",set);
			editor.putStringSet("StringSetnew",set2);
		}
		else{
			editor.putStringSet("StringSet",null);
			editor.putStringSet("StringSetnew",null);
		}
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

	@Override
	protected void onResume() {
		super.onResume();
		getprefs();
	}

	private void getprefs() {
		System.out.println("In Get prefs");
		spHomeScreen=getSharedPreferences("spHomeScreen", 0);
		editor=spHomeScreen.edit();
		Set<String> set=new TreeSet<String>();
		Set<String> setnew=new TreeSet<String>();
		set=spHomeScreen.getStringSet("StringSet", null);
		setnew=spHomeScreen.getStringSet("StringSetnew", null);

		if(set!=null){
			str1=new ArrayList<String>();
			for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				str1.add(string);	
			}
		}
		if(setnew!=null){
			strnew=new ArrayList<String>();
			for (Iterator<String> iterator = setnew.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				strnew.add(string);	
			}
		}

		Set<String> set1=new TreeSet<String>();
		set1=spHomeScreen.getStringSet("MainRowNames", null);
		if(set1!=null){
			mainRowNames=new ArrayList<String>();
			for (Iterator<String> iterator = set1.iterator(); iterator.hasNext();) { 
				String string = (String) iterator.next();
				mainRowNames.add(string);	
			}
		}
		Set<String> set2=new TreeSet<String>();
		set2=spHomeScreen.getStringSet("MainRowColors", null);
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
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		str1=new ArrayList<String>();
		init();
	}
}