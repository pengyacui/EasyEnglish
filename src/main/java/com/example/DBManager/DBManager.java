package com.example.DBManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import com.example.easyenglish.R;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.os.Environment;
import android.util.Log;

public class DBManager{
	
	private Context context;
	private SQLiteDatabase database;
	public static final String DBNAME="words.db";
	public static final String packageName="com.example.easyenglish";
	public static final String DATAPATH="/data"+Environment.getDataDirectory().getAbsolutePath()+"/"
								+packageName+"/databases";
	
	public DBManager(Context context){
		this.context=context;
		File file=new File(DATAPATH);
		if(!file.exists()){
		file.mkdirs();
		}
	}
	
	public SQLiteDatabase getDatabase(){
		return database;
	}
		
	
	public void setDatabase(SQLiteDatabase database){
		this.database=database;
	}
	
	public void openDatabase(){
		this.database=this.openDatabase(DATAPATH+"/"+DBNAME);
	}
	
	private SQLiteDatabase openDatabase(String dbfile){
		try {
		if(!(new File(dbfile).exists())){
			InputStream is=this.context.getResources().openRawResource(R.raw.words);
				FileOutputStream fos=new FileOutputStream(dbfile);
				byte[] buffer=new byte[400000];
				int count=0;
				while((count=is.read(buffer))>0){
					fos.write(buffer,0,count);
				}
				fos.close();
				is.close();
		}
		SQLiteDatabase db = SQLiteDatabase.openOrCreateDatabase(dbfile,
				null);
		return db;
		
		} catch (FileNotFoundException e) {
			Log.e("databases","file not found");
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void closeDatabase(){
		this.database.close();
	}

}
