package com.example.newbostonorg1;

import java.net.URL;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class WeatherXMLParsing extends Activity implements OnClickListener {
	EditText etCity;
	EditText etState,etCode;
	TextView tvCurrentWeather;
	Button bnGetWeather; 
	//String baseURL="http://weather.yahooapis.com/forecastrss?w=2502265";
	String baseURL="http://weather.yahooapis.com/forecastrss?w=";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weatherxmlparsing);
		etCode=(EditText) findViewById(R.id.etCode);
		etCity=(EditText) findViewById(R.id.etCity);
		etState=(EditText) findViewById(R.id.etState);
		tvCurrentWeather=(TextView) findViewById(R.id.tvCurrentWeather);
		bnGetWeather=(Button) findViewById(R.id.bnGetWeather);
		bnGetWeather.setOnClickListener(this);

	}
	@Override
	public void onClick(View arg0) {

		StringBuilder URL=new StringBuilder(baseURL);
		String fullURL=URL.append(etCode.getText().toString()).toString();
		try {
			URL website=new URL(fullURL);
			SAXParserFactory spf=SAXParserFactory.newInstance();
			SAXParser sp=spf.newSAXParser();
			XMLReader xr=sp.getXMLReader();
			HandlingXMLStuff stuff=new HandlingXMLStuff();
			xr.setContentHandler(stuff);
			xr.parse(new InputSource(website.openStream()));
			String information=stuff.dataReturned();
			tvCurrentWeather.setText(information);
			int firstC=information.indexOf("In ");
			int secondC=information.indexOf(",");
			int thirdC=information.lastIndexOf(",");
			System.out.println(firstC+"      "+secondC+"        "+thirdC);
			System.out.println(information);
			String city=information.substring(firstC+3, secondC);
			System.out.println(city);
			String Region=information.substring(secondC+1, thirdC);
			System.out.println(Region);
			etCity.setText(city);
			etState.setText(Region);
		} catch (Exception e) {
			tvCurrentWeather.setText("Error");
			e.printStackTrace();
		}

	}
}
