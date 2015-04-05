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
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class Display extends Activity implements OnClickListener {
	ViewFlipper flipper;   
	int clickCount=0;
	ImageButton ibnBackward,ibnForward,ibnHomeScreen,ibnClickAnswer,ibnNoIdea,ibnFair,ibnGood,ibnVeryGood,ibnExcellent,ibnClickMnemonic,ibnClickWord;
	TextView tvWordName,tvWordMeaning,tvWordMnemonic,tvWordSentenceList,tvWordSentenceText,tvWordSynonymList,tvWordSynonymText,tvMeaningName,tvMnemonicName;
	LinearLayout lvWordSentence,lvWordSynonym;
	String color,wordMeaning,wordMnemonic,wordName;
	View view;
	ProgressBar pbStatusIndicator;
	ArrayList<String> nameValues=new ArrayList<String>();
	ArrayList<String> mainRowNames=new ArrayList<String>();
	ArrayList<String> mainRowColors=new ArrayList<String>();
	MnemonicDB db;
	int rowcounter;
	Random random;
	private SharedPreferences spHomeScreen;
	private Editor editor;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.display);
		init();
		random=new Random();
		Intent intent=getIntent();
		Bundle bundle=intent.getExtras();
		db=new MnemonicDB(getApplicationContext());
		db=(MnemonicDB) bundle.getParcelable("database");
		nameValues=bundle.getStringArrayList("NameValues");
		mainRowNames=bundle.getStringArrayList("MainRowNames");
		mainRowColors=bundle.getStringArrayList("MainRowColors");
		db.setcontext(getApplicationContext());
		db.open();
		int a[]=db.getCount();
		int j=a[6]+a[7]+a[8]+a[9]+a[10]+a[11]+a[12]+a[13]+a[14]+a[15]+a[16];
		pbStatusIndicator.setProgress((int)((j)/11));
		if(db.getProgressStat().contentEquals("FALSE"))
			pbStatusIndicator.setVisibility(View.INVISIBLE);
		if(bundle.getString("Source").contentEquals("HomeScreen")){
			rowcounter=0;
			color=db.getColor(nameValues.get(rowcounter));
			wordMeaning=db.getMeaning(nameValues.get(rowcounter));
			System.out.println("name values"+nameValues.size());
			System.out.println(wordMeaning);
			wordMnemonic=db.getMnemonic(nameValues.get(rowcounter));
			db.close();
			colorSetter();
			tvWordName.setText(nameValues.get(rowcounter));
			wordName=nameValues.get(rowcounter);
		}
		else{
			color=db.getColor((String)bundle.getString("Source"));
			wordMeaning=db.getMeaning((String)bundle.getString("Source"));
			wordMnemonic=db.getMnemonic((String)bundle.getString("Source"));
			db.close();
			colorSetter();
			tvWordName.setText((String)bundle.getString("Source"));
			wordName=(String)bundle.getString("Source");
		}
		view=new View(this);
	}
	private void colorSetter() {
		if(color.contentEquals("RED")){
			ibnClickAnswer.setBackgroundResource(R.drawable.red_meaning);
			ibnClickMnemonic.setBackgroundResource(R.drawable.red_mnemonic);
			ibnClickWord.setBackgroundResource(R.drawable.red_word);	
		}
		else if(color.contentEquals("ORANGERED")){
			ibnClickAnswer.setBackgroundResource(R.drawable.orangered_meaning);
			ibnClickMnemonic.setBackgroundResource(R.drawable.orangered_mnemonic);
			ibnClickWord.setBackgroundResource(R.drawable.orangered_word);
		}
		else if(color.contentEquals("YELLOW")){
			ibnClickAnswer.setBackgroundResource(R.drawable.yellow_meaning);
			ibnClickMnemonic.setBackgroundResource(R.drawable.yellow_mnemonic);
			ibnClickWord.setBackgroundResource(R.drawable.yellow_word);
		}
		else if(color.contentEquals("GREENYELLOW")){
			ibnClickAnswer.setBackgroundResource(R.drawable.lightgreen_meaning);
			ibnClickMnemonic.setBackgroundResource(R.drawable.lightgreen_mnemonic);
			ibnClickWord.setBackgroundResource(R.drawable.lightgreen_word);
		}
		else if(color.contentEquals("GREEN")){
			ibnClickAnswer.setBackgroundResource(R.drawable.green_meaning);
			ibnClickMnemonic.setBackgroundResource(R.drawable.green_mnemonic);
			ibnClickWord.setBackgroundResource(R.drawable.green_word);
		}
		else{
			ibnClickAnswer.setBackgroundResource(R.drawable.blueellipseicon_meaning);
			ibnClickMnemonic.setBackgroundResource(R.drawable.blueellipseicon_mnemonic);
			ibnClickWord.setBackgroundResource(R.drawable.blueellipseicon_word);
		}
	}
	private void init() {
		flipper=(ViewFlipper) findViewById(R.id.vFToggler);
		flipper.setOnClickListener(this);
		pbStatusIndicator=(ProgressBar) findViewById(R.id.pbStatusIndicator);
		ibnBackward=(ImageButton) findViewById(R.id.ibnBackward);
		ibnForward=(ImageButton) findViewById(R.id.ibnForward);
		ibnBackward.setOnClickListener(this);
		ibnForward.setOnClickListener(this);
		ibnClickAnswer=(ImageButton) findViewById(R.id.ibnClickAnswer);
		ibnNoIdea=(ImageButton) findViewById(R.id.ibnNoIdea);
		ibnFair=(ImageButton) findViewById(R.id.ibnFair);
		ibnGood=(ImageButton) findViewById(R.id.ibnGood);
		ibnVeryGood=(ImageButton) findViewById(R.id.ibnVeryGood);
		ibnExcellent=(ImageButton) findViewById(R.id.ibnExcellent);
		ibnClickMnemonic=(ImageButton) findViewById(R.id.ibnClickMnemonic);
		ibnClickWord=(ImageButton) findViewById(R.id.ibnClickWord);
		ibnHomeScreen=(ImageButton) findViewById(R.id.ibnHomeScreen);
		tvMeaningName=(TextView) findViewById(R.id.tvMeaningName);
		tvMnemonicName=(TextView) findViewById(R.id.tvMnemonicName);
		ibnHomeScreen.setOnClickListener(this);
		ibnClickMnemonic.setOnClickListener(this);
		ibnClickWord.setOnClickListener(this);
		ibnClickAnswer.setOnClickListener(this);
		ibnNoIdea.setOnClickListener(this);
		ibnFair.setOnClickListener(this);
		ibnGood.setOnClickListener(this);
		ibnVeryGood.setOnClickListener(this);
		ibnExcellent.setOnClickListener(this);
		tvWordName=(TextView) findViewById(R.id.tvWordName);
		tvWordMeaning=(TextView) findViewById(R.id.tvWordMeaning);
		tvWordMnemonic=(TextView) findViewById(R.id.tvWordMnemonic);
		tvWordSentenceList=(TextView) findViewById(R.id.tvWordSentenceList);
		tvWordSynonymList=(TextView) findViewById(R.id.tvWordSynonymList);
		lvWordSynonym=(LinearLayout) findViewById(R.id.lvWordSynonym);
		lvWordSentence=(LinearLayout) findViewById(R.id.lvWordSentence);
	}
	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case (R.id.vFToggler):
			countCheck();
		break;
		case R.id.ibnClickAnswer:
			countCheck();
			view.postInvalidate();
			break;
		case R.id.ibnClickMnemonic:
			countCheck();
			view.postInvalidate();
			break;
		case R.id.ibnClickWord:
			countCheck();
			view.postInvalidate();
			break;
		case R.id.ibnNoIdea:
			ibnClickAnswer.setBackgroundResource(R.drawable.red_meaning);
			ibnClickMnemonic.setBackgroundResource(R.drawable.red_mnemonic);
			ibnClickWord.setBackgroundResource(R.drawable.red_word);
			view.postInvalidate();
			db.open();
			db.updateColor(nameValues.get(rowcounter), "RED");
			db.close();
			break;
		case R.id.ibnFair:
			ibnClickAnswer.setBackgroundResource(R.drawable.orangered_meaning);
			ibnClickMnemonic.setBackgroundResource(R.drawable.orangered_mnemonic);
			ibnClickWord.setBackgroundResource(R.drawable.orangered_word);
			view.postInvalidate();
			db.open();
			db.updateColor(nameValues.get(rowcounter), "ORANGERED");
			db.close();
			break;
		case R.id.ibnGood:
			ibnClickAnswer.setBackgroundResource(R.drawable.yellow_meaning);
			ibnClickMnemonic.setBackgroundResource(R.drawable.yellow_mnemonic);
			ibnClickWord.setBackgroundResource(R.drawable.yellow_word);
			view.postInvalidate();
			db.open();
			db.updateColor(nameValues.get(rowcounter), "YELLOW");
			db.close();
			break;
		case R.id.ibnVeryGood:
			ibnClickAnswer.setBackgroundResource(R.drawable.lightgreen_meaning);
			ibnClickMnemonic.setBackgroundResource(R.drawable.lightgreen_mnemonic);
			ibnClickWord.setBackgroundResource(R.drawable.lightgreen_word);
			view.postInvalidate();
			db.open();
			db.updateColor(nameValues.get(rowcounter), "GREENYELLOW");
			db.close();
			break;
		case R.id.ibnExcellent:
			ibnClickAnswer.setBackgroundResource(R.drawable.green_meaning);
			ibnClickMnemonic.setBackgroundResource(R.drawable.green_mnemonic);
			ibnClickWord.setBackgroundResource(R.drawable.green_word);
			view.postInvalidate();
			db.open();
			db.updateColor(nameValues.get(rowcounter), "GREEN");
			db.close();
			break;
		case R.id.ibnForward:
			rowcounter=random.nextInt(400);
			db.open();
			color=db.getColor(nameValues.get(rowcounter));
			wordMeaning=db.getMeaning(nameValues.get(rowcounter));
			wordMnemonic=db.getMnemonic(nameValues.get(rowcounter));
			db.close();
			colorSetter();
			tvWordName.setText(nameValues.get(rowcounter));
			wordName=nameValues.get(rowcounter);
			break;
		case R.id.ibnBackward:
			rowcounter=random.nextInt(400);
			db.open();
			color=db.getColor(nameValues.get(rowcounter));
			wordMeaning=db.getMeaning(nameValues.get(rowcounter));
			wordMnemonic=db.getMnemonic(nameValues.get(rowcounter));
			db.close();
			colorSetter();
			tvWordName.setText(nameValues.get(rowcounter));
			wordName=nameValues.get(rowcounter);
			break;	
		case R.id.ibnHomeScreen:
			Intent intent=new Intent(getApplicationContext(), HomeScreen.class);
			startActivity(intent);
			break;
		}
	}
	private void countCheck() {
		if(clickCount%3==0){
			ibnForward.setClickable(false);
			ibnBackward.setClickable(false);
			ibnForward.setAlpha(0f);
			ibnBackward.setAlpha(0f); 
			clickCount++;
			int first=wordMeaning.indexOf("|");
			int last=wordMeaning.lastIndexOf("|");
			String meaning=wordMeaning.substring(0, first);
			String synonym=wordMeaning.substring(first+1, last);
			String sentence=wordMeaning.substring(last+1, wordMeaning.length());
			tvWordMeaning.setText(meaning);
			tvMeaningName.setText(wordName);
			if(synonym.contentEquals("NA"))
				lvWordSynonym.setVisibility(View.INVISIBLE);
			else
				tvWordSynonymList.setText(synonym);
			if(sentence.contentEquals("NA"))
				lvWordSentence.setVisibility(View.INVISIBLE);
			else
				tvWordSentenceList.setText(sentence);
			flipper.showNext();
		}else if(clickCount%3==1){
			clickCount++;
			if(wordMnemonic.contentEquals("NA"))
			{
				clickCount++; 
				flipper.showNext();
				ibnForward.setClickable(true);
				ibnBackward.setClickable(true);
				ibnForward.setAlpha(1f);
				ibnBackward.setAlpha(1f);
				flipper.showNext();
			}
			else{
				tvWordMnemonic.setText(wordMnemonic);
				tvMeaningName.setText(wordName);
				tvMnemonicName.setText(wordName);
				flipper.showNext();
			}
		}else if(clickCount%3==2){
			ibnForward.setClickable(true);
			ibnBackward.setClickable(true);
			ibnForward.setAlpha(1f);
			ibnBackward.setAlpha(1f);
			clickCount++;
			flipper.showNext();
		}
	}
	@Override
	protected void onPause() {
		super.onPause();
		saveprefs();
	}
	private void saveprefs() {
		spHomeScreen=getSharedPreferences("spDispScreen", 0);
		editor=spHomeScreen.edit(); 
		Set<String> set=new TreeSet<String>();
		for (int i = 0; i < nameValues.size(); i++) {
			set.add(nameValues.get(i));
		}
		editor.putStringSet("Stringset",set);
		/*Set<String> set1=new TreeSet<String>();
		for (int i = 0; i < mainRowNames.size(); i++) {
			set1.add(mainRowNames.get(i));
		}
		editor.putStringSet("MainRowNamesSet",set1);
		Set<String> set2=new TreeSet<String>();
		for (int i = 0; i < mainRowColors.size(); i++) {
			set2.add(mainRowColors.get(i));
		}
		editor.putStringSet("MainRowColorsSet",set2);
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
	@Override
	protected void onResume() {
		super.onResume();
		getprefs();
	}
	private void getprefs() {
		spHomeScreen=getSharedPreferences("spDispScreen", 0);
		editor=spHomeScreen.edit();
		Set<String> set=new TreeSet<String>();
		set=spHomeScreen.getStringSet("StringSet", null);
		if(set!=null){
			nameValues=new ArrayList<String>();
			for (Iterator<String> iterator = set.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				nameValues.add(string);	
			}
		}
		/*Set<String> set2=new TreeSet<String>();
		set2=spHomeScreen.getStringSet("MainRowNamesSet", null);
		if(set2!=null){
			mainRowNames=new ArrayList<String>();
			for (Iterator<String> iterator = set2.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				mainRowNames.add(string);	
			}
		}
		Set<String> set3=new TreeSet<String>();
		set3=spHomeScreen.getStringSet("MainRowColorsSet", null);
		if(set3!=null){
			mainRowColors=new ArrayList<String>();
			for (Iterator<String> iterator = set3.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				mainRowColors.add(string);	
			}
		}
		 */
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
}
