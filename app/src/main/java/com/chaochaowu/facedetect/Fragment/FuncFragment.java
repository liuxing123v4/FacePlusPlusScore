package com.chaochaowu.facedetect.Fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.chaochaowu.facedetect.R;

public class FuncFragment extends Fragment {
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
	                         Bundle savedInstanceState){
		return inflater.inflate(R.layout.frame_func,container);
	}

	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		TextView tv = (TextView) getView().findViewById(R.id.funcTextView1);
		tv.setText("这是功能页面");
	}
}
