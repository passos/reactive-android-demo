package com.example.listviewdemo;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.ListView;

public class MainActivity extends Activity {
	public static String TAG = MLog.tag(MainActivity.class);

	private ListView mListView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		SearchResultAdapter listAdapter = new SearchResultAdapter(this);

		mListView = (ListView) findViewById(R.id.main_listview);
		mListView.setAdapter(listAdapter);
		listAdapter.doSearch("Australia");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
}
