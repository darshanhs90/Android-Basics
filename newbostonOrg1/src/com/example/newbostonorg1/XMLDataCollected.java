package com.example.newbostonorg1;

public class XMLDataCollected {
	int temperature=0;
	String city=null,state=null,code=null;

	public void setCode(String code){
		this.code=code;
	}
	public void setRegion(String region){
		this.state=region;
	}
	public void setCity(String city){
		this.city=city ;
	}
	public void setTemp(int temp){
		temperature=temp;
	}
	public String dataToString(){
		return "In "+city+","+ state+","+"the temperature is "+temperature;
	}
}