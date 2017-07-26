package com.example.easyenglish;

//import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.DBManager.DBTools;
import com.example.MyAdapter.MyAdapter;
import com.example.UserHleper.Meta;
import com.example.UserHleper.NEMT;
import com.example.UserHleper.Values;

public class Unitpage extends Activity {

	private TextView unittext;
	private ListView listview;
	private Button back;
	private Button btnsearch;
	MyAdapter adapter;
	List<Meta> meta;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub		
		
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.unitpage);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
		
		final Intent intent=new Intent();
		
		String[] str=getIntent().getStringArrayExtra("text");		
		final String tablename=str[0];
		String titletext=str[1];
		
		View view = getWindow().getDecorView();
		unittext=(TextView) view.findViewById(R.id.pagename);
		back=(Button)view.findViewById(R.id.btnback);
		btnsearch=(Button)view.findViewById(R.id.btnsearch);
		
		DBTools dbt=new DBTools();
		List<NEMT> unit=dbt.getUnitcount(Unitpage.this, tablename);		
				
		listview = (ListView) findViewById(R.id.unitlistview);	
		adapter=new MyAdapter(Unitpage.this,unit);
		listview.setAdapter(adapter);
		
		unittext.setText(titletext);		
		
		back.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Unitpage.this.finish();
			}
		});
		
		btnsearch.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
			intent.setClass(Unitpage.this, Searchpage.class);
			startActivity(intent);
			}
		});
		
		listview.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				Values.POSTION=pos+1+"";				
				intent.setClass(Unitpage.this,Wordspage.class);
				intent.putExtra("words", tablename);
				startActivity(intent);
			}
		});
	}

}
