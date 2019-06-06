package com.chaochaowu.facedetect.ui;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.chaochaowu.facedetect.R;
import com.chaochaowu.facedetect.bean.PersonInfo;
import com.chaochaowu.facedetect.ui.FirstActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class RankListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
	List<String> data = new ArrayList<String>();
	ArrayAdapter adapter;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rank_list_view);
		List<PersonInfo> list= DataSupport.findAll(PersonInfo.class);
		for(PersonInfo p:list){
			data.add("username:"+p.getUsername()+"颜值："+p.getScore());
		}
		ListView listView = (ListView) findViewById(R.id.rank_list_view);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
		listView.setAdapter(adapter);
		listView.setEmptyView(findViewById(R.id.nodata));
//				listView.setOnItemClickListener(this);
	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

	}
}
