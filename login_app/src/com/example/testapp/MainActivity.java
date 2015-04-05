package com.example.testapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends Activity {

    private static final String User_name = "com.example.testapp.MESSAGE";
	private static final String Pass_word = "com.example.testapp.MESSAGE1";

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); 
        setContentView(R.layout.activity_main); 
    }
    public void submitfn(View view){
    	Intent intent=new Intent(this,SubmitActivity.class);
    	EditText editText=(EditText) findViewById(R.id.username);
    	EditText editText1=(EditText) findViewById(R.id.password);
    	String username=editText.getText().toString();
    	
    	String password=editText1.getText().toString();
    	
    	intent.putExtra(getUserName(),username);
    	intent.putExtra(getPassWord(),password);
    	
    	startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
	public static String getUserName() {
		return User_name;
	}
	public static String getPassWord() {
		return Pass_word;
	}
    
}
