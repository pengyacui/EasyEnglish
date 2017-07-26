package com.example.easyenglish;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;

public class MainActivity extends Activity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().setFeatureInt(Window.FEATURE_CUSTOM_TITLE, R.layout.titlebar);
		setContentView(R.layout.main);
	
		findView();	
		findViewById(R.id.btn1).setOnClickListener(this);
		findViewById(R.id.btn2).setOnClickListener(this);
	    findViewById(R.id.btn3).setOnClickListener(this);
		findViewById(R.id.btn4).setOnClickListener(this);
		findViewById(R.id.btn5).setOnClickListener(this);		
	}
		
	private void findView(){
		
		findViewById(R.id.text11);
		findViewById(R.id.text12);
		findViewById(R.id.text21);
		findViewById(R.id.text22);
		findViewById(R.id.text31);
		findViewById(R.id.text32);
		findViewById(R.id.text41);
		findViewById(R.id.text42);
		findViewById(R.id.text51);
		findViewById(R.id.text52);
	}

	@Override
	public void onClick(View view) {
		// TODO Auto-generated method stub
		
		Intent intent=new Intent(MainActivity.this,Unitpage.class);
		String[] str=new String[]{};
		Resources res =getResources();
		
		switch (view.getId()) {
		case R.id.btn1:
			str=res.getStringArray(R.array.NEMT);
			break;
		case R.id.btn2:
			str=res.getStringArray(R.array.CET4);
			break;
		case R.id.btn3:
			str=res.getStringArray(R.array.CET6);
			break;
		case R.id.btn4:
			str=res.getStringArray(R.array.IETSL);
			break;
		case R.id.btn5:
			str=res.getStringArray(R.array.GRE);
			break;
		default:
			break;
		}		
		intent.putExtra("text",str);
		startActivity(intent);
	}

}
