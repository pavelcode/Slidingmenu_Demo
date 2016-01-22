package com.example.slidingmenu_demo;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


/**
 * 主界面在Fragment中加载GridView，主界面图片压缩显示，滑动点击更改主界面图片
 * @author Administrator
 *
 */
public class SlidingMenu04Activity extends SlidingFragmentActivity {
	private Fragment mContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle("ResponsiveUI");
		
		//设置主视图界面
		setContentView(R.layout.slidingmenu_fragment_main);

		initSlidingMenu(savedInstanceState);
	}
	
	private void initSlidingMenu(Bundle savedInstanceState){
		// check if the content frame contains the menu frame
		if (findViewById(R.id.slidingmenu_fragment_left) == null) {
			setBehindContentView(R.layout.slidingmenu_fragment_left);
			getSlidingMenu().setSlidingEnabled(true);
			getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
			// show home as up so we can toggle
			getActionBar().setDisplayHomeAsUpEnabled(true);
		} else {
			// add a dummy view
			View v = new View(this);
			setBehindContentView(v);
			getSlidingMenu().setSlidingEnabled(false);
			getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
		}
		
		// 设置主界面Fragment视图内容
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		if (mContent == null)
			mContent = new SlidingMenu04FragmentGridView(0);
		
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_main, mContent).commit();

		// set the Behind View Fragment
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_left, new SlidingMenu04FragmentLeft()).commit();
	
		// 设置滑动菜单的属性值
		SlidingMenu sm = getSlidingMenu();
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
		sm.setShadowDrawable(R.drawable.slidingmenu_shadowleft);
		sm.setBehindScrollScale(0.25f);
		sm.setFadeDegree(0.25f);
	}
	
	public void switchContent(final Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_main, fragment).commit();
		
		Handler h = new Handler();
		h.postDelayed(new Runnable() {
			public void run() {
				getSlidingMenu().showContent();
			}
		}, 50);
	}

	public void onBirdPressed(int pos) {
		Intent intent = SlidingMenu04ActivityShow.newInstance(this, pos);
		startActivity(intent);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}
}
