package com.chaochaowu.facedetect.ui;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageButton;

import com.chaochaowu.facedetect.R;

public class WebViewActivity extends AppCompatActivity {
	Button another_button;
	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getSupportActionBar().hide();
		setContentView(R.layout.activity_web_view);
		WebView webView = (WebView)findViewById(R.id.web_view);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient());
//		webView.loadUrl("https://www.xiaohongshu.com/explore");
// 		webView.loadUrl("https://www.xiaohongshu.com/explore");
		webView.loadUrl("https://thispersondoesnotexist.com/");

		another_button = (Button) findViewById(R.id.another_button);
		another_button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				WebView webView = (WebView)findViewById(R.id.web_view);
				webView.getSettings().setJavaScriptEnabled(true);
				webView.setWebViewClient(new WebViewClient());
		//		webView.loadUrl("https://www.xiaohongshu.com/explore");
		// 		webView.loadUrl("https://www.xiaohongshu.com/explore");
				webView.loadUrl("https://thispersondoesnotexist.com/");
			}
		});
	}

}
