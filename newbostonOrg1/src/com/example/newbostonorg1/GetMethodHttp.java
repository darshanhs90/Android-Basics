package com.example.newbostonorg1;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;


public class GetMethodHttp {


	public String getInternetData() throws Exception{
		BufferedReader bufferedReader=null;
		String data=null;
		try {
			HttpClient client=new DefaultHttpClient();
			URI uri=new URI("http://www.google.com");
			HttpGet get=new HttpGet();
			get.setURI(uri);
			HttpResponse httpResponse=client.execute(get);
			bufferedReader=new BufferedReader(new InputStreamReader(httpResponse.getEntity().getContent()));
			StringBuffer buffer=new StringBuffer("");
			String line="";
			String newLine=System.getProperty("line.separator");
			while((line=bufferedReader.readLine())!=null){
				buffer.append(line+newLine);
			}
			bufferedReader.close();
			data=buffer.toString();
			return data;
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(bufferedReader!=null){
				bufferedReader.close();
				return data;
			}
		}
		return null;
	}
}
