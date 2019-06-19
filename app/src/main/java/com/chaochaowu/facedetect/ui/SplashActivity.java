package com.chaochaowu.facedetect.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.widget.TextView;

import com.chaochaowu.facedetect.R;

public class SplashActivity extends AppCompatActivity {

	private final int SPLASH_DISPLAY_LENGHT = 4000;
	private Handler handler;
	TextView splash_text;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		getSupportActionBar().hide();

//		splash_text = (TextView)findViewById(R.id.splash_text);
//		splash_text.setText(
//						"喜欢一个人,\n" +
//						"始于颜值,\n" +
//						"陷于才华,\n" +
//						"忠于人品,\n");
		handler = new Handler();
		// 延迟SPLASH_DISPLAY_LENGHT时间然后跳转到MainActivity
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				Intent intent = new Intent(SplashActivity.this,
						MDLoginActivity.class);
				startActivity(intent);
				SplashActivity.this.finish();
			}
		}, SPLASH_DISPLAY_LENGHT);


	}

	}

