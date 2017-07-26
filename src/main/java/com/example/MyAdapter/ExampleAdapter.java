package com.example.MyAdapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.UserHleper.Example;
import com.example.easyenglish.R;

public class ExampleAdapter extends BaseAdapter {

	private List<Example> data;
	private LayoutInflater inflater;
	
	public ExampleAdapter(Context context,List<Example> data) {
		super();
		this.data=data;
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
		private TextView example_1;
//		private TextView example_2;
//		private TextView example_3;
	}
	
	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder=null;
		if(arg1==null){
			holder=new ViewHolder();
			arg1=inflater.inflate(R.layout.exampleitem, null);
			holder.example_1=(TextView) arg1.findViewById(R.id.example_1);
//			holder.example_2=(TextView) arg1.findViewById(R.id.example_2);
//			holder.example_3=(TextView) arg1.findViewById(R.id.example_3);
			arg1.setTag(holder);
		}else{
			holder=(ViewHolder) arg1.getTag();
		}
		
		holder.example_1.setText(data.get(position).toString());
//		holder.example_2.setText(data.get(position).getStr2().toString());
//		holder.example_3.setText(data.get(position).getStr3().toString());
		return arg1;
	}

}
