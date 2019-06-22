package com.chaochaowu.facedetect.ui;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.chaochaowu.facedetect.Fragment.FrameActivity;
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
		Toolbar toolbar = (Toolbar)findViewById(R.id.rank_list_toolbar);
		setSupportActionBar(toolbar);

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
	public void onItemClick(AdapterView<?> adapterView, View view, final int i, long l) {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setTitle("敲一下").setMessage("“改头换面”，重新再来？")
				.setPositiveButton("删了", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						retList.remove(i);
						listItemAdapter.notifyDataSetChanged();
					}
				})
				.setNegativeButton("后悔了", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
					}
				});
		builder.show();
	}

	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.toolbar_to_main,menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case R.id.main_page1:
				Toast.makeText(this,"返回主页面",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(RankListViewActivity.this,
						FirstActivity.class);
				startActivity(intent);
				break;
			case R.id.face_item_detect1:
				Toast.makeText(this,"返回颜值测评",Toast.LENGTH_SHORT).show();
				Intent intent1 = new Intent(RankListViewActivity.this,
						MainActivity.class);
				startActivity(intent1);
				break;
			default:
		}
		return true;
	}

}
