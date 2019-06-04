package com.chaochaowu.facedetect.ui;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;

import com.chaochaowu.facedetect.R;

public class SplashActivity extends AppCompatActivity {

	private final int SPLASH_DISPLAY_LENGHT = 3000;
	private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_splash);
		getSupportActionBar().hide();
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

