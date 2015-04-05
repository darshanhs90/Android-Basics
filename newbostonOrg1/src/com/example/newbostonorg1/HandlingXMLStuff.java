package com.example.newbostonorg1;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
public class HandlingXMLStuff extends DefaultHandler{
	XMLDataCollected datacollected=new XMLDataCollected();
	String data;
	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		if(localName.equals("yweather:location")||qName.equals("yweather:location")){
			String city=attributes.getValue("city");
			datacollected.setCity(city); 
			datacollected.setRegion(attributes.getValue("region"));
		}
		else if(localName.equals("yweather:condition")||qName.equals("yweather:condition")){
			String temp1=attributes.getValue("temp");
			int temp=Integer.parseInt(temp1);
			datacollected.setTemp(temp); 
		}
		data=datacollected.dataToString();
	}
	public String dataReturned(){
		return data;
	}

}