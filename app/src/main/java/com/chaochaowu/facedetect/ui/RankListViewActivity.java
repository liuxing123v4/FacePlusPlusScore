package com.chaochaowu.facedetect.ui;

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
import com.chaochaowu.facedetect.ui.FirstActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

public class RankListViewActivity extends ListActivity implements AdapterView.OnItemClickListener{
	List<String> data = new ArrayList<String>();
	ArrayAdapter adapter;
	private SimpleAdapter listItemAdapter;
	private List<HashMap<String, String>> listItems;
	List<HashMap<String, String>> retList = new ArrayList<HashMap<String, String>>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_rank_list_view);
		List<PersonInfo> list= DataSupport.order("score").find(PersonInfo.class);
		//使其倒着输出
		Collections.reverse(list);
		for(PersonInfo p:list){
//			data.add("username:"+p.getUsername()+"颜值："+p.getScore());
			HashMap<String, String> map = new HashMap<>();
			String score = ""+p.getScore();
			map.put("ItemTitle", p.getUsername());
			map.put("ItemDetail", score);
			retList.add(map);
		}
//		ListView listView = (ListView) findViewById(R.id.rank_list_view);
//		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,data);
//		listView.setAdapter(adapter);
//		listView.setEmptyView(findViewById(R.id.nodata));
//				listView.setOnItemClickListener(this);

		listItemAdapter = new SimpleAdapter(this, retList,
				R.layout.list_item,
				new String[]{"ItemTitle", "ItemDetail"},
				new int[]{R.id.itemTitle, R.id.itemDetail}
		);
		this.setListAdapter(listItemAdapter);


	}

	@Override
	public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

	}
}
