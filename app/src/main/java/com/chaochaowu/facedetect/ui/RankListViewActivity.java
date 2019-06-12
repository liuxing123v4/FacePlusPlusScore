package com.chaochaowu.facedetect.ui;

import android.app.Activity;
import android.app.ListActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import com.chaochaowu.facedetect.R;
import com.chaochaowu.facedetect.bean.PersonInfo;
import com.chaochaowu.facedetect.bean.RankPerson;
import com.chaochaowu.facedetect.ui.FirstActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RankListViewActivity extends AppCompatActivity implements AdapterView.OnItemClickListener{
	List<String> data = new ArrayList<String>();
	ArrayAdapter adapter;
	ListActivity rank_list_view;
	private SimpleAdapter listItemAdapter;
	private List<HashMap<String, String>> listItems;
	List<HashMap<String, String>> retList = new ArrayList<HashMap<String, String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rank_list_view);
		List<RankPerson> list= DataSupport.order("score").find(RankPerson.class);
		//使其倒着输出
		Collections.reverse(list);
		int count = 0;
		for(RankPerson p:list){
			count+=1;
			String ranknum = "Rank "+count;
//			data.add("username:"+p.getUsername()+"颜值："+p.getScore());
			HashMap<String, String> map = new HashMap<>();
			String score = "Score:"+p.getScore();
			map.put("ItemRankNum",ranknum);
			map.put("ItemTitle", p.getRankname());
			map.put("ItemDetail", score);
			retList.add(map);
		}
		ListView listView = (ListView) findViewById(R.id.rank_list_view);
		listItemAdapter = new SimpleAdapter(this, retList,
				R.layout.list_item,
				new String[]{"ItemRankNum","ItemTitle", "ItemDetail"},
				new int[]{R.id.itemRankNum,R.id.itemTitle, R.id.itemDetail}
		);
//		adapter = new ArrayAdapter<String>(this,R.layout.list_item,data);
		listView.setAdapter(listItemAdapter);
		listView.setEmptyView(findViewById(R.id.nodata));
		listView.setOnItemClickListener(this);


//		rank_list_view.setListAdapter(listItemAdapter);


	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

	}
}
