package com.chaochaowu.facedetect.Fragment;

import android.content.Intent;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.chaochaowu.facedetect.R;
import com.chaochaowu.facedetect.ui.FirstActivity;
import com.chaochaowu.facedetect.ui.MDLoginActivity;
import com.chaochaowu.facedetect.ui.MainActivity;
import com.chaochaowu.facedetect.ui.RankListViewActivity;
import com.chaochaowu.facedetect.ui.SplashActivity;

public class FrameActivity extends AppCompatActivity {
	private Fragment mFragments[];
	private RadioGroup mRadioGroup;
	private RadioButton rbtHome;
	private RadioButton rbtFunc;
	private RadioButton rbtSetting;
	private FragmentTransaction mFragmentTransaction;
	private FragmentManager mFragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_frame);
		Toolbar toolbar = (Toolbar)findViewById(R.id.frame_toolbar);
		setSupportActionBar(toolbar);

		mFragments = new Fragment[3];
		mFragmentManager = getSupportFragmentManager();
		mFragments[0] = mFragmentManager.findFragmentById(R.id.fragment_home);
		mFragments[1] = mFragmentManager.findFragmentById(R.id.fragment_func);
		mFragments[2] = mFragmentManager.findFragmentById(R.id.fragment_setting);
		mFragmentTransaction =
				mFragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
		mFragmentTransaction.show(mFragments[0]).commit();

		rbtHome = (RadioButton) findViewById(R.id.radio_home);
		rbtFunc = (RadioButton) findViewById(R.id.radio_func);
		rbtSetting = (RadioButton) findViewById(R.id.radio_setting);
		rbtHome.setBackgroundResource(R.drawable.shape3);

		mRadioGroup = (RadioGroup) findViewById(R.id.buttonGroup);
		mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				mFragmentTransaction =
						mFragmentManager.beginTransaction().hide(mFragments[0]).hide(mFragments[1]).hide(mFragments[2]);
				rbtHome.setBackgroundResource(R.drawable.shape2);
				rbtFunc.setBackgroundResource(R.drawable.shape2);
				rbtSetting.setBackgroundResource(R.drawable.shape2);

				switch (checkedId) {
					case R.id.radio_home:
						mFragmentTransaction.show(mFragments[0]).commit();
						rbtHome.setBackgroundResource(R.drawable.shape3);
						break;
					case R.id.radio_func:
						mFragmentTransaction.show(mFragments[1]).commit();
						rbtFunc.setBackgroundResource(R.drawable.shape3);

						break;
					case R.id.radio_setting:
						mFragmentTransaction.show(mFragments[2]).commit();
						rbtSetting.setBackgroundResource(R.drawable.shape3);

						break;
					default:
						break;
				}


			}
		});
	}

	public boolean onCreateOptionsMenu(Menu menu){
		getMenuInflater().inflate(R.menu.toolbar_to_main,menu);
		return true;
	}

	public boolean onOptionsItemSelected(MenuItem item){
		switch (item.getItemId()){
			case R.id.main_page1:
				Toast.makeText(this,"返回主页面",Toast.LENGTH_SHORT).show();
				Intent intent = new Intent(FrameActivity.this,
						FirstActivity.class);
				startActivity(intent);
				break;
			case R.id.face_item_detect1:
				Toast.makeText(this,"返回颜值测评",Toast.LENGTH_SHORT).show();
				Intent intent1 = new Intent(FrameActivity.this,
						MainActivity.class);
				startActivity(intent1);
				break;
			default:
		}
		return true;
	}
}