package com.example.slidingmenu_demo;

import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;


import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;


/**
 * 
 * 简单的左边侧拉效果，主界面是Activity
 * 创建SlidingMenu,设置 侧拉模式，距离，显示速度
 *                 阴影图片，阴影宽度
 *                 依附方式，触摸模式
 *                 布局文件
 * 创建Fragment，在SlidingMenu的布局中,根据Id设置Fragment
 *                 
 * @author Administrator
 *
 */
public class SlidingMenu00Activity extends Activity {


	private SlidingMenu menu;
	private FragmentManager mFragmentManager;
	private FragmentTransaction mFragmentTransaction;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slidingmenu00_main);
	    initSlidingMenu();	
	}

	private void initSlidingMenu() {
		menu = new SlidingMenu(SlidingMenu00Activity.this);
				
		menu.setMode(SlidingMenu.LEFT);//设置侧拉模式，左边，右边，左右两边
		menu.setBehindOffset(100);//设置侧滑距离另一边的距离，值越大，侧滑栏的宽度越小
		menu.setFadeEnabled(true);//设置滑动时是否淡入淡出
		menu.setFadeDegree(0.35f);//淡入淡出的比例
			
		
		menu.setShadowDrawable(R.drawable.slidingmenu_shap_view);//为主布局设置阴影背景
		menu.setShadowWidth(0);//设置渐变的程度，范围是0-1.0f,设置的越大，则在侧滑栏刚划出的时候，颜色就越暗。1.0f的时候，颜色为全黑
		
		menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);//设置menu依附在哪个activity上，以什么样的方式依附
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);	//设置触摸模式，全屏触摸
		menu.setMenu(R.layout.slidingmenu00_view);//添加菜单布局
		
		//设置加载Fragment
		mFragmentManager = getFragmentManager();
		mFragmentTransaction = mFragmentManager.beginTransaction();
		mFragmentTransaction.replace(R.id.slidingmenu_frg, new SlidingMenu00Fragment());
		mFragmentTransaction.commit();
		
	}

}
