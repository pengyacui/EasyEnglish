package com.example.easyenglish;

import java.util.ArrayList;
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

import com.example.DBManager.DBTools;
import com.example.MyAdapter.WordsAdapter;
import com.example.UserHleper.NEMT;
import com.example.UserHleper.Values;

public class Wordspage extends Activity {

	private ListView listview;
	private Button wordback;
	List<NEMT> data=new ArrayList<NEMT>();
	WordsAdapter adapter;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.wordspage);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE,R.layout.wordpagetitlebar);
		final String tablename=getIntent().getStringExtra("words");
		
		DBTools dbt=new DBTools();
		data=dbt.getUnitWords(Wordspage.this, tablename, Values.POSTION);
		adapter=new WordsAdapter(Wordspage.this, data);
		listview=(ListView) findViewById(R.id.wordlist);
		listview.setAdapter(adapter);
		View view=getWindow().getDecorView();
		wordback=(Button) view.findViewById(R.id.btnwpback);				
		
		wordback.setOnClickListener(new OnClickListener() {			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Wordspage.this.finish();
			}
		});
		
		listview.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int pos,
					long arg3) {
				// TODO Auto-generated method stub
				String p=pos+1+"";
				String id=data.get(pos).getId()+"";
				String sum=data.size()+"";
				Intent intent=new Intent(Wordspage.this,Worditempage.class);
				intent.putExtra("word", new String[]{tablename,id,sum,p});
				startActivity(intent);
			}
		});
	}

}
