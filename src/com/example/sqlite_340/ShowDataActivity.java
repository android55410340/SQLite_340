package com.example.sqlite_340;

import java.util.ArrayList;
import java.util.HashMap;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleAdapter;
public class ShowDataActivity extends Activity {
    private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.show);
		listView = (ListView) findViewById(R.id.listView1);
		
		control340DB control503db = new control340DB(this);
		ArrayList<HashMap<String, String>> arrayList = control503db
				.SelectAllData();
		
		SimpleAdapter adapter;
		adapter = new SimpleAdapter(ShowDataActivity.this, arrayList,
				R.layout.show_item, new String[]{"MemberID", "Name" , "Tel"},
				new int[] {R.id.ColMemberID,R.id.ColName,R.id.ColTel});
		listView.setAdapter(adapter);
		
	}
}