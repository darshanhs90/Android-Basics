package com.example.newbostonorg1;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


public class HotorNot  {
	public static final String KEY_ROWID="_id";
	public static final String KEY_NAME="persons_name";
	public static final String KEY_HOTNESS="persons_hotness";
	private static final String DATABASE_NAME="HotOrNotDb";
	private static final String DATABASE_TABLE="peopleTable";
	private static final int DATABASE_VERSION=1;
	private DbHelper ourdbHelper;
	private final Context ourcontext;
	private SQLiteDatabase ourdatabase;


	private static class DbHelper extends SQLiteOpenHelper{

		public DbHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
			// TODO Auto-generated constructor stub
		}

		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL("CREATE TABLE "+DATABASE_TABLE+" ("+
					KEY_ROWID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
					KEY_NAME+" TEXT UNIQUE NOT NULL, "+
					KEY_HOTNESS+" TEXT NOT NULL);");

		}

		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS "+DATABASE_TABLE);
			onCreate(db); 
		}


	}



	public HotorNot(Context context) {
		ourcontext=context;
	}


	public HotorNot open() throws SQLException{
		ourdbHelper=new DbHelper(ourcontext);
		ourdatabase=ourdbHelper.getWritableDatabase();
		return this;
	}
	public void close(){
		ourdbHelper.close();
	}


	public long createEntry(String name, String hotness) {
		ContentValues cv=new ContentValues();
		cv.put(KEY_NAME, name);
		cv.put(KEY_HOTNESS,hotness);
		return(ourdatabase.insert(DATABASE_TABLE, null, cv));
	}


	public String getData() throws SQLException{
		String[] columns=new String[]{KEY_ROWID,KEY_NAME,KEY_HOTNESS};
		Cursor cursor=ourdatabase.query(DATABASE_TABLE, columns, null, null, null, null, null);
		String str="";
		int iRow=cursor.getColumnIndex(KEY_ROWID);
		int iName=cursor.getColumnIndex(KEY_NAME);
		int iHotness=cursor.getColumnIndex(KEY_HOTNESS);

		for (cursor.moveToFirst();!cursor.isAfterLast();cursor.moveToNext()) {
			str=str+cursor.getString(iRow)+" "+cursor.getString(iName)+" "+cursor.getString(iHotness)+"\n";
		}
		return str;
	}


	public String getName(long l) throws SQLException{
		String[] columns=new String[]{KEY_ROWID,KEY_NAME};
		Cursor cursor=ourdatabase.query(DATABASE_TABLE, columns, KEY_ROWID+"="+l, null, null, null, null);
		if(cursor!=null){
			cursor.moveToFirst();
			return cursor.getString(1);
		}
		return null;
	}


	public String getHotness(long l) throws SQLException{
		// TODO Auto-generated method stub
		String[] columns=new String[]{KEY_ROWID,KEY_HOTNESS};
		Cursor cursor=ourdatabase.query(DATABASE_TABLE, columns, KEY_ROWID+"="+l, null, null, null, null);
		if(cursor!=null){
			cursor.moveToFirst();
			return cursor.getString(1);
		}
		return null;
	}


	public void updateEntry(long mL, String mName, String mHotness) throws SQLException{
		ContentValues values=new ContentValues();
		//values.put(KEY_ROWID, mL); 
		values.put(KEY_NAME, mName);
		values.put(KEY_HOTNESS, mHotness);		
		ourdatabase.update(DATABASE_TABLE, values, KEY_ROWID+"="+mL, null);
		
	}


	public void deleteEntry(long dL) throws SQLException{
		// TODO Auto-generated method stub
		ourdatabase.delete(DATABASE_TABLE, KEY_ROWID+"="+dL, null);
	}
}
