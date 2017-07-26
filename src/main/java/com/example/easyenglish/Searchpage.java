package com.example.easyenglish;


import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.example.DBManager.DBTools;
import com.example.UserHleper.NEMT;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Editable;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextWatcher;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class Searchpage extends Activity{

	private ImageView searchclear;
	private AutoCompleteTextView searchtext;
	private Button btnspeek1;
	private ImageView search2;
	private List<String> list;
	ArrayAdapter<String> adapter;
	private String string;
	private DBTools dbTools;
	private RelativeLayout layout;
	private List<NEMT> wordList;
	private TextView example;
	private TextView key;
	private TextView phono;
	private TextView trans;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_CUSTOM_TITLE);
		setContentView(R.layout.searchpage);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.searchbar);
		findView();
		
		Typeface typeface=Typeface.createFromAsset(getAssets(), "font/SEGOEUI.TTF");
		phono.setTypeface(typeface);
		final int len=searchtext.getText().length();
	    dbTools=new DBTools();
		if(len>0){
		searchclear.setVisibility(View.VISIBLE);
		}
		
		searchclear.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				searchtext.setText("");
				searchclear.setVisibility(View.GONE);
			}
		});
		
		search2.setOnClickListener(new OnClickListener() {
			 
			@SuppressWarnings("static-access")
			@Override
			public void onClick(View arg0) {
				if(layout.VISIBLE==View.VISIBLE){
				layout.setVisibility(View.VISIBLE);}	
				getWordlist();
				setText();
			}
		});
		
		btnspeek1.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub			
			}
		});
		
		searchtext.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {
				if(arg3>0){
				string =arg0.toString();
				@SuppressWarnings("unused")
				String[] liststring=getInputAdatper();
				searchclear.setVisibility(View.VISIBLE);
				}else{
					searchclear.setVisibility(View.GONE);
				}
			}
			@Override
			public void beforeTextChanged(CharSequence arg0, int arg1, int arg2,
					int arg3) {}		
			@Override
			public void afterTextChanged(Editable arg0) {}
		});
	}
	
	private String[] getInputAdatper(){
		
		list=dbTools.fuzzyEnquiry(Searchpage.this, string);
		String[] strings=new String[list.size()];
		for (int i = 0; i < list.size(); i++) {
			strings[i]=list.get(i).toString();
		}
		adapter=new ArrayAdapter<String>(Searchpage.this,android.R.layout.simple_dropdown_item_1line,strings);
		searchtext.setAdapter(adapter);
		return strings;
	}
	
	private void getWordlist(){
		
		wordList=dbTools.getSearchNemts(this, string);
	}
	
	private void setText(){
		
		key.setText(wordList.get(0).getWord_key().toString());
		trans.setText(wordList.get(0).getWord_trans().toString());
		phono.setText(wordList.get(0).getWord_phono().toString());
		String examples = wordList.get(0).getWord_example();
		final Pattern highlight = Pattern.compile(wordList.get(0)
				.getWord_key());

		SpannableString s = new SpannableString(examples);
		Matcher m = highlight.matcher(s.toString());
		while (m.find()) {
			s.setSpan(new ForegroundColorSpan(Color.BLUE), m.start(), m.end(),
					Spannable.SPAN_INCLUSIVE_INCLUSIVE);
			example.setText(s);
		}
		example.setText(s);
	}
	
	private void findView(){		
		View view=getWindow().getDecorView();
		layout=(RelativeLayout) findViewById(R.id.seachlayout);
		searchtext=(AutoCompleteTextView) view.findViewById(R.id.searchtext);
		search2=(ImageView)view.findViewById(R.id.search2);
		searchclear=(ImageView) view.findViewById(R.id.searchclear);
		example=(TextView) findViewById(R.id.wordexample1);
		key=(TextView) findViewById(R.id.wordkey1);
		phono=(TextView) findViewById(R.id.wordphono1);
		trans=(TextView) findViewById(R.id.wordtrans1);
		btnspeek1=(Button) findViewById(R.id.btnspeek1);
	}
	
}
