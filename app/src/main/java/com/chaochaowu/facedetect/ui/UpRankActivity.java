package com.chaochaowu.facedetect.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.chaochaowu.facedetect.R;
import com.chaochaowu.facedetect.bean.FaceppBean;
import com.chaochaowu.facedetect.bean.PersonInfo;
import com.chaochaowu.facedetect.bean.RankPerson;
import com.chaochaowu.facedetect.eventbus.FaceEvent;

import org.greenrobot.eventbus.EventBus;
import org.litepal.crud.DataSupport;
import org.litepal.tablemanager.Connector;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class UpRankActivity extends AppCompatActivity {
	final String TAG = "Register";
	private EditText realname;
	private EditText realage;
	private Button up_rank_button;
	private String rankname,rankage;
	private TextView rank_face_value;
	private TextView rank_suggest;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_up_rank);
		Intent intent = getIntent();
		final Double rankscore = intent.getDoubleExtra("score", 0.0);
		realname = (EditText)findViewById(R.id.rank_name);
		realage = (EditText)findViewById(R.id.rank_age);
		rank_face_value = (TextView)findViewById(R.id.rank_face_value);
		rank_suggest = (TextView)findViewById(R.id.rank_suggest);
		up_rank_button = (Button) findViewById(R.id.up_rank_button);
		rank_face_value.setText("颜值:"+rankscore);

		if(rankscore<68){
			rank_suggest.setText(
					"老铁:,\n"+
					"去face++逛逛吧,\n"+
					"颜值不是你,\n"+
					"想买就能买");
		}else{
			rank_suggest.setText(
					"喜欢一个人,\n" +
					"始于颜值,\n" +
					"陷于才华,\n" +
					"忠于人品,\n"+
					"快去上榜吧！");
		}


		Connector.getDatabase();

		up_rank_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {

				rankname = realname.getText().toString();
				rankage = realage.getText().toString();
				List<RankPerson> list = DataSupport.where("rankname=?", rankname).find(RankPerson.class);
				if (list.size() == 0) {
					RankPerson person = new RankPerson();
					person.setRankname(rankname);
					person.setRankage(rankage);
					person.setScore(rankscore);
					person.save();
				} else {
					double now_score = list.get(0).getScore();
					if (now_score < rankscore) {
						PersonInfo person = new PersonInfo();
						person.setScore(rankscore);
						Log.i(TAG, "onClick: " + "数据已更新");
						person.updateAll("rankname = ?", rankname);
						Toast.makeText(UpRankActivity.this, "恭喜您上榜成功，您离颜值巅峰更近一步", Toast.LENGTH_LONG).show();
					} else {
						Toast.makeText(UpRankActivity.this, "不要上榜啦，您颜值报表liao！！！", Toast.LENGTH_LONG).show();
					}
				}
				Intent intent1 = new Intent(UpRankActivity.this, RankListViewActivity.class);
				startActivity(intent1);
			}

		});

	}


}
