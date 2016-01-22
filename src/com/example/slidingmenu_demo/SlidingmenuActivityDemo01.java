package com.example.slidingmenu_demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;

/**
 * 实现侧滑菜单，点击侧滑菜单的按钮，修改Activity中的内容
 * @author Administrator
 *
 */
public class SlidingmenuActivityDemo01 extends Activity{
	
	private TextView tv;
	private SlidingMenu menu;
	private Button btn1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.slidingmenu_main);
		//设置Acitivity中显示内容
		tv = (TextView) findViewById(R.id.slidingmenu_tv);
		tv.setText("默认");
		initSlidingMenu();
		btn1 = (Button) findViewById(R.id.slidingmenu_btn);
		btn1.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				String text1 =(String) btn1.getText();
				tv.setText(text1);
			}
		});
	}



	/**
	 * 方法说明：
	 *
	 */
	private void initSlidingMenu() {
		// TODO Auto-generated method stub
		menu = new SlidingMenu(SlidingmenuActivityDemo01.this);
		WindowManager manager = (WindowManager) getSystemService(Context.WINDOW_SERVICE);
		int width = manager.getDefaultDisplay().getWidth();
		menu.setMode(SlidingMenu.LEFT);//设置默认位置
		menu.setBehindWidth(width/3);//设置menu的宽度
		menu.setFadeEnabled(true);//设置滑动时是否淡入淡出
		menu.setFadeDegree(0.35f);//淡入淡出的比例
		menu.setMenu(R.layout.slidingmenu_view);//添加菜单布局
		menu.setShadowDrawable(R.drawable.slidingmenu_shap_view);//为主布局设置阴影背景
		menu.setShadowWidth(width/3);//设置阴影图片的宽度
		//设置menu依附在哪个activity上，以什么样的方式依附
		menu.attachToActivity(this, SlidingMenu.SLIDING_WINDOW);
		//设置在屏幕任何一点滑动都有效
		menu.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);	
	}



	

}
