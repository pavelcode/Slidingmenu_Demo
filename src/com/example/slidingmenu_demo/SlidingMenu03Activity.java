package com.example.slidingmenu_demo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


/**
 * 点击侧滑选项，修改主Activity的背景颜色
 * @author Administrator
 *
 */
public class SlidingMenu03Activity extends SlidingFragmentActivity {

	private Fragment mContent;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setTitle("Change Fragment");
						
		initSlidingMenu(savedInstanceState);
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/**
	 * 初始化滑动菜单
	 */
	private void initSlidingMenu(Bundle savedInstanceState){	
		//如果保存的状态不为空则得到ColorFragment，否则实例化ColorFragment
		if (savedInstanceState != null)
			mContent = getSupportFragmentManager().getFragment(savedInstanceState, "mContent");
		if (mContent == null)
			mContent = new SlidingMenu03FragmentRight(R.color.red);	
		
		// 设置主视图界面
		setContentView(R.layout.slidingmenu_fragment_main);
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_main, mContent).commit();

		// 设置滑动菜单视图界面
		setBehindContentView(R.layout.slidingmenu_fragment_left);
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_left, new SlidingMenu03FragmentLeft()).commit();

		// 设置滑动菜单的属性值
		getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		getSlidingMenu().setShadowWidthRes(R.dimen.slidingmenu_list_padding);	
		getSlidingMenu().setShadowDrawable(R.drawable.slidingmenu_shadowleft);
		getSlidingMenu().setBehindOffsetRes(R.dimen.slidingmenu_offset);
		getSlidingMenu().setFadeDegree(0.35f);
		
	}
	
	/**
	 * 切换Fragment，也是切换视图的内容
	 */
	public void switchContent(Fragment fragment) {
		mContent = fragment;
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_main, fragment).commit();
		getSlidingMenu().showContent();
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
	
	/**
	 * 保存Fragment的状态
	 */
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		getSupportFragmentManager().putFragment(outState, "mContent", mContent);
	}
	
}
