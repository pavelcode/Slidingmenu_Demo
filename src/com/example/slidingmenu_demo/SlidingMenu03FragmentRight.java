package com.example.slidingmenu_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

public class SlidingMenu03FragmentRight extends Fragment {
	
	private int mColorRes = -1;
	
	public SlidingMenu03FragmentRight() { 
		this(R.color.white);
	}
	
	public SlidingMenu03FragmentRight(int colorRes) {
		mColorRes = colorRes;
		//保存当前Fragment实例，当恢复Fragment的时候就会跳过onCreate()和onDestroy()方法
		setRetainInstance(true);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		if (savedInstanceState != null)
		mColorRes = savedInstanceState.getInt("mColorRes");
		
		int color = getResources().getColor(mColorRes);
		// construct the RelativeLayout
		RelativeLayout v = new RelativeLayout(getActivity());
		v.setBackgroundColor(color);		
		return v;
	}
	
	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt("mColorRes", mColorRes);
	}
	
}
