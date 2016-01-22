package com.example.slidingmenu_demo;


import android.os.Bundle;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


/**
 * 左右滑动效果
 * @author Administrator
 *
 */
public class SlidingMenu02Activity extends SlidingFragmentActivity {
		
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		// 设置标题
		setTitle("Left and Right");	
		
		// 初始化滑动菜单
		initSlidingMenu(savedInstanceState);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/**
	 * 初始化滑动菜单
	 */
	private void initSlidingMenu(Bundle savedInstanceState){
		// 设置滑动菜单的属性值
		getSlidingMenu().setMode(SlidingMenu.LEFT_RIGHT);
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		getSlidingMenu().setShadowWidthRes(R.dimen.slidingmenu_shadow_width);	
		getSlidingMenu().setShadowDrawable(R.drawable.slidingmenu_shadowleft);
		getSlidingMenu().setBehindOffsetRes(R.dimen.slidingmenu_offset);
		getSlidingMenu().setFadeDegree(0.35f);
		
		// 设置主界面的视图
		setContentView(R.layout.slidingmenu_fragment_main);
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_main, new SlidingMenuSampleListFragment()).commit();		
				
		// 设置滑动菜单的左视图界面
		setBehindContentView(R.layout.slidingmenu_fragment_left);
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_left, new SlidingMenuSampleListFragment()).commit();
	
		// 设置滑动菜单的右视图界面
		getSlidingMenu().setSecondaryMenu(R.layout.slidingmenu_fragment_right);
		getSlidingMenu().setSecondaryShadowDrawable(R.drawable.slidingmenu_shadowright);
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_right, new SlidingMenuSampleListFragment()).commit();
	}

	/**
	 * 菜单按钮点击事件，通过点击ActionBar的Home图标按钮来打开滑动菜单
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			toggle();
			return true;	
		}		
		return super.onOptionsItemSelected(item);
	}
	
}
