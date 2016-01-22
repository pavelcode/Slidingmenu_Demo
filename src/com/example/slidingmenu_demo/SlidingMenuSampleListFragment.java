package com.example.slidingmenu_demo;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * 
 *	功能描述：列表Fragment，用来显示滑动菜单打开后的内容
 */
public class SlidingMenuSampleListFragment extends ListFragment {

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		return inflater.inflate(R.layout.slidingmenu_fragment_listview, null);
	}

	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		SampleAdapter adapter = new SampleAdapter(getActivity());
		
		for (int i = 0; i < 10; i++) {
			adapter.add(new SampleItem("Sample List", android.R.drawable.ic_menu_search));
		}
		setListAdapter(adapter);
	}
	
	public class SampleAdapter extends ArrayAdapter<SampleItem> {
		public SampleAdapter(Context context) {
			super(context, 0);
		}

		public View getView(int position, View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = LayoutInflater.from(getContext()).inflate(R.layout.slidingmenu_fragment_listview_row, null);
			}
			ImageView icon = (ImageView) convertView.findViewById(R.id.row_icon);
			icon.setImageResource(getItem(position).iconRes);
			TextView title = (TextView) convertView.findViewById(R.id.row_title);
			title.setText(getItem(position).tag);

			return convertView;
		}
	}
	
	private class SampleItem {
		public String tag;
		public int iconRes;
		public SampleItem(String tag, int iconRes) {
			this.tag = tag; 
			this.iconRes = iconRes;
		}
	}
}
