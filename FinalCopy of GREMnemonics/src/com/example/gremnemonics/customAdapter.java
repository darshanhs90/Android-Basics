package com.example.gremnemonics;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Filter;
import android.widget.ImageView;
import android.widget.TextView;

public class customAdapter extends ArrayAdapter<String> {
	private final Context context;
	private String[] vals;
	ArrayList<String> original;
	private Filter filter;
	private ArrayList<String> nlist=new ArrayList<String>();
	private MnemonicDB db;
	public customAdapter(Context context, int resource, String[] objects) {
		super(context, resource, objects);
		this.context=context;
		this.vals=objects;
		this.db=new MnemonicDB(context);
		original=new ArrayList<String>();
		for(int i=0;i<objects.length;i++){
			original.add(objects[i]);  
		}
	}


	@SuppressLint("ResourceAsColor")
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View rowView = inflater.inflate(R.layout.rowlayout, parent, false);
		TextView textView = (TextView) rowView.findViewById(R.id.label);
		ImageView imageView = (ImageView) rowView.findViewById(R.id.icon);
		//System.out.println(position);

		if(position>vals.length-1){
			textView.setText("");
			imageView.setVisibility(View.INVISIBLE);
			rowView.setVisibility(View.GONE);
			//System.out.println("Setting as GONE");
		}
		else
		{
			String a=vals[position];
			String color;
			textView.setText(a);
			db.open();
			System.out.println(a);
			color=db.getColor(a);
			db.close();
			if(color.contentEquals("RED")){
				imageView.setBackgroundResource(R.drawable.temp_red);
				//imageView.setBackgroundColor(com.example.gremnemonics.R.color.Red);
			}
			else if(color.contentEquals("ORANGERED")){
				System.out.println("ORANGERED");
				imageView.setBackgroundResource(R.drawable.temp_orangered);
				//imageView.setBackgroundColor(com.example.gremnemonics.R.color.OrangeRed);
			}
			else if(color.contentEquals("YELLOW")){
				imageView.setBackgroundResource(R.drawable.temp_yellow);
				//imageView.setBackgroundColor(com.example.gremnemonics.R.color.Yellow);
			}
			else if(color.contentEquals("GREENYELLOW")){
				imageView.setBackgroundResource(R.drawable.temp_greenyellow);
				//imageView.setBackgroundColor(com.example.gremnemonics.R.color.YellowGreen);
			}
			else if(color.contentEquals("GREEN")){
				imageView.setBackgroundResource(R.drawable.temp_green);
				//imageView.setBackgroundColor(com.example.gremnemonics.R.color.Green);
			}
			else{
				imageView.setBackgroundResource(R.drawable.temp_blue);
				//	imageView.setBackgroundColor(com.example.gremnemonics.R.color.Blue);
			}
		}
		return rowView;

	}
	public Filter getFilter()
	{
		if (filter == null) {
			Log.i("Before Filter", "Before Filter");
			filter = new PkmnNameFilter();
		}

		return filter;
	}

	public ArrayList<String> getNlist() {
		return nlist;
	}


	public void setNlist(ArrayList<String> nlist) {
		this.nlist = nlist;
	}

	private class PkmnNameFilter extends Filter
	{
		private ArrayList<String> fitems;
		@Override
		protected FilterResults performFiltering(CharSequence constraint)
		{   
			FilterResults results = new FilterResults();
			String prefix = constraint.toString().toLowerCase();

			if (prefix == null || prefix.length() == 0)
			{
				Log.i("prefix is null or 0", "prefix is null or 0");
				ArrayList<String> list = new ArrayList<String>(original);
				results.values = list;
				nlist=list;
				results.count = list.size();
			}
			else
			{
				Log.i("prefix is !null or !0", "prefix is !null or !0");
				final ArrayList<String> list = new ArrayList<String>(original);
				nlist = new ArrayList<String>();
				int count = list.size();

				for (int i=0; i<count; i++)
				{
					final String pkmn = list.get(i);
					final String value = pkmn.toLowerCase();

					if (value.startsWith(prefix))
					{
						nlist.add(pkmn);
					}
				}
				setNlist(nlist);
				results.values = nlist;
				results.count = nlist.size();
				//System.out.println(nlist.get(0));
			}
			return results;
		}

		@SuppressWarnings("unchecked")
		@Override
		protected void publishResults(CharSequence constraint, FilterResults results) {
			vals=new String[nlist.size()];
			//System.out.println("Nlist size on publish"+nlist.size());
			for (int i = 0; i < nlist.size(); i++) {
				vals[i]=nlist.get(i);
			}
			notifyDataSetChanged();			
			/*Log.i("publish result", "publish result");
			fitems = (ArrayList<String>)results.values;
			//clear();
			int count = fitems.size();
			for (int i=0; i<count; i++)
			{
				String pkmn = (String)fitems.get(i);
				add(pkmn);
			}*/
		}
	}

}
