package com.chaochaowu.facedetect.ui;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.chaochaowu.facedetect.R;

public class FirstActivity extends AppCompatActivity {
	ImageButton camera_img;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_first);
		camera_img = (ImageButton)findViewById(R.id.camera_button);

		camera_img.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent intent = new Intent(FirstActivity.this, MainActivity.class);
				startActivity(intent);
			}

	});

	}
}
