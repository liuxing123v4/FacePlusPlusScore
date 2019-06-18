package com.chaochaowu.facedetect.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.chaochaowu.facedetect.Fragment.FrameActivity;
import com.chaochaowu.facedetect.R;

import org.litepal.LitePal;

public class FirstActivity extends AppCompatActivity {
	ImageButton camera_img;
	ImageButton Rank_button;
	ImageButton faceplus_button;
	ImageButton graph_button;
	private String username;

	final String TAG ="First.activity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		getSupportActionBar().hide();
		LitePal.initialize(this);
		camera_img = (ImageButton)findViewById(R.id.face_detect);
		Intent intent = getIntent();
		username = intent.getStringExtra("username");
//		Bmob.initialize(this,"d116f11d641d99b83c9c43895ce7b62b");
		camera_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(FirstActivity.this, MainActivity.class);
				intent.putExtra("username",username);
				startActivity(intent);

			}
		});
		Rank_button = (ImageButton) findViewById(R.id.rank_image);
		Rank_button.setOnClickListener(new View.OnClickListener() {

			public void onClick(View view) {
				Intent list = new Intent(FirstActivity.this, RankListViewActivity.class);
				startActivity(list);
			}
		});
		faceplus_button = (ImageButton)findViewById(R.id.faceplus_button);
		faceplus_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(FirstActivity.this, FrameActivity.class);
				startActivity(intent);
			}
		});

		graph_button = (ImageButton)findViewById(R.id.graph_button);
		graph_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(FirstActivity.this, WebViewActivity.class);
				startActivity(intent);
			}
		});

	}
}

