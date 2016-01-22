package com.example.slidingmenu_demo;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class SlidingMenu03FragmentLeft extends ListFragment {

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.slidingmenu_fragment_listview, null);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		String[] colors = getResources().getStringArray(R.array.color_names);
		
		ArrayAdapter<String> colorAdapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, android.R.id.text1, colors);
		
		setListAdapter(colorAdapter);
	}

	@Override
	public void onListItemClick(ListView lv, View v, int position, long id) {
		Fragment newContent = null;
		switch (position) {
		case 0:
			newContent = new SlidingMenu03FragmentRight(R.color.red);
			break;
		case 1:
			newContent = new SlidingMenu03FragmentRight(R.color.green);
			break;
		case 2:
			newContent = new SlidingMenu03FragmentRight(R.color.blue);
			break;
		case 3:
			newContent = new SlidingMenu03FragmentRight(android.R.color.white);
			break;
		case 4:
			newContent = new SlidingMenu03FragmentRight(android.R.color.black);
			break;
		}
		if (newContent != null)
			switchFragment(newContent);
	}

	// 切换Fragment视图内ring
	private void switchFragment(Fragment fragment) {
		if (getActivity() == null)
			return;
		
		if (getActivity() instanceof SlidingMenu03Activity) {
			SlidingMenu03Activity fca = (SlidingMenu03Activity) getActivity();
			fca.switchContent(fragment);
		} 
	}


}
