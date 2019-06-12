package com.chaochaowu.facedetect.Fragment;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.chaochaowu.facedetect.R;
import com.chaochaowu.facedetect.adapter.ExamplePagerAdapter;

import net.lucode.hackware.magicindicator.FragmentContainerHelper;
import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.buildins.UIUtil;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ClipPagerTitleView;

import java.util.ArrayList;
import java.util.List;


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
		getSupportActionBar().hide();
		setContentView(R.layout.activity_frame);

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
}