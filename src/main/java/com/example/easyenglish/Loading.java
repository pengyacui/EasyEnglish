package com.example.easyenglish;

import com.example.DBManager.DBManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;

public class Loading extends Activity {

	private final int SPLASH_DISPLAY_LENGTH=2000;
	public DBManager dbHelper;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);	
		this.fullScreen();

		
		dbHelper = new DBManager(this);
        dbHelper.openDatabase();
        dbHelper.closeDatabase();
			
		this.setContentView(R.layout.loading);
		Handler h=new Handler();	
		h.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				Intent intent=new Intent(Loading.this,MainActivity.class);
				Loading.this.startActivity(intent);
				Loading.this.finish();
			}
		},SPLASH_DISPLAY_LENGTH);
	}

	private void fullScreen(){			
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
	}
	
}
