package com.example.slidingmenu_demo;

import android.os.Bundle;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


/**
 * 上面的TitilBar并不随着滑动移动
 * @author Administrator
 *
 */
public class SlidingMenu06Activity extends SlidingFragmentActivity {

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle("Sliding Content only");
		
		initSlidingMenu();
	}

	/**
	 * 初始化滑动菜单
	 */
	private void initSlidingMenu(){
		// 设置滑动菜单打开后的视图界面
		setBehindContentView(R.layout.slidingmenu_fragment_left);
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_left, new SlidingMenuSampleListFragment()).commit();

		// 设置主界面视图
		setContentView(R.layout.slidingmenu_fragment_main);
        //getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_main, new SlidingMenuSampleListFragment()).commit();
		
		// 设置当打开滑动菜单时，ActionBar不能够跟随着一起滑动
		setSlidingActionBarEnabled(false);
		
		// 设置滑动菜单的属性值
		SlidingMenu sm = getSlidingMenu();
		sm.setShadowWidthRes(R.dimen.slidingmenu_list_padding);
		sm.setShadowDrawable(R.drawable.slidingmenu_shadowleft);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);

		getActionBar().setDisplayHomeAsUpEnabled(true);			
	}

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
