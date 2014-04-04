package com.valkesh.adapter;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.valkesh.baseadapterdemo.R;
import com.valkesh.model.FriendDetailsListModel;

public class FriendAdapter extends BaseAdapter implements
		OnClickListener {

	
	private Context mContext;
	private ArrayList<FriendDetailsListModel> mFriendsListOriginal = new ArrayList<FriendDetailsListModel>();
	private static LayoutInflater inflater = null;

	public FriendAdapter(Context context,
			ArrayList<FriendDetailsListModel> data) {
		mContext = context;
		mFriendsListOriginal = data;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		return mFriendsListOriginal.size();
	}

	@Override
	public Object getItem(int position) {
		return mFriendsListOriginal.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	static class ViewHolder {
		TextView email_id, f_name, age, gender;
		Button addHim;
	}

	public View getView(int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;

		LayoutInflater mInflater = (LayoutInflater) mContext
				.getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
		if (convertView == null) {
			convertView = mInflater.inflate(R.layout.activity_display, null);
			holder = new ViewHolder();
			holder.f_name = (Button) convertView.findViewById(R.id.f_name);
			holder.age = (Button) convertView.findViewById(R.id.age);
			holder.gender = (Button) convertView.findViewById(R.id.gender);
			holder.addHim = (Button) convertView.findViewById(R.id.trackhim);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		FriendDetailsListModel rowItem = (FriendDetailsListModel) getItem(position);
		
		final String email_id_on = rowItem.getEmail_id();
		final String name_on = rowItem.getEmail_id();
		
		holder.f_name.setText(rowItem.getF_name());
		holder.gender.setText(rowItem.getGender());
		holder.age.setText(rowItem.getEmail_id());
		holder.addHim.setOnClickListener(new  OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
//				Intent intent = new Intent(mContext, XYZ.class);
//				mContext.startActivity(intent);
//				((Activity) mContext).finish();
			}
		});
		return convertView;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub

	}	
}
