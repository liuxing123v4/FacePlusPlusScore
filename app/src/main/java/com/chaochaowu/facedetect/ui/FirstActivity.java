package com.chaochaowu.facedetect.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.chaochaowu.facedetect.R;

public class FirstActivity extends AppCompatActivity {
	ImageButton camera_img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		getSupportActionBar().hide();
		camera_img = (ImageButton)findViewById(R.id.face_detect);
//		Bmob.initialize(this,"d116f11d641d99b83c9c43895ce7b62b");
		camera_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(FirstActivity.this, MainActivity.class);
				startActivity(intent);

			}

	});

	}
	}

