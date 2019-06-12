package com.chaochaowu.facedetect.ui;

import android.annotation.SuppressLint;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.chaochaowu.facedetect.R;

public class WebViewActivity extends AppCompatActivity {

	@SuppressLint("SetJavaScriptEnabled")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web_view);
		WebView webView = (WebView)findViewById(R.id.web_view);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient());
//		webView.loadUrl("https://www.xiaohongshu.com/explore");
// 		webView.loadUrl("https://www.xiaohongshu.com/explore");
		webView.loadUrl("http://www.fsbus.com/sheyingjiqiao/");
	}
}
