package com.example.DBManager;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.UserHleper.NEMT;

public class DBTools {

	private SQLiteDatabase db;
	List<NEMT> list=new ArrayList<NEMT>();

	//多表模糊查询
	public List<String> fuzzyEnquiry(Context context,String wordpart){
		List<String> stringlist=new ArrayList<String>();
		DBManager helper=new DBManager(context);
		helper.openDatabase();
		db=helper.getDatabase();
		String sqlString="select Word_Key from TABLE_CET4 where Word_Key like '"+wordpart+"%'"+
				" union select Word_Key from TABLE_CET6 where Word_Key like '"+wordpart+"%'"+
				"union select Word_Key from TABLE_GRE where Word_Key like '"+wordpart+"%'"+
				"union select Word_Key from TABLE_NMET where Word_Key like '"+wordpart+"%'"+
				"union select Word_Key from TABLE_IETSL where Word_Key like '"+wordpart+"%'";
		Cursor cursor=db.rawQuery(sqlString, null);
		while(cursor.moveToNext()){
			int i=cursor.getColumnIndex("Word_Key");

			String wordkey=cursor.getString(i);
			stringlist.add(new String(wordkey));
		}
		cursor.close();
		db.close();
		helper.closeDatabase();
		return stringlist;
	}

	//查询某单元数据
	public List<NEMT> getUnitWords(Context context,String tablename,String position){
		DBManager helper=new DBManager(context);
		helper.openDatabase();
		db=helper.getDatabase();
		String sql="select Word_Key,Word_Id from '"+tablename+"' where Word_Unit="+position+";";
		Cursor cursor=db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			int i=cursor.getColumnIndex("Word_Key");
			int i2=cursor.getColumnIndex("Word_Id");
			String wordkey=cursor.getString(i);
			int wordid=cursor.getInt(i2);
			list.add(new NEMT(wordid,wordkey));
		}
//		closeInfo();
		cursor.close();
		db.close();
		helper.closeDatabase();
		return list;
	}

	//获取单词的数据
	public List<NEMT> getNMET(Context context,String tablename,String id){
		DBManager helper=new DBManager(context);
		helper.openDatabase();
		db=helper.getDatabase();
		String sql="select * from "+tablename+" where Word_Id="+id+";";
		Cursor cursor=db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			int i=cursor.getColumnIndex("Word_Id");
			int i1=cursor.getColumnIndex("Word_Key");
			int i2=cursor.getColumnIndex("Word_Phono");
			int i3=cursor.getColumnIndex("Word_Trans");
			int i4=cursor.getColumnIndex("Word_Example");
			int i5=cursor.getColumnIndex("Word_Unit");

			int wid=cursor.getInt(i);
			String wordkey=cursor.getString(i1);
			String wordphono=cursor.getString(i2);
			String wordtrans=cursor.getString(i3);
			String wordexample=cursor.getString(i4);
			int wordunit=cursor.getInt(i5);

			list.add(new NEMT(wid,wordkey,wordphono,wordtrans,wordexample,wordunit));
		}
//		closeInfo();
		cursor.close();
		db.close();
		helper.closeDatabase();
		return list;
	}

	public List<NEMT> getSearchNemts(Context context,String word){
		List<NEMT> list = new ArrayList<NEMT>();
		DBManager helper=new DBManager(context);
		helper.openDatabase();
		db=helper.getDatabase();
		String sql="select * from TABLE_CET4 where Word_Key = '"+word+"' union select * from TABLE_CET6 where Word_Key='"+word+"' union select * from TABLE_GRE where Word_Key ='"+word+"' union select * from TABLE_IETSL where Word_Key = '"+word+"' union select * from TABLE_NMET where Word_Key='"+word+"' group by Word_Key;";
		Cursor cursor=db.rawQuery(sql, null);
		while(cursor.moveToNext()){
			int i=cursor.getColumnIndex("Word_Id");
			int i1=cursor.getColumnIndex("Word_Key");
			int i2=cursor.getColumnIndex("Word_Phono");
			int i3=cursor.getColumnIndex("Word_Trans");
			int i4=cursor.getColumnIndex("Word_Example");
			int i5=cursor.getColumnIndex("Word_Unit");

			int wid=cursor.getInt(i);
			String wordkey=cursor.getString(i1);
			String wordphono=cursor.getString(i2);
			String wordtrans=cursor.getString(i3);
			String wordexample=cursor.getString(i4);
			int wordunit=cursor.getInt(i5);

			list.add(new NEMT(wid,wordkey,wordphono,wordtrans,wordexample,wordunit));
		}
//		closeInfo();
		cursor.close();
		db.close();
		helper.closeDatabase();
		return list;
	}

	//统计单元数
	public List<NEMT> getUnitcount(Context context,String tablename){
		DBManager helper=new DBManager(context);
		helper.openDatabase();
		db=helper.getDatabase();
		Cursor cursor=db.query(tablename, new String[]{"Word_Unit"}, null, null, "Word_Unit", null, null);
		while(cursor.moveToNext()){
			int i=cursor.getColumnIndex("Word_Unit");
			int unit=cursor.getInt(i);
			list.add(new NEMT(unit));

			System.out.println(list.size()+"");
		}
		db.close();
		helper.closeDatabase();
		cursor.close();
		return list;
	}

	//前一个后一个单词获取
	public List<NEMT> getWordPreorNext(Context context,String tablename,String unit){
		String sql="select * from "+tablename+" where Word_Unit="+unit+";";
		DBManager helper=new DBManager(context);
		helper.openDatabase();
		db=helper.getDatabase();
		Cursor cursor=db.rawQuery(sql,null);
		while(cursor.moveToNext()){
			int i=cursor.getColumnIndex("Word_Id");
			int i1=cursor.getColumnIndex("Word_Key");
			int i2=cursor.getColumnIndex("Word_Phono");
			int i3=cursor.getColumnIndex("Word_Trans");
			int i4=cursor.getColumnIndex("Word_Example");
			int i5=cursor.getColumnIndex("Word_Unit");

			int wid=cursor.getInt(i);
			String wordkey=cursor.getString(i1);
			String wordphono=cursor.getString(i2);
			String wordtrans=cursor.getString(i3);
			String wordexample=cursor.getString(i4);
			int wordunit=cursor.getInt(i5);
			list.add(new NEMT(wid,wordkey,wordphono,wordtrans,wordexample,wordunit));
		}
//		closeInfo();
		cursor.close();
		db.close();
		helper.closeDatabase();
		return list;
	}
}
