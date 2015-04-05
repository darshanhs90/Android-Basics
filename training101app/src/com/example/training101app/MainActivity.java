package com.example.training101app;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private static final String New_MSG = "com.example.training101app.MESSAGE";
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    public void submit(View view){
    	Intent intent=new Intent(this,DisplayMessageActivity.class);
    	EditText editText=(EditText) findViewById(R.id.editabletext);
    	String string=editText.getText().toString();
    	intent.putExtra(getNewMsg(),string);
    	startActivity(intent); 
    }


	public static String getNewMsg() {
		return New_MSG;
	}
    
}
