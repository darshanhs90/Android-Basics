package com.example.newbostonorg1;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MenuAct extends ListActivity {
	String classes[]={"MainActivity","TextPlay","Email","Camera","Data","GFX","GFXSurface",
			"SoundStuff","Slider","Tabs","SimpleBrowser","Flipper","SharedPrefs",
			"ExternalData","SQLLiteExample","Accelerate","HTTPExample","WeatherXMLParsing",
			"GLExample","Voice","TextVoice","StatusBar","SeekBarVolume"};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//FullScreen
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		ArrayAdapter<String> adapter=new ArrayAdapter<String>(MenuAct.this,android.R.layout.simple_list_item_1,classes);
		setListAdapter(adapter);
	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);

		try {
			Class class1 = Class.forName("com.example.newbostonorg1."+classes[position]);
			Intent intent=new Intent(MenuAct.this,class1);
			startActivity(intent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		MenuInflater inflater=getMenuInflater();
		inflater.inflate(R.menu.menu, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.preferences:
			Intent intent=new Intent(this, Prefs.class);
			startActivity(intent);

			break;
		case R.id.aboutus:
			Intent intent1=new Intent(this, AboutUs.class);
			startActivity(intent1);
			break;
		case R.id.exit:
			finish();
			break;
		}
		return super.onOptionsItemSelected(item);
	}
}
