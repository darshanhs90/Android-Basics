package com.example.newbostonorg1;

import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.view.View;
import android.widget.ListView;

public class Prefs extends PreferenceActivity {


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		addPreferencesFromResource(R.xml.prefs);
	}
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
	}

}
