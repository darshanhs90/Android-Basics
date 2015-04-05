package com.example.training102app_actionbar;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getActionBar().setDisplayHomeAsUpEnabled(true);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
    	MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	// TODO Auto-generated method stub
    	if(item.getItemId()==R.id.action_search)
    	{
    		//opensearch();
    		return true;
    	}	
    	if(item.getItemId()==R.id.action_search1)
    	{
    		//opensearch1();
    		return true;
    	}	
    	if(item.getItemId()==R.id.action_search2)
    	{
    		//opensearch2();
    		return true;
    	}	
    	return super.onOptionsItemSelected(item);
    }
}
