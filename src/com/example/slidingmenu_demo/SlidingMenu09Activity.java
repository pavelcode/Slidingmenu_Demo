package com.example.slidingmenu_demo;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.MenuItem;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu.CanvasTransformer;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;


/**
 * @author Administrator
 *
 */
public class SlidingMenu09Activity extends SlidingFragmentActivity {
	private CanvasTransformer mTransformer;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setTitle("Scale");
		
		initAnimation();
		
		initSlidingMenu();
		
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	/**
	 * 初始化滑动菜单
	 */
	private void initSlidingMenu(){
		// 设置主界面视图
		setContentView(R.layout.slidingmenu_fragment_main);
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_main, new SlidingMenuSampleListFragment()).commit();
				
		// 设置滑动菜单视图
		setBehindContentView(R.layout.slidingmenu_fragment_left);
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_left, new SlidingMenuSampleListFragment()).commit();

		// 设置滑动菜单的属性值
		SlidingMenu sm = getSlidingMenu();		
		sm.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
		sm.setShadowDrawable(R.drawable.slidingmenu_shadowleft);
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		sm.setFadeDegree(0.35f);
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
		sm.setBehindScrollScale(0.0f);
		sm.setBehindCanvasTransformer(mTransformer);
		
		setSlidingActionBarEnabled(true);
	}

	/**
	 * 初始化动画
	 */
	private void initAnimation(){
		mTransformer = new CanvasTransformer(){
			@Override
			public void transformCanvas(Canvas canvas, float percentOpen) {
				canvas.scale(percentOpen, 1, 0, 0);				
			}
			
		};
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
