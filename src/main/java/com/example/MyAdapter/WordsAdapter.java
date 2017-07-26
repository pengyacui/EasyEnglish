package com.example.MyAdapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.UserHleper.NEMT;
import com.example.easyenglish.R;
import com.example.easyenglish.R.drawable;

public class WordsAdapter extends BaseAdapter {

	/*
	 * 获取word表中的数据给wordpage
	 */

	//	private Context context;
	private LayoutInflater inflater;
	private List<NEMT> data;

	public WordsAdapter(Context context,List<NEMT> data) {
		super();
		this.data=data;
//		this.context=context;
		this.inflater=LayoutInflater.from(context);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return data.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return 0;
	}

	class ViewHolder{
		private ImageView wordgo;
		private ImageView worditem;
		private TextView wordtext;
	}


	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		if(arg1==null){
			holder=new ViewHolder();
			arg1=inflater.inflate(R.layout.worditem, null);
			holder.wordgo=(ImageView) arg1.findViewById(R.id.wordqianjin);
			holder.worditem=(ImageView) arg1.findViewById(R.id.worditem);
			holder.wordtext=(TextView) arg1.findViewById(R.id.wordtext);
			arg1.setTag(holder);
		}else{
			holder=(ViewHolder) arg1.getTag();
		}

		holder.worditem.setBackgroundResource(R.mipmap.danci_item);
		holder.wordgo.setBackgroundResource(drawable.worditemselector);
		holder.wordtext.setText(data.get(position).getWord_key());

		return arg1;
	}

}
