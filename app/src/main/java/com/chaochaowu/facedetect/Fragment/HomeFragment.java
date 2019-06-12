package com.chaochaowu.facedetect.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.chaochaowu.facedetect.R;

public class HomeFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		return inflater.inflate(R.layout.frame_home,container);
	}

	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		WebView webView = getView().findViewById(R.id.home_webview);
		webView.getSettings().setJavaScriptEnabled(true);
		webView.setWebViewClient(new WebViewClient());
//		webView.loadUrl("https://www.xiaohongshu.com/explore");
// 		webView.loadUrl("https://www.xiaohongshu.com/explore");
		webView.loadUrl("http://www.fsbus.com/sheyingjiqiao/");
	}
}
