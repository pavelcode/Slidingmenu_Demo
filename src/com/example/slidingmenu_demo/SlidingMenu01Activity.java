package com.example.slidingmenu_demo;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


/**
 * SlidingMenu 简单的左边划出效果
 * @author Administrator
 *
 */
public class SlidingMenu01Activity extends FragmentActivity {
	private SlidingMenu menu;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		initSlidingMenu();
	}

	/**
	 * 初始化滑动菜单
	 */
	private void initSlidingMenu() {
		// 设置主界面视图
		setContentView(R.layout.slidingmenu_fragment_main);
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_main, new SlidingMenuSampleListFragment()).commit();

		// 设置滑动菜单的属性值
		menu = new SlidingMenu(this);
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		menu.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
		menu.setShadowDrawable(R.drawable.slidingmenu_shadowleft);
		menu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		menu.setFadeDegree(0.35f);
		menu.attachToActivity(this, SlidingMenu.SLIDING_CONTENT);
		// 设置滑动菜单的视图界面
		menu.setMenu(R.layout.slidingmenu_fragment_left);	
		
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_left, new SlidingMenuSampleListFragment()).commit();
	}
	
	@Override
	public void onBackPressed() {
		//点击返回键关闭滑动菜单
		if (menu.isMenuShowing()) {
			menu.showContent();
		} else {
			super.onBackPressed();
		}
	}

}
