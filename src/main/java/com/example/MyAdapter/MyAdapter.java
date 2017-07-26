package com.example.MyAdapter;

import java.util.ArrayList;
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

public class MyAdapter extends BaseAdapter {
	
	List<NEMT> data = new ArrayList<NEMT>();
	private LayoutInflater inflater;
//	private Context context;
	public MyAdapter(Context context, List<NEMT> data) {
		super();
//		this.context = context;
		this.data = data;
		this.inflater = LayoutInflater.from(context);
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

	class ViewHolder {
		private ImageView imgbtn;
		private TextView unittext;
		private ImageView imggobtn;
	}

	@Override
	public View getView(int position, View arg1, ViewGroup arg2) {
		// TODO Auto-generated method stub
		ViewHolder holder = null;
		if (arg1 == null) {
			arg1 = inflater.inflate(R.layout.item, null);
			holder = new ViewHolder();
			holder.imgbtn = (ImageView) arg1.findViewById(R.id.unitimgbtn);
			holder.unittext = (TextView) arg1.findViewById(R.id.unittext);
			holder.imggobtn = (ImageView) arg1.findViewById(R.id.unitgobtn);
			arg1.setTag(holder);
		} else {
			holder = (ViewHolder) arg1.getTag();
		}
		
		switch (position% 6) {
		case 0:
			holder.imgbtn.setBackgroundResource(drawable.cetselector);
			break;
		case 1:
			holder.imgbtn.setBackgroundResource(drawable.cet02selector);
			break;
		case 2:
			holder.imgbtn.setBackgroundResource(drawable.cet03selector);
			break;
		case 3:
			holder.imgbtn.setBackgroundResource(drawable.cet04selector);
			break;
		case 4:
			holder.imgbtn.setBackgroundResource(drawable.cet05selector);
			break;
		case 5:
			holder.imgbtn.setBackgroundResource(drawable.cet6selector);
			break;
		}
		
		holder.unittext.setText("Unit" + data.get(position).getWord_unit());
		holder.imggobtn.setBackgroundResource(drawable.btngoselector);
		return arg1;
	}

}
