package com.example.gremnemonics;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.SearchView.OnQueryTextListener;
import android.widget.Toast;

public class DisplayItems extends Activity implements OnQueryTextListener, OnItemClickListener, OnClickListener{
	String ListValue;
	int rowValue=0;
	String QueryValue;
	//ArrayAdapter<String> adapter;
	customAdapter adapter;
	ListView lvDynamicWords;
	SearchView svWordSearch;
	MnemonicDB db;
	ArrayList<String> nameValues,str1,colors,mainRowNames,mainRowColors;
	ArrayList<String> newNameValues;
	SharedPreferences spDisplayItems;
	ProgressBar pbStatusIndicator;
	ImageButton ibnHome;
	View v;
	String b[];
	ImageButton ibnRandomize,ibnListIcon,ibnStatsIcon,ibnSettings;
	Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.displayitems);
		Intent intent=getIntent();
		v=new View(this);
		Bundle bundle=intent.getExtras();
		db=new MnemonicDB(getApplicationContext());
		db=(MnemonicDB) bundle.getParcelable("database");
		nameValues=bundle.getStringArrayList("NameValues");
		mainRowNames=bundle.getStringArrayList("MainRowNames");
		mainRowColors=bundle.getStringArrayList("MainRowColors");
		//	colors=bundle.getStringArrayList("Colors");
		db.setcontext(getApplicationContext());
		b = new String[nameValues.size()];
		System.out.println(nameValues.size());
		for(int i=0;i<nameValues.size();i++){ 
			b[i]=nameValues.get(i);
		}
		adapter=new customAdapter(this,R.layout.rowlayout, b);
		lvDynamicWords=(ListView) findViewById(R.id.lvDynamicWords);
		lvDynamicWords.setAdapter(adapter);
		svWordSearch=(SearchView) findViewById(R.id.svWordSearch);
		svWordSearch.setOnQueryTextListener(this);
		ibnHome=(ImageButton) findViewById(R.id.ibnHome);
		ibnHome.setOnClickListener(this);
		pbStatusIndicator=(ProgressBar) findViewById(R.id.pbStatusIndicator);
		lvDynamicWords.setOnItemClickListener(this); 
		ibnRandomize=(ImageButton) findViewById(R.id.ibnRandomise);
		ibnListIcon=(ImageButton) findViewById(R.id.ibnListIcon);
		ibnStatsIcon=(ImageButton) findViewById(R.id.ibnStatsIcon);
		ibnSettings=(ImageButton) findViewById(R.id.ibnSettings);
		ibnRandomize.setOnClickListener(this);
		ibnListIcon.setOnClickListener(this);
		ibnStatsIcon.setOnClickListener(this);	
		ibnSettings.setOnClickListener(this);
		newNameValues=new ArrayList<String>();
		lvDynamicWords.smoothScrollToPosition(0);
		//lvDynamicWords.setSmoothScrollbarEnabled(false);
		db.open();
		if(mainRowNames!=null && mainRowNames.size()!=4362){
			str1=db.getRowNames(4362);
		}
		else{
			str1=mainRowNames;
		}
		int a[]=db.getCount();
		int j=a[6]+a[7]+a[8]+a[9]+a[10]+a[11]+a[12]+a[13]+a[14]+a[15]+a[16];
		pbStatusIndicator.setProgress((int)((j)/11));
		if(db.getProgressStat().contentEquals("FALSE"))
			pbStatusIndicator.setVisibility(View.INVISIBLE);
		db.close();
	}
	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent=new Intent(this, Display.class);
		Bundle bundle=new Bundle();
		bundle.putParcelable("database", db);
		String newText;
		db.open();
		if(adapter.getNlist().size()!=0){
			System.out.println("Selected text is "+adapter.getNlist().get(arg2));
			String strin=(String) adapter.getNlist().get(arg2);
			newText=strin;
			System.out.println(newText);
			int rowID=db.getRowId(newText);
			System.out.println("onitemclick row id "+rowID);
			newNameValues=db.getRowNames(getRowValue(rowID));
		}
		else{
			String strin=(String) adapter.getItem(arg2);
			newText=strin;
			System.out.println(newText);
			int rowID=db.getRowId(newText);
			System.out.println("onitemclick row id "+rowID);
			newNameValues=db.getRowNames(getRowValue(rowID));
		}
		db.close();
		bundle.putStringArrayList("NameValues", newNameValues);
		bundle.putString("Source", newText);
		bundle.putStringArrayList("MainRowNames", mainRowNames);
		bundle.putStringArrayList("MainRowColors", mainRowColors);
		intent.putExtras(bundle);
		startActivity(intent);
	}
	@Override
	public boolean onQueryTextChange(String newText) {
		newText=newText.toLowerCase();
		//	customAdapter adapterNew = this.adapter;
		if(newText!=null)//||newText.contentEquals(""))
		{
			adapter.getFilter().filter(newText);
			if(adapter.getNlist().size()>0)
				Toast.makeText(getApplicationContext(), "Scroll up if no word is displayed", Toast.LENGTH_SHORT).show();
			lvDynamicWords.smoothScrollToPosition(0);
		}
		return true;
	}
	@Override
	public boolean onQueryTextSubmit(String query) {
		return true;
	}

	public int getRowValue(int rowID){


		if(rowID>4000){
			rowID=3961;
		}else if(rowID>3600){
			rowID=3600;
		}else if(rowID>3200){
			rowID=3200;
		}else if(rowID>2800){
			rowID=2800;
		}else if(rowID>2400){
			rowID=2400;
		}else if(rowID>2000){
			rowID=2000;
		}else if(rowID>1600){
			rowID=1600;
		}else if(rowID>1200){
			rowID=1200;
		}else if(rowID>800){
			rowID=800;
		}else if(rowID>400){
			rowID=400;
		}  
		else rowID=1;
		return rowID;
	}
	@Override
	protected void onResume() {
		super.onResume();
		adapter=new customAdapter(this,R.layout.rowlayout, b);
		lvDynamicWords.setAdapter(adapter);
		getprefs();
	}
	@Override
	protected void onPause() { 
		super.onPause();
		saveprefs();
	}
	private void saveprefs() {
		spDisplayItems=getSharedPreferences("spDispItemsScreen", 0);
		editor=spDisplayItems.edit(); 
		Set<String> set=new TreeSet<String>();
		if(nameValues!=null){
			for (int i = 0; i < nameValues.size(); i++) {
				set.add(nameValues.get(i));
			}
			editor.putStringSet("Stringset",set);}
		else
			editor.putStringSet("Stringset",null);
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
	private void getprefs() {
		spDisplayItems=getSharedPreferences("spDispItemsScreen", 0);
		editor=spDisplayItems.edit();
		Set<String> set=new TreeSet<String>();
		set=spDisplayItems.getStringSet("StringSet", null);
		if(set!=null){
			nameValues=new ArrayList<String>();
			for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				nameValues.add(string);	
			}
		}
		/*Set<String> set1=new TreeSet<String>();
		set1=spDisplayItems.getStringSet("MainRowNames", null);
		if(set1!=null){
			mainRowNames=new ArrayList<String>();
			for (Iterator<String> iterator = set1.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				mainRowNames.add(string);	
			}
		}
		Set<String> set2=new TreeSet<String>();
		set2=spDisplayItems.getStringSet("MainRowColors", null);
		if(set2!=null){
			mainRowColors=new ArrayList<String>();
			for (Iterator<String> iterator = set2.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				mainRowColors.add(string);	
			}
		 */
		Set<String> set1=new TreeSet<String>();
		set1=spDisplayItems.getStringSet("MainRowNames", null);
		if(set1!=null){
			mainRowNames=new ArrayList<String>();
			for (Iterator<String> iterator = set1.iterator(); iterator.hasNext();) { 
				String string = (String) iterator.next();
				mainRowNames.add(string);	
			}
		}
		Set<String> set2=new TreeSet<String>();
		set2=spDisplayItems.getStringSet("MainRowColors", null);
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
	public void onClick(View v) {
		switch (v.getId()) {

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
		case R.id.ibnListIcon:
			Intent listIcon=new Intent(this,DisplayItems.class);
			Bundle bundle1=new Bundle();
			bundle1.putParcelable("database", db);
			bundle1.putStringArrayList("NameValues",this.str1);
			bundle1.putStringArrayList("MainRowNames", mainRowNames);	
			bundle1.putStringArrayList("MainRowColors", mainRowColors);	
			listIcon.putExtras(bundle1); 
			startActivity(listIcon);
			break;
		case R.id.ibnStatsIcon:
			Intent stats=new Intent(getApplicationContext(), Stats.class);
			Bundle bundle2=new Bundle();
			bundle2.putParcelable("database", db);
			bundle2.putStringArrayList("NameValues", str1);
			bundle2.putStringArrayList("MainRowNames", mainRowNames);	
			bundle2.putStringArrayList("MainRowColors", mainRowColors);	
			stats.putExtras(bundle2);
			startActivity(stats);
			break;
		case R.id.ibnSettings:
			Intent settings=new Intent(getApplicationContext(), Settings.class);
			Bundle bundle3=new Bundle();
			bundle3.putParcelable("database", db);
			bundle3.putStringArrayList("NameValues", str1);
			bundle3.putStringArrayList("MainRowNames", mainRowNames);	
			bundle3.putStringArrayList("MainRowColors", mainRowColors);	
			settings.putExtras(bundle3);
			startActivity(settings);
			break;
		case R.id.ibnHome:
			Intent inten=new Intent(getApplicationContext(), HomeScreen.class);
			startActivity(inten);
			break;
		}
	}
}
