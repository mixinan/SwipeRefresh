package com.example.swiperefresh;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v4.widget.SwipeRefreshLayout.OnRefreshListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity implements OnRefreshListener{
	private ListView listView;
	private SwipeRefreshLayout refreshLayout;
	private List<String> data;
	private ArrayAdapter<String> adapter;
	private Handler handler = new Handler();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		initView();
		initData();
		setListener();
		
	}

	private void initView() {
		listView = (ListView) findViewById(R.id.listview);
		refreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefreshlayout);
		refreshLayout.setColorScheme(R.color.blue, R.color.red, R.color.green, R.color.gray);
	}

	private void initData() {
		data = new ArrayList<String>();
		for (int i = 'A'; i < 'Z'; i++) {
			data.add(i + "对应字母： " + (char)i);
		}
		adapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, android.R.id.text1, data);
		listView.setAdapter(adapter);
	}

	private void setListener() {
		refreshLayout.setOnRefreshListener(this);
	}

	@Override
	public void onRefresh() {
		handler.postDelayed(new Runnable() {
			
			@Override
			public void run() {
				data.add(0, "我是刷新的数据 : "+System.currentTimeMillis());
				adapter.notifyDataSetChanged();
				refreshLayout.setRefreshing(false);
			}
		}, 1200);
	}

}
