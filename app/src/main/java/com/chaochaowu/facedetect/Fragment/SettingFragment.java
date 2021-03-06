package com.chaochaowu.facedetect.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.chaochaowu.facedetect.R;

public class SettingFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		return inflater.inflate(R.layout.frame_setting,container);
	}

	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		final WebView webView = getView().findViewById(R.id.setting_webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient());
//		webView.loadUrl("https://www.xiaohongshu.com/explore");
		webView.loadUrl("http://www.yokamen.cn/");
		webView.setOnKeyListener(new View.OnKeyListener() {
			@Override
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN) {
					//按返回键操作并且能回退网页
					if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
						//后退
						webView.goBack();
						return true;
					}
				}
				return false;
			}
		});
// 		webView.loadUrl("http://www.meiyanw.com/");
//		webView.loadUrl("http://www.fsbus.com/sheyingjiqiao/");
	}
}
