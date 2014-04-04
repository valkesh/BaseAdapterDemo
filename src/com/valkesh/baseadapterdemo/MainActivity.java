package com.valkesh.baseadapterdemo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import com.valkesh.adapter.FriendAdapter;
import com.valkesh.model.FriendDetailsListModel;
import com.valkesh.model.JSONParser;

public class MainActivity extends Activity {
	private Context mContext;
	private ListView FriendList;
	private FriendAdapter friend_Adapter;
	private String url = "";
	private ArrayList<FriendDetailsListModel> mFriendsList = new ArrayList<FriendDetailsListModel>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FriendList = (ListView) findViewById(R.id.lv_friend_list);
		url = "http://valkeshpatel.com/friend/f_id=1";
//		new ProgressTask().execute();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
	}

	@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

	class ProgressTask extends AsyncTask<String, String, String> {
		private ProgressDialog dialog;
		private String response = null;

		@Override
		protected void onPreExecute() {
			super.onPreExecute();
			dialog = new ProgressDialog(mContext);
			dialog.setMessage("Loading Customer...");
			dialog.setIndeterminate(true);
			dialog.setCancelable(true);
			dialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			JSONParser jParser = new JSONParser();
			// get JSON data from URL
			response = jParser.getResponseFromUrl(url);
			return null;
		}

		@Override
		protected void onPostExecute(String result) {
			super.onPostExecute(result);
			// if (dialog.isShowing()) {
			dialog.dismiss();
			// }

			if (response.equalsIgnoreCase("") || response.trim().length() <= 0) {
				Toast.makeText(mContext, "No data found", Toast.LENGTH_SHORT)
						.show();
			} else {
				try {
					Log.d("Drive_List_response", "" + response);
					JSONArray objJsonArry = new JSONArray(response);
					for (int i = 0; i < objJsonArry.length(); i++) {
						JSONObject objJosn = objJsonArry.getJSONObject(i);
						FriendDetailsListModel objModel = new FriendDetailsListModel();
						objModel.setF_name(objJosn.getString("name"));
						objModel.setDob(objJosn.getString("age"));
						objModel.setGender(objJosn.getString("gender"));
						mFriendsList.add(objModel);
					}
				} catch (JSONException e) {
					e.printStackTrace();
					Log.e("ERROR", e.toString());
				}
				Log.e("LISTSIZE", "" + mFriendsList.size());
				friend_Adapter = new FriendAdapter(mContext, mFriendsList);
				FriendList.setAdapter(friend_Adapter);
			}
		}
	}
}
