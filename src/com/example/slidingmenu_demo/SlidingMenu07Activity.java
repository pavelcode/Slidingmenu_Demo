package com.example.slidingmenu_demo;

import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.jeremyfeinstein.slidingmenu.lib.SlidingMenu;
import com.jeremyfeinstein.slidingmenu.lib.app.SlidingFragmentActivity;

/**
 * @author Administrator
 *  综合的例子展示SlidingMenu的基本属性
<1> 设置滑动菜单显示模式（左边、右边或者左右两边都有）；
<2> 设置滑动菜单触摸模式（全屏触摸打开滑动菜单、边缘触摸打开滑动菜单或者触摸不能打开滑动菜单）；
<3> 设置滑动菜单滑动时缩放的效果以及关闭此效果（值越大效果越明显）；效果很不明显
<4> 设置滑动菜单宽度（值越大效果越明显）；
<5> 设置滑动菜单滑动时的阴影效果以及关闭此效果（值越大效果越明显）；
<6> 设置滑动菜单滑动时渐入渐出的效果（值越大效果越明显）。
 *
 */
public class SlidingMenu07Activity extends SlidingFragmentActivity {
	
	public static final String TAG = SlidingMenu07Activity.class.getSimpleName();
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		//设置标题栏的标题
		setTitle("SlidingMenu Properties");

		//设置是否能够使用ActionBar来滑动
		setSlidingActionBarEnabled(true);

		//设置是否显示Home图标按钮
		getActionBar().setDisplayHomeAsUpEnabled(true);

		//设置主界面视图
		setContentView(R.layout.slidingmenu_activity05);	

		//初始化滑动菜单
		initSlidingMenu(savedInstanceState);
		
		//初始化组件
		initView();	
	}

	/**
	 * 初始化滑动菜单
	 */
	private void initSlidingMenu(Bundle savedInstanceState) {
		// 设置滑动菜单的视图
		setBehindContentView(R.layout.slidingmenu_fragment_left);
		getSupportFragmentManager().beginTransaction().replace(R.id.slidingmenu_fragment_left, new SlidingMenuSampleListFragment()).commit();		

		// 实例化滑动菜单对象
		SlidingMenu sm = getSlidingMenu();
		// 设置滑动阴影的宽度
		sm.setShadowWidthRes(R.dimen.slidingmenu_shadow_width);
		// 设置滑动阴影的图像资源
		sm.setShadowDrawable(R.drawable.slidingmenu_shadowleft);
		// 设置滑动菜单视图的宽度
		sm.setBehindOffsetRes(R.dimen.slidingmenu_offset);
		// 设置渐入渐出效果的值
		sm.setFadeDegree(0.35f);
		// 设置触摸屏幕的模式
		sm.setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);		
	}
	
	/**
	 * 初始化组件
	 */
	private void initView() {
		// 设置滑动菜单的位置（左边、右边或者左右两边都有）
		RadioGroup mode = (RadioGroup) findViewById(R.id.mode);
		mode.check(R.id.left);
		mode.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				SlidingMenu sm = getSlidingMenu();
				switch (checkedId) {
				case R.id.left:
					sm.setMode(SlidingMenu.LEFT);
					sm.setShadowDrawable(R.drawable.slidingmenu_shadowleft);
					break;
				case R.id.right:
					sm.setMode(SlidingMenu.RIGHT);
					sm.setShadowDrawable(R.drawable.slidingmenu_shadowright);
					break;
				case R.id.left_right:
					sm.setMode(SlidingMenu.LEFT_RIGHT);
					sm.setSecondaryMenu(R.layout.slidingmenu_fragment_right);					
					getSupportFragmentManager().beginTransaction()
					 						   .replace(R.id.slidingmenu_fragment_right,
					 						new SlidingMenuSampleListFragment()).commit();
					sm.setSecondaryShadowDrawable(R.drawable.slidingmenu_shadowright);
					sm.setShadowDrawable(R.drawable.slidingmenu_shadowleft);
				}
			}
		});

		// 设置触摸的模式（全屏触摸滑动、边缘触摸滑动或者触摸不能滑动）
		RadioGroup touchAbove = (RadioGroup) findViewById(R.id.touch_above);
		touchAbove.check(R.id.touch_above_full);
		touchAbove.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {
				switch (checkedId) {
				case R.id.touch_above_full:
					getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_FULLSCREEN);
					break;
				case R.id.touch_above_margin:
					getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
					break;
				case R.id.touch_above_none:
					getSlidingMenu().setTouchModeAbove(SlidingMenu.TOUCHMODE_NONE);
					break;
				}
			}
		});

		// 设置滑动菜单滑动时缩放的效果（值越大效果越明显）
		SeekBar scrollScale = (SeekBar) findViewById(R.id.scroll_scale);
		scrollScale.setMax(1000);
		scrollScale.setProgress(333);
		scrollScale.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				Log.i(TAG,"滑动菜单缩放-->"+(float) seekBar.getProgress() / seekBar.getMax());
				getSlidingMenu().setBehindScrollScale((float) seekBar.getProgress() / seekBar.getMax());
			}
		});

		// 设置滑动菜单宽度（值越大宽度越大）
		SeekBar behindWidth = (SeekBar) findViewById(R.id.behind_width);
		behindWidth.setMax(1000);
		behindWidth.setProgress(750);
		behindWidth.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				float percent = (float) seekBar.getProgress() / seekBar.getMax();
				Log.i(TAG,"滑动菜单宽度-->"+percent);
				getSlidingMenu().setBehindWidth((int) (percent * getSlidingMenu().getWidth()));		
				//getSlidingMenu().setBehindOffset(getSlidingMenu().getWidth()/2);
				getSlidingMenu().requestLayout();
			}
		});

		// 设置滑动菜单滑动时的阴影效果（值越大效果越明显）
		CheckBox shadowEnabled = (CheckBox) findViewById(R.id.shadow_enabled);
		shadowEnabled.setChecked(true);
		shadowEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						if (isChecked)
							getSlidingMenu().setShadowDrawable(getSlidingMenu().getMode() == SlidingMenu.LEFT ? R.drawable.slidingmenu_shadowleft: R.drawable.slidingmenu_shadowright);
						else
							getSlidingMenu().setShadowDrawable(null);
					}
				});
		SeekBar shadowWidth = (SeekBar) findViewById(R.id.shadow_width);
		shadowWidth.setMax(1000);
		shadowWidth.setProgress(75);
		shadowWidth.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				float percent = (float) seekBar.getProgress() / (float) seekBar.getMax();
				int width = (int) (percent * (float) getSlidingMenu().getWidth());
				
				getSlidingMenu().setShadowWidth(width);
				getSlidingMenu().invalidate();
			}
		});

		// 设置滑动菜单滑动时渐入渐出的效果（值越大效果越明显）
		CheckBox fadeEnabled = (CheckBox) findViewById(R.id.fade_enabled);
		fadeEnabled.setChecked(true);
		fadeEnabled.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
					@Override
					public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
						getSlidingMenu().setFadeEnabled(isChecked);
					}
				});
		SeekBar fadeDeg = (SeekBar) findViewById(R.id.fade_degree);
		fadeDeg.setMax(1000);
		fadeDeg.setProgress(666);
		fadeDeg.setOnSeekBarChangeListener(new OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				getSlidingMenu().setFadeDegree(
						(float) seekBar.getProgress() / seekBar.getMax());
			}
		});
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
