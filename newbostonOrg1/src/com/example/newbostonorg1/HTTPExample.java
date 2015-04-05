package com.example.newbostonorg1;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HTTPExample extends Activity {
	TextView tvHttp;
	HttpClient client;
	//deprecated-should be replaced
	String URL="http://api.twitter.com/1/statuses/user_timeline.json?screen_name=";
	JSONObject json;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.httpexample);
		tvHttp=(TextView) findViewById(R.id.tvHttp);
		/*GetMethodHttp getMethodHttp=new GetMethodHttp();
		try {
			tvHttp.setText(getMethodHttp.getInternetData());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		client=new DefaultHttpClient();
		new read().execute("text");
		json=new JSONObject();
	}
	public JSONObject 	lastTweet(String username) throws ClientProtocolException,IOException,JSONException{
		StringBuilder builder=new StringBuilder(URL);
		builder.append(username);
		
		HttpGet get=new HttpGet(URL.toString());
		HttpResponse response=client.execute(get);
		int  status=response.getStatusLine().getStatusCode();
		if(status>=200 && status<=299){
			HttpEntity entity=response.getEntity();
			String data=EntityUtils.toString(entity);
			JSONArray timeline=new JSONArray(data);
			JSONObject jsonObject=timeline.getJSONObject(0);
			return jsonObject;
		}
		Toast.makeText(getApplicationContext(), "error", Toast.LENGTH_SHORT).show();
		return null;
	}
	
	public class read extends AsyncTask<String, Integer, String> {

		@Override
		protected String doInBackground(String... params) {
			try {
				json=lastTweet("darshanhs");
				return json.getString(params[0]);
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return null;
		}
		@Override
		protected void onPostExecute(String result) {
			// TODO Auto-generated method stub
			super.onPostExecute(result);
			tvHttp.setText(result);
		}
	}
}
